package br.com.alura.leilao.automatedTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LancesPage {

    private static final String URL_LANCES = "http://localhost:8080/leilao/2";

    private WebDriver driver;

    public LancesPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(options);

        this.driver.navigate().to(URL_LANCES);
    }

    public boolean isPaginaAtual() {
        return driver.getCurrentUrl().contains(URL_LANCES);
    }

    public boolean isTituloLeilaoVisivel() {
        return driver.getPageSource().contains("Dados do Leilão");
    }

    public void fechar() {
        this.driver.quit();
    }

}
