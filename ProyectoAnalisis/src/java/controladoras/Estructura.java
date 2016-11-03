/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

/**
 *
 * @author Jorge Alejandro
 */
public class Estructura {
    private String tipo;
    private String nombre;
    private int tamaño;

    public Estructura(String tipo, String nombre, int tamaño) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamaño = tamaño;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTamaño() {
        return tamaño;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    
}
