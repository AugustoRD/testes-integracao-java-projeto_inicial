package br.com.alura.leilao.automatedTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPage {

    private static final String URL_LOGIN = "http://localhost:8080/login";

    private WebDriver driver;

    public LoginPage() {
        // Devolvemos as opções de segurança do Chrome moderno
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Deixamos o Selenium Manager assumir o controle (sem System.setProperty)
        this.driver = new ChromeDriver(options);

        this.driver.navigate().to(URL_LOGIN);
    }

    public void preencherFormularioDeLogin(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuarLogin() {
        driver.findElement(By.id("login-form")).submit();
    }

    public String getNomeUsuarioLogado() {
        try {
            return driver.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean isPaginaAtual() {
        return driver.getCurrentUrl().contains(URL_LOGIN);
    }

    public boolean isMensagemDeLoginInvalidoVisivel() {
        return driver.getPageSource().contains("Usuário e senha inválidos");
    }

    public void fechar() {
        this.driver.quit();
    }

}
