/*
 * The MIT License
 *
 * Copyright 2020 bieito.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.bieitosousa.di05_informe;

/**
 *
 * @author bieito
 */

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
//import net.sf.jasperreports.view.JRViewer.*;
//import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;

//import net.sf.jasperreports.view.*;
//import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
//import net.sf.jasperreports.view.JRViewer.*;
//import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;


public class Factura{
        static final String  fileInforme = "."+File.separator+"informe"+File.separator+"Facturas_bieito_ejercicio2.jasper";
        static final int []addressid = {0,4,5,8,9,13,15,19,20,21,22,23,24,26,27,29,30,31,32,33,34,35,36,38,39,40,42,45,46};
        public static Connection conexion = null;
        String baseDatos = "jdbc:hsqldb:hsql://localhost";
        String usuario = "sa";
        String clave = "";

   //Constructor que conecta a la base de datos de prueba
    public Factura()
    {
        try{
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conexion = DriverManager.getConnection(baseDatos,usuario,clave);
        }

        catch (ClassNotFoundException cnfe){
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        }

        catch (SQLException sqle){
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        }

        catch (java.lang.InstantiationException sqlex){
            System.err.println("Imposible Conectar");
            System.exit(1);
        }

        catch (Exception ex){
            System.err.println("Imposible Conectar");
            System.exit(1);
        }

    }
        //El método ejecutar recibe el parametro del informe
    public void ejecutar(int n) 
    {
       if (n<=addressid.length && n>=0){

        try
        {
            //Cargamos los parametros del informe en una tabla Hash
            Map parametros = new HashMap();
            parametros.put("addressid",addressid[n]);
     
            //Generamos el informe en memoria
            JasperPrint print = JasperFillManager.fillReport(fileInforme, parametros, conexion);

            // Exporta el informe a PDF  
            JasperExportManager.exportReportToPdfFile(print, "informe.pdf");

            //Abre el archivo PDF generado
            File path = new File ("informe.pdf");
            Desktop.getDesktop().open(path);
        }

        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.toString(),"Error",JOptionPane.WARNING_MESSAGE);
        }
       }else{
           System.out.println("NUMERO NO VALIDO : Introduce un numero entre 0 y "+addressid.length);
       }
    }
 
    
}


