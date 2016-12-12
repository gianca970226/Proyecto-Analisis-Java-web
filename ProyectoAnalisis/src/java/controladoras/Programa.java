package controladoras;
import com.google.gson.Gson;
import java.util.*;
public class Programa {
LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();
String resultado="";
public void oe (int x,int y) {
agregar("oe","subrutina",2);
agregar("x",Integer.toString(x),2);
agregar("y",Integer.toString(y),2);
x=x+5;
agregar("x",Integer.toString(x),4);
int z=34*2;
agregar("z",Integer.toString(z),5);
oe1(5);

agregar(null,null,6);
agregar("oe","subrutina",7);

}


public void oe1 (int x) {
agregar("oe1","subrutina",8);
agregar("x",Integer.toString(x),8);
x=x+5;
agregar("x",Integer.toString(x),10);
int z=34*2;
agregar("z",Integer.toString(z),11);
agregar("oe1","subrutina",12);

}

public void principal(){
try{
int [] a = {2,1,20,17};
agregarLista("a",a,15);
int lon = a.length;
agregar("lon",Integer.toString(lon),16);
int i = 1;
agregar("i",Integer.toString(i),17);
for(i=1;i<lon;i++){
agregar("i",Integer.toString(i),18);
int x=a[i];
agregar("x",Integer.toString(x),20);
int j=i-1;
agregar("j",Integer.toString(j),21);
while(j >= 0 && x < a[j]){
agregar(null,null,22);
int temp=j+1;
agregar("temp",Integer.toString(temp),24);
a[temp]=a[j];
agregarLista("a",a,25);
j=j-1;
agregar("j",Integer.toString(j),26);
}
agregar(null,null,22);
int aux=j+1;
agregar("aux",Integer.toString(aux),28);
a[aux]=x;
agregarLista("a",a,29);
int z = 0;
agregar("z",Integer.toString(z),30);
oe1(5);

agregar(null,null,31);
}
agregar(null,null,18);

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

