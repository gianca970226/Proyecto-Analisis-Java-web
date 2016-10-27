package controladoras;
import com.google.gson.Gson;import java.util.LinkedList;public class Programa {
LinkedList<EstructuraLog> log=new LinkedList<EstructuraLog>();public void principal() {
String oe1 = "234";
agregar("oe1",oe1,2);
int oe2 = 12;
agregar("oe2",Integer.toString(oe2),3);
int oe3 = oe2;
agregar("oe3",Integer.toString(oe3),4);
int oe8 = 12;
agregar("oe8",Integer.toString(oe8),5);
int oe4 = 15;
agregar("oe4",Integer.toString(oe4),6);
int oe5 = 12;
agregar("oe5",Integer.toString(oe5),7);
int oe6 = 3344;
agregar("oe6",Integer.toString(oe6),8);

 while(oe3 > oe2){
String x = "12";
agregar("x",x,11);

}


}
public void  agregar(String x,String valor,int linea)
{
log.add(new EstructuraLog(x, valor,linea));
}
public String mostrar()
{  
    Gson json = new Gson();
    String resultados="{\"Variables\":";
    resultados += json.toJson(log)+"}";
    
    return resultados;
}}

