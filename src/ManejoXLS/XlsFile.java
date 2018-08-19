package ManejoXLS;


import Analizador.CError;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author damon
 */
public class XlsFile {
    
    private List cellData;
    private ArrayList<Columna> columnas;
    private ArrayList<CError> errores;
    
    public XlsFile(String hoja){
        columnas = new ArrayList<>();
        errores = new ArrayList<>();
        switch(hoja.toLowerCase()){
            case "encuesta":
                columnas.add(new Columna(Columna.TipoColumna.TIPO));
                columnas.add(new Columna(Columna.TipoColumna.IDPREGUNTA));
                columnas.add(new Columna(Columna.TipoColumna.ETIQUETA));
                columnas.add(new Columna(Columna.TipoColumna.SUGERIR));
                columnas.add(new Columna(Columna.TipoColumna.CODIGO_PRE));
                columnas.add(new Columna(Columna.TipoColumna.CODIGO_POST));
                columnas.add(new Columna(Columna.TipoColumna.RESTRINGIR));
                columnas.add(new Columna(Columna.TipoColumna.RESTRINGIRMSN));
                columnas.add(new Columna(Columna.TipoColumna.REQUERIDO));
                columnas.add(new Columna(Columna.TipoColumna.REQUERIDOMSN));
                columnas.add(new Columna(Columna.TipoColumna.PREDETERMINADO));
                columnas.add(new Columna(Columna.TipoColumna.APLICABLE));
                columnas.add(new Columna(Columna.TipoColumna.LECTURA));
                columnas.add(new Columna(Columna.TipoColumna.CALCULO));
                columnas.add(new Columna(Columna.TipoColumna.REPETICION));
                columnas.add(new Columna(Columna.TipoColumna.MULTIMEDIA));
                columnas.add(new Columna(Columna.TipoColumna.APARIENCIA));
                columnas.add(new Columna(Columna.TipoColumna.PARAMETRO));
                break;
            case "opciones":
                columnas.add(new Columna(Columna.TipoColumna.NOMBRE_LISTA));
                columnas.add(new Columna(Columna.TipoColumna.NOMBRE));
                columnas.add(new Columna(Columna.TipoColumna.ETIQUETA));
                columnas.add(new Columna(Columna.TipoColumna.MULTIMEDIA));
                break;
            case "configuracion":
                columnas.add(new Columna(Columna.TipoColumna.TITULO_FORMULARIO));
                columnas.add(new Columna(Columna.TipoColumna.IDFORM));
                columnas.add(new Columna(Columna.TipoColumna.ESTILO));
                columnas.add(new Columna(Columna.TipoColumna.IMPORTAR));
                columnas.add(new Columna(Columna.TipoColumna.CODIGO_PRINCIPAL));
                columnas.add(new Columna(Columna.TipoColumna.CODIGO_GLOBAL));
                break;
        }
        
        
        
    }
       
