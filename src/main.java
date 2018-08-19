
import ManejoXLS.XlsFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 *
 * @author damon
 */
public class main {

    /**
     * @param args the command line arguments
     */

    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*File f = new File("C:/Users/damon/Desktop/entrada.xls");
        if(f.exists()){
            //XlsFile archivoExcel = new XlsFile(f);
            //archivoExcel.obtener();
            XlsFile.readXLSFileWithBlankCells(f);
        }*/
        
        File file = new File("C:/Users/damon/Desktop/Arbol.xls");
        
        XlsFile encuesta = new XlsFile("Encuesta");
        encuesta.readXLSFileWithBlankCellsEncuesta(file);
        encuesta.imprimirErrores();
        encuesta.imprimirColumnas();
        
        System.out.println(encuesta.generaSalidaEncuesta());
        
        /*XlsFile opciones = new XlsFile("Opciones");
        opciones.readXLSFileWithBlankCellsOpciones(file);
        opciones.imprimirErrores();
        opciones.imprimirColumnas();
        
        System.out.println(opciones.generaSalidaOpciones());*/
        
        /*XlsFile configuracion = new XlsFile("Configuracion");
        configuracion.readXLSFileWithBlankCellsConfiguracion(file);
        configuracion.imprimirErrores();
        configuracion.imprimirColumnas();
        
        System.out.println(configuracion.generaSalidaConfiguracion());*/

    }
}
