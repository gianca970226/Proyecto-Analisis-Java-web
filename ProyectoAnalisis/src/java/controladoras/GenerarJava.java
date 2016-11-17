/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Jorge Alejandro
 */
public class GenerarJava {

    public String codigo;
    public String java;
    private HashMap<String, String> funciones;
    private LinkedList<HashMap> ambientesvariables;
//    private LinkedList<LinkedList<Estructura>> ambientesvariables;

    public GenerarJava() {
        ambientesvariables = new LinkedList<HashMap>();
//        ambientesvariables = new LinkedList<LinkedList<Estructura>>();
        funciones = new HashMap<String, String>();
        codigo = "";
        java = "";
    }

    public void ambienteVariables() {
        HashMap<String, String> variables = new HashMap<String, String>();
        ambientesvariables.add(variables);
//        LinkedList<Estructura> variables = new LinkedList<Estructura>();
//        ambientesvariables.add(variables);
    }

    public void escribirParametros(Token f) {
        HashMap<String, String> ambiente = ambientesvariables.get(ambientesvariables.size() - 1);
        for (Map.Entry<String, String> entry : ambiente.entrySet()) {
            String identificador = entry.getKey();
            String tipo = entry.getValue();
            if ("stack".equals(tipo)) {
                recibir("agregarPila(\"" + identificador + "\"," + identificador + "," + f.beginLine + ");");
            } else if ("queue".equals(tipo)) {
                recibir("agregarCola(\"" + identificador + "\"," + identificador + "," + f.beginLine + ");");
            } else if ("array".equals(tipo)) {
                recibir("agregarLista(\"" + identificador + "\"," + identificador + "," + f.beginLine + ");");
            } else if (tipo.equals("chain")) {
                recibir("agregar(\"" + identificador + "\"," + identificador + "," + f.beginLine + ");");
            } else {
                recibir("agregar(\"" + identificador + "\",Integer.toString(" + identificador + ")," + f.beginLine + ");");
            }
        }
    }

    public void escribirFunction(String retorno, String id, String parametros) {
        insertarFunction(retorno, id);
        String convertido = conversionTipo(retorno);
        recibir("\npublic " + convertido + " " + id + " (" + parametros + ") {");
    }

    public void escribirFinFunction(String id) {
        recibir("return " + id + ";\n}\n");
    }

    public void escribirCallFunctionVariableOArreglo(String tipo, String id1, String id2, String elementos) {
        if (tipo == null) {
            recibir(id1 + "=" + id2 + "(" + elementos + ");");
        } else {
            recibir(tipo + " " + id1 + "=" + id2 + "(" + elementos + ");");
        }
    }

    public void escribirCallFunctionArregloPos(String id1, String pos, String id2, String elementos) {
        recibir(id1 + "[" + pos + "]=" + id2 + "(" + elementos + ");");
    }

    public void escribirProcedure(String id, String parametros) {
        recibir("\npublic void " + id + " (" + parametros + ") {");
    }

    public void escribirFinProcedure() {
        recibir("\n}\n");
    }

    public void escribirCallProcedure(String id, String elementos) {
        recibir(id + "(" + elementos + ");\n");
    }

    public void escribirPrincipal() {
        codigo = codigo + "public void principal(){\n"
                + "try{\n";
    }

    public void recibir(String texto) {
        codigo = codigo + texto + "\n";

    }

    public void escribirInstruccion(String tipo, String id, String instruccion) {
        if (tipo == null) {
            recibir(id + "=" + instruccion + ";");
        } else {
            recibir(tipo + " " + id + "=" + instruccion + ";");
        }

    }

    public void escribirRepeat() {
        recibir("do{");
    }

    public void escribirFinRepeat(String condicion) {
        recibir("while(" + condicion + ");");
    }

    public void escribirIf(String condicion) {
        recibir("if(" + condicion + "){");
    }

    public void escribirElif(String condicion) {
        recibir("else if(" + condicion + "){");
    }

    public void escribirElse() {
        recibir("else{");
    }

    public void escribirWhile(String condicion) {
        recibir("while(" + condicion + "){");
    }

    public void escribirFor(String condicion) {
        recibir("for(" + condicion + "){");
    }

    public void escribirVariable(String identificador, String valores[], Token longitud) {
        valores[1] = conversionTipo(valores[1]);
        if (longitud == null) {
            recibir(valores[1] + " " + identificador + " = " + valores[0] + ";");
        } else {
            valores[1] = "int";
            recibir(valores[1] + " " + identificador + " = " + valores[0] + ".length;");
        }
    }

