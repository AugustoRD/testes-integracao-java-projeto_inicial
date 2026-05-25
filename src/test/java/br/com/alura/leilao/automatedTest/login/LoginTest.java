package br.com.alura.leilao.automatedTest.login;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void deveFazerLoginComSucesso() {

        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.id("username")).sendKeys("fulano");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("login-form")).submit();

        assertFalse(driver.getCurrentUrl().equals("http://localhost:8080/login"));

        assertEquals("fulano", driver.findElement(By.id("usuario-logado")).getText());

    }

    @Test
    public void naoDeveriaFazerLoginComDadosInvalidos() {

        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.id("username")).sendKeys("fulano");
        driver.findElement(By.id("password")).sendKeys("senhaErrada");
        driver.findElement(By.id("login-form")).submit();

        assertEquals("http://localhost:8080/login?error", driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains("Usuário e senha inválidos."));

        assertThrows(NoSuchElementException.class,
                () -> driver.findElement(By.id("usuario-logado")));

    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.driver.navigate().to("http://localhost:8080/leiloes/2");
        assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/login"));
        assertFalse(driver.getPageSource().contains("Dados do Leilão"));
    }

}
