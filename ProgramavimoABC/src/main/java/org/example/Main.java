package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static WebDriver browser;
    public static final String URL = "https://programavimoabc.lt/filmai.php";
    public static final String PAVADINIMAS = "FROZEN";
    public static final String ZANRAS = "Animation, Comedy";
    public static final String AKTORIAI = "Kristen Bell";
    public static final String REZISIERIUS = "Chris Buck";
    public static final String TRUKME = "142";

    public static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get(URL);
    }
    public static void sendFilm(String pavadinimas, String zanras, String aktoriai, String rezisierius, String trukme) {
        // Įvedamas filmo pavadinimas į laukelį Pavadinimas
        WebElement pavadinimasInput = browser.findElement(By.name("pavadinimas"));
        pavadinimasInput.sendKeys(pavadinimas);

        // Įvedamas filmo žanro pavadinimas į laukelį Žanras
        WebElement zanrasInput = browser.findElement(By.name("zanras"));
        zanrasInput.sendKeys(zanras);

        // Įvedamas filmo aktorius į laukelį Aktoriai
        WebElement aktoriaiInput = browser.findElement(By.name("aktoriai"));
        aktoriaiInput.sendKeys(aktoriai);

        // Ivedamas filmo režisierius į laukelį Režisierius
        WebElement rezisieriusInput = browser.findElement(By.name("rezisierius"));
        rezisieriusInput.sendKeys(rezisierius);

        // Įvedama filmo trukmė minutėmis į laukelį Trukmė
        WebElement trukmeInput = browser.findElement(By.name("trukme"));
        trukmeInput.sendKeys(trukme);

        // Paspaudžiamas siųsti mygtukas
        WebElement siustiButton = browser.findElement(By.name("insert"));
        siustiButton.click();
    }
    public static boolean checkSendData(String message) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(2));
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("msg-good")));

            if (successMessage.getText().equals(message)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public static void deleteFilm(String id){
        // Įrašomas id į laukelį Id
        WebElement trynimuiInput = browser.findElement(By.name("id"));
        trynimuiInput.sendKeys(id);

        // Paspaudžiamas trinti mygtukas
        WebElement trintiButton = browser.findElement(By.name("delete"));
        trintiButton.click();
    }

    public static void updateFilm(String id, String pavadinimas, String zanras, String aktoriai, String rezisierius, String trukme) {
        WebElement trynimuiInput = browser.findElement(By.name("id"));
        trynimuiInput.sendKeys(id);

        WebElement pavadinimasInput = browser.findElement(By.name("pavadinimas"));
        pavadinimasInput.sendKeys(pavadinimas);

        WebElement zanrasInput = browser.findElement(By.name("zanras"));
        zanrasInput.sendKeys(zanras);

        WebElement aktoriaiInput = browser.findElement(By.name("aktoriai"));
        aktoriaiInput.sendKeys(aktoriai);

        WebElement rezisieriusInput = browser.findElement(By.name("rezisierius"));
        rezisieriusInput.sendKeys(rezisierius);

        WebElement trukmeInput = browser.findElement(By.name("trukme"));
        trukmeInput.sendKeys(trukme);

        WebElement updateButton = browser.findElement(By.name("update"));
        updateButton.click();
    }
    public static void closeBrowser() {
        browser.quit();
    }

    public static void main(String[] args) {

        System.out.println("Justinos Dabasinskaite T23/1s");

        setUp();
        sendFilm(PAVADINIMAS, ZANRAS, AKTORIAI, REZISIERIUS, TRUKME);
        checkSendData("Duomenys įrašyti sėkmingai");
        deleteFilm("3");
        checkSendData("Įrašas ištrintas sėkmingai");
        updateFilm("210", "FROZEN 2", "Animation, Comedy", "Jenifer", "Chris Bun", "121");
        checkSendData("Įrašas paredaguotas sėkmingai");
        closeBrowser();
    }
}