    public void escribirArreglo(String tipo, String identificador, String elementos) {
        tipo = conversionTipo(tipo);
        recibir(tipo + " " + identificador + " = {" + elementos + "};");
    }

    public void escribirPila(String id1) {
        recibir("Stack<Object>" + id1 + "= new Stack<Object>();");
    }
    
    public void escribirApilar(String id1, String valor) {
        recibir(id1 + ".add(" + valor + ");");
    }
    
    public void escribirDesapilar(String tipo, String id1, String id2) {
        if (tipo == null) {
            insertarVariable("number", id1);
            tipo = buscarVariable(id1);
            tipo = conversionTipo(tipo);
            recibir(tipo + " " + id1 + "=Integer.parseInt(" + id2 + ".pop().toString());");
        } else {
            if (tipo.equals("number")) {
                recibir(id1 + "=Integer.parseInt(" + id2 + ".pop().toString());");
            } else {
                recibir(id1 + "=" + id2 + ".pop().toString();");
            }
        }
    }

    public void escribirCola(String id1) {
        recibir("LinkedList<Object>" + id1 + "= new LinkedList<>();");
    }
    
    public void escribirEncolar(String id1, String parametro)
    {
        recibir(id1 + ".addFirst("+parametro+");");
    }
    
    public void escribirDesencolar(String id1, String tipo, String id2)
    {
        if (tipo == null) {
            insertarVariable("number", id1);
            tipo = buscarVariable(id1);
            tipo = conversionTipo(tipo);
            recibir(tipo + " " + id1 + "=Integer.parseInt(" + id2 + ".removeLast().toString());");
        } else {
            if (tipo.equals("number")) {
                recibir(id1 + "=Integer.parseInt(" + id2 + ".removeLast().toString());");
            } else {
                recibir(id1 + "=" + id2 + ".removeLast().toString();");
            }
        }
    }

    public void escribirVariableAccesoArreglo(String tipo, String id1, String id2, String pos1) {
        if (tipo == null) {
            insertarVariable("number", id1);
            tipo = buscarVariable(id1);
            tipo = conversionTipo(tipo);
            recibir(tipo + " " + id1 + "=" + id2 + "[" + pos1 + "];");
        } else {
            recibir(id1 + "=" + id2 + "[" + pos1 + "];");
        }
    }

    public void escribirArregloAccesoVariable(String id1, String id2, String id3, Token longitud) {
        if (longitud == null) {
            recibir(id1 + "[" + id2 + "]" + "=" + id3 + ";");
        } else {
            recibir(id1 + "[" + id2 + "]" + "=" + id3 + ".length;");
        }
    }

    public void escribirArregloAccesoArreglo(String tipo, String id1, String id2, String id3, String id4) {
        recibir(id1 + "[" + id2 + "]=" + id3 + "[" + id4 + "];");
    }

    public void escribirVariableVariableOperacionVariable(String tipo, String id1, String id2, String operacion, String id3, Token pisobajo1, Token pisobajo2) {
        if (tipo == null) {
            if (pisobajo1 == null || pisobajo2 == null) {
                recibir(id1 + "=" + id2 + operacion + id3 + ";");
            } else {
                recibir(id1 + "=(int) Math.floor(" + id2 + operacion + id3 + ");");
            }

        } else {
            tipo = conversionTipo(tipo);
            if (pisobajo1 == null || pisobajo2 == null) {
                recibir(tipo + " " + id1 + "=" + id2 + operacion + id3 + ";");
            } else {
                recibir(tipo + " " + id1 + "=(int) Math.floor(" + id2 + operacion + id3 + ");");
            }
        }
    }

    public void escribirVariableVariableOperacionArreglo(String tipo, String id1, String id2, String operacion, String id3, String id4, Token pisobajo1, Token pisobajo2) {
        if (tipo == null) {
            if (pisobajo1 == null || pisobajo2 == null) {
                recibir(id1 + "=" + id2 + operacion + id3 + "[" + id4 + "];");
            } else {
                recibir(id1 + "=(int) Math.floor(" + id2 + operacion + id3 + "[" + id4 + "]);");
            }
        } else {
            tipo = conversionTipo(tipo);
            if (pisobajo1 == null || pisobajo2 == null) {

                recibir(tipo + " " + id1 + "=" + id2 + operacion + id3 + "[" + id4 + "];");
            } else {
                recibir(tipo + " " + id1 + "=(int) Math.floor(" + id2 + operacion + id3 + "[" + id4 + "]);");
            }
        }
    }

