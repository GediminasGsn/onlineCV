import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

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
        String url = "https://www.cvbankas.lt/?location%5B%5D=606&padalinys%5B%5D=76&keyw=";
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
                if (!driver.getCurrentUrl().equals(url1)) {
                    break; //end loop
                }
                //Wait
                try {
                    Thread.sleep(5000); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();