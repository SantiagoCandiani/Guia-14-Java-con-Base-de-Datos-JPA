package libreria.menus;

import java.util.Scanner;
import libreria.servicios.AutorServicio;

/**
 * @author scandiani
 */
public class MenuAutor {

    private Scanner leer;
    private AutorServicio autorServ;

    public MenuAutor() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.autorServ = new AutorServicio();
    }

    public void presioneTecla() {
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        leer.next();
    }

    public void menuAutores() {
        try {

            System.out.println("--------Menu Autor--------");
            System.out.println("");
            System.out.println("1) Ver Lista Completa.");
            System.out.println("2) Insertar Lista Precargada.");
            System.out.println("3) Insertar un nuevo Autor.");
            System.out.println("4) Editar un Autor.");
            System.out.println("5) Eliminar un autor.");
            System.out.println("6) Eliminar todos los autores.");
            System.out.println("7) Buscar un autor por ID.");
            System.out.println("8) Buscar un autor por su nombre completo.");
            System.out.println("9) Salir.");

            System.out.println("");
            System.out.print("Ingrese una opcion: ");
            System.out.println("");

            int op = leer.nextInt();
            leer.nextLine(); // Consumir el salto de línea restante

            switch (op) {
                case 1:
                    autorServ.mostrarAutores();
                    presioneTecla();
                    menuAutores();
                    break;
                case 2:
                    autorServ.insertarListaAutoresAutomatica();
                    presioneTecla();
                    menuAutores();
                    break;
                case 3:
                    System.out.println("Ingrese Nombre y Apellido del autor:");
                    autorServ.crearAutor(leer.next());
                    presioneTecla();
                    menuAutores();
                    break;
                case 4:
                    System.out.println("Seleccione el ID del autor a modificar de la siguiente lista:");
                    autorServ.mostrarAutores();
                    autorServ.editar(leer.nextInt());
                    presioneTecla();
                    menuAutores();
                    break;
                case 5:
                    System.out.println("Seleccione el ID del autor a eliminar de la siguiente lista:");
                    autorServ.mostrarAutores();
                    autorServ.eliminarPorId(leer.nextInt());
                    presioneTecla();
                    menuAutores();
                    break;
                case 6:
                    autorServ.eliminarTodos();
                    presioneTecla();
                    menuAutores();
                    break;
                case 7:
                    System.out.println("Ingrese el ID del autor:");
                    autorServ.mostrarAutor(autorServ.buscarPorId(leer.nextInt()));
                    presioneTecla();
                    menuAutores();
                    break;
                case 8:
                    System.out.println("Ingrese el nombre completo del autor:");
                    autorServ.mostrarAutor(autorServ.buscarPorNombre(leer.next()));
                    presioneTecla();
                    menuAutores();
                    break;
                case 9:
                    System.out.println("Saliendo del menu Autores...");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    presioneTecla();
                    menuAutores();
                    break;
            }

        } catch (Exception e) {
            System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
            presioneTecla();
            menuAutores();
        }
    }//menuAutores

}//class
