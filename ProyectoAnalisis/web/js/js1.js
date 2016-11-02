/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Ambiente = function (nombre, valor, linea)
{
    this.nombre = nombre;
    this.valor = valor;
    this.linea = linea;
}
var contador = 1;
var actual = 1;
var texto;
var resultados;
var contadorLineas = 0;
var bandera = true;
var subrutinas = new Object();
var contadorSubrutinas = 0;
var contadorSub = 0;
var pila;
var objetoAux = new Array();
function activar()
{
    $('li.noactivo').click(function () {
        $('li.noactivo').removeClass("active");
        $(this).addClass("active");
    });
}
function removeAllChilds(a)
{
    var a = document.getElementById(a);
    while (a.hasChildNodes())
        a.removeChild(a.firstChild);
}
$(function ()
{



    $.post("Controladora", {
        operacion: "leer",
        texto: texto
    }, function (data) {
        var resultado = data;
        editAreaLoader.setValue("textarea_" + actual, resultado + "");
    }).fail(function ()
    {
        alert("Error en la operacion");
    });
    $('#step').click(function (e) {
        if (bandera)
        {
            $.ajax({
                type: 'POST',
                url: "Controladora",
                data: {'operacion': 'analizar'},
                success: function (data)
                {
                    resultados = JSON.parse(data);
                    alert(data);
                },
                async: false
            });

            bandera = false;
            enviar()
            pila = new stack();
            console.log(resultados.Variables)
            var arregloaux = new Array()
            for (i = 0; i < resultados.Variables.length; i++) {
                if (typeof resultados.Variables[i].lista != 'undefined')
                {
                        arregloaux.push(new Ambiente(resultados.Variables[i].nombre, resultados.Variables[i].lista.toString(), resultados.Variables[i].linea))
                } else {
                    arregloaux.push(new Ambiente(resultados.Variables[i].nombre, resultados.Variables[i].valor, resultados.Variables[i].linea))
                }
            }
        
        pila.push(new rutina("principal", 0, "textarea_" + actual + " ", 0, arregloaux))
    } else {
    var codigo = editAreaLoader.getValue(pila.peek().nombre.trim());
    codigo = codigo.split('\n')
    if (pila.peek().variables[(pila.peek().contadorLinea)].valor == "subrutina")
    {
        ambientes(subrutinas[pila.peek().variables[(pila.peek().contadorLinea)].nombre])
        var nom = pila.peek().variables[(pila.peek().contadorLinea)].nombre;
        var arr = pila.peek().variables
        var index = pila.peek().contadorLinea;
        var aux2 = pila.peek().variables[(pila.peek().contadorLinea)].linea;
        var arregl = sacarRutina(arr, nom, index)
        var arregloaux = new Array();
        arregl.forEach(function (item, index, array) {
            arregloaux.push(new Ambiente(item.nombre, item.valor, item.linea))
        });
        pila.push(new rutina(pila.peek().variables[(pila.peek().contadorLinea)].nombreRutina, 0, "textarea_" + actual + "." + contadorSub, arregloaux))

        contadorSub++;


        pila.actualizar1(aux2 - 1)
        console.log(pila)
    }

    if (pila.peek().contadorLinea == 0)
    {
        document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, resultados.Variables[pila.peek().contadorLinea ].linea - pila.peek().aux);
    } else
    {
        document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, resultados.Variables[pila.peek().contadorLinea - 1].linea - pila.peek().aux);
    }
    console.log(pila.peek().variables)
    removeAllChilds("tablaVariables");
    var trEncabezado = $("<tr><td>Variable</td><td>Valor</td> </tr>");
    $("#tablaVariables").append(trEncabezado);
    for (i = 0; i < pila.peek().variables.length; i++) {
        var tr = document.createElement("tr");
        tr.className = "variables";
        if ((i + 1) <= pila.peek().contadorLinea) {
            var tdNombre = document.createElement("td");
            tdNombre.appendChild(document.createTextNode(pila.peek().variables[i].nombre));
            var tdValor = document.createElement("td");
            if (pila.peek().variables[i].valor != null)
            {
                tdValor.appendChild(document.createTextNode(("value", pila.peek().variables[i].valor)));
            } else
            {
                tdValor.appendChild(document.createTextNode(("value", pila.peek().variables[i].lista)));
            }
            tr.appendChild(tdNombre);
            tr.appendChild(tdValor);
        }
        document.getElementById("tablaVariables").appendChild(tr);
    }
    if (resultados.Variables[contadorLineas].valor == 'fin')
    {
        var oe = pila.peek().contadorLinea
        var frame = document.getElementById("frame_textarea_1.0"),
                frameDoc = frame.contentDocument || frame.contentWindow.document;
        frameDoc.documentElement.innerHTML = "";
        pila.pop();
        pila.actualizar(pila.peek().contadorLinea + oe)
    }
    pila.actualizar(pila.peek().contadorLinea + 1)
    contadorLineas++;
    }
});


