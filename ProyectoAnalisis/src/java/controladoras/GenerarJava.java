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

/**
 *
 * @author Jorge Alejandro
 */
public class GenerarJava {

    public String codigo;
    public String java;
    private HashMap<String, String> funciones;
    private LinkedList<HashMap>ambientesvariables;
//    private LinkedList<LinkedList<Estructura>> ambientesvariables;

    public GenerarJava() {
        ambientesvariables = new LinkedList<HashMap>();
//        ambientesvariables = new LinkedList<LinkedList<Estructura>>();
        funciones = new HashMap<String, String>();
        codigo = "";
        java = "";
    }

    public void ambienteVariables() {
        HashMap<String,String>variables=new HashMap<String, String>();
        ambientesvariables.add(variables);
//        LinkedList<Estructura> variables = new LinkedList<Estructura>();
//        ambientesvariables.add(variables);
    }

    public void escribirFunction(String retorno, String id, String parametros) {
        if ("string".equals(retorno)) {
            retorno = "String";
        }
        insertarFunction(retorno, id);
        recibir("\npublic " + retorno + " " + id + " (" + parametros + ") {");
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
        codigo = codigo + "public void principal() {\n";
    }

    public void recibir(String texto) {
        codigo = codigo + texto + "\n";

    }
    
    public void escribirRepeat()
    {
        recibir("do{");
    }
    public void escribirRepeat(String condicion)
    {
        recibir("while("+condicion+");");
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

    public void escribirVariable(String identificador, String valores[]) {
        recibir(valores[1] + " " + identificador + " = " + valores[0] + ";");
    }

    public void escribirArreglo(String tipo, String identificador, String elementos) {
        recibir(tipo + " [] " + identificador + " = {" + elementos + "};");
    }

    public void escribirVariableAccesoArreglo(String tipo, String id1, String id2, String[] valores1) {
        if (tipo == null) {
            recibir(id1 + "=" + id2 + "[" + valores1[0] + "];");
        } else {
            recibir(tipo + " " + id1 + "=" + id2 + "[" + valores1[0] + "];");
        }
    }

    public void escribirArregloAccesoVariable(String id1, String id2, String id3) {
        recibir(id1 + "[" + id2 + "]" + "=" + id3 + ";");
    }

    public void escribirArregloAccesoArreglo(String tipo, String id1, String id2, String id3, String id4) {
        if (tipo == null) {
            recibir(id1 + "[" + id2 + "]=" + id3 + "[" + id4 + "];");
        } else {
            recibir(tipo + " " + id1 + "[" + id2 + "]=" + id3 + "[" + id4 + "];");
        }
    }

    public void escribirVariableVariableOperacionVariable(String tipo, String id1, String id2, String operacion, String id3) {
        if (tipo == null) {
            recibir(id1 + "=" + id2 + operacion + id3 + ";");
        } else {
            recibir(tipo + " " + id1 + "=" + id2 + operacion + id3 + ";");
        }
    }

    public void escribirVariableVariableOperacionArreglo(String tipo, String id1, String id2, String operacion, String id3, String id4) {
        if (tipo == null) {
            recibir(id1 + "=" + id2 + operacion + id3 + "[" + id4 + "];");
        } else {
            recibir(tipo + " " + id1 + "=" + id2 + operacion + id3 + "[" + id4 + "];");
        }
    }

    public void escribirArregloVariableOperacionArreglo(String tipo, String id1, String id2, String id3, String operador, String id4, String id5) {
        recibir(id1 + "[" + id2 + "]=" + id3 + operador + id4 + "[" + id5 + "];");
    }

    public void escribirArregloVariableOperacionVariable(String tipo, String id1, String id2, String id3, String operador, String id4) {
        recibir(id1 + "[" + id2 + "]=" + id3 + operador + id4 + ";");
    }

    public void escribirLog(Token n, String[] valores) {
        if (valores == null) {
            recibir("agregar(" + null + "," + null + "," + n.beginLine + ");");
        } else if (valores[1].equals("String")) {
            recibir("agregar(\"" + n.image + "\"," + n.image + "," + n.beginLine + ");");
        } else if (valores[1].equals("int")) {
            recibir("agregar(\"" + n.image + "\",Integer.toString(" + n.image + ")," + n.beginLine + ");");
        } else if (valores[1].equals("subrutina")) {
            recibir("agregar(\"" + valores[0] + "\",\"" + valores[1] + "\"," + n.beginLine + ");");
        } else {
            recibir("agregarLista(\"" + n.image + "\"," + n.image + "," + n.beginLine + ");");
        }
    }

    public void escribir() {
        java = "package controladoras;\n"
                + "import com.google.gson.Gson;\n"
                + "import java.util.LinkedList;\n"
                + "public class Programa {\n"
                + "LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();\n"
                + codigo + "\n"
                + "}\n"
                + "public void  agregar(String x,String valor,int linea)\n"
                + "{\nlog.add(new EstructuraLog(x, valor,linea));\n}\n"
                + "public void  agregarLista(String x,int[] lista,int linea)\n"
                + "{\nlog.add(new EstructuraLog(x, lista, linea));\n}\n"
                + "public String mostrar()\n"
                + "{  \n"
                + "    Gson json = new Gson();\n"
                + "    String resultados=\"{\\\"Variables\\\":\";\n"
                + "    resultados += json.toJson(log)+\"}\";\n"
                + "    \n"
                + "    return resultados;\n"
                + "}"
                + "}\n";
        System.out.println(java);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\Programa.java");//C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\Programa.java
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
        ambientesvariables.get(ambientesvariables.size()-1).put(identificador,tipo);
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
            String tipo = (String) ambientesvariables.get(ambientesvariables.size()-1).get(identificador);
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
