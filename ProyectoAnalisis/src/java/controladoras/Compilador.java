/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Alejandro
 */
public class Compilador {

    Class clase;
    Object objeto;
    String Resultado;

    public Compilador() {
    }

    public String compilar2() {

        String resultado = "ecelente";
        try {
            clase = Class.forName(Compilador.class.getPackage().getName() + "." + "Programa");
            objeto = clase.newInstance();
            Method ejecutarTarea = clase.getMethod("principal", null);
            ejecutarTarea.invoke(objeto, null);
            Method ejecutarTarea2 = clase.getMethod("getResultado", null);
            resultado = (String) ejecutarTarea2.invoke(objeto, null);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al obtener la clase");
            resultado = ex.toString();
        } catch (InstantiationException ex) {
            System.out.println(ex.getMessage());
            resultado = ex.toString();
        } catch (IllegalAccessException ex) {
            System.out.println("Error acceso ilegal");
            resultado = ex.toString();
        } catch (NoSuchMethodException ex) {
            System.out.println("No se encontro el metodo");
            resultado = ex.toString();
        } catch (SecurityException ex) {
            System.out.println("Excepcion de seguridad");
            resultado = ex.toString();
        } catch (IllegalArgumentException ex) {
            System.out.println("Argumentacion ilegal");
            resultado = ex.toString();
        } catch (InvocationTargetException ex) {
            System.out.println(ex.getMessage());
            resultado = ex.toString();
        }

        return resultado;
    }

    public void compilar() {

        try {
            clase = Class.forName(Compilador.class.getPackage().getName() + "." + "Programa");
            objeto = clase.newInstance();
            Method ejecutarTarea = clase.getMethod("principal", null);
            ejecutarTarea.invoke(objeto, null);
            Method ejecutarTarea1 = clase.getMethod("mostrar", null);
            Resultado = (String) ejecutarTarea1.invoke(objeto, null);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al obtener la clase");
        } catch (InstantiationException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            System.out.println("Error acceso ilegal");
        } catch (NoSuchMethodException ex) {
            System.out.println("No se encontro el metodo");
        } catch (SecurityException ex) {
            System.out.println("Excepcion de seguridad");
        } catch (IllegalArgumentException ex) {
            System.out.println("Argumentacion ilegal");
        } catch (InvocationTargetException ex) {
//            Gson json = new Gson(); 
//            String resultados=json.toJson(ex.toString());
//            return resultados;
            System.out.println("Error al invocar" + ex.toString());
        }

    }

    public String getResultado() {
        return Resultado;
    }

    public static void main(String[] args) {

        Compilador com = new Compilador();
        String resultado = com.compilar2();
        System.out.println(resultado);
    }
}
