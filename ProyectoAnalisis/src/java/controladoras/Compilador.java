/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

import java.util.LinkedList;

/**
 *
 * @author Jorge Alejandro
 */
public class Compilador {

    private LinkedList<Variable>variables;
    
    public Compilador() {
        variables=new LinkedList<Variable>();
    }
    
    public void addVariable(Object identificador, Object valor)
    {
        Variable variable=new Variable(identificador, valor);
        variables.add(variable);
    }
    
    public void findAddVariable(Object identificadorn, Object identificadorv)
    {
        Object valor=null;
        for (Variable variable : variables) {
            if (variable.getIdentificador().toString().equals(identificadorv.toString())) {
                valor = variable.getValor();
            }
        }
        Variable variable=new Variable(identificadorn, valor);
        variables.add(variable);
    }
    
    public void showVariables()
    {
        for (Variable variable : variables) {
            System.out.println(variable.toString());
        }
    }
}
