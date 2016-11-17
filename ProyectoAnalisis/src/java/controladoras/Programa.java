package controladoras;
import com.google.gson.Gson;
import java.util.*;
public class Programa {
LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();
String resultado="";
public void oe (int x) {
agregar("oe","subrutina",2);
agregar("x",Integer.toString(x),2);
x=x+5;
agregar("x",Integer.toString(x),4);
int z=34*2;
agregar("z",Integer.toString(z),5);
agregar("oe","subrutina",6);

}

public void principal(){
try{
int [] a = {2,1,20,17};
agregarLista("a",a,9);
int lon = a.length;
agregar("lon",Integer.toString(lon),10);
int i = 1;
agregar("i",Integer.toString(i),11);
agregar(null,null,12);
for(i=1;i<lon;i++){
agregar("i",Integer.toString(i),12);
int x=a[i];
agregar("x",Integer.toString(x),14);
int j=i-1;
agregar("j",Integer.toString(j),15);
agregar(null,null,16);
while(j >= 0 && x < a[j]){
agregar(null,null,16);
int temp=j+1;
agregar("temp",Integer.toString(temp),18);
a[temp]=a[j];
agregarLista("a",a,19);
j=j-1;
agregar("j",Integer.toString(j),20);
}
int aux=j+1;
agregar("aux",Integer.toString(aux),22);
a[aux]=x;
agregarLista("a",a,23);
oe(4);

agregar(null,null,24);
int z = 0;
agregar("z",Integer.toString(z),25);
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

