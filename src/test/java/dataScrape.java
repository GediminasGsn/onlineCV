import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class dataScrape {

    @Test
    public void openLandP() {
        ChromeDriver driver = new ChromeDriver();
        String url = "https://cvonline.lt/lt/search?limit=20&offset=0&categories%5B0%5D=INFORMATION_TECHNOLOGY&towns%5B0%5D=540&fuzzy=true&suitableForRefugees=false&isHourlySalary=false&isRemoteWork=false&isQuickApply=false&searchId=c88daca5-263e-4c7c-8d82-de01c65b5344";
        driver.get(url);
        driver.manage().window().maximize();

    }

    @Test
    public void scrape() {
        ChromeDriver driver = new ChromeDriver();
        String url = "https://cvonline.lt/lt/search?limit=20&offset=0&categories%5B0%5D=INFORMATION_TECHNOLOGY&towns%5B0%5D=540&fuzzy=true&suitableForRefugees=false&isHourlySalary=false&isRemoteWork=false&isQuickApply=false&searchId=c88daca5-263e-4c7c-8d82-de01c65b5344";
        driver.get(url);
        driver.manage().window().maximize();
        //accept cookies
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
        //Loop through every page
        for (int i = 1; i < 201; i++) {

            String url1 = "https://cvonline.lt/lt/search?limit=30&offset=" + i + "0&categories%5B0%5D=INFORMATION_TECHNOLOGY&towns%5B0%5D=540&fuzzy=true&suitableForRefugees=false&isHourlySalary=false&isRemoteWork=false&isQuickApply=false&searchId=c88daca5-263e-4c7c-8d82-de01c65b5344";
            driver.get(url1);
            if (i == 2) {
                continue;
            }
                if (!driver.getCurrentUrl().equals(url1)) {
                    break; //end loop
                }

                //Wait
                try {
                    Thread.sleep(5000); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Listing all adds
                List<WebElement> h3Elements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]"));

                //Run it through condition
                for (WebElement h3 : h3Elements) {
                    String h3Text = h3.getText();
                    if (h3Text.contains("QA") || h3Text.contains("automation") || h3Text.contains("engineer") || h3Text.contains("testuotojas")) {
                        System.out.println("");
                        System.out.println("Pozicija: " + h3.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]/div/a/div[2]/div[1]/div/span[1]")).getText()); // Print position
                        System.out.println("Darbo uždarbis: " + h3.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]/div/a/div[2]/div[2]/div[2]/div/span[1]/span")).getText()); //Print salary
                        System.out.println("Skelbimo URL: " + h3.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]/div/a")).getAttribute("href")); //Print URL
                    }

                }

            }
        }
    }
