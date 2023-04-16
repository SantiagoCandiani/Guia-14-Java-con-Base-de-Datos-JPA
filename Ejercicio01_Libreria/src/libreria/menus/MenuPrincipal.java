package libreria.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author scandiani
 */
public class MenuPrincipal {

    private MenuAutor menuAu;
    private MenuEditorial menuEd;
    private MenuLibro menuLi;
    private Scanner leer;

    public MenuPrincipal() {
        this.menuAu = new MenuAutor();
        this.menuEd = new MenuEditorial();
        this.menuLi = new MenuLibro();
        this.leer = new Scanner(System.in).useDelimiter("\n");

    }

    public void presioneTecla() {
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        leer.next();
    }

    public void menuPrincipal() {
        try {

            System.out.println("                                                                                         \n "
                    + "                 ###   #  ###   #     #  ####  #####  ####  ####  ####                                          \n "
                    + "                 #  #  #  #  #  #     #  #  #    #    #     #     #  #                                          \n "
                    + "                 ###   #  ###   #     #  #  #    #    ###   #     ####                                          \n "
                    + "                 #  #  #  #  #  #     #  #  #    #    #     #     #  #                                          \n "
                    + "                 ###   #  ###   ####  #  ####    #    ####  ####  #  #                                          \n "
                    + "                                                                                                 ");
            System.out.println("<---------SISTEMA DE GESTION DE BIBLIOTECA--------->");
            System.out.println("1) Menu de Autor.");
            System.out.println("2) Menu de Editorial.");
            System.out.println("3) Menu de Libro.");
            System.out.println("4) Salir.");

            System.out.println("");
            System.out.print("Ingrese una opcion: ");
            System.out.println("");
            int op = leer.nextInt();
            switch (op) {
                case 1:
                    menuAu.menuAutores();
                    presioneTecla();
                    menuPrincipal();
                    break;
                case 2:
                    menuEd.menuEditoriales();
                    presioneTecla();
                    menuPrincipal();
                    break;
                case 3:
                    menuLi.menuLibros();
                    presioneTecla();
                    menuPrincipal();
                    break;
                case 4:
                    System.out.println("Usted Salio del programa con Exito.");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    break;
            }
        } catch (Exception e) {
            System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
        }
    }

}//class
