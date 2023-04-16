package libreria.menus;

import java.util.Scanner;
import libreria.servicios.EditorialServicio;

/**
 * @author scandiani
 */
public class MenuEditorial {

    private Scanner leer;
    private EditorialServicio editorialServ;

    public MenuEditorial() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.editorialServ = new EditorialServicio();
    }

    public void presioneTecla() {
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        leer.next();
    }

    public void menuEditoriales() {
        try {

            System.out.println("--------Menu Editorial--------");
            System.out.println("");
            System.out.println("1) Ver Lista Completa.");
            System.out.println("2) Insertar Lista Precargada.");
            System.out.println("3) Insertar un nuevo Editorial.");
            System.out.println("4) Editar un Editorial.");
            System.out.println("5) Eliminar un editorial.");
            System.out.println("6) Eliminar todos los editoriales.");
            System.out.println("7) Buscar un editorial por ID.");
            System.out.println("8) Buscar un editorial por su nombre completo.");
            System.out.println("9) Salir.");

            System.out.println("");
            System.out.print("Ingrese una opcion: ");
            System.out.println("");

            int op = leer.nextInt();
            leer.nextLine(); // Consumir el salto de línea restante

            switch (op) {
                case 1:
                    editorialServ.mostrarEditoriales();
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 2:
                    editorialServ.insertarListaEditorialesAutomatica();
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 3:
                    System.out.println("Ingrese el Nombre de la editorial:");
                    editorialServ.crearEditorial(leer.next());
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 4:
                    System.out.println("Seleccione el ID del editorial a modificar de la siguiente lista:");
                    editorialServ.mostrarEditoriales();
                    editorialServ.editar(leer.nextInt());
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 5:
                    System.out.println("Seleccione el ID del editorial a eliminar de la siguiente lista:");
                    editorialServ.mostrarEditoriales();
                    editorialServ.eliminarPorId(leer.nextInt());
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 6:
                    editorialServ.eliminarTodos();
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 7:
                    System.out.println("Ingrese el ID del editorial:");
                    editorialServ.mostrarEditorial(editorialServ.buscarPorId(leer.nextInt()));
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 8:
                    System.out.println("Ingrese el nombre completo del editorial:");
                    editorialServ.mostrarEditorial(editorialServ.buscarPorNombre(leer.next()));
                    presioneTecla();
                    menuEditoriales();
                    break;
                case 9:
                    System.out.println("Saliendo del menu Editoriales...");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    presioneTecla();
                    menuEditoriales();
                    break;
            }

        } catch (Exception e) {
            System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
            presioneTecla();
            menuEditoriales();
        }
    }//menuEditoriales

}//class
