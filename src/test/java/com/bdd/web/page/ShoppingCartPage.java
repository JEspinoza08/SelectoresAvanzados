package com.bdd.web.page;

import com.fw.Parameters;
import com.fw.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.logging.Level;

import static com.fw.Util.explicitWaiting;

/**
 * @author Karla Ccallo
 */
public class ShoppingCartPage {

    public ShoppingCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='table-responsive']//table/tbody/tr")
    private List<WebElement> tr_collection;
    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;
    @FindBy(xpath = "//input[@id='name' or @id='bbb']")
    private WebElement nameInput;
    @FindBy(id = "country")
    private WebElement countryInput;

    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(id = "card")
    private WebElement cardInput;
    @FindBy(id = "month")
    private WebElement monthInput;
    @FindBy(id = "year")
    private WebElement yearInput;
    @FindBy(xpath = "//button[@class='btn btn-primary'][text()='Purchase']")
    private WebElement purchaseButton;
    @FindBy(xpath = "//div[@class='sa-icon sa-custom']/following-sibling::h2")
    private WebElement messageSuccess;

    @FindBy(xpath = "//h2[text()='Products']")
    private WebElement titleProducts;


    //ACTIONS
    public boolean validarProductoAgregado(String producto) {
        Util.waitThread(1000);
        return validarElementoEnTabla(tr_collection, producto);
    }

    private boolean validarElementoEnTabla(List<WebElement> tr_collection, String producto) {
        int cantidad = tr_collection.size();
        boolean seEncontroProducto = false;
        if (cantidad > 0) {

            for (WebElement trElement : tr_collection) {
                List<WebElement> td_collection = trElement.findElements(By.xpath("td[2]"));
                for (WebElement tdElement : td_collection) {
                    String prod = tdElement.getText().trim();
                    if (prod.trim().contains(producto)) {
                        seEncontroProducto = true;
                        Util.logger(this.getClass()).log(Level.WARNING, "Elemento encontrado {0}",prod);
                        break;
                    }
                }
            }
        }
        Util.screenshot(Parameters.driver);
        return seEncontroProducto;
    }

    public boolean getMensaje(String mensaje) {
        Util.waitThread(2000);
        Alert alert = Parameters.driver.switchTo().alert();
        String textoAlert = alert.getText();
        Util.logger(this.getClass()).log(Level.WARNING, mensaje);
        alert.accept();
        return textoAlert.contains(mensaje);
    }

    public void clicPlaceOrder() {
        Util.logger(this.getClass()).log(Level.WARNING, "Click en boton PLaceOrder");
        JavascriptExecutor js = (JavascriptExecutor) Parameters.driver;
        js.executeScript("arguments[0].scrollIntoView();", placeOrderButton);
        js.executeScript("arguments[0].click();", placeOrderButton);
        //placeOrderButton.click();
    }

    public void typeName(String name) {
        Util.logger(this.getClass()).log(Level.WARNING, "Escribiendo el texto: {0}", name);
        explicitWaiting(1000, driver -> ExpectedConditions.visibilityOf(nameInput));
        Util.screenshot(Parameters.driver);
        nameInput.sendKeys(name);
    }

    public void typeCountry(String country) {
        Util.logger(this.getClass()).log(Level.WARNING, "Escribiendo el texto: {0}", country);
        explicitWaiting(1000, driver -> ExpectedConditions.visibilityOf(countryInput));
        countryInput.sendKeys(country);
    }

    public void typeCity(String city) {
        Util.logger(this.getClass()).log(Level.WARNING, "Escribiendo el texto: {0}", city);
        explicitWaiting(1000, driver -> ExpectedConditions.visibilityOf(cityInput));
        cityInput.sendKeys(city);
    }

    public void typeCard(String card) {
        Util.logger(this.getClass()).log(Level.WARNING, "Escribiendo el texto: {0}", card);
        explicitWaiting(1000, driver -> ExpectedConditions.visibilityOf(cityInput));
        cardInput.sendKeys(card);
    }

    public void typeMounth(String mounth) {
        Util.logger(this.getClass()).log(Level.WARNING, "Escribiendo el texto: {0}", mounth);
        explicitWaiting(1000, driver -> ExpectedConditions.visibilityOf(monthInput));
        monthInput.sendKeys(mounth);
    }

    public void typeYear(String year) {
        Util.logger(this.getClass()).log(Level.WARNING, "Escribiendo el texto: {0}", year);
        explicitWaiting(1000, driver -> ExpectedConditions.visibilityOf(yearInput));
        yearInput.sendKeys(year);
    }

    public void clicPurchase() {
        Util.logger(this.getClass()).log(Level.WARNING, "Click en Purchase");
        Util.screenshot(Parameters.driver);
        purchaseButton.click();
    }

    public boolean getMensajeSuccess(String mensaje) {
        explicitWaiting(3000, driver -> ExpectedConditions.visibilityOf(messageSuccess));
        return messageSuccess.getText().equalsIgnoreCase(mensaje);
    }

    public boolean estaEnPaginaCarrito() {
        explicitWaiting(8000, driver -> ExpectedConditions.visibilityOf(titleProducts));
        return titleProducts.getText().contains("Products");
    }
}
