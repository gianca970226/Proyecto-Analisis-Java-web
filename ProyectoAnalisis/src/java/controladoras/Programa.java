package controladoras;
import com.google.gson.Gson;
import java.util.*;
public class Programa {
LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();
String resultado="";
public int funcion1 (int y) {
agregar("funcion1","subrutina",2);
agregar("y",Integer.toString(y),2);
int s=y*5;
agregar("s",Integer.toString(s),4);
int x = 4;
agregar("x",Integer.toString(x),5);
funcion(5);

agregar(null,null,6);
agregar("funcion1","subrutina",7);
return s;
}


public void funcion (int y) {
agregar("funcion","subrutina",9);
agregar("y",Integer.toString(y),9);
int s=y*5;
agregar("s",Integer.toString(s),11);
agregar("funcion","subrutina",12);

}

public void principal(){
try{
int x = 2;
agregar("x",Integer.toString(x),15);
int [] a = {2,8,10};
agregarLista("a",a,16);
int [] b = {10,5};
agregarLista("b",b,17);
int y= b[1]*a[0];
agregar("y",Integer.toString(y),18);
a[1] = b[1]*a[0];
agregarLista("a",a,19);
int z=x*a[0];
agregar("z",Integer.toString(z),20);
Stack<Object>f= new Stack<Object>();
agregarPila("f",f,21);
f.add(x);
agregarPila("f",f,22);
int i=Integer.parseInt(f.pop().toString());
agregar("i",Integer.toString(i),23);
int p=(int) Math.floor(y/z);
agregar("p",Integer.toString(p),24);
a[1] = a[1]+z;
agregarLista("a",a,25);
a[x]=z;
agregarLista("a",a,26);
a[x]=z*5;
agregarLista("a",a,27);
a[1]=z+y;
agregarLista("a",a,28);
int q = a.length;
agregar("q",Integer.toString(q),29);
agregar(null,null,30);
while(x < z){
agregar(null,null,30);
x=x+1;
agregar("x",Integer.toString(x),32);
}
a[2]=funcion1(x);
agregarLista("a",a,34);
agregar(null,null,35);
if(x < z){
agregar(null,null,35);
int t = x;
agregar("t",Integer.toString(t),37);
t=10*5;
agregar("t",Integer.toString(t),38);
}
else{
agregar(null,null,40);
int t = x;
agregar("t",Integer.toString(t),42);
}

}catch(Exception e)
{
resultado=e.toString();}
}
public String getResultado()
{
return this.resultado;}
public void  agregar(String x,String valor,int linea)
{
log.add(new EstructuraLog(x, valor,linea));
}
public void  agregarLista(String x,int[] lista,int linea)
{
int []aux=new int[lista.length];
System.arraycopy(lista, 0, aux,0, lista.length);
log.add(new EstructuraLog(x, aux, linea));
}
public void  agregarPila(String x,Stack<Object>pila,int linea)
{
Object []aux=pila.toArray();
log.add(new EstructuraLog(x, aux,linea));
}
public void  agregarCola(String x,Queue<Object>cola,int linea)
{
Object []aux=cola.toArray();
log.add(new EstructuraLog(x, aux,linea));
}
public String mostrar()
{
Gson json = new Gson();
String resultados="{\"Variables\":";
resultados += json.toJson(log)+"}";
return resultados;
}
}

