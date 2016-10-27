/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.el.parser.TokenMgrError;

/**
 *
 * @author Jorge Alejandro
 */
@WebServlet(name = "Controladora", urlPatterns = {"/Controladora"})
public class Controladora extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        String operacion = request.getParameter("operacion");
        if ("run".equals(operacion)) {
            String texto = request.getParameter("texto");
            escribir(texto);
            String[] archivoPrueba = {"D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\code1.txt"};
            //C:\Users\Jorge Alejandro\Documents\NetBeansProjects\ProyectoAnalisisPrueba\src\java\controladoras\texto.txt
            try {
                try {
                    AnalizadorSintactico.main(archivoPrueba);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                     response.getWriter().print(ex.getMessage());
                    Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (TokenMgrError ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Ejecutado!");
            response.setContentType("text/plain");
            response.getWriter().print("Ejecutado correctamente");
        
        } else if ("analizar".equals(operacion)) {
            Compilador compilador = new Compilador();
            compilador.compilar();
            response.getWriter().write(compilador.Resultado);
        }
        else if ("leer".equals(operacion)) {
            String cadena=muestraContenido("D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\code1.txt");
            response.getWriter().write(cadena);
        }
    }

    public String muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        String resultado = "";
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
           resultado +=cadena+"\n";
        }
        b.close();
        return resultado;
    }

    public void escribir(String texto) {
        System.out.println(texto);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\code1.txt");
            pw = new PrintWriter(fichero);
            pw.println(texto);

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
