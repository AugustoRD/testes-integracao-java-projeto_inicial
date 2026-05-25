package br.com.alura.leilao.automatedTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import br.com.alura.leilao.automatedTest.core.PageObject;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.driver.navigate().to(URL_LOGIN);
    }

    public void preencherFormularioDeLogin(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuarLogin(String username, String password) {
        this.preencherFormularioDeLogin(username, password);
        driver.findElement(By.id("login-form")).submit();
        return new LeiloesPage(driver);
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

}
