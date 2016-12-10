var subrutinas = new Object();//objeto que tienen las subrutinas que el usuairo ha creado
var nodes;
var edges;
var network;
var pila;//pila de ambientes se va apilando cada que se llaman subrutinas
var resultados;//obtengo toda la lista log 
var contadorLineas = 0; // lleva el contador de linea del programa 
var contadorSubrutinas = 0; //este me ayuda para poder identificar las subrutinas para el nombre 



var contador = 1;
var actual = 1;











$(function ()
{
    arbol();
    //asociamos la function setp a correspondiente boton
    $('#step').click(step);
    //libreria para capturar evento de presionar una tecla 
    var listener = new window.keypress.Listener();
    listener.simple_combo("i", function () {
        step()
    });

    //Leemos el archivo que quedo en la ultima ejecucion
    $.post("Controladora", {
        operacion: "leer",
    }, function (data) {
        var resultado = data;
        //cargamos en el campo de editor de texto lo que nos devuelve el servidor
        editAreaLoader.setValue("textarea_" + actual, resultado + "");
    }).fail(function ()
    {
        alert("Error en la operacion");
    });


    function step() {


        mostrarTabla("tabla_" + pila.peek().nombre)
        pintarNodo(pila.peek().id, "#00ff00");


        //vemos si ya se termina un subrutina 
        if (pila.peek().contadorLinea == pila.peek().variables.length)
        {
            /*si todavia no es la subrutina principal no sea finaliza si no que quitamos de la pila
             y borramos los frame que que genero y ademas sus respectivas tablas*/

            if (pila.peek().nombre != "textarea_1 ") {
                removeAllChilds("tabla_" + pila.peek().nombre);
                var iframes = document.getElementsByTagName('iframe');
                //borramos el frame que se genero para represenar esta subrutina
                for (var i = 0; i < iframes.length; i++) {
                    if (iframes[i].id.trim() == "frame_" + pila.peek().nombre) {
                        iframes[i].parentNode.removeChild(iframes[i]);
                    }
                }
                //borramos la tabla tambiem que se utilizo para este ambiente
                document.getElementById("Variables").removeChild(document.getElementById("tabla_" + pila.peek().nombre));
                pintarNodo(pila.peek().id, "#ff0000");
                //eliminamos el ambiente de la pila
                pila.pop();

            } else
            {
                // si ya se termino la rutina principal ya se termina el proceso
                alert("Fin")

                //location.reload();
            }
        }

        //vemos si es un llamado a una subrutina 
        if (pila.peek().variables[(pila.peek().contadorLinea)].valor == "subrutina")
        {
            //creamos un nuevo area de editor de texto con el codigo correspondiente
            //guardado en la subrutinas 
            crearAmbiente(subrutinas[pila.peek().variables[(pila.peek().contadorLinea)].nombre]);
            //como tenemos que sacar del las variables (codigo de sub rutina)
            //entonces vamos a subtraer llamando el metodo sacarRutina()
            var nombreSubrutina = pila.peek().variables[(pila.peek().contadorLinea)].nombre;
            var codigoVariables = pila.peek().variables
            var index = pila.peek().contadorLinea;
            var aux2 = pila.peek().variables[(pila.peek().contadorLinea)].linea;
            var arreglo1 = sacarRutina(codigoVariables, nombreSubrutina, index)

            //pinto step en donde llamo la funcion
            document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, pila.peek().variables[pila.peek().contadorLinea - 1].linea - pila.peek().aux);
            //guardo el id del nodo que va llamar la subrutina
            var desdeNodo = pila.peek().id;
            //pinto el nodo de un color de espera
            pintarNodo(pila.peek().id, "#00ffff");
            //agrego a la pila la nueva subrutina
            pila.push(new rutina(pila.peek().variables[(pila.peek().contadorLinea)].nombreRutina, 0, "textarea_" + actual + "." + contadorSubrutinas, 0, arreglo1, contadorSubrutinas + 2))
            contadorSubrutinas++;

            //Creamos la tabla con sus atributos y su respectivo encabezado
            var tabla = document.createElement("table");
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
            //fin de encabezado de la nueva tabla
            var thbody = document.createElement("tbody")
            thbody.setAttribute("id", "bodyTable" + pila.peek().nombre);
            tabla.appendChild(thhead)
            tabla.appendChild(thbody)
            document.getElementById("Variables").appendChild(tabla);
            //agregamos la tabla nueva a div variables


            var cont = 0;
            pila.actualizar1(aux2 - 1);

            var lineaaux = pila.peek().variables[0].linea;
            var parametros = "";
            //miro los paramentros que tiene esta nueva subrutina para asi colocarlos de una en la 
            //tabla que esto creando
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
            //fin de la busca de parametros
            //agregamos el nuevo nodo de la subrutina 
            agregarNodo(contadorSubrutinas + 1, parametros, desdeNodo, pila.peek().id);
            pila.actualizar(cont - 1);
        }
        //fin de que hacer cuando se llega un llamado a un subrutina donde se deja ya listo la
        //nueva que se va ejecutar en esta subrutina

        //buscamos el body de la tabla del ambiente que se esta ejecutando actualmente 
        //que estan el el tope de la pila 
        var thbody = document.getElementById("bodyTable" + pila.peek().nombre);
        //si no contiene la tabla la variable que estamos analizando
        if (!contiene(thbody.children, pila.peek().variables[pila.peek().contadorLinea].nombre) && (typeof pila.peek().variables[pila.peek().contadorLinea].valor != 'undefined' || typeof pila.peek().variables[pila.peek().contadorLinea].lista != 'undefined'))
        {
            //accedemos ah crear el correspondiente tr estos tr tiene como id el valor de la variable 
            //osea el nombre de la variable que se esta listando
            var tr = document.createElement("tr")
            tr.id = pila.peek().variables[pila.peek().contadorLinea].nombre
            var thNombre = document.createElement("td")
            var thValor = document.createElement("td")
            thNombre.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].nombre));
            thValor.appendChild(document.createTextNode(pila.peek().variables[pila.peek().contadorLinea].valor));
            tr.appendChild(thNombre);
            tr.appendChild(thValor);
            thbody.appendChild(tr);
            //fin agregamos la variable ala actual tabla
        } else if (typeof pila.peek().variables[pila.peek().contadorLinea].valor != 'undefined' || typeof pila.peek().variables[pila.peek().contadorLinea].lista != 'undefined')
        {

            //Si Si lo contiene seria modificar ese campo de la tabla por el nuevo valor
            var tr1;
            var arreglo = thbody.childNodes;
            var i;
            //buscamos el correspondiente de los hijos para basearlo  y volver a poner con el valor nuevo
            for (i = 0; i < arreglo.length; i++) {
                if (arreglo[i].id == pila.peek().variables[pila.peek().contadorLinea].nombre.trim())
                {
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

//--------------------------------------------

        //pintamos el paso a paso color azul
        if (pila.peek().contadorLinea == 0)
        {
            document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, pila.peek().variables[pila.peek().contadorLinea ].linea - pila.peek().aux);
        } else
        {
            document.getElementById("frame_" + pila.peek().nombre.trim()).contentWindow.step(pila.peek().variables[(pila.peek().contadorLinea)].linea - pila.peek().aux, pila.peek().variables[pila.peek().contadorLinea - 1].linea - pila.peek().aux);
        }

        if (pila.peek().variables.length < pila.peek().contadorLinea + 1) {
            //     console.log("aaa" + typeof pila.peek().variables[pila.peek().contadorLinea].nombre);
            if (pila.peek().variables[pila.peek().contadorLinea].linea == pila.peek().variables[pila.peek().contadorLinea + 1].linea)
            {
                //  console.log("Holal")
                pila.actualizar(pila.peek().contadorLinea + 1)
                contadorLineas++;
            }
        }

        pila.actualizar(pila.peek().contadorLinea + 1)
        contadorLineas++;

    }
    /*
     Aqui mandamos a solicitar la estructura log del codigo compildado
     como esta lista ya podemos sacar las subrutinas creamos la pila 
     y agregamos el primer nodo como el principal
     */
    function inicioStep()
    {
        $.ajax({
            type: 'POST',
            url: "Controladora",
            data: {'operacion': 'analizar'},
            success: function (data)
            {
                resultados = JSON.parse(data);
                //alert(data);
            },
            async: false
        });

        //metodo enviar para subdividir la estructura log 
        sacarSubRutinas();
        pila = new stack();
        var arregloaux = new Array()
        //agregamos los datos de la lista log en el array de esta rutina que es la principal
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
    }
    /*
     nos ingresa un id donde recorro los hijos que son todos las tablas
     que se han generado y este id es el que se va ser visible 
     en este caso el visible va ser el que se este ejecutado
     */
    function mostrarTabla(id)
    {
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
    /*agregamos al grafo de ambientes un nuevo nodo donde la damos un id 
     y de creamos un aristas desde donde lo crearon
     **/
    function agregarNodo(id, label, from, to) {
        nodes.add({id: id, label: label});
        try {
            edges.update({
                id: id,
                from: from,
                to: to,
            });
        } catch (err) {
            alert(err);
        }

    }

    /*Esta funcion me crea la estructura de los ambientes en forma de grafo
     como inicio le mandamos un nodo que es el principal del algotrimo de entrada 
     */
    function arbol()
    {
        // creamos  un arreglo de nodos 
        nodes = new vis.DataSet([
            {id: 1, label: 'Principal', color: '#FFFF00'},
        ]);
        edges = new vis.DataSet();
        var container = document.getElementById('mynetwork');
        var data = {
            nodes: nodes,
            edges: edges
        };
        var options = {};
        network = new vis.Network(container, data, options);
    }




    /*pintarNodo nos pide un id que es el id del nodo de la estructura del arbol de ejecucion
     y un color que es el color que se va actualizar ese nodo*/
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

    /*esta funcion se encarga de decir si ese id se encuentra como hijo en la el arreglo 
     de hijos que le ingresa tambiem como parametro como para ver si ya esta en la tabla de
     variables del padre de estos hijos
     */
    function contiene(hijos, id)
    {
        var ban = false;
        var i;
        for (i = 0; i < hijos.length; i++) {
            if (hijos[i].id == id)
            {
                ban = true
                break;
            }
        }
        return ban
    }

    /*nos entra un arreglo con todo el codigo fuente de la rutina que llama a una subrutina 
     que le entra como parametro nombre y el indice es desde donde empieza ese llamado en la 
     estructura log que envio el servidor
     */
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
        objetoAux[0].valor = null
        objetoAux[objetoAux.length - 1].valor = null
        return objetoAux
    }


    /*
     Me ingresa un codigo fuente splitiado por salto de lineas me organiza en la variable subtina 
     que es un hash donde la llave es el nombre de la subrtuina y el valor 
     tenemos el codigo de esa subrutina
     */
    function sacarSubRutinas(codigo)
    {
        var codigo = editAreaLoader.getValue("textarea_" + actual + "");
        var indice1 = codigo.indexOf("declare");
        var indice2 = codigo.indexOf("enddeclare");
        codigo = codigo.substring(indice1 + 7, indice2);
        codigo = codigo.split("\n");
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

    /*Asiganamos al boton run la funcion correspondiente que este se encarga 
     de coger el codigo funene y mandarlo al servidor para que este se encarge de 
     traducirlo
     */
    $("#run").on("click", run);
    function run()
    {
        var texto = editAreaLoader.getValue("textarea_" + actual + "");
        $.post("Controladora", {
            operacion: "run",
            texto: texto
        }, function (data) {
            removeAllChilds("Errores");
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
        inicioStep();
    }
    /*funcion que recibe como parametro un id del html donde se encarga de eliminar 
     todos sus hijos de este id que entra como parametro
     */
    function removeAllChilds(a)
    {
        var a = document.getElementById(a);
        while (a.hasChildNodes()) {
            a.removeChild(a.firstChild);
        }
    }





    //me imprime en pantalla las subrutinas que hay en el codigo de entrada
    function mostrarSubrutinas()
    {
        for (x in subrutinas)
        {
            console.log(x + '-> ' + subrutinas[x])
        }
    }
    $("#breakpoint").on("click", MostarPuntosSeleccionados);
    function MostarPuntosSeleccionados()
    {
        var beackPoint1 = "";
        for (x in window.parent.frames[actual - 1].lineas)
        {
            beackPoint1 = beackPoint1 + window.parent.frames[actual - 1].lineas[x];
        }
        alert(beackPoint1);
    }

    $('#tabla').click(function (e) {
        alert("hola")
        $(".frames").hide();
        var id = e.target.id;
        actual = id;
        $("#frame_textarea_" + id + "").show();
    });
});


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


/* 
 este crea una nueva area de trabajo con la informacion que le entra  como paramentro
 colocandole de id al textarea dependiendo como va el contadorde subrutinas
 */
function crearAmbiente(mensaje) {
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

}



function activar()
{
    $('li.noactivo').click(function () {
        $('li.noactivo').removeClass("active");
        $(this).addClass("active");
    });
}












