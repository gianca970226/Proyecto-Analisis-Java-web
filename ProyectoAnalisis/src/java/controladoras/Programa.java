package controladoras;
import com.google.gson.Gson;
import java.util.*;
public class Programa {
LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();
String resultado="";public void principal(){
try{
int [] a = {5,2,20,17};
agregarLista("a",a,4);
int lon = a.length;
agregar("lon",Integer.toString(lon),5);
int i = 1;
agregar("i",Integer.toString(i),6);
agregar(null,null,7);
for(i=1;i<lon;i++){
agregar("i",Integer.toString(i),7);
int x=a[i];
agregar("x",Integer.toString(x),9);
int j=i-1;
agregar("j",Integer.toString(j),10);
agregar(null,null,11);
while(j >= 0 && x < a[j]){
agregar(null,null,11);
int temp=j+1;
agregar("temp",Integer.toString(temp),13);
a[temp]=a[j];
agregarLista("a",a,14);
j=j-1;
agregar("j",Integer.toString(j),15);
}
int aux=j+1;
agregar("aux",Integer.toString(aux),17);
a[aux]=x;
agregarLista("a",a,18);
int z = 0;
agregar("z",Integer.toString(z),19);
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