    public XlsFile(File file){
        List cellDataAux = new ArrayList();
        try {
            FileInputStream fileInpuntStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInpuntStream);
            //Obtenemos la hoja solicitada
            HSSFSheet hssfSheet = workbook.getSheetAt(0);
            //Creamos nuestro iterador
            Iterator rowIterator = hssfSheet.rowIterator();
            //Recorrido de la iteracion
            while (rowIterator.hasNext()) {
                //Capturamos datos de las celdas en la fila 0
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                //Almacenamos datos en el Itedor
                Iterator iterator = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                //Recorremos los datos de cada fila
                while(iterator.hasNext()){
                    //Guardamos los datos de cada celda en hssfCell
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    //Y los datos del hssfCell los almacenamos en cellTemp
                    cellTemp.add(hssfCell);
                }
                //Los datos almacenados en cellTemp los guardamos en cellData
                cellDataAux.add(cellTemp);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.cellData = cellDataAux;
    }
    
    public void obtener(){
        for(int i=0; i < this.cellData.size(); i++){
            //Obtenemos los datos del cellData y los almacenamos en cellTempList
            List cellTempList = (List) this.cellData.get(i);
            for(int j=0; j < cellTempList.size(); j++){
                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
                //Capturamos los datos
                String stringCellValue = hssfCell.toString();
                System.out.print(stringCellValue+" ");
            }
            System.out.print("  Total de celdas: " + cellTempList.size());
            System.out.println();
        }   
        
    }
    
    public void readXLSFileWithBlankCellsEncuesta(File file) {
        try {
            InputStream ExcelFileToRead = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
           
            //Capturo la hoja con el nombre enviado en el parametro
            HSSFSheet sheet = wb.getSheet("Encuesta");
            if(sheet == null){
                errores.add(new CError("No existe la hoja: ENCUESTA" , CError.Tipo.GENERAL));
                return;
            }    
            HSSFRow row;
            HSSFCell cell;
            Iterator rows = sheet.rowIterator();
            
            int auxFila = 0;
            int pos0, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10, pos11, pos12, pos13, pos14, pos15, pos16, pos17;
            pos0 = pos1 = pos2 = pos3 = pos4 = pos5 = pos6 = pos7 = pos8 = pos9 = pos10 = pos11 = pos12 = pos13 = pos14 = pos15 = pos16 = pos17 = 0;
            
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                
                //For para recorrer las celdas de las filas
                for(int i=0; i<sheet.getRow(0).getLastCellNum(); i++) {
                    cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    
                    //Si estoy recorriendo la fila0
                    if (auxFila==0){
                        
                        // <editor-fold defaultstate="collapsed" desc="Verificacion de titulos de columnas">    
                        
                        //Valido los encabezados y guardo la posicion en la que vienen en el archivo xls
                        switch(cell.toString().trim().toUpperCase()){
                            case "TIPO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos0 != 0){
                                    errores.add(new CError("La columna TIPO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos0 = i;
                                }
                                break;
                            case "IDPREGUNTA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos1 != 0){
                                    errores.add(new CError("La columna IDPREGUNTA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos1 = i;
                                }
                                break;
                            case "ETIQUETA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos2 != 0){
                                    errores.add(new CError("La columna ETIQUETA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos2 = i;
                                }
                                break;
                            case "SUGERIR":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos3 != 0){
                                    errores.add(new CError("La columna SUGERIR esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos3 = i;
                                }
                                break;
                            case "CODIGO_PRE":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos4 != 0){
                                    errores.add(new CError("La columna CODIGO_PRE esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos4 = i;
                                }
                                break;
                            case "CODIGO_POST":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos5 != 0){
                                    errores.add(new CError("La columna CODIGO_POST esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos5 = i;
                                }
                                break;
                            case "RESTRINGIR":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos6 != 0){
                                    errores.add(new CError("La columna RESTRINGIR esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos6 = i;
                                }
                                break;
                            case "RESTRINGIRMSN":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos7 != 0){
                                    errores.add(new CError("La columna RESTRINGIRMSN esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos7 = i;
                                }
                                break;
                            case "REQUERIDO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos8 != 0){
                                    errores.add(new CError("La columna REQUERIDO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos8 = i;
                                }
                                break;
                            case "REQUERIDOMSN":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos9 != 0){
                                    errores.add(new CError("La columna REQUERIDOMSN esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos9 = i;
                                }
                                break;
                            case "PREDETERMINADO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos10 != 0){
                                    errores.add(new CError("La columna PREDETERMINADO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos10 = i;
                                }
                                break;
                            case "APLICABLE":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos11 != 0){
                                    errores.add(new CError("La columna APLICABLE esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos11 = i;
                                }
                                break;
                            case "LECTURA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos12 != 0){
                                    errores.add(new CError("La columna LECTURA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos12 = i;
                                }
                                break;
                            case "CALCULO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos13 != 0){
                                    errores.add(new CError("La columna CALCULO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos13 = i;
                                }
                                break;
                            case "REPETICION":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos14 != 0){
                                    errores.add(new CError("La columna REPETICION esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos14 = i;
                                }
                                break;
                            case "MULTIMEDIA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos15 != 0){
                                    errores.add(new CError("La columna MULTIMEDIA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos15 = i;
                                }
                                break;
                            case "APARIENCIA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos16 != 0){
                                    errores.add(new CError("La columna APARIENCIA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos16 = i;
                                }
                                break;
                            case "PARAMETRO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos17 != 0){
                                    errores.add(new CError("La columna PARAMETRO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos17 = i;
                                }
                                break; 
                            case "":
                                break;

                            default:
                                //Cualquier otra cosa es error
                                errores.add(new CError("Nombre de columna invalida en la posicion " + i, CError.Tipo.GENERAL));
                            }
                        // </editor-fold>
                        
                    }
                    //Si ya estoy recorriendo a partir de la fila 1 en adelante
                    else{
                        // <editor-fold defaultstate="collapsed" desc="Capturo el contenido de las celdas y lo guardo">
                        
                        String valCelda = (cell.toString().isEmpty() ? "null" : cell.toString());

                        if(i==pos0){
                            columnas.get(0).addCelda(valCelda);
                        }
                        else if(i==pos1){
                            columnas.get(1).addCelda(valCelda);
                        }
                        else if(i==pos2){
                            columnas.get(2).addCelda(valCelda);
                        }
                        else if(i==pos3){
                            columnas.get(3).addCelda(valCelda);
                        }
                        else if(i==pos4){
                            columnas.get(4).addCelda(valCelda);
                        }
                        else if(i==pos5){
                            columnas.get(5).addCelda(valCelda);
                        }
                        else if(i==pos6){
                            columnas.get(6).addCelda(valCelda);
                        }
                        else if(i==pos7){
                            columnas.get(7).addCelda(valCelda);
                        }
                        else if(i==pos8){
                            columnas.get(8).addCelda(valCelda);
                        }
                        else if(i==pos9){
                            columnas.get(9).addCelda(valCelda);
                        }
                        else if(i==pos10){
                            columnas.get(10).addCelda(valCelda);
                        }
                        else if(i==pos11){
                            columnas.get(11).addCelda(valCelda);
                        }
                        else if(i==pos12){
                            columnas.get(12).addCelda(valCelda);
                        }
                        else if(i==pos13){
                            columnas.get(13).addCelda(valCelda);
                        }
                        else if(i==pos14){
                            columnas.get(14).addCelda(valCelda);
                        }
                        else if(i==pos15){
                            columnas.get(15).addCelda(valCelda);
                        }
                        else if(i==pos16){
                            columnas.get(16).addCelda(valCelda);
                        }
                        else if(i==pos17){
                            columnas.get(17).addCelda(valCelda);
                        }
                        
                    // </editor-fold>
                    }
                    //System.out.print(cell.toString()+" ");
                    
                }
                System.out.println();
                auxFila++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void readXLSFileWithBlankCellsOpciones(File file) {
        try {
            InputStream ExcelFileToRead = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
           
            //Capturo la hoja con el nombre enviado en el parametro
            HSSFSheet sheet = wb.getSheet("Opciones");
            if(sheet == null){
                errores.add(new CError("No existe la hoja: OPCIONES" , CError.Tipo.GENERAL));
                return;
            }    
            HSSFRow row;
            HSSFCell cell;
            Iterator rows = sheet.rowIterator();
            
            int auxFila = 0;
            int pos0, pos1, pos2, pos3;
            pos0 = pos1 = pos2 = pos3 = 0;
            
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                
                //For para recorrer las celdas de las filas
                for(int i=0; i<sheet.getRow(0).getLastCellNum(); i++) {
                    cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    
                    //Si estoy recorriendo la fila0
                    if (auxFila==0){
                        
                        // <editor-fold defaultstate="collapsed" desc="Verificacion de titulos de columnas">    
                        
                        //Valido los encabezados y guardo la posicion en la que vienen en el archivo xls
                        switch(cell.toString().trim().toUpperCase()){
                            case "NOMBRE_LISTA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos0 != 0){
                                    errores.add(new CError("La columna NOMBRE_LISTA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos0 = i;
                                }
                                break;
                            case "NOMBRE":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos1 != 0){
                                    errores.add(new CError("La columna NOMBRE esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos1 = i;
                                }
                                break;
                            case "ETIQUETA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos2 != 0){
                                    errores.add(new CError("La columna ETIQUETA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos2 = i;
                                }
                                break;
                            case "MULTIMEDIA":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos3 != 0){
                                    errores.add(new CError("La columna MULTIMEDIA esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos3 = i;
                                }
                                break; 
                            case "":
                                break;
                            default:
                                //Cualquier otra cosa es error
                                errores.add(new CError("Nombre de columna invalida en la posicion " + i, CError.Tipo.GENERAL));
                            }
                        // </editor-fold>
                        
                    }
                    //Si ya estoy recorriendo a partir de la fila 1 en adelante
                    else{
                        // <editor-fold defaultstate="collapsed" desc="Capturo el contenido de las celdas y lo guardo">
                        
                        String valCelda = (cell.toString().isEmpty() ? "null" : cell.toString());

                        if(i==pos0){
                            columnas.get(0).addCelda(valCelda);
                        }
                        else if(i==pos1){
                            columnas.get(1).addCelda(valCelda);
                        }
                        else if(i==pos2){
                            columnas.get(2).addCelda(valCelda);
                        }
                        else if(i==pos3){
                            columnas.get(3).addCelda(valCelda);
                        }
                    // </editor-fold>
                    }
                    //System.out.print(cell.toString()+" ");
                    
                }
                System.out.println();
                auxFila++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void readXLSFileWithBlankCellsConfiguracion(File file) {
        try {
            InputStream ExcelFileToRead = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
           
            //Capturo la hoja con el nombre enviado en el parametro
            HSSFSheet sheet = wb.getSheet("Configuracion");
            if(sheet == null){
                errores.add(new CError("No existe la hoja: CONFIGURACION" , CError.Tipo.GENERAL));
                return;
            }    
            HSSFRow row;
            HSSFCell cell;
            Iterator rows = sheet.rowIterator();
            
            int auxFila = 0;
            int pos0, pos1, pos2, pos3, pos4, pos5;
            pos0 = pos1 = pos2 = pos3 = pos4 = pos5 = 0;
            
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                
                //For para recorrer las celdas de las filas
                for(int i=0; i<sheet.getRow(0).getLastCellNum(); i++) {
                    cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    
                    //Si estoy recorriendo la fila0
                    if (auxFila==0){
                        
                        // <editor-fold defaultstate="collapsed" desc="Verificacion de titulos de columnas">    
                        
                        //Valido los encabezados y guardo la posicion en la que vienen en el archivo xls
                        switch(cell.toString().trim().toUpperCase()){
                            case "TITULO_FORMULARIO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos0 != 0){
                                    errores.add(new CError("La columna TITULO_FORMULARIO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos0 = i;
                                }
                                break;
                            case "IDFORM":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos1 != 0){
                                    errores.add(new CError("La columna IDFORM esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos1 = i;
                                }
                                break;
                            case "ESTILO":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos2 != 0){
                                    errores.add(new CError("La columna ESTILO esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos2 = i;
                                }
                                break;
                            case "IMPORTAR":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos3 != 0){
                                    errores.add(new CError("La columna IMPORTAR esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos3 = i;
                                }
                                break;
                            case "CODIGO_PRINCIPAL":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos4 != 0){
                                    errores.add(new CError("La columna CODIGO_PRINCIPAL esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos4 = i;
                                }
                                break;
                            case "CODIGO_GLOBAL":
                                //Verifico si es la primera vez que viene y si no es un error
                                if(pos5 != 0){
                                    errores.add(new CError("La columna CODIGO_GLOBAL esta repetida", CError.Tipo.GENERAL));
                                }else{
                                    pos5 = i;
                                }
                                break; 
                            case "":
                                break;
                            default:
                                //Cualquier otra cosa es error
                                errores.add(new CError("Nombre de columna invalida en la posicion " + i, CError.Tipo.GENERAL));
                            }
                        // </editor-fold>
                        
                    }
                    //Si ya estoy recorriendo a partir de la fila 1 en adelante
                    else{
                        // <editor-fold defaultstate="collapsed" desc="Capturo el contenido de las celdas y lo guardo">
                        
                        String valCelda = (cell.toString().isEmpty() ? "null" : cell.toString());

                        if(i==pos0){
                            columnas.get(0).addCelda(valCelda);
                        }
                        else if(i==pos1){
                            columnas.get(1).addCelda(valCelda);
                        }
                        else if(i==pos2){
                            columnas.get(2).addCelda(valCelda);
                        }
                        else if(i==pos3){
                            columnas.get(3).addCelda(valCelda);
                        }
                        else if(i==pos4){
                            columnas.get(4).addCelda(valCelda);
                        }
                        else if(i==pos5){
                            columnas.get(5).addCelda(valCelda);
                        }
                        
                    // </editor-fold>
                    }
                    //System.out.print(cell.toString()+" ");
                    
                }
                System.out.println();
                auxFila++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void imprimirColumnas(){
        for(Columna col: this.columnas){
            System.out.print(col.getTipo() + ": ");
            for(String cel: col.getCeldas()){
                System.out.print(cel + " ");
            }
            System.out.println("");
        }
    }
    
    public void imprimirErrores(){
        if(this.errores.isEmpty())
        {
            System.out.println("No existen errores en la lectura del archivo");
            return;
        }
        
        this.errores.forEach((error) -> {
            System.out.println("Erro tipo: " + error.getTipo() + " Descripcion: " + error.getDesripcion());
        });
    }
    
    public String generaSalidaEncuesta(){
        String salida="Encuesta [ \n";
        String contenido;
        if(columnas.get(0).getCeldas().size() != columnas.get(1).getCeldas().size() || columnas.get(0).getCeldas().size() != columnas.get(2).getCeldas().size()){
           this.errores.add(new CError("Error en listas internas", CError.Tipo.GENERAL));
           return "";
        }
        
        int totalCeldas = columnas.get(0).getCeldas().size();
        //Recorrido por el total de filas
        for(int i=0; i<totalCeldas; i++){
            //Por cada columna en mi lista
            for(Columna columna: columnas){
                switch(columna.getTipo()){
                    case TIPO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("tipo > " + contenido + " ~\n") : "tipo > null ~\n"; 
                        break;
                    case IDPREGUNTA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("idpregunta > " + contenido + " ~\n") : "idpregunta > null ~\n"; 
                        break;
                    case ETIQUETA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("etiqueta > " + contenido + " ~\n") : "etiqueta > null ~\n"; 
                        break;
                    case SUGERIR:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("sugerir > " + contenido + " ~\n") : "sugerir > null ~\n"; 
                        break;
                    case CODIGO_PRE:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("codigo_pre > " + contenido + " ~\n") : "codigo_pre > null ~\n"; 
                        break;
                    case CODIGO_POST:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("codigo_post > " + contenido + " ~\n") : "codigo_post > null ~\n"; 
                        break;
                    case RESTRINGIR:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("restringir > " + contenido + " ~\n") : "restringir > null ~\n"; 
                        break;
                    case RESTRINGIRMSN:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("restringirmsn > " + contenido + " ~\n") : "restringirmsn > null ~\n"; 
                        break;
                    case REQUERIDO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("requerido > " + contenido + " ~\n") : "requerido > null ~\n"; 
                        break;
                    case REQUERIDOMSN:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("requeridomsn > " + contenido + " ~\n") : "requeridomsn > null ~\n"; 
                        break;
                    case PREDETERMINADO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("predeterminado > " + contenido + " ~\n") : "predeterminado > null ~\n"; 
                        break;
                    case APLICABLE:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("aplicable > " + contenido + " ~\n") : "aplicable > null ~\n"; 
                        break;
                    case LECTURA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("lectura > " + contenido + " ~\n") : "lectura > null ~\n"; 
                        break;
                    case CALCULO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("calculo > " + contenido + " ~\n") : "calculo > null ~\n"; 
                        break;
                    case REPETICION:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("repeticion > " + contenido + " ~\n") : "repeticion > null ~\n"; 
                        break;
                    case MULTIMEDIA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("multimedia > " + contenido + " ~\n") : "multimedia > null ~\n"; 
                        break;
                    case APARIENCIA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("apariencia > " + contenido + " ~\n") : "apariencia > null ~\n"; 
                        break;
                    case PARAMETRO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("parametro > " + contenido + " ~\n") : "parametro > null ~\n"; 
                        break;
                }
            }
        }
        salida += "]";
        return salida;
    }
    
    public String generaSalidaOpciones(){
        String salida="Opciones [ \n";
        String contenido;
        if(columnas.get(0).getCeldas().size() != columnas.get(1).getCeldas().size() || columnas.get(0).getCeldas().size() != columnas.get(2).getCeldas().size()){
           this.errores.add(new CError("Error en listas internas", CError.Tipo.GENERAL));
           return "";
        }
        
        int totalCeldas = columnas.get(0).getCeldas().size();
        //Recorrido por el total de filas
        for(int i=0; i<totalCeldas; i++){
            //Por cada columna en mi lista
            for(Columna columna: columnas){
                switch(columna.getTipo()){
                    case NOMBRE_LISTA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("nombre_lista > " + contenido + " ~\n") : "nombre_lista > null ~\n"; 
                        break;
                    case NOMBRE:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("nombre > " + contenido + " ~\n") : "nombre > null ~\n"; 
                        break;
                    case ETIQUETA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("etiqueta > " + contenido + " ~\n") : "etiqueta > null ~\n"; 
                        break;
                    case MULTIMEDIA:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("multimedia > " + contenido + " ~\n") : "multimedia > null ~\n"; 
                        break;
                }
            }
        }
        salida += "]";
        return salida;
    }
    
    public String generaSalidaConfiguracion(){
        String salida="Configuracion [ \n";
        String contenido;
        if(columnas.get(0).getCeldas().size() != columnas.get(1).getCeldas().size() || columnas.get(0).getCeldas().size() != columnas.get(2).getCeldas().size()){
           this.errores.add(new CError("Error en listas internas", CError.Tipo.GENERAL));
           return "";
        }
        
        int totalCeldas = columnas.get(0).getCeldas().size();
        //Recorrido por el total de filas
        for(int i=0; i<totalCeldas; i++){
            //Por cada columna en mi lista
            for(Columna columna: columnas){
                switch(columna.getTipo()){
                    case TITULO_FORMULARIO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("titulo_formulario > " + contenido + " ~\n") : "titulo_formulario > null ~\n"; 
                        break;
                    case IDFORM:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("idform > " + contenido + " ~\n") : "idform > null ~\n"; 
                        break;
                    case ESTILO:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("estilo > " + contenido + " ~\n") : "estilo > null ~\n"; 
                        break;
                    case IMPORTAR:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("importar > " + contenido + " ~\n") : "importar > null ~\n"; 
                        break;
                    case CODIGO_PRINCIPAL:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("codigo_principal > " + contenido + " ~\n") : "codigo_principal > null ~\n"; 
                        break;
                    case CODIGO_GLOBAL:
                        contenido = (!columna.getCeldas().isEmpty()) ? columna.getCeldas().get(i) : "";
                        salida += (!contenido.isEmpty()) ? ("codigo_global > " + contenido + " ~\n") : "codigo_global > null ~\n"; 
                        break;
                }
            }
        }
        salida += "]";
        return salida;
    }
   
}
