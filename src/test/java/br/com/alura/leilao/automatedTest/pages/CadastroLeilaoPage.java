package br.com.alura.leilao.automatedTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.automatedTest.core.PageObject;

public class CadastroLeilaoPage extends PageObject {

    private static final String URL_FORM = "http://localhost:8080/leiloes/new";

    public CadastroLeilaoPage(WebDriver driver) {
        super(driver);
    }

    public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        this.driver.findElement(By.id("nome")).sendKeys(nome);
        this.driver.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        this.driver.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        this.driver.findElement(By.id("button-submit")).click();

        return new LeiloesPage(driver);
    }

    public boolean isMensagensDeValidacaoVisiveis() {
        String pageSource = this.driver.getPageSource();
        return pageSource.contains("não deve estar em branco") && pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }

    public boolean isPaginaAtual() {
        return this.driver.getCurrentUrl().equals(URL_FORM);
    }

}
