package libreria.menus;

import java.util.Scanner;
import libreria.servicios.LibroServicio;

/**
 * @author scandiani
 */
public class MenuLibro {

    private Scanner leer;
    private LibroServicio libroServ;

    public MenuLibro() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.libroServ = new LibroServicio();
    }

    public void presioneTecla() {
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        leer.next();
    }

    public void menuLibros() {
        try {

            System.out.println("--------Menu Libro--------");
            System.out.println("");
            System.out.println("1) Ver Lista Completa.");
            System.out.println("2) Insertar Lista Precargada.");
            System.out.println("3) Insertar un nuevo Libro.");
            System.out.println("4) Editar un Libro.");
            System.out.println("5) Eliminar un libro.");
            System.out.println("6) Eliminar todos los libros.");
            System.out.println("7) Buscar un libro por ISBN.");
            System.out.println("8) Buscar un libro por su nombre completo.");
            System.out.println("9) Buscar un libro por nombre del Autor.");
            System.out.println("10) Buscar un libro por nombre de la Editorial.");
            System.out.println("11) Salir.");

            System.out.println("");
            System.out.print("Ingrese una opcion: ");
            System.out.println("");

            int op = leer.nextInt();
            leer.nextLine(); // Consumir el salto de línea restante

            switch (op) {
                case 1:
                    libroServ.mostrarLibros();
                    presioneTecla();
                    menuLibros();
                    break;
                case 2:
                    libroServ.insertarListaLibrosAutomatica();
                    presioneTecla();
                    menuLibros();
                    break;
                case 3:
                    libroServ.crearLibro();
                    presioneTecla();
                    menuLibros();
                    break;
                case 4:
                    System.out.println("Seleccione el ISBN del libro a modificar de la siguiente lista:");
                    libroServ.mostrarLibros();
                    libroServ.editar(leer.nextLong());
                    presioneTecla();
                    menuLibros();
                    break;
                case 5:
                    System.out.println("Seleccione el ISBN del libro a eliminar de la siguiente lista:");
                    libroServ.mostrarLibros();
                    libroServ.buscarPorISBN(leer.nextLong());
                    presioneTecla();
                    menuLibros();
                    break;
                case 6:
                    libroServ.eliminarTodos();
                    presioneTecla();
                    menuLibros();
                    break;
                case 7:
                    System.out.println("Ingrese el ISBN del libro:");
                    libroServ.mostrarLibro(libroServ.buscarPorISBN(leer.nextLong()));
                    presioneTecla();
                    menuLibros();
                    break;
                case 8:
                    System.out.println("Ingrese el nombre completo del libro:");
                    libroServ.mostrarLibro(libroServ.buscarPorNombre(leer.next()));
                    presioneTecla();
                    menuLibros();
                    break;
                case 9:
                    System.out.println("Ingrese el nombre completo del autor del libro:");
                    libroServ.mostrarLibro(libroServ.buscarPorAutor(leer.next()));
                    presioneTecla();
                    menuLibros();
                    break;
                case 10:
                    System.out.println("Ingrese el nombre completo de la editorial del libro:");
                    libroServ.mostrarLibro(libroServ.buscarPorEditorial(leer.next()));
                    presioneTecla();
                    menuLibros();
                    break;
                case 11:
                    System.out.println("Saliendo del menu Libros...");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    presioneTecla();
                    menuLibros();
                    break;
            }

        } catch (Exception e) {
            System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
            presioneTecla();
            menuLibros();
        }
    }//menuLibros

}//class
