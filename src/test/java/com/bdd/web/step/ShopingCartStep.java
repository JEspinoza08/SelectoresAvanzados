package com.bdd.web.step;

import com.bdd.web.page.HomePage;
import com.bdd.web.page.ShoppingCartPage;
import com.fw.Parameters;
import com.fw.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
/**
 * @author Karla Ccallo
 */
public class ShopingCartStep {

    private WebDriver driver;

    public ShopingCartStep(WebDriver driver) {
        this.driver = driver;
    }

    private ShoppingCartPage shoppingCartPage() {
        return new ShoppingCartPage(this.driver);
    }
    private HomePage homePage() {
        return new HomePage(this.driver);
    }

    public void validarProductosAgregados(String producto1, String producto2) {
        homePage().clickCart();
        Assert.assertTrue("No cargo Productos del carrito",
                shoppingCartPage().estaEnPaginaCarrito());
        Assert.assertTrue("No se pudo validar el producto agregado"+producto1,
                shoppingCartPage().validarProductoAgregado(producto1));
        Assert.assertTrue("No se pudo validar el producto agregado"+producto2,
                shoppingCartPage().validarProductoAgregado(producto2));    }

    public boolean getmensaje(String mensaje) {
        return shoppingCartPage().getMensaje(mensaje);
    }

    public void completarFormularioRegistro(String name, String country, String city, String creditCard, String mount, String year) {
        shoppingCartPage().clicPlaceOrder();
        Util.waitThread(3000);
        shoppingCartPage().typeName(name);
        shoppingCartPage().typeCountry(country);
        shoppingCartPage().typeCity(city);
        shoppingCartPage().typeCard(creditCard);
        shoppingCartPage().typeMounth(mount);
        shoppingCartPage().typeYear(year);
        shoppingCartPage().clicPurchase();
    }

    public void getmensajeSuccess(String mensaje) {
        Assert.assertTrue("No se valido mensaje:"+mensaje,
                shoppingCartPage().getMensajeSuccess(mensaje));
        Util.screenshot(Parameters.driver);
    }
}
