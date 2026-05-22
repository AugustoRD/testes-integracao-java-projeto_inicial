package br.com.alura.leilao.automatedTest.login;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    @Test
    public void deveFazerLoginComSucesso() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.id("username")).sendKeys("fulano");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("login-form")).submit();

        assertFalse(driver.getCurrentUrl().equals("http://localhost:8080/login"));

        assertEquals("fulano", driver.findElement(By.id("usuario-logado")).getText());

        driver.quit();
    }

    @Test
    public void naoDeveriaFazerLoginComDadosInvalidos() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.id("username")).sendKeys("fulano");
        driver.findElement(By.id("password")).sendKeys("senhaErrada");
        driver.findElement(By.id("login-form")).submit();

        assertEquals("http://localhost:8080/login?error", driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains("Usuário e senha inválidos."));

        assertThrows(NoSuchElementException.class,
                () -> driver.findElement(By.id("usuario-logado")));

        driver.quit();
    }

}
