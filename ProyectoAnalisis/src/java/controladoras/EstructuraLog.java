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
    Stack<Object> pila;
    Queue<Object> cola;

    public EstructuraLog(String nombre, String valor, int linea) {
        this.nombre = nombre;
        this.valor = valor;
        this.lista=null;
        this.linea = linea;
        pila=null;
        cola=null;
    }
    //Constructor para las listas
    public EstructuraLog(String nombre, int[] lista, int linea) {
        this.nombre = nombre;
        this.valor = null;
        this.lista = lista;
        this.linea = linea;
        this.pila=null;
        this.cola=null;
    }
    
    public EstructuraLog(String nombre, Stack<Object>pila, int linea) {
        this.nombre = nombre;
        this.valor = null;
        this.lista = null;
        this.linea = linea;
        this.pila=pila;
        this.cola=null;
    }
    
    public EstructuraLog(String nombre, Queue<Object>cola, int linea) {
        this.nombre = nombre;
        this.valor = null;
        this.lista = null;
        this.linea = linea;
        this.pila=null;
        this.cola=cola;
    }
    
    

}
