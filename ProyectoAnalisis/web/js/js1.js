/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var contador = 1;
var actual = 1;
var texto;
var resultados;
var contadorLineas = 0;
function activar()
{
    $('li.noactivo').click(function () {
        $('li.noactivo').removeClass("active");
        $(this).addClass("active");
    });
}
function removeAllChilds(a)
 {
 var a=document.getElementById(a);
 while(a.hasChildNodes())
	a.removeChild(a.firstChild);	
 }
$(function ()
{
    $('#step').click(function (e) {
        contadorLineas++;
        removeAllChilds("tablaVariables");
        var trEncabezado=$("<tr><td>Variable</td><td>Valor</td> </tr>");
        $("#tablaVariables").append(trEncabezado);
        for (i = 0; i < resultados.Variables.length; i++) {
             var tr=document.createElement("tr");
             tr.className="variables";
            if ((i + 1) <= contadorLineas) {
                var tdNombre=document.createElement("td");
                tdNombre.appendChild(document.createTextNode(resultados.Variables[i].nombre));
                var tdValor=document.createElement("td");
                tdValor.appendChild(document.createTextNode(("value",resultados.Variables[i].valor)));
                tr.appendChild(tdNombre);
                tr.appendChild(tdValor);              
            }
            document.getElementById("tablaVariables").appendChild(tr);
        }

    });
    
    
    $('#tabla').click(function (e) {

        $(".frames").hide();
        var id = e.target.id;
        actual = id;
        $("#frame_textarea_" + id + "").show();

    });
    
    $('#run1').click(function (e) {
        arreglo = texto.split("\n");
        alert(arreglo.length);
        sacarAmbientes(arreglo, 1);

    });
    
    function sacarProcedimientos(codigofuente)
    {
    }
    
    function sacarAmbientes(codigofuente, i)
    {
        var mensaje = "";
        while (codigofuente[i] != "end")
        {

            if (codigofuente[i].trim() == "begin")
            {
                i = sacarAmbientes(codigofuente, (i + 1)) + 1;
            } else
            {
                mensaje = mensaje + codigofuente[i];
                i++;
            }
        }
        ambientes(mensaje);
        return i;
    }
    
    
    $("#enviar").on("click", enviar);
    function enviar()
    {
        ttexto = editAreaLoader.getValue("textarea_" + actual + "");
        alert(texto);
        $.post("Controladora", {
            operacion: "analizar",
            texto: texto
        }, function (data) {
            var resultado = data;
            alert(resultado);
        }).fail(function ()
        {
            alert("Error en la operacion");
        });
    }


    $("#breakpoint").on("click", breakpoint);
    function breakpoint()
    {
        var beackPoint1 = "";
        for (x in window.parent.frames[actual - 1].lineas)
        {
            beackPoint1 = beackPoint1 + window.parent.frames[actual - 1].lineas[x];
        }
        alert(beackPoint1);
    }
    
    
    $("#mover").on("click", mover);
    function mover()
    {
        $.post("Controladora", {
            operacion: "mover"
        }, function (data) {
            var resultado = data;
            alert(resultado);
        }).fail(function ()
        {
            alert("Error en la operacion");
        });
    }
    $("#run").on("click", run);
    function run()
    {
        contadorLineas=0;
        $.post("Controladora", {
            operacion: "run"
        }, function (data) {
            alert(data)
            resultados = JSON.parse(data);
            alert(resultados);
        }).fail(function ()
        {
            alert("Error en la operacion");
        });
    }
});
function processFiles(files) {
    var file = files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
// Cuando éste evento se dispara, los datos están ya disponibles.
// Se trata de copiarlos a una área <div> en la página.
        $("#frame_textarea_1").addClass("frames");
        $(".frames").hide();
        contador++;
        $('li.noactivo').removeClass("active");
        var nuevoProyecto = $('<textarea id="textarea_' + contador + '" class="textarea" name="content" cols="80" rows="1"></textarea>');
        var btnNuevo = $('<li class="noactivo active" onclick="activar()" ><a id="' + contador + '" class="proyectos" href="#" >' + contador + '</a></li>');
        $("#tabla").append(btnNuevo);
        $("#container").append(nuevoProyecto);
        alert(e.target.result);
        $("#textarea_" + contador + "").val(e.target.result);
        editAreaLoader.init({
            id: "textarea_" + contador + ""		// textarea id
            , syntax: "java"			// syntax to be uses for highgliting
            , start_highlight: true		// to display with highlight mode on start-up
        });
        $("#frame_textarea_" + contador + "").addClass("frames");
        $("#frame_textarea_" + contador + "").attr("style", "heigth:1000");
        actual = contador;
    };
    reader.readAsText(file);
}

function ambientes(mensaje) {
    console.log(mensaje);
    var nuevoProyecto = $('<textarea id="textarea_' + (actual) + ".1" + '" class="textarea" name="content" cols="80" rows="1"></textarea>');
    $("#container").append(nuevoProyecto);
    $(nuevoProyecto).attr("value", "" + mensaje);
    editAreaLoader.init({
        id: "textarea_" + (actual) + ".1"		// textarea id
        , syntax: "java"			// syntax to be uses for highgliting
        , start_highlight: true		// to display with highlight mode on start-up
    });
    editAreaLoader.setValue("textarea_" + (actual) + ".1", mensaje + "");
    alert(mensaje);

}