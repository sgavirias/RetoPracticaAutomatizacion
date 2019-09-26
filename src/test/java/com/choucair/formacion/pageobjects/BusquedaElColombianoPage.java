package com.choucair.formacion.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@DefaultUrl("https://www.elcolombiano.com/")

public class BusquedaElColombianoPage extends PageObject {



    @FindBy(xpath = "//*[@id=\"menu_1960827557\"]/div/ul/li[1]/div/a")
    WebElementFacade menu;

    @FindBy(xpath = "//*[@id=\"menu_1101739894\"]/div/ul/li[2]")
    WebElementFacade submenu;

    /*@FindBy(xpath = "//*[@id=\"_2911645938_myCarrusel\"]/article[4]")
    WebElementFacade eleNoticiaUno;*/

    @FindBy(xpath = "//span[@class='icon-font-plus']")
    WebElementFacade btnAumento;

    @FindBy(xpath = "//div[@id='ec_cab_column-12']//span[@class='iter-field-element']//input[contains(@id,'')]")
                     //input[@id='_2445795004_keywords']
    WebElementFacade clickBuscar;

    @FindBy(xpath = "//input[@id='_30547279_keywords']")
    WebElementFacade clickBuscadorNoticias;

    @FindBy(xpath = "//select[@id='_30547279_startYear']")
    WebElementFacade clickFechaUno;

    @FindBy(xpath = "//select[@id='_30547279_endYear']")
    WebElementFacade clickFechaDos;

    @FindBy(xpath = "//div[@class='iter-panel-bd']//div//div[1]//div[1]//div[1]//div[1]//div[2]")
    WebElementFacade clickCalendarioInicial;

    @FindBy(xpath = "//div[@id='iter-content-wrapper']//div[2]//div[1]//div[1]//div[1]//div[2]")
    WebElementFacade clickCalendarioFinal;

    @FindBy(xpath = "//div[@class='titular']")
    WebElementFacade lblHistorico;

    //@FindBy(xpath = "//span[contains(text(),'Septiembre')]")
    //WebElementFacade clickMesInicial;

    public void menu() {

        Actions act = new Actions(getDriver());
        act.moveToElement(menu).perform();
        act.moveToElement(submenu).click().perform();
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0, 300)");
    }

    public void noticia1(String noticia) {


        $("//article["+noticia+"]//div[1]//div[1]//div[1]//a[1]//div[1]//img[1]").click();

        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0, 300)");

        btnAumento.click();

        String tamañoLetra = $("//div[@class='text']/p[1]").getCssValue("font-size");
        String fuenteMax = "20px";

        Serenity.takeScreenshot(); //Evidencias
        if (tamañoLetra != fuenteMax){

            btnAumento.click();

        }
    }

    public void buscar() {

        Actions act = new Actions(getDriver());
        act.moveToElement(clickBuscar).click().perform();
        clickBuscar.sendKeys("FARC", Keys.ENTER);



    }

    public void fechaInicial(String diaInicial, String mesInicial) {

        Actions act = new Actions(getDriver());
        act.moveToElement(clickFechaUno).click().perform();
        clickFechaUno.sendKeys("1998",Keys.ENTER);
        act.moveToElement(clickCalendarioInicial).click().perform();

        while(true){

            String mesA = $("//div[@id='startdate-pick']//span[@class='ui-datepicker-month']").getText();

            if(mesA.compareTo(mesInicial) == 0){
                break;

            }else {

                $("//*[@id=\"startdate-pick\"]/div/div/a[1]").click();

            }

        }

        act.moveToElement($("//div[@id='startdate-pick']//a[@class='ui-state-default'][contains(text(),"+diaInicial+")]")).click().perform();



        //*****Método de busqueda de la fecha por lista desplegable*****

        /*$("//*[@id=\"_30547279_startMonth\"]/option["+mesInicial+"]").click();
        $("//*[@id=\"_30547279_startDay\"]/option["+diaInicial+"]").click();*/


    }

    public void fechaFinal(String diaFinal, String mesFinal) {

        Actions act = new Actions(getDriver());
        act.moveToElement(clickFechaDos).click().perform();
        clickFechaDos.sendKeys("2010",Keys.ENTER);
        act.moveToElement(clickCalendarioFinal).click().perform();

        while(true){

            String mesB = $("//*[@id=\"enddate-pick\"]/div/div/div/span[1]").getText();

            if(mesB.compareTo(mesFinal) == 0){
                break;

            }else {

                $("//*[@id=\"enddate-pick\"]/div/div/a[1]/span").click();

            }

        }

        act.moveToElement($("//div[@id='enddate-pick']//td//a[contains(text(),"+diaFinal+")]")).click().perform();
        act.moveToElement(clickBuscadorNoticias).click().perform();
        clickBuscadorNoticias.sendKeys("FARC", Keys.ENTER);

    }

    public void paginacion(String paginacion) {

        int coor = $("//*[@id=\"_20191887_mylistCarrousel\"]").getCoordinates().onPage().getY();
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollTo(0,"+coor+");");

        Actions act = new Actions(getDriver());
        act.moveToElement($("//div[@id=\"mylistCarrousel\"]//li[@data-page="+paginacion+"]")).click().perform();

        int coor2 = $("//*[@id=\"20191887\"]/ul/li[5]/div").getCoordinates().onPage().getY();
        JavascriptExecutor jse2 = (JavascriptExecutor) getDriver();
        jse2.executeScript("window.scrollTo(0,"+coor2+");");

        act.moveToElement($("//*[@id=\"20191887\"]/ul/li[5]/div")).click().perform();
    }

    public void verificarBusqueda() {

        Serenity.takeScreenshot(); //Evidencias

        String lblHistorico = "HISTÓRICO";
        String lblHomePpal = ($("//div[@class='titular']")).getText();
        assertThat(lblHomePpal, containsString(lblHistorico));
        System.out.println("\n                  ***************" +lblHomePpal + " Validación de página exitosa ***************");
    }
}
