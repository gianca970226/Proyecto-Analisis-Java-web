<%-- 
    Document   : index
    Created on : 9/10/2016, 10:12:55 PM
    Author     : Jorge Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Write Code</title>
        <link rel="shortcut icon" href="imagenes/icono.png"> 
        <script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/ambiente.js"></script>
        <script type="text/javascript" src="js/js1.js"></script>
        <script type="text/javascript" src="js/stack.js"></script>

        <script type="text/javascript" src="js/keypress.js"></script>
        <script language="javascript" type="text/javascript" src="edit_area/edit_area_loader.js"></script>
        <script language="javascript" type="text/javascript">
            editAreaLoader.init({
                id: "textarea_1"		// textarea id
                , syntax: "java"			// syntax to be uses for highgliting
                , start_highlight: true		// to display with highlight mode on start-up
                , is_editable: true
            });
            $("#line_number").attr('onclick', "breakpoint()");

        </script>
        <script src="js/googleAnalytics.js"></script>
        <script type="text/javascript" src="js/vis.js"></script>
        <script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>
        <link href="css/vis-network.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/estilo.css" >
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    </head>
    <body>
        <nav class="col-xs-12 navbar-btn">
            <input  id="archivo" type="file"   class="filestyle" data-input="false" data-buttonName="btn-danger"data-buttonText="" onchange='processFiles(this.files)'>
            <button id="run" class="btn-lg"><span class="glyphicon glyphicon-play "></span></button>
            <button id="step" class="btn-lg"><span class="glyphicon glyphicon-download"></span></button>
            <button id="auto" class="btn-lg"><span class="glyphicon glyphicon-asterisk"></span></button>
        </nav>



        <!--<div class="col-xs-12">
            <div id="cssmenu"  >
                <ul id="tabla" >
                    <li class="noactivo active">
                        <a id="1" class="proyectos" href="#" >1</a>
                    </li>
                </ul>
            </div>
        </div>-->
        <div class="col-xs-8">
            <div id="container" >
                <textarea id="textarea_1" onclick="activar()" class="textarea" name="content" cols="80" rows="1"></textarea>
            </div>
        </div>

        <div id="Variables" class="col-xs-4 table-responsive" style="" >
            <table id="tabla_textarea_1" class="table table-bordered ambientes" >
                <thead>
                    <tr>
                        <th>NOMBRE VARIABLE</th>
                        <th>VALOR</th>
                    </tr>
                </thead>
                <tbody id="bodyTabletextarea_1 ">
                </tbody>
            </table>
        </div>

        <div class="col-xs-12">
            <label id="Errores" style="color: red">Eroores</label>
        </div>
        <div class="col-xs-8">
            <div id="mynetwork" class="col-xs-12" ></div>
        </div>

        <div id="puntosCorte" class="col-xs-4 table-responsive" style="" >
            <table id="puntosCorteTabla" class="table table-bordered ambientes" >
                <thead>
                    <tr>
                        <th>CODIGO LINEA</th>
                        <th>NUMERO LINEA</th>
                        <th>CANTIDAD</th>
                    </tr>
                </thead>
                <tbody id="bodyPuntosCorte">
                </tbody>
            </table>
        </div>

    </body>
</html>