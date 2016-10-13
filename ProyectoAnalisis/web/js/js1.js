/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var contador = 1;
var actual = 0;
function activar()
{
    $('li.noactivo').click(function () {

        $('li.noactivo').removeClass("active");
        $(this).addClass("active");
    });
}

$(function ()
{

    $('#tabla').click(function (e) {

        $(".frames").hide();
        var id = e.target.id;
        actual = id;
        $("#frame_textarea_" + id + "").show();

    });
    $("#enviar").on("click", enviar);
    function enviar()
    {
        var texto = editAreaLoader.getValue("textarea_1");
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

    $("#generar").on("click", generar);
    function generar()
    {
        $.post("Controladora", {
            operacion: "generar"
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
        var nuevoProyecto = $('<textarea id="textarea_' + contador + '" class="textarea" name="content" cols="80" rows="1"></textarea>');
        var btnNuevo = $('<li class="noactivo" onclick="activar()" ><a id="' + contador + '" class="proyectos" href="#" >' + contador + '</a></li>');
        $("#tabla").append(btnNuevo);
        $("#container").append(nuevoProyecto);

        $("#textarea_" + contador + "").val(e.target.result);
        editAreaLoader.init({
            id: "textarea_" + contador + ""		// textarea id
            , syntax: "java"			// syntax to be uses for highgliting
            , start_highlight: true		// to display with highlight mode on start-up
        });
        $("#frame_textarea_" + contador + "").addClass("frames");
        $("#frame_textarea_" + contador + "").attr("style", "heigth:1000");
    };
    reader.readAsText(file);
}