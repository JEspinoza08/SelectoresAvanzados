package com.bdd.web.step;

import com.bdd.web.page.HomePage;
import org.openqa.selenium.WebDriver;
/**
 * @author Karla Ccallo
 */
public class HomeStep {

    private WebDriver driver;

    public HomeStep(WebDriver driver) {
        this.driver = driver;
    }

    private HomePage homePage() {
        return new HomePage(this.driver);
    }

    public boolean getMensajeWelcome() {
        return homePage().getMensajeWelcome();
    }

    public void elegirCategoria(String categoria) {
        homePage().elegirCategoria(categoria);
    }

    public void seleccionoProducto(String producto) {
        homePage().seleccionoProducto(producto);
    }

    public void agregarProducto() {
        homePage().agregarProducto();
    }

    public void clickHome() {
        homePage().clickHome();
    }
}
