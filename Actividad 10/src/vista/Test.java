/*
Se pretende escribir un programa para manejar la información de la liga de fútbol española. La información que se 
pretende guardar es: nombre de los equipos, partidos jugados, partidos ganados, empatados y perdidos y puntos
acumulados en la liga.
El programa pedirá todos esos datos por teclado y los enviará a un archivo binario llamado "liga.dat". Ten en cuenta que 
en la liga de primera división hay 20 equipos.
Después de teclear los datos, el programa los recuperará del fichero y los mostrará por pantalla con este formato:

Equipo          Jugados  Ganados  Empatados Perdidos Puntos
Castañazo F.C.    15       10        3         2       33
Atlético Penoso   15       8         3         4       27
Rec. Geriátrico   15       7         5         3       26
etc... .. .. .. .. ..

El Programa debe implementar un sencillo menú de opciones que tenga este aspecto en pantalla:
PROGRAMA DE LA LIGA DE FÚTBOL - MENÚ DE OPCIONES
1. Registrar equipos
2. Mostrar tabla de posiciones
3. Buscar un equipo
4. Borrar un equipo
5. Modificar un equipo
6. Salir del programa
Después, y según la opción elegida por el usuario, debes llamar a una función por cada opción.
Registrar equipos: esta función permitirá al usuario introducir por teclado los nombres de los equipos y sus estadísticas, 
que serán añadidos al fichero de datos (Equipos.dat)
Mostrar tabla de posiciones: leerá el archivo de datos (lEquipos.dat) y mostrará su contenido en la pantalla, en orden 
descendente por puntuación.
Buscar un equipo: pide al usuario un nombre de equipo y lo busca en el archivo. Si lo encuentra, muestra sus datos por 
la pantalla.
Borrar un equipo: pide al usuario un nombre de equipo y, si existe, lo borra del archivo.
Modificar un equipo: pide al usuario un nombre de equipo y, si existe, muestra por la pantalla sus datos actuales y pide 
al usuario unos datos nuevos. Después, sustituye los datos del equipo por los nuevos y lo guarda todo en el archivo.

Actividad 2.
Partiendo del avance de su proyecto de aula, implemente mediante archivos secuenciales de texto el registro de paquetes 
que ingresan a la central de entregas Delivery-Upar: Para ello, tener en cuenta lo siguiente:

a. Cada registro del archivo está asociado a un paquete registrado, y debe poseer todos los campos de información
requeridos. 
b. Se debe tener en cuenta las validaciones requeridas para registrar paquetes en el archivo.
c. Se debe permitir además del registro de nuevos paquetes, la búsqueda, modificación y eliminación de paquetes 
registrados.
d. Implementar alguna consulta sobre el archivo que arroje varios resultados.
*/

package vista;

