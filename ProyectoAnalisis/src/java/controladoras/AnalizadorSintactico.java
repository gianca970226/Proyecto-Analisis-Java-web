
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Wed Oct 12 23:17:23 COT 2016
//----------------------------------------------------

package controladoras;

import java_cup.runtime.*;
import java.io.FileReader;
import controladoras.Compilador;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Wed Oct 12 23:17:23 COT 2016
  */
public class AnalizadorSintactico extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public AnalizadorSintactico() {super();}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalizadorSintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\010\000\002\002\005\000\002\002\004\000\002\003" +
    "\005\000\002\004\004\000\002\004\002\000\002\005\005" +
    "\000\002\005\005\000\002\005\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\020\000\004\007\005\001\002\000\004\002\022\001" +
    "\002\000\004\007\007\001\002\000\004\010\021\001\002" +
    "\000\006\004\010\010\ufffd\001\002\000\004\011\015\001" +
    "\002\000\004\010\014\001\002\000\006\004\010\010\ufffd" +
    "\001\002\000\004\010\ufffe\001\002\000\004\010\uffff\001" +
    "\002\000\010\004\017\005\016\006\020\001\002\000\006" +
    "\004\ufffc\010\ufffc\001\002\000\006\004\ufffa\010\ufffa\001" +
    "\002\000\006\004\ufffb\010\ufffb\001\002\000\004\002\001" +
    "\001\002\000\004\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\020\000\004\002\003\001\001\000\002\001\001\000" +
    "\004\003\005\001\001\000\002\001\001\000\006\004\010" +
    "\005\011\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\004\012\005\011\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalizadorSintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalizadorSintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalizadorSintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    
    /* Reporte de error encontrado. */
    public void report_error(String message, Object info) {
        StringBuilder m = new StringBuilder("Error");
        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
            if (s.left >= 0) {                
                m.append(" in line "+(s.left+1));
                if (s.right >= 0)
                    m.append(", column "+(s.right+1));
            }
        }
        m.append(" : "+message);
        System.err.println(m);
    }
   
    /* Cuando se encuentra un error de donde el sistema no puede
        recuperarse, se lanza un error fatal. Se despliega el mensaje
        de error y se finaliza la ejecucion. */
    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
        //System.exit(1);
    }

    /* Metodo main para garantizar la ejecucion del analizador
       lexico y sintactico, ademas que se pase como parametro la tabla
       de simbolos correspondiente. */
    public static void main(String[] args){
        try {
            AnalizadorSintactico asin = new AnalizadorSintactico(
                    new AnalizadorLexico( new FileReader(args[0])));
            Object result = asin.parse().value;
            System.out.println("\n*** Resultados finales ***");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$AnalizadorSintactico$actions {


    public Compilador compilador=new Compilador();

  private final AnalizadorSintactico parser;

  /** Constructor */
  CUP$AnalizadorSintactico$actions(AnalizadorSintactico parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$AnalizadorSintactico$do_action(
    int                        CUP$AnalizadorSintactico$act_num,
    java_cup.runtime.lr_parser CUP$AnalizadorSintactico$parser,
    java.util.Stack            CUP$AnalizadorSintactico$stack,
    int                        CUP$AnalizadorSintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalizadorSintactico$result;

      /* select the action based on the action number */
      switch (CUP$AnalizadorSintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // exp_variable ::= IDENTIFICADOR ASIGNACION IDENTIFICADOR 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    compilador.findAddVariable(n, v);
    compilador.showVariables();

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_variable",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // exp_variable ::= IDENTIFICADOR ASIGNACION CADENA 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    compilador.addVariable(i,c);
    compilador.showVariables();

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_variable",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // exp_variable ::= IDENTIFICADOR ASIGNACION NUMERO 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)).value;
		int nleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.peek()).value;
		
    compilador.addVariable(i, n);
    compilador.showVariables();

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_variable",3, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // exp_e ::= 
            {
              Object RESULT =null;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_e",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // exp_e ::= exp_variable exp_e 
            {
              Object RESULT =null;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_e",2, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // exp_a ::= BEGIN exp_e END 
            {
              Object RESULT =null;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_a",1, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= exp_s EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)).value;
		RESULT = start_val;
              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-1)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalizadorSintactico$parser.done_parsing();
          return CUP$AnalizadorSintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // exp_s ::= BEGIN exp_a END 
            {
              Object RESULT =null;

              CUP$AnalizadorSintactico$result = parser.getSymbolFactory().newSymbol("exp_s",0, ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.elementAt(CUP$AnalizadorSintactico$top-2)), ((java_cup.runtime.Symbol)CUP$AnalizadorSintactico$stack.peek()), RESULT);
            }
          return CUP$AnalizadorSintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}
