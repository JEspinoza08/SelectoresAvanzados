package com.bdd.web.page;

import com.fw.Parameters;
import com.fw.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import static com.fw.Util.explicitWaiting;

/**
 * @author Karla Ccallo
 */
public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy
    private WebElement userTitle;

    @FindBy(id = "logout2")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[@id='itemc' and @class='list-group-item']")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//a[@class='hrefch' or @href='prod.html?idp_=1']")
    private List<WebElement> productList;

    @FindBy(xpath = "//a[starts-with(@class,'btn btn-success btn-lg')]")
    private WebElement addToCartButton;

    @FindBy
    private WebElement cartButton;

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    private WebElement homeButton;

    public JavascriptExecutor js = (JavascriptExecutor)Parameters.driver;


    public boolean getMensajeWelcome() {
        Util.waitThread(5000);
        Util.screenshot(Parameters.driver);

        this.userTitle =Parameters.driver.findElement(By.id("nameofuser"));
        String value = (String) js.executeScript("return arguments[0].value",userTitle);

        return value.contains("Welcome");
    }

    public void logout() {
        Util.logger(this.getClass()).log(Level.WARNING, "Click en boton LogOut");
        logOutButton.click();
        Util.screenshot(Parameters.driver);
    }

    public void elegirCategoria(String categoria) {
        Util.logger(this.getClass()).log(Level.WARNING, "Seleccionando una categoria");
        Util.screenshot(Parameters.driver);
        explicitWaiting(5000, driver -> ExpectedConditions.visibilityOf(categoryList.get(0)));
        Util.waitThread(2000);
        clickElementFromAListByText(categoryList, categoria);
    }

    public void seleccionoProducto(String producto) {
        Util.logger(this.getClass()).log(Level.WARNING, "Seleccionando el producto: {0}", producto);
        explicitWaiting(5000, driver -> ExpectedConditions.visibilityOf(productList.get(0)));
        Util.screenshot(Parameters.driver);
        Util.waitThread(2000);
        clickElementFromAListByText(productList, producto);
    }

    public void agregarProducto() {
        Util.logger(this.getClass()).log(Level.WARNING, "Click en boton AddToCartButton");
        explicitWaiting(3000, driver -> ExpectedConditions.elementToBeClickable(addToCartButton));
        Util.screenshot(Parameters.driver);
        addToCartButton.click();
        aceptarAlerta();
    }
    public void clickCart() {
        Util.logger(this.getClass()).log(Level.WARNING, "Click en boton cartButton");
        explicitWaiting(3000, driver -> ExpectedConditions.elementToBeClickable(cartButton));
        this.cartButton =Parameters.driver.findElement(By.cssSelector("#cartur"));
        js.executeScript("arguments[0].click();", cartButton);
    }

    private void clickElementFromAListByText(List<WebElement> list, String toFind) {
        Iterator var3 = list.iterator();
        System.out.println("lista:"+list.toString());
        System.out.println("a encontrar:"+toFind);
        while (var3.hasNext()) {
            WebElement element = (WebElement) var3.next();
            System.out.println("elemento:"+element.getText());
            if (element.getText().equals(toFind)) {
                element.click();
                break;
            }
        }
    }

    private void aceptarAlerta() {
        Util.waitThread(5000);
        Alert alert = Parameters.driver.switchTo().alert();
        String textoAlert = alert.getText();
        Util.logger(this.getClass()).log(Level.WARNING, textoAlert);
        alert.accept();
    }

    public void clickHome() {
        Util.logger(this.getClass()).log(Level.WARNING, "Click en Home");
        explicitWaiting(3000, driver -> ExpectedConditions.elementToBeClickable(cartButton));
        homeButton.click();
    }
}
