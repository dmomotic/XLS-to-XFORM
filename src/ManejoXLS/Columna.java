package ManejoXLS;

import java.util.ArrayList;

public class Columna {
   private TipoColumna tipo;
   private ArrayList<String> celdas;
   
   public Columna(TipoColumna tipo){
       this.tipo = tipo;
       celdas = new ArrayList<>();
   }
   
   public void addCelda(String celda){
       this.celdas.add(celda);
   }
   
   public void setCelda(int pos, String celda){
       celdas.remove(pos-1);
       celdas.add(pos-1, celda);
   }
   
   public ArrayList<String> getCeldas(){
       return this.celdas;
   }
   
   public TipoColumna getTipo(){
       return this.tipo;
   }
   
   public static enum TipoColumna{
       /*Opciones para hoja Encuesta*/
       TIPO,
       IDPREGUNTA,
       ETIQUETA,
       SUGERIR,
       CODIGO_PRE,
       CODIGO_POST,
       RESTRINGIR,
       RESTRINGIRMSN,
       REQUERIDO,
       REQUERIDOMSN,
       PREDETERMINADO,
       APLICABLE,
       LECTURA,
       CALCULO,
       REPETICION,
       MULTIMEDIA,
       APARIENCIA,
       PARAMETRO,
       /*Opciones para hoja Opciones*/
       NOMBRE_LISTA,
       NOMBRE,
       //ETIQUETA,
       //MULTIMEDIA
       /*Opciones para hoja configuraciones*/
       TITULO_FORMULARIO,
       IDFORM,
       ESTILO,
       IMPORTAR,
       CODIGO_PRINCIPAL,
       CODIGO_GLOBAL
   }
}
