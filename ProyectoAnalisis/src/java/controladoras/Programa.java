package controladoras;
import com.google.gson.Gson;
import java.util.LinkedList;
public class Programa {
LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();

public int funcion1 (int y) {
agregar("funcion1","subrutina",2);
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
int s=y*5;
agregar("s",Integer.toString(s),11);
agregar("funcion","subrutina",12);

}

public void principal() {
String oe1 = "234";
agregar("oe1",oe1,15);
int x = 2;
agregar("x",Integer.toString(x),16);
int [] a = {2,8,10};
agregarLista("a",a,17);
int y=5*a[0];
agregar("y",Integer.toString(y),18);
int z=x*a[0];
agregar("z",Integer.toString(z),19);
a[1]=z;
agregarLista("a",a,20);
a[x]=z;
agregarLista("a",a,21);
a[x]=z*5;
agregarLista("a",a,22);
a[1]=z+y;
agregarLista("a",a,23);
a[x]=z+a[1];
agregarLista("a",a,24);
while(x < z){
agregar(null,null,25);
x=x+1;
agregar("x",Integer.toString(x),27);
}
a[2]=funcion1(x);
agregarLista("a",a,29);
if(x < z){
agregar(null,null,30);
int t = x;
agregar("t",Integer.toString(t),32);
t=10*5;
agregar("t",Integer.toString(t),33);
}
else{
agregar(null,null,35);
int t = x;
agregar("t",Integer.toString(t),37);
}

}
public void  agregar(String x,String valor,int linea)
{
log.add(new EstructuraLog(x, valor,linea));
}
public void  agregarLista(String x,int[] lista,int linea)
{
log.add(new EstructuraLog(x, lista, linea));
}
public String mostrar()
{  
    Gson json = new Gson();
    String resultados="{\"Variables\":";
    resultados += json.toJson(log)+"}";
    
    return resultados;
}}

