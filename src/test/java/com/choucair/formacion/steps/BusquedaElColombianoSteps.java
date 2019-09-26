package com.choucair.formacion.steps;

import au.com.bytecode.opencsv.CSVReader;
import com.choucair.formacion.pageobjects.BusquedaElColombianoPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class BusquedaElColombianoSteps {

    BusquedaElColombianoPage busquedaElColombianoPage;

    String[]datos;

    public void leerCSV(String id)  {

        au.com.bytecode.opencsv.CSVReader reader;
        try {

            reader = new CSVReader(new FileReader("src/test/resources/Datadriven/badeDatos.csv"));
            String[] fila;
            while ((fila = reader.readNext())!=null){

                Logger.getLogger(fila[0]);

                if (id.equals(fila[0].trim())){
                    datos = fila;
                }
            }

            reader.close();

        } catch (IOException e){
            Logger.getLogger(""+e);
        }
    }
    @Step

    public void busqueda(String id) {
        leerCSV(id);

        busquedaElColombianoPage.open();
        busquedaElColombianoPage.menu();
        busquedaElColombianoPage.noticia1(datos[1]);


    }

    public void busquedaFarc(String id) {
        leerCSV(id);

        busquedaElColombianoPage.buscar();
        busquedaElColombianoPage.fechaInicial(datos[4],datos[3]);
        busquedaElColombianoPage.fechaFinal(datos[6],datos[5]);
        busquedaElColombianoPage.paginacion(datos[7]);
        busquedaElColombianoPage.verificarBusqueda();
    }
}

