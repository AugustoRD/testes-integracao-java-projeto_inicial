package br.com.alura.leilao.automatedTest.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HelloWorldSelenium {

    @Test
    public void hello() {

        // Uma configuração básica de segurança
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // A mágica acontece aqui: O Selenium Manager faz o resto!
        WebDriver driver = new ChromeDriver(options);

        // Acessamos o sistema (que também está rodando no Linux)
        driver.navigate().to("http://localhost:8080/leiloes");

        driver.quit();
    }
}