    public void escribirArregloVariableOperacionArreglo(String tipo, String id1, String id2, String id3, String operador, String id4, String id5, Token pisobajo1, Token pisobajo2) {
        if (pisobajo1 == null || pisobajo2 == null) {
            recibir(id1 + "[" + id2 + "]=" + id3 + operador + id4 + "[" + id5 + "];");
        } else {
            recibir(id1 + "[" + id2 + "]=(int) Math.floor(" + id3 + operador + id4 + "[" + id5 + "]);");
        }

    }

    public void escribirArregloVariableOperacionVariable(String tipo, String id1, String id2, String id3, String operador, String id4, Token pisobajo1, Token pisobajo2) {
        if (pisobajo1 == null || pisobajo2 == null) {
            recibir(id1 + "[" + id2 + "]=" + id3 + operador + id4 + ";");
        } else {
            recibir(id1 + "[" + id2 + "]=(int) Math.floor(" + id3 + operador + id4 + ");");
        }
    }

    public void escribirVariableArregloOperacionVariable(String tipo, String id1, String id2, String pos1, String operacion, String id3, Token pisobajo1, Token pisobajo2) {
        if (pisobajo1 == null || pisobajo2 == null) {
            if (tipo == null) {
                insertarVariable("number", id1);
                tipo = buscarVariable(id1);
                tipo = conversionTipo(tipo);
                recibir(tipo + " " + id1 + "= " + id2 + "[" + pos1 + "]" + operacion + id3 + ";");
            }
        } else {
            recibir(id1 + "= (int) Math.floor(" + id2 + "[" + pos1 + "]" + operacion + id3 + ");");
        }

    }

    public void escribirVariableArregloOperacionArreglo(String tipo, String id1, String id2, String pos1, String operacion, String id3, String pos2, Token pisobajo1, Token pisobajo2) {
        if (pisobajo1 == null || pisobajo2 == null) {
            if (tipo == null) {
                insertarVariable("number", id1);
                tipo = buscarVariable(id1);
                tipo = conversionTipo(tipo);
                recibir(tipo + " " + id1 + "= " + id2 + "[" + pos1 + "]" + operacion + id3 + "[" + pos2 + "];");
            }
        } else {
            recibir(id1 + "= (int) Math.floor(" + id2 + "[" + pos1 + "]" + operacion + id3 + "[" + pos2 + "]);");
        }
    }

    public void escribirArregloArregloOperacionVariable(String tipo, String id1, String pos1, String id2, String pos2, String operacion, String id3, Token pisobajo1, Token pisobajo2) {
        if (pisobajo1 == null || pisobajo2 == null) {
            recibir(id1 + "[" + pos1 + "]" + " = " + id2 + "[" + pos1 + "]" + operacion + id3 + ";");
        } else {
            recibir(id1 + "[" + pos1 + "] = (int) Math.floor(" + id2 + "[" + pos2 + "]" + operacion + id3 + ");");
        }
    }

    public void escribirArregloArregloOperacionArreglo(String tipo, String id1, String pos1, String id2, String pos2, String operacion, String id3, String pos3, Token pisobajo1, Token pisobajo2) {
        if (pisobajo1 == null || pisobajo2 == null) {
            recibir(id1 + "[" + pos1 + "]" + " = " + id2 + "[" + pos1 + "]" + operacion + id3 + "[" + pos3 + "];");
        } else {
            recibir(id1 + "[" + pos1 + "] = (int) Math.floor(" + id2 + "[" + pos2 + "]" + operacion + id3 + "[" + pos3 + "]);");
        }
    }

    public String conversionTipo(String tipo) {
        if ("number".equals(tipo)) {
            return "int";
        } else if ("chain".equals(tipo)) {
            return "String";
        } else {
            return "int []";
        }
    }

    public void escribirLog(Token n, String[] valores) {
        if (valores == null) {
            recibir("agregar(" + null + "," + null + "," + n.beginLine + ");");
        } else if (valores[1].equals("chain")) {
            recibir("agregar(\"" + n.image + "\"," + n.image + "," + n.beginLine + ");");
        } else if (valores[1].equals("number")) {
            recibir("agregar(\"" + n.image + "\",Integer.toString(" + n.image + ")," + n.beginLine + ");");
        } else if (valores[1].equals("subrutina")) {
            recibir("agregar(\"" + valores[0] + "\",\"" + valores[1] + "\"," + n.beginLine + ");");
        } else if (valores[1].equals("stack")) {
            recibir("agregarPila(\"" + valores[0] + "\"," + n.image + "," + n.beginLine + ");");
        } else if (valores[1].equals("queue")) {
            recibir("agregarCola(\"" + valores[0] + "\"," + n.image + "," + n.beginLine + ");");
        } else {
            recibir("agregarLista(\"" + n.image + "\"," + n.image + "," + n.beginLine + ");");
        }
    }

