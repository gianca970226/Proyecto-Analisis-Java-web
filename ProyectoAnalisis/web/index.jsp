<%-- 
    Document   : index
    Created on : 9/10/2016, 10:12:55 PM
    Author     : Jorge Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>EditArea Test</title>
        <script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/js1.js"></script>
        <script type="text/javascript" src="js/stack.js"></script>
         <script type="text/javascript" src="js/keypress.js"></script>
        <script language="javascript" type="text/javascript" src="edit_area/edit_area_loader.js"></script>
        <script language="javascript" type="text/javascript">
            editAreaLoader.init({
                id: "textarea_1"		// textarea id
                , syntax: "java"			// syntax to be uses for highgliting
                , start_highlight: true		// to display with highlight mode on start-up
            });
            $("#line_number").attr('onclick', "breakpoint()");
        </script>
        <link rel="stylesheet" type="text/css" href="css/estilo.css" >
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    </head>
    <body>
        <nav class="col-xs-12">
            <input  id="archivo"type="file" onchange='processFiles(this.files)' >
            <!--  <button id="enviar">Enviar</button>
            <button id="breakpoint">breakpoint</button>
             <button id="mover">Mover Archivos</button>-->
            <button id="run"><span class="glyphicon glyphicon-play"></span></button>
            <button id="step"><span class="glyphicon glyphicon-download"></span>
            </button>
        </nav>
        <div class="col-xs-12">
            <div id="cssmenu"  >
                <ul id="tabla" >
                    <li class="noactivo active">
                        <a id="1" class="proyectos" href="#" >1</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-xs-10">
            <div id="container" >
                <textarea id="textarea_1" onclick="activar()" class="textarea" name="content" cols="80" rows="1"></textarea>
            </div>
        </div>

        <div id="Variables" class="col-xs-2 table-responsive" style="" >
            
        </div>
        <div class="col-xs-12">
            <label id="Errores">Eroores</label>
        </div>

    </body>
</html>