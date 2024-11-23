package com.fw;

import com.fw.datasession.SessionData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Karla Ccallo
 */
public class Util {

    public static String getValueFromDataTable(DataTable dataTable, String title) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        return (String) ((Map) list.get(0)).get(title);
    }

    public static void waitThread(int mill) {
        try {
            Thread.sleep(mill);
        } catch (InterruptedException interruptedException) {
            //TODO
        }
    }

    public static void screenshot(WebDriver webDriver) {
        takeScreenShotWeb((Scenario) getVariableOnSession("scenario"), webDriver);
    }

    public static void takeScreenShotWeb(Scenario scenario, WebDriver driver) {
        byte[] screenshot = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "evidencia");
    }

    public static <T> T getVariableOnSession(String key) {
        return SessionData.sessionVariableCalled(key);
    }

    public static void saveVariableOnSession(String key, Object value) {
        SessionData.setSessionVariable(key).to(value);
    }
    public static void explicitWaiting(int ms, ExpectedCondition<?> expectedCondition) {
        new WebDriverWait(Parameters.driver,ms).until(ExpectedConditions.refreshed(expectedCondition));
    }
    public static Logger logger(Class aClass) {
        return Logger.getLogger(aClass.getName());
    }
}