    public void escribir() {
        java = "package controladoras;\n"
                + "import com.google.gson.Gson;\n"
                + "import java.util.*;\n"
                + "public class Programa {\n"
                + "LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();\n"
                + "String resultado=\"\";"
                + codigo + "\n"
                + "}catch(Exception e)\n"
                + "{\n"
                + "resultado=e.toString();"
                +"}\n"
                +"}\n"
                +"public String getResultado()\n"
                + "{\n"
                + "return this.resultado;"
                + "}\n"
                + "public void  agregar(String x,String valor,int linea)\n"
                + "{\nlog.add(new EstructuraLog(x, valor,linea));\n}\n"
                + "public void  agregarLista(String x,int[] lista,int linea)\n"
                + "{\n"
                + "int []aux=new int[lista.length];\n"
                + "System.arraycopy(lista, 0, aux,0, lista.length);\n"
                + "log.add(new EstructuraLog(x, aux, linea));\n}\n"
                + "public void  agregarPila(String x,Stack<Object>pila,int linea)\n"
                + "{\n"
                + "Object []aux=pila.toArray();\n"
                + "log.add(new EstructuraLog(x, aux,linea));\n}\n"
                + "public void  agregarCola(String x,Queue<Object>cola,int linea)\n"
                + "{\n"
                + "Object []aux=cola.toArray();\n"
                + "log.add(new EstructuraLog(x, aux,linea));\n}\n"
                + "public String mostrar()\n"
                + "{\n"
                + "Gson json = new Gson();\n"
                + "String resultados=\"{\\\"Variables\\\":\";\n"
                + "resultados += json.toJson(log)+\"}\";\n"
                + "return resultados;\n"
                + "}\n"
                + "}\n";
        System.out.println(java);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\Programa.java");//C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\Programa.java
            pw = new PrintWriter(fichero);
            pw.println(java);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void insertarVariable(String tipo, String identificador) {
        ambientesvariables.get(ambientesvariables.size() - 1).put(identificador, tipo);
    }
//    public void insertarVariable(String tipo, String identificador) {
//        boolean existe = false;
//        for (int i = 0; i < ambientesvariables.get(ambientesvariables.size() - 1).size(); i++) {
//            if (ambientesvariables.get(ambientesvariables.size() - 1).get(i).getNombre().equals(identificador)) {
//                existe = true;
//                break;
//            }
//        }
//        if (!existe) {
//            ambientesvariables.get(ambientesvariables.size() - 1).add(new Estructura(tipo, identificador, 0));
//        }
//
//    }

    public String buscarVariable(String identificador) {
        try {
            String tipo = (String) ambientesvariables.get(ambientesvariables.size() - 1).get(identificador);
            return tipo;
        } catch (Exception E) {
            return null;
        }
    }
//    public String buscarVariable(String identificador) {
//        String tipo=null;
//        for (int i = 0; i < ambientesvariables.get(ambientesvariables.size() - 1).size(); i++) {
//            if (ambientesvariables.get(ambientesvariables.size() - 1).get(i).getNombre().equals(identificador))
//            {
//                tipo=ambientesvariables.get(ambientesvariables.size() - 1).get(i).getTipo();
//                break;
//            }
//        }
//        return tipo;
//    }

//    public int buscarTamaño(String identificador)
//    {
//        int tamaño=-1;
//        for (int i = 0; i < ambientesvariables.get(ambientesvariables.size() - 1).size(); i++) {
//            if (ambientesvariables.get(ambientesvariables.size() - 1).get(i).getNombre().equals(identificador))
//            {
//                tamaño=ambientesvariables.get(ambientesvariables.size() - 1).get(i).getTamaño();
//                break;
//            }
//        }
//        return tamaño;
//    }
    public String buscarFunction(String identificador) {
        try {
            String tipo = funciones.get(identificador);
            return tipo;
        } catch (Exception E) {
            return null;
        }
    }

    public void insertarFunction(String retorno, String id) {
        funciones.put(id, retorno);
    }
}