$('#tabla').click(function (e) {

    $(".frames").hide();
    var id = e.target.id;
    actual = id;
    $("#frame_textarea_" + id + "").show();

});

function sacarRutina(arreglo, nombre, index)
{
    var objetoAux = new Array();
    objetoAux.push(new Ambiente(pila.peek().variables[index].nombre, pila.peek().variables[index].valor, pila.peek().variables[index].linea))
    index++;
    var contador = 1;
    console.log(arreglo)


    while (arreglo[index].nombre != nombre)
    {
        objetoAux.push(new Ambiente(pila.peek().variables[index].nombre, pila.peek().variables[index].valor, pila.peek().variables[index].linea))
        index++;
        contador++;
    }
    objetoAux.push(new Ambiente(pila.peek().variables[index].nombre, pila.peek().variables[index].valor, pila.peek().variables[index].linea))
    pila.peek().variables.splice((index - contador - 1), (contador + 1));
    return objetoAux
}
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


function enviar()
{
    var codigo = editAreaLoader.getValue("textarea_" + actual + "");
    var indice1 = codigo.indexOf("declare");
    var indice2 = codigo.indexOf("enddeclare");
    codigo = codigo.substring(indice1 + 7, indice2);
    sacarSubRutinas(codigo.split("\n"))
    mostrarSubrutinas()

}
function sacarSubRutinas(codigo)
{
    for (var i = 0; i < codigo.length; i++) {
        if (codigo[i].indexOf("function") != -1 || codigo[i].indexOf("procedure") != -1)
        {
            var j = i;
            var cadena = '';
            while (codigo[j].trim() != 'end')
            {
                cadena += codigo[j] + '\n'
                j++
            }
            cadena += 'end'
            var cadena2 = codigo[i].substring(codigo[i].indexOf(" "), codigo[i].indexOf("(")).trim()

            subrutinas[cadena2.substring(cadena2.indexOf(" "), cadena2.length).trim()] = cadena
        }
    }
}
function mostrarSubrutinas()
{
    for (x in subrutinas)
    {
        console.log(x + '-> ' + subrutinas[x])
    }
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
    texto = editAreaLoader.getValue("textarea_" + actual + "");
    contadorLineas = 0;
    $.post("Controladora", {
    operacion: "run",
            texto: texto
    }, function (data) {


        document.getElementById("Errores").appendChild(document.createTextNode(("value", data)));
    }).fail(function ()
    {
        alert("Error en la operacion");
    });
    }
}
);
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

                editAreaLoader.init({
                    id: "textarea_" + contador + ""		// textarea id
                    , syntax: "java"			// syntax to be uses for highgliting
                    , start_highlight: true		// to display with highlight mode on start-up
                });
                editAreaLoader.setValue("textarea_" + contador + "", e.target.result + "");
                $("#frame_textarea_" + contador + "").addClass("frames");
                $("#frame_textarea_" + contador + "").attr("style", "heigth:1000");
                actual = contador;
            };
            reader.readAsText(file);
        }

function ambientes(mensaje) {
    console.log(mensaje);
    var nombre = "textarea_" + (actual) + "." + contadorSubrutinas;
    var nuevoProyecto = $('<textarea id="textarea_' + (actual) + "." + contadorSubrutinas + '" class="textarea" name="content" cols="80" rows="1"></textarea>');
    $("#container").append(nuevoProyecto);
    $(nuevoProyecto).attr("value", "" + mensaje);

    editAreaLoader.init({
        id: nombre		// textarea id
        , syntax: "java"			// syntax to be uses for highgliting
        , start_highlight: true		// to display with highlight mode on start-up
    });

    editAreaLoader.setValue(nombre, mensaje + "");
    contadorSubrutinas++;

}