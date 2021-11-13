
package datos;

import entidades.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivoEquipo {
    private File archivo;
    private FileWriter aEscritura;
    private Scanner aLectura;

    public ArchivoEquipo() {
        this.archivo = new File("Equipos.dat");
    }
    
    public ArchivoEquipo(String name){
        this.archivo = new File(name);
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public FileWriter getaEscritura() {
        return aEscritura;
    }

    public void setaEscritura(FileWriter aEscritura) {
        this.aEscritura = aEscritura;
    }

    public Scanner getaLectura() {
        return aLectura;
    }

    public void setaLectura(Scanner aLectura) {
        this.aLectura = aLectura;
    }
    
    public Equipo leerEquipo(String linea[]){
        Equipo e = new Equipo();
        e.setNombre(linea[0]);
        e.setPartidos_jugados(Integer.valueOf(linea[1]));
        e.setPartidos_ganados(Integer.valueOf(linea[2]));
        e.setPartidos_empatados(Integer.valueOf(linea[3]));
        e.setPartidos_perdidos(Integer.valueOf(linea[4]));
        e.setPuntos(Integer.valueOf(linea[5]));
        
        return e;
    }
    
    public Equipo modificar(String nombreAmodificar) throws IOException{
       
        Equipo modificado = null;
        try{ 
            this.aLectura = new Scanner(this.archivo);        
            while (this.aLectura.hasNext()) {
               String linea[] = this.aLectura.nextLine().split(";");
               Equipo e = this.leerEquipo(linea);
               if (nombreAmodificar.equals(e.getNombre())) {
                    modificado = e;
                    break;
                }
            }
            return modificado;
        
       }catch (FileNotFoundException ex){
           throw  new IOException("El archivo no se encuentra ");          
       }
       finally{
           if (this.aLectura!=null) {
               this.aLectura.close();
           }
       }        
    }
    
    public Equipo eliminar(String nombreAeliminar) throws IOException{
        
        List<Equipo> listadoInicial = this.leer();
        ArchivoEquipo archivoAux = new ArchivoEquipo("ArchivoTmpClasificacion.dat");
        Equipo eliminado = null;
        
        for(Equipo e: listadoInicial){
            if (nombreAeliminar.equals(e.getNombre())) {//elemento a eliminar
                eliminado = e;
            }else{
                archivoAux.escribir(e);
            }
        }
        
        if (this.archivo.delete()) {
            if (archivoAux.archivo.exists()) {
                if (!archivoAux.archivo.renameTo(this.archivo)) {
                    throw  new IOException("El archivo temporal no pudo ser eliminado");

                }
            }else{
                this.archivo.createNewFile();
            }
        }else {
            throw  new IOException("El archivo original no pudo ser eliminado");
        }
            
        return eliminado;
    }
    
    public Equipo buscar(String nombrebuscado) throws IOException{
        
        Equipo buscado = null;
        try{ 
            this.aLectura = new Scanner(this.archivo);        
            while (this.aLectura.hasNext()) {
               String linea[] = this.aLectura.nextLine().split(";");
               Equipo e = this.leerEquipo(linea);
               if (nombrebuscado.equals(e.getNombre())) {
                    buscado = e;
                    break;
                }
            }
            return buscado;
        
       }catch (FileNotFoundException ex){
           throw  new IOException("El archivo no se encuentra ");          
       }
       finally{
           if (this.aLectura!=null) {
               this.aLectura.close();
           }
       }
    }
    
    public List<Equipo> leer(){
       List<Equipo> list = null;
        
        try{ 
            this.aLectura = new Scanner(this.archivo);
            list = new ArrayList();

            while (this.aLectura.hasNext()) {
               String linea[] = this.aLectura.nextLine().split(";");
               Equipo e = this.leerEquipo(linea);
               list.add(e);
            }
            return list;
        
       }catch (FileNotFoundException ex){
           System.out.println("El archivo no se encuentra ");
           return null;
       }
       finally{
           if (this.aLectura!=null) {
               this.aLectura.close();
           }
       }
    }
    
    public boolean escribir(Equipo e){
        PrintWriter escritor = null;
        boolean error = false;
        try{
           
            this.aEscritura = new FileWriter(this.archivo, true);
            escritor = new PrintWriter(this.aEscritura);
            escritor.println(e.getDatosFileText());
            error = true;
        
        }catch(IOException ioe){
           System.out.println("Error al abrir el archivo para escritura...");
        } 
        
        finally{
            if (escritor!=null)
               escritor.close();
           
            if (this.aEscritura!=null) {
                try {
                    this.aEscritura.close();
                } catch  (IOException ioe) {
                    System.out.println(ioe);
                }
            }
           return error;
       }
    }
    
    
}
