/*Seccion de codigo de usuario*/
package controladoras;
//import LeguajesCompiler.Lexico.Yytoken;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java_cup.runtime.*;



/* Seccion de opciones y declaraciones de JFlex */
%% //inicio de opciones
//Cambiamos el nombre la funcion para el siguiente token por nextToken
%function nextToken
//Clase publica
%public
//Cambiamos el nombre de la clase del analizador
%class AnalizadorLexico
//Agregamos soporte a unicode
%unicode
//Codigo java
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
//    public AnalizadorLexico() {
//    }	
    private int contador;
//    private ArrayList<Yytoken> tokens;
//
//	private void writeOutputFile() throws IOException{
//			String filename = "file.out";
//			BufferedWriter out = new BufferedWriter(
//				new FileWriter(filename));
//            System.out.println("\n*** Tokens guardados en archivo ***\n");
//			for(Yytoken t: this.tokens){
//				System.out.println(t);
//				out.write(t + "\n");
//			}
//			out.close();
//	}
//	public ArrayList<Yytoken> getTokens() {
//        return tokens;
//    }
%}
//Creamos un contador para los tokens
%init{
    contador = 0;
//    tokens = new ArrayList<Yytoken>();
%init}
//Cuando se alcanza el fin del archivo de entrada
%eof{
//    try{
//            this.writeOutputFile();
//
//    }catch(IOException ioe){
//            ioe.printStackTrace();
//    }
%eof}
//Activar el contador de lineas, variable yyline
%line
//Activar el contador de columna, variable yycolumn
%column
//Activar para trabajar con CUP
%cup
//Fin de opciones

//Expresiones regulares
//Declaraciones
letra=[A-Za-z]
digito=[0-9]
alfanumerico={letra}|{digito}
numero=({digito})+
identificador={letra}({alfanumerico})*
comentario="#".({alfanumerico})*
cadena="'".{{alfanumerico}}*."'"
//fin declaraciones

/* Seccion de reglas lexicas */
%% 
//Regla     {Acciones}

<YYINITIAL> {
{comentario}   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"comentario",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.comentario);
    //return t;
}
{identificador}   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"identificador",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.identificador, new String(yytext()));
	//return t;
}
{numero}    {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"numero",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.numero , new Integer(yytext()));
    //return t;
}
{alfanumerico}    {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"alfanumerico",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.alfanumerico , new String(yytext()));
    //return t;
}
{cadena}    {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"cadena",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.cadena , new String(yytext()));
    //return t;
}
"return"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"return",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.return);
    //return t;
}
";"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"coma",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.coma);
    //return t;
}

"while"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"while",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.WHILE);
    //return t;
}
"for"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"for",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.for);
	//return t;
}
"to"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"to",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.to);
    //return t;
}
"do"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"do",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.do);
    //return t;
}
"repeat"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"repeat",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.repeat);
	//return t;
}
"until"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"until",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.until);
    //return t;
}
"if"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"if",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.if);
	//return t;
}
"then"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"then",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.then);
	//return t;
}
"else"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"else",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.else);
    //return t;
}
"begin"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"begin",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.begin);
    //return t;
}
"end"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"end",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.end);
	//return t;
}
"length"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"length",yyline,yycolumn);
    //tokens.add(t);
	return symbol(sym.length);
    //return t;
}
"null"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"null",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.null);
	//return t;
}
"procedure"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"procedure",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.procedure);
	//return t;
}
"function"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"function",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.function);
	//return t;
}
"en"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"en",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.en);
	//return t;
}
"es"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"es",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.es);
	//return t;
}
"call"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"call",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.call);
	//return t;
}
"and"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"and",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.and);
	//return t;
}
"or"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"or",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.or);
	//return t;
}
"not"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"not",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.not);
	//return t;
}
"mod"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"mod",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.mod);
	//return t;
}
"div"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"div",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.div);
	//return t;
}
"true"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"true",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.true);
	//return t;
}
"false"   {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"false",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.false);
	//return t;
}

"pa1"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"pia1",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.pia1);
	//return t;
}
"pa2"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"pa2",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.pa2);
	//return t;
}
"pb1"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"pb1",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.pb1);
	//return t;
}
"pb2"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"pb2",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.pb2);
	//return t;
}
"<-"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"asignacion",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.asignacion);
	//return t;
}
"<"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"menor",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.menor);
	//return t;
}

">"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),">",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.>);
	//return t;
}
"<="  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(), "menorigual",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.menorigual);
	//return t;
}
">="  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"mayorigual",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.mayorigual);
	//return t;
}
"="  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"igual",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.igual);
	//return t;
}
"!="  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"diferente",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.diferente);
	//return t;
}
"+"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"suma",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.suma);
	//return t;
}
"*"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"multiplicacion",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.multiplicacion);
	return t;
}
"-"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"resta",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.resta);
	//return t;
}
"/"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"division",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.division);
	//return t;
}
"["  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"corcheteabierto",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.corcheteabierto);
	//return t;
}
"]"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"corchetecerrado",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.corchetecerrado);
	//return t;
}
"("  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"parentesisabierto",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.parentesisabierto);
	//return t;
}
")"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"parentesiscerrado",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.parentesiscerrado);
	//return t;
}
".."  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"dospuntos",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.dospuntos);
	//return t;
}
"."  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"unpunto",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.unpunto);
	//return t;
}
/*Los metacaracteres es necesario usar la secuencia de escape */
"\^"  {
    contador++;
    //Yytoken t = new Yytoken(contador,yytext(),"potencia",yyline,yycolumn);
    //tokens.add(t);
    return symbol(sym.potencia);
	//return t;
}
(" ") {}

[\t\r\n\f] {}

}

[^]                    { throw new Error("Caracter ilegal <"+yytext()+"> en la linea->"+yyline+"columna->"+yycolumn); }
