package com.bdd.web.glue;

import com.bdd.web.step.HomeStep;
import com.bdd.web.step.ShopingCartStep;
import com.fw.Parameters;
import com.fw.Util;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
/**
 * @author Karla Ccallo
 */
public class demoBlazeGlue {

    private ShopingCartStep shopingCartStep;
    private HomeStep homeStep;
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        Util.saveVariableOnSession("scenario", this.scenario);
        String basePath = System.getProperty("user.dir");
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", basePath+"/driver/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", basePath+"/driver/chromedriver.exe");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        Parameters.driver = new ChromeDriver();
        shopingCartStep = new ShopingCartStep(Parameters.driver);
        homeStep = new HomeStep(Parameters.driver);
    }

    @After
    public void tearsDown() {
        Util.waitThread(2000);
        Scenario scenario = Util.getVariableOnSession("scenario");
        if (scenario.isFailed()) {
            Util.takeScreenShotWeb(scenario,Parameters.driver);
        }
        Parameters.driver.quit();
    }

    @Dado("que estoy en la plataforma de demoblaze")
    public void queEstoyEnLaPlataformaDeDemoBlaze() {
        Parameters.driver.get("https://www.demoblaze.com/index.html");
        Parameters.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Parameters.driver.manage().window().maximize();
    }


    @Entonces("se deberia validar que estoy en el home de la plataforma")
    public void seDeberiaValidarQueEstoyEnElHomeDeLaPlataforma() {
        Assertions.assertTrue(homeStep.getMensajeWelcome());
    }

    @Y("doy clic en categoria {string}")
    public void doyClicEnCategoria(String categoria) {
        homeStep.elegirCategoria(categoria);
    }

    @Y("selecciono el producto {string}")
    public void seleccionoElProducto(String producto) {
        homeStep.seleccionoProducto(producto);
    }

    @Cuando("agrego el producto al carrito de compras")
    public void agregoElProductoAlCarritoDeCompras() {
        homeStep.agregarProducto();
    }

    @Entonces("valido que los productos se hayan agregado {string} {string}")
    public void validoQueElProductoSeHayaAgregado(String producto1, String producto2) {
        shopingCartStep.validarProductosAgregados(producto1,producto2);
    }

    @Y("regreso a la pagina principal")
    public void regresoALaPaginaPrincipal() {
        homeStep.clickHome();
    }

    @Y("completo el formulario de compra")
    public void completoElFormularioDeCompra(DataTable dataTable) {
        String name = Util.getValueFromDataTable(dataTable, "name");
        String country = Util.getValueFromDataTable(dataTable, "country");
        String city = Util.getValueFromDataTable(dataTable, "city");
        String creditCard = Util.getValueFromDataTable(dataTable, "creditCard");
        String mount = Util.getValueFromDataTable(dataTable, "mount");
        String year = Util.getValueFromDataTable(dataTable, "year");
        shopingCartStep.completarFormularioRegistro(name,country,city,creditCard,mount,year);
    }

    @Entonces("se deberia validar el mensaje exitoso {string}")
    public void seDeberiaValidarElMensajeExitoso(String mensaje) {
        shopingCartStep.getmensajeSuccess(mensaje);
    }
}
