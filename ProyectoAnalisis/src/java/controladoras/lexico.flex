/*Seccion de codigo de usuario*/
package controladoras;

import java_cup.runtime.*;
import java.io.Reader;
import controladoras.GenerarJava;



/* Seccion de opciones y declaraciones de JFlex */
%% //inicio de opciones
   
/* ------ Seccion de opciones y declaraciones de JFlex -------------- */  
   
/* 
    Cambiamos el nombre de la clase del analizador a Lexer
*/
%class AnalizadorLexico

/*
    Activar el contador de lineas, variable yyline
    Activar el contador de columna, variable yycolumn
*/
%line
%column

%eofval{
GenerarJava.escribir();
System.out.println("FIN DEL ARCHIVO");
return null;
%eofval}
    
/* 
   Activamos la compatibilidad con Java CUP para analizadores
   sintacticos(parser)
*/
%cup
   
/*
    Declaraciones

    El codigo entre %{  y %} sera copiado integramente en el 
    analizador generado.
*/
%{
    /*  Generamos un java_cup.Symbol para guardar el tipo de token 
        encontrado */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Generamos un Symbol para el tipo de token encontrado 
       junto con su valor */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
//Creamos un contador para los tokens
//%init{
//    contador = 0;
//    tokens = new ArrayList<Yytoken>();
//%init}
//Fin de opciones

//Expresiones regulares
//Declaraciones
LETRA=[A-Za-z]
DIGITO=[0-9]
ALFANUMERICO={LETRA}|{DIGITO}
NUMERO=({DIGITO})+
IDENTIFICADOR={LETRA}({ALFANUMERICO})*
CADENA="'".({ALFANUMERICO})*."'"
SALTO = \r|\n|\r\n
/* Espacio es un espacio en blanco, tabulador \t, salto de linea 
    o avance de pagina \f, normalmente son ignorados */
ESPACIO     = {SALTO} | [ \t\f]
//fin declaraciones

/* Seccion de reglas lexicas */
%% 
//Regla     {Acciones}

<YYINITIAL> {
"BEGIN"   {
    return symbol(sym.BEGIN);
}
"END"   {
    return symbol(sym.END);
}
"<-" {
    return symbol(sym.ASIGNACION);
}

{IDENTIFICADOR}   {
    return symbol(sym.IDENTIFICADOR, new String(yytext()));
}
{NUMERO}    {
    return symbol(sym.NUMERO, new Integer(yytext()));
}
{CADENA}    {
    return symbol(sym.CADENA , new String(yytext()));
}


/* No hace nada si encuentra el espacio en blanco */
{ESPACIO}       { /* ignora el espacio */ }

}

[^]                    { throw new Error("Caracter ilegal <"+yytext()+"> en la linea->"+yyline+"columna->"+yycolumn); }