import datos.ArchivoEquipo;
import entidades.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {
    
    //Los puse globales para no estar creandolo en cada metodo
    static ArchivoEquipo archivoTexto = new ArchivoEquipo();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        Menu();
    }
    
    public static void Menu() throws IOException{
        
        Scanner sc = new Scanner(System.in);
              
        int opc;
        System.out.println("");   
        System.out.println("***PROGRAMA DE LA LIGA DE FUTBOL***");
        System.out.println("");
        System.out.println("*********Elija una opcion**********");
        System.out.println("* 1. Registrar equipos            *");
        System.out.println("* 2. Mostrar tabla de posiciones  *");
        System.out.println("* 3. Buscar un equipo             *");
        System.out.println("* 4. Borrar un equipo             *");
        System.out.println("* 5. Modificar un equipo          *");
        System.out.println("* 6. Salir                        *");
        System.out.println("***********************************");

        boolean opc_valida = false;

        do{ 

           System.out.print("Digite el numero de la opcion: ");
            if (sc.hasNextInt()) {

                opc = sc.nextInt();

                if (opc>0 && opc<7) {
                    opc_valida=true;
                    Switch(opc);
                }else{
                    sc.nextLine();
                    System.err.println("\tOpcion fuera de rango ingrese una opcion valida");
                }
            }else{
                sc.nextLine();
                System.err.println("\tSolo debe ingresar números");
            }     

        }while(!opc_valida); 
    }
    
    public static void Switch(int opc) throws IOException{
        
        switch (opc) {
            case 1:
                    RegistrarEquipo();
                break;
            case 2:
                    mostrarTabladePosiciones();
                break;
            case 3:
                    buscar();
                break;
            case 4:
                    eliminar();
                break;
            case 5:
                    modificar();
                break;    
            default:
                if (opc<=0 || opc>6) {
                    System.err.println("\t\tEscoja una opcion valida");
                    Menu();
                }else if (opc==6) {
                    System.out.println("Adios");
                    System.exit(0);
                }
        }
    }
    
    public static void RegistrarEquipo() throws IOException{
        String nombre;
        char seguir='S';
        int pj=0,pg=0,pe=0,pp=0,pts=0;
        Scanner re = new Scanner(System.in);
        //el buffer solo lo uso para capturar el dato del nombre del equipo
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        
        while (seguir=='S') {
            
            System.out.println("****REGISTRO DE EQUIPO****");
            System.out.print("Nombre del equipo: "); 
            //nombre = re.next();
            nombre = br.readLine();

            boolean opc_valida_pj = false;
            do{ 
                System.out.print("Partidos jugados: ");
                if (re.hasNextInt()) {
                    pj = re.nextInt();
                    if (pj<0) {
                        System.err.println("\tIngrese un valor positivo");
                    }else{
                        opc_valida_pj = true;
                    }
                }else{
                    re.nextLine();
                    System.err.println("\tSolo debe ingresar números");
                }     
            }while(!opc_valida_pj); 

            boolean opc_valida_pg = false;
            do{ 
                System.out.print("Partidos ganados: ");
                if (re.hasNextInt()) {
                    pg = re.nextInt();
                    if (pg<0) {
                        System.err.println("\tIngrese un valor positivo");
                    }else{
                        opc_valida_pg = true;
                    }
                }else{
                    re.nextLine();
                    System.err.println("\tSolo debe ingresar números");
                }     
            }while(!opc_valida_pg);

            boolean opc_valida_pe = false;
            do{ 
                System.out.print("Partidos empatados: ");
                if (re.hasNextInt()) {
                    pe = re.nextInt();
                    if (pe<0) {
                        System.err.println("\tIngrese un valor positivo");
                    }else{
                        opc_valida_pe = true;
                    }
                }else{
                    re.nextLine();
                    System.err.println("\tSolo debe ingresar números");
                }     
            }while(!opc_valida_pe);

            boolean opc_valida_pp = false;
            do{ 
                System.out.print("Partidos perdidos: ");
                if (re.hasNextInt()) {
                    pp = re.nextInt();
                    if (pp<0) {
                        System.err.println("\tIngrese un valor positivo");
                    }else{
                        opc_valida_pp = true;
                    }
                }else{
                    re.nextLine();
                    System.err.println("\tSolo debe ingresar números");
                }     
            }while(!opc_valida_pp);

            boolean opc_valida_pts = false;
            do{ 
                System.out.print("Puntos: ");
                if (re.hasNextInt()) {
                    pts = re.nextInt();
                    if (pts<0) {
                        System.err.println("\tIngrese un valor positivo");
                    }else{
                        opc_valida_pts = true;
                    }
                }else{
                    re.nextLine();
                    System.err.println("\tSolo debe ingresar números");
                }     
            }while(!opc_valida_pts);

            ArchivoEquipo archivoTexto = new ArchivoEquipo();
            Equipo eq1 = new Equipo(nombre, pj, pg, pe, pp, pts);

            archivoTexto.escribir(eq1);//guarda los datos ingresados al archivo
            System.out.println("¡Registrado exitosamente!\n");

            System.out.println("¿Desea agregar registrar otro Equipo? S=si ó  N=no");
            seguir = re.next().toUpperCase().charAt(0);
       
        }
        
        if (seguir=='N') {
            Menu();;
        }
    }
    
    public static void mostrarTabladePosiciones() throws IOException{
        
        List<Equipo> lista = archivoTexto.leer();
        imprimirLista(lista);
    }
    
    public static void imprimirEquipo(Equipo e){
        
        System.out.printf("%10s \t%10d \t%10d \t%10d \t%10d \t%10d %n",e.getNombre(), e.getPartidos_jugados(), e.getPartidos_ganados(), e.getPartidos_empatados(), e.getPartidos_perdidos(), e.getPuntos());
    }
    
    public static void imprimirLista(List<Equipo> lista) throws IOException{
        
        try {
            
            if (lista!=null) {
                System.out.println("");
                System.out.println("****************************************LIGA ESPAÑOLA***************************************");
                System.out.println("Equipo              Jugados           Ganados        Empatados        Perdidos        Puntos");
        
                Collections.sort(lista, Collections.reverseOrder());
        
                for(Equipo e: lista){
                    imprimirEquipo(e);
                }
               
                Menu();
                
            }
            
        } catch (java.lang.NullPointerException npe) {
            System.out.println("No hay archivos, o no hay equipos registrados");
            Menu();
        }
        
    }
    
    public static void modificarDatos(Equipo e) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner re = new Scanner(System.in);
        System.out.println("***Modificacion de datos del equipo "+e.getNombre());
        System.out.print("Nombre del equipo : "); 
        e.setNombre(br.readLine());
        
        boolean opc_valida_pj = false;
        do{ 
            System.out.print("Partidos jugados: ");
            if (re.hasNextInt()) {
                e.setPartidos_jugados(re.nextInt());
                if (e.getPartidos_jugados()<0) {
                    System.err.println("\tIngrese un valor positivo");
                }else{
                    opc_valida_pj = true;
                }
            }else{
                re.nextLine();
                System.err.println("\tSolo debe ingresar números");
            }     
        }while(!opc_valida_pj); 
        
        boolean opc_valida_pg = false;
        do{ 
            System.out.print("Partidos ganados: ");
            if (re.hasNextInt()) {
                e.setPartidos_ganados(re.nextInt());
                if (e.getPartidos_ganados()<0) {
                    System.err.println("\tIngrese un valor positivo");
                }else{
                    opc_valida_pg = true;
                }
            }else{
                re.nextLine();
                System.err.println("\tSolo debe ingresar números");
            }     
        }while(!opc_valida_pg);
        
        boolean opc_valida_pe = false;
        do{ 
            System.out.print("Partidos empatados: ");
            if (re.hasNextInt()) {
                e.setPartidos_empatados(re.nextInt());
                if (e.getPartidos_empatados()<0) {
                    System.err.println("\tIngrese un valor positivo");
                }else{
                    opc_valida_pe = true;
                }
            }else{
                re.nextLine();
                System.err.println("\tSolo debe ingresar números");
            }     
        }while(!opc_valida_pe);
        
        boolean opc_valida_pp = false;
        do{ 
            System.out.print("Partidos perdidos: ");
            if (re.hasNextInt()) {
                e.setPartidos_perdidos(re.nextInt());
                if (e.getPartidos_perdidos()<0) {
                    System.err.println("\tIngrese un valor positivo");
                }else{
                    opc_valida_pp = true;
                }
            }else{
                re.nextLine();
                System.err.println("\tSolo debe ingresar números");
            }     
        }while(!opc_valida_pp);
        
        boolean opc_valida_pts = false;
        do{ 
            System.out.print("Puntos: ");
            if (re.hasNextInt()) {
                e.setPuntos(re.nextInt());
                if (e.getPuntos()<0) {
                    System.err.println("\tIngrese un valor positivo");
                }else{
                    opc_valida_pts = true;
                }
            }else{
                re.nextLine();
                System.err.println("\tSolo debe ingresar números");
            }     
        }while(!opc_valida_pts);
        archivoTexto.escribir(e);
        System.out.println("¡Datos Actualizados correctamente!");
        Menu();
    }
    
    public static void modificar() throws IOException{
        
        System.out.println("");
        System.out.println("***Modificar equipo***");
        System.out.print("Digite el nombre del equipo a modificar: ");
        String nombreAmodificar = br.readLine();
          
        Equipo buscadoAmodificar = archivoTexto.buscar(nombreAmodificar);
        if(buscadoAmodificar!=null){ // 
            System.out.println("Equipo encontrado: ");
            System.out.println("****************************************LIGA ESPAÑOLA***************************************");
            System.out.println("Equipo              Jugados           Ganados        Empatados        Perdidos        Puntos");
            imprimirEquipo(buscadoAmodificar);//imprime los datos del equipo que va a modificar
            System.out.println("");
            archivoTexto.eliminar(nombreAmodificar);//elimina el archivo antes de escribir uno nuevo
            modificarDatos(buscadoAmodificar);//El usuario ingresa los datos nuevos "Modificando"
            
        }else{
            System.out.println("El equipo no se encuentra en el archivo");
            Menu();
        }
        
    }
    
    public static void eliminar() throws IOException{
        System.out.println("");
        System.out.println("***Eliminar equipo***");
        System.out.print("Digite el nombre del equipo: ");
        String nombreAeliminar = br.readLine();
        
        Equipo eliminado = archivoTexto.eliminar(nombreAeliminar);
        if(eliminado!=null){ // 
                System.out.println("Equipo eliminado: ");
                imprimirEquipo(eliminado);
            }
            else{
                System.out.println("El equipo no se encuentra en el archivo");
            }
        Menu();
    }

    public static void buscar() throws IOException{
        
        System.out.println("");
        System.out.println("***Buscar equipo***");
        System.out.print("Digite el nombre del equipo: ");
        String nombrebuscado = br.readLine();
        
        Equipo buscado = archivoTexto.buscar(nombrebuscado);
        if(buscado!=null){ // 
            System.out.println("Equipo encontrado: ");
            System.out.println("****************************************LIGA ESPAÑOLA***************************************");
            System.out.println("Equipo              Jugados           Ganados        Empatados        Perdidos        Puntos");
            imprimirEquipo(buscado);
        }else{
            System.out.println("El equipo no se encuentra en el archivo");
        }
        Menu();
    }
}
