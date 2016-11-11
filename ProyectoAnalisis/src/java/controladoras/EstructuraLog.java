package controladoras;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gianka
 */
public class EstructuraLog {

    String nombre;
    String valor;
    int[] lista;
    int linea;
    Object []pilaYcola;

    public EstructuraLog(String nombre, String valor, int linea) {
        this.nombre = nombre;
        this.valor = valor;
        this.lista=null;
        this.linea = linea;
        pilaYcola=null;
    }
    //Constructor para las listas
    public EstructuraLog(String nombre, int[] lista, int linea) {
        this.nombre = nombre;
        this.valor = null;
        this.lista = lista;
        this.linea = linea;
        this.pilaYcola=null;
    }
    //Constructor para pilas y colas
    public EstructuraLog(String nombre, Object []pilaYcola, int linea) {
        this.nombre = nombre;
        this.valor = null;
        this.lista = null;
        this.linea = linea;
        this.pilaYcola=pilaYcola;
    }
}
