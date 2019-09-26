package com.choucair.formacion.definition;

import com.choucair.formacion.steps.BusquedaElColombianoSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class BusquedaElColombianoDefinition {


    @Steps

    BusquedaElColombianoSteps busquedaElColombianoSteps;

    String casoPrueba;

    @Given("^ingreso a la pagina el colombiano caso \"([^\"]*)\"$")
    public void ingreso_a_la_pagina_el_colombiano_caso(String id){
        this.casoPrueba = id;
        busquedaElColombianoSteps.busqueda("001");
        busquedaElColombianoSteps.busquedaFarc("002");
    }

    @When("^selecciono las noticias$")
    public void selecciono_las_noticias(){
    }

    @Then("^tomo las evidencias de ellas$")
    public void tomo_las_evidencias_de_ellas(){
    }

}
