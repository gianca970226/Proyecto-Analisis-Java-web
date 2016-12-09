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
var desdeNodo = 0;
var banderaFinal = false;
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
var nodes;
var edges;
var contSub = 2;
var network;

function activar()
{
    $('li.noactivo').click(function () {
        $('li.noactivo').removeClass("active");
        $(this).addClass("active");
    });
}
function removeAllChilds(a)
{
    console.log(a)
    var a = document.getElementById(a);
    while (a.hasChildNodes())
        a.removeChild(a.firstChild);
}
function removeAllFrame()
{
    var iframes = document.getElementsByTagName('iframe');
    for (var i = 0; i < iframes.length; i++) {
        iframes[i].parentNode.removeChild(iframes[i]);
    }
}
function mostrarTabla(id)
{
    console.log(id)

    var a = document.getElementById("Variables");
    var tablas = a.children;

    for (var i = 0; i < tablas.length; i++) {

        if (tablas[i].id.trim() == id.trim())
        {
            document.getElementById(tablas[i].id.trim()).style.display = "block";
        } else
        {
            document.getElementById(tablas[i].id.trim()).style.display = "none";
        }
    }
}
function arbol()
{
    // create an array with nodes
    nodes = new vis.DataSet([
        {id: 1, label: 'Principal', color: '#FFFF00'},
        //  {id: 2, label: 'Principal', color: '#FFFF00'},
    ]);

    // create an array with edges
    edges = new vis.DataSet();
    var container = document.getElementById('mynetwork');
    var data = {
        nodes: nodes,
        edges: edges
    };
    var options = {};
    network = new vis.Network(container, data, options);

}

