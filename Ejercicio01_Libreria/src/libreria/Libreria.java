package libreria;

import libreria.menus.MenuPrincipal;

/**
 * @author scandiani
 */
public class Libreria {

    public static void main(String[] args) {

        try {
            MenuPrincipal menuP = new MenuPrincipal();
            menuP.menuPrincipal();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}//main
