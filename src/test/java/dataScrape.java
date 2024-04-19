import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class dataScrape {
    private static ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void openLandP() {
        String url = "https://cvonline.lt/lt/search?limit=20&offset=0&categories%5B0%5D=INFORMATION_TECHNOLOGY&towns%5B0%5D=540&fuzzy=true&suitableForRefugees=false&isHourlySalary=false&isRemoteWork=false&isQuickApply=false&searchId=c88daca5-263e-4c7c-8d82-de01c65b5344";
        driver.get(url);


    }

    @Test
    public void scrape() {

        String url = "https://cvonline.lt/lt/search?limit=20&offset=0&categories%5B0%5D=INFORMATION_TECHNOLOGY&towns%5B0%5D=540&fuzzy=true&suitableForRefugees=false&isHourlySalary=false&isRemoteWork=false&isQuickApply=false&searchId=c88daca5-263e-4c7c-8d82-de01c65b5344";
        driver.get(url);


        // Find the element containing the total count of job listings
        WebElement totalCountElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/div/span[1]"));
        String totalCountText = totalCountElement.getText().replaceAll("[^\\d]", ""); // Remove non-numeric characters
        int totalCount = Integer.parseInt(totalCountText);

        //Loop through every page
        int totalElements  = 0;
        for (int i = 1; i < 201; i++) {

            String url1 = "https://cvonline.lt/lt/search?limit=30&offset=" + i + "0&categories%5B0%5D=INFORMATION_TECHNOLOGY&towns%5B0%5D=540&fuzzy=true&suitableForRefugees=false&isHourlySalary=false&isRemoteWork=false&isQuickApply=false&searchId=c88daca5-263e-4c7c-8d82-de01c65b5344";
            driver.get(url1);

            if (i == 2) {
                continue;
            }//skip duplicate page
                if (!driver.getCurrentUrl().equals(url1)) {
                    break; //end loop
                }

                //Wait
                try {
                    Thread.sleep(5000); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            List<WebElement> h3Elements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]"));
            if (h3Elements.isEmpty()) {
                break;
            }
                //Listing all adds
//                List<WebElement> h3Elements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]"));

                //Run it through condition
            for (WebElement h3 : h3Elements) {
                String h3Text = h3.getText();
                if (h3Text.contains("QA") || h3Text.contains("automation") || h3Text.contains("engineer") || h3Text.contains("testuotojas") || h3Text.contains("Junior") || h3Text.contains("Tester") || h3Text.contains("Quality") || h3Text.contains("Assurance")) {
                    System.out.println("");
                    System.out.println("Pozicija: " + h3.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]/div/a/div[2]/div[1]/div/span[1]")).getText()); // Print position

                    // Try to find the salary element and print it
                    try {
                        String salary = h3.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]/div/a/div[2]/div[2]/div[2]/div/span[1]/span")).getText(); //Print salary
                        System.out.println("Darbo uždarbis: " + salary);
                    } catch (NoSuchElementException e) {
                        System.out.println("Darbo uždarbis nematomas");
                    }

                    System.out.println("Skelbimo URL: " + h3.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/ul/li[1]/div/a")).getAttribute("href")); //Print URL
                }
            }
            if (totalElements >= totalCount) {
                break;
            }
            }
        }
    }
