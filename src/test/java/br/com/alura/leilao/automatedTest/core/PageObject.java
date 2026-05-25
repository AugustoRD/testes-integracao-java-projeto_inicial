package br.com.alura.leilao.automatedTest.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PageObject {

    protected WebDriver driver;

    public PageObject(WebDriver driver) {

        if (driver != null) {
            this.driver = driver;
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            this.driver = new ChromeDriver(options);
        }
    }

    public void fechar() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

}
