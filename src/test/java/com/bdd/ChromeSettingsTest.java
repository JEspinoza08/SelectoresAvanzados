package com.bdd;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettingsTest {

    WebDriver driver;
    @Test
    public void testWithShadowOld() {
        getWebdriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("chrome://settings");
            Thread.sleep(1000);
            WebElement shadowHost = driver.findElement(By.tagName("settings-ui"));
            WebElement shadowRoot1 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);

            WebElement root2 = shadowRoot1.findElement(By.cssSelector("cr-toolbar"));
            WebElement shadowRoot2 = (WebElement) js.executeScript("return arguments[0].shadowRoot", root2);

            WebElement root3 = shadowRoot2.findElement(By.cssSelector("cr-toolbar-search-field"));
            WebElement shadowRoot3 = (WebElement) js.executeScript("return arguments[0].shadowRoot", root3);

            WebElement search = shadowRoot3.findElement(By.cssSelector("input#searchInput"));

            search.sendKeys("Ejemplo de búsqueda en configuración");

            System.out.println("Texto ingresado: " + search.getAttribute("value"));
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testWithShadowCurrent() {
        getWebdriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("chrome://settings");

            Thread.sleep(1000);

            WebElement root1 = driver.findElement(By.tagName("settings-ui"));
            Object shadowRoot1 = js.executeScript("return arguments[0].shadowRoot", root1);

            Object root2 = js.executeScript("return arguments[0].querySelector('cr-toolbar').shadowRoot", shadowRoot1);

            Object root3 = js.executeScript("return arguments[0].querySelector('cr-toolbar-search-field').shadowRoot", root2);

            WebElement searchField = (WebElement) js.executeScript("return arguments[0].querySelector('input#searchInput')", root3);

            searchField.sendKeys("Ejemplo de búsqueda en configuración");
            Thread.sleep(10000);
            System.out.println("Texto ingresado: " + searchField.getAttribute("value"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public void getWebdriver(){
        String basePath = System.getProperty("user.dir");
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", basePath+"/driver/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", basePath+"/driver/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

}