function pintarNodo(id, color)
{
    for (var i = 1; i <= nodes.length; i++) {
        if (nodes._data[i].id == id)
        {
            nodes._data[i].color = color;
            break;
        }

    }
    nodes.update([{id: id, color: {background: color}}]);

}
$(function ()
{
    arbol();
    var listener = new window.keypress.Listener();
    listener.simple_combo("i", function () {
        step()
    });

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
    $('#auto').click(auto);
    function auto()
    {
        alert("Hols")
    }

    $('#step').click(step);
    function step() {
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
            //metodo enviar para subdividir la estructura log 
            enviar();
            pila = new stack();
            var arregloaux = new Array()
            for (i = 0; i < resultados.Variables.length; i++) {
                if (typeof resultados.Variables[i].lista != 'undefined')
                {
                    arregloaux.push(new Ambiente(resultados.Variables[i].nombre, resultados.Variables[i].lista.toString(), resultados.Variables[i].linea))
                } else if (typeof resultados.Variables[i].pilaYcola != 'undefined') {
                    arregloaux.push(new Ambiente(resultados.Variables[i].nombre, resultados.Variables[i].pilaYcola.toString(), resultados.Variables[i].linea))
                } else {
                    arregloaux.push(new Ambiente(resultados.Variables[i].nombre, resultados.Variables[i].valor, resultados.Variables[i].linea))
                }
            }
            pila.push(new rutina("principal", 0, "textarea_" + actual + " ", 0, arregloaux, 1))
        } else {
            console.log(pila);
            mostrarTabla("tabla_" + pila.peek().nombre)
            pintarNodo(pila.peek().id, "#00ff00");
            //vemos si ya se termina un subrutina 
            if (pila.peek().contadorLinea == pila.peek().variables.length)
            {
                //si todavia no es la subrutina principa no sea finalizad si no que quitamos de la pila
                //y borramos los frame que que genero y ademas sus respectivas tablas
                if (pila.peek().nombre != "textarea_1 ") {
                    removeAllChilds("tabla_" + pila.peek().nombre);
                    var iframes = document.getElementsByTagName('iframe');
                    //borramos los frame que genero esa rutina
                    for (var i = 0; i < iframes.length; i++) {
                        if (iframes[i].id.trim() == "frame_" + pila.peek().nombre) {
                            iframes[i].parentNode.removeChild(iframes[i]);
                        }
                    }
                    document.getElementById("Variables").removeChild(document.getElementById("tabla_" + pila.peek().nombre));
                    pintarNodo(pila.peek().id, "#ff0000");
                    pila.pop();

                } else
                {
                    alert("Fin")
                    banderaFinal = true;
                    //location.reload();
                }
            }
            if (pila.peek().variables[(pila.peek().contadorLinea)].valor == "subrutina")
            {
                ambientes(subrutinas[pila.peek().variables[(pila.peek().contadorLinea)].nombre])
                var nommbreSubrutina = pila.peek().variables[(pila.peek().contadorLinea)].nombre;
                var arr = pila.peek().variables
                var index = pila.peek().contadorLinea;
                var aux2 = pila.peek().variables[(pila.peek().contadorLinea)].linea;
                var arreglo1 = sacarRutina(arr, nommbreSubrutina, index)
                arreglo1[0].valor = null
                arreglo1[arreglo1.length - 1].valor = null

                document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, pila.peek().variables[pila.peek().contadorLinea - 1].linea - pila.peek().aux);
                desdeNodo = pila.peek().id;
                pintarNodo(pila.peek().id, "#00ffff");
                pila.push(new rutina(pila.peek().variables[(pila.peek().contadorLinea)].nombreRutina, 0, "textarea_" + actual + "." + contadorSub, 0, arreglo1, contSub))


                //Colocamos en la tabla los parametos de que vienen en la funcion o procedimiento
                var tabla = document.createElement("table")
                tabla.setAttribute("id", "tabla_" + pila.peek().nombre);
                tabla.setAttribute("class", "table table-bordered ambientes");

                var thhead = document.createElement("thead")
                var trEncabezado = document.createElement("tr")
                var tdVariable = document.createElement("th")
                tdVariable.appendChild(document.createTextNode("Variable"))
                var tdValor = document.createElement("th")
                tdValor.appendChild(document.createTextNode("Valor"))
                trEncabezado.appendChild(tdVariable)
                trEncabezado.appendChild(tdValor)
                thhead.appendChild(trEncabezado)
                var thbody = document.createElement("tbody")
                thbody.setAttribute("id", "bodyTable" + pila.peek().nombre);
                tabla.appendChild(thhead)
                tabla.appendChild(thbody)
                document.getElementById("Variables").appendChild(tabla);


                contadorSub++;
                var cont = 0;

                pila.actualizar1(aux2 - 1)
                var lineaaux = pila.peek().variables[0].linea;
                var parametros = "";
                while (pila.peek().variables[cont].linea == lineaaux)
                {
                    if (typeof pila.peek().variables[cont].valor != 'undefined' || typeof pila.peek().variables[cont].lista != 'undefined')
                    {
                        var tr = document.createElement("tr")
                        tr.id = pila.peek().variables[cont].nombre
                        if (cont != 0) {
                            parametros += pila.peek().variables[cont].valor + ","
                            parametros += pila.peek().variables[cont].nombre
                        } else
                        {
                            parametros = pila.peek().variables[cont].nombre + ",";
                        }
                        var thNombre = document.createElement("td")
                        var thValor = document.createElement("td")
                        thNombre.appendChild(document.createTextNode(pila.peek().variables[cont].nombre));

                        thValor.appendChild(document.createTextNode(pila.peek().variables[cont].valor));
                        tr.appendChild(thNombre);
                        tr.appendChild(thValor);
                        thbody.appendChild(tr);
                    }
                    cont++;
                }
                //agregamos el nuevo nodo de la subrutina 
                nodes.add({id: contSub, label: parametros});
                contSub++;
                try {
                    edges.update({
                        id: contSub,
                        from: desdeNodo,
                        to: pila.peek().id,
                    });
                } catch (err) {
                    alert(err);
                }
                pila.actualizar(cont - 1);


            }

            var thbody = document.getElementById("bodyTable" + pila.peek().nombre);
            //si no contiene la tabla la variable que estamos analizando
            if (!contiene(thbody.children, pila.peek().variables[pila.peek().contadorLinea].nombre) && (typeof pila.peek().variables[pila.peek().contadorLinea].valor != 'undefined' || typeof pila.peek().variables[pila.peek().contadorLinea].lista != 'undefined'))
            {

                var tr = document.createElement("tr")
                tr.id = pila.peek().variables[pila.peek().contadorLinea].nombre
                var thNombre = document.createElement("td")
                var thValor = document.createElement("td")
                thNombre.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].nombre));
                thValor.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].valor));
                tr.appendChild(thNombre);
                tr.appendChild(thValor);
                thbody.appendChild(tr);
            } else if (typeof pila.peek().variables[pila.peek().contadorLinea].valor != 'undefined' || typeof pila.peek().variables[pila.peek().contadorLinea].lista != 'undefined')
            {

                //Si Si lo contiene seria modificar ese campo de la tabla por el nuevo valor

                var tr1;

                var arreglo = thbody.childNodes;
                var i;
                for (i = 0; i < arreglo.length; i++) {
                    if (arreglo[i].id == pila.peek().variables[pila.peek().contadorLinea].nombre.trim())
                    {
                        console.log(arreglo[i])

                        tr1 = arreglo[i];
                        tr1.innerHTML = '';
                        break;
                    }

                }
                var thNombre1 = document.createElement("td")
                var thValor1 = document.createElement("td")
                thNombre1.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].nombre));
                if (pila.peek().variables[pila.peek().contadorLinea].valor != null)
                {
                    console.log(tr1)
                    thValor1.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].valor));
                } else
                {
                    if (typeof pila.peek().variables[pila.peek().contadorLinea].lista != 'undefined') {
                        thValor1.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].lista));
                    }
                }
                tr1.appendChild(thNombre1);
                tr1.appendChild(thValor1);

            }



            //pintamos el paso a paso color azul
            if (pila.peek().contadorLinea == 0)
            {
                document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, pila.peek().variables[pila.peek().contadorLinea ].linea - pila.peek().aux);
            } else
            {
                document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, pila.peek().variables[pila.peek().contadorLinea - 1].linea - pila.peek().aux);
            }

            if (pila.peek().variables.length < pila.peek().contadorLinea + 1) {
                console.log("aaa" + typeof pila.peek().variables[pila.peek().contadorLinea].nombre);
                if (pila.peek().variables[pila.peek().contadorLinea].linea == pila.peek().variables[pila.peek().contadorLinea + 1].linea)
                {
                    console.log("Holal")
                    pila.actualizar(pila.peek().contadorLinea + 1)
                    contadorLineas++;
                }
            }

            pila.actualizar(pila.peek().contadorLinea + 1)
            contadorLineas++;
        }
    }

    $('#tabla').click(function (e) {

        $(".frames").hide();
        var id = e.target.id;
        actual = id;
        $("#frame_textarea_" + id + "").show();
    });
    function contiene(arreglo, id)
    {

        var ban = false;
        var i;
        for (i = 0; i < arreglo.length; i++) {
            if (arreglo[i].id == id)
            {
                ban = true
                break;
            }

        }
        return ban
    }
    function sacarRutina(arreglo, nombre, index)
    {
        var objetoAux = new Array();
        objetoAux.push(new Ambiente(pila.peek().variables[index].nombre, pila.peek().variables[index].valor, pila.peek().variables[index].linea))
        index++;
        var contador = 1;
        while (arreglo[index].nombre != nombre)
        {
            objetoAux.push(new Ambiente(pila.peek().variables[index].nombre, pila.peek().variables[index].valor, pila.peek().variables[index].linea))
            index++;
            contador++;
        }
        objetoAux.push(new Ambiente(pila.peek().variables[index].nombre, pila.peek().variables[index].valor, pila.peek().variables[index].linea))
        pila.peek().variables.splice((index - contador), (contador + 1));
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

            removeAllChilds("Errores")
            if (data == "") {
                document.getElementById("Errores").appendChild(document.createTextNode(("value", "Correcto")));
            } else {

                document.getElementById("Errores").appendChild(document.createTextNode(("value", data)))
            }
            ;
        }).fail(function ()
        {
            alert("Error en la operacion");
        });
    }
}
);
function processFiles(files)
{
    var file = files[0];
    var reader = new FileReader();
    reader.onload = function (e) {

        editAreaLoader.setValue("textarea_1", e.target.result + "");
    };
    reader.readAsText(file);
}
function processFiles1(files) {
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