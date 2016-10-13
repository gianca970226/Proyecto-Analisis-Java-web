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
public class Variable {
    private Object identificador;
    private Object valor;

    public Variable(Object identificador, Object valor) {
        this.identificador = identificador;
        this.valor = valor;
    }

    public Object getIdentificador() {
        return identificador;
    }

    public Object getValor() {
        return valor;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Variable: "+identificador.toString()+" valor: "+valor.toString();
    }
    
    
}
