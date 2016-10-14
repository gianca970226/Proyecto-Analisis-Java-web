/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge Alejandro
 */
@WebServlet(name = "Controladora", urlPatterns = {"/Controladora"})
public class Controladora extends HttpServlet {
    
    public static void moverArch(String archNombre) {
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "controladoras" + File.separator + arch.getName();
            System.out.println("------------"+nuevoDir);
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }
        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
    }

    public static void moverArch2(String archNombre) {
        File forig = new File("C:\\xampp\\tomcat\\bin\\" + archNombre);
        if (forig.exists()) {
            File fdest = new File("D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras" + archNombre);//"C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\
            forig.renameTo(fdest);
            System.out.println("EL fichero" + archNombre + " movido correctamente");
        } else {
            System.out.print("El fichero " + archNombre + " no existe.");
        }
    }


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
        if ("generar".equals(operacion))
        {
            System.out.println("\n*** Generando ***\n");
            String archLexico = "";
            String archSintactico = "";
            System.out.println("\n*** Procesando archivo default ***\n");
            archLexico = "D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\lexico.flex";//C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\lexico.flex
            archSintactico = "D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\sintactico.cup";//C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\sintactico.cup
            String[] alexico = {archLexico};
            String[] asintactico = {"-parser", "AnalizadorSintactico", archSintactico};
            jflex.Main.main(alexico);
            System.out.println("Generado el lexico");
            System.out.println(asintactico);
            try {
                java_cup.Main.main(asintactico);
            } catch (Exception ex) {
                System.out.println("Error al generar el analizador sintactico");
            }
            System.out.println("Generado!");
            response.setContentType("text/plain");
            response.getWriter().write("Generado correctamente");
        }
        else if ("mover".equals(operacion)) {
//            movemos los archivos generados
           
            moverArch2("AnalizadorSintactico.java");
            moverArch2("sym.java");
            response.setContentType("text/plain");
            response.getWriter().write("Se intento moverlos");
        }
        else if("analizar".equals(operacion))
        {
//            String texto = request.getParameter("texto");
//            System.out.println(texto);
//            FileWriter fichero = null;
//            PrintWriter pw = null;
//            try {
//                fichero = new FileWriter("C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\ProyectoAnalisisPrueba\\src\\java\\controladoras\\texto.txt");
//                pw = new PrintWriter(fichero);
//                pw.println(texto);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (null != fichero) {
//                        fichero.close();
//                    }
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//            }
            
//            String[] archivoPrueba = {"D:\\Proyectos\\Proyecto-Analisis-Java-web\\ProyectoAnalisis\\src\\java\\controladoras\\texto.txt"};//C:\\Users\\Jorge Alejandro\\Documents\\NetBeansProjects\\ProyectoAnalisisPrueba\\src\\java\\controladoras\\texto.txt
//            AnalizadorSintactico.main(archivoPrueba);
//            System.out.println("Ejecutado!");
//            response.setContentType("text/plain");
//            response.getWriter().write("Ejecutado correctamente");
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
