package libreria.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

/**
 * @author scandiani
 */
public class EditorialServicio {

    private final EditorialDAO DAO;
    private Scanner leer;

    public EditorialServicio() {
        this.DAO = new EditorialDAO();
        this.leer = new Scanner(System.in).useDelimiter("\n");;
    }

    public void insertarListaEditorialesAutomatica() {
        List<String> nombres = Arrays.asList("Editorial 1", "Editorial 2", "Editorial 3", "Editorial 4", "Editorial 5", "Editorial 6", "Editorial 7", "Editorial 8", "Editorial 9", "Editorial 10");

        for (String nombre : nombres) {
            Editorial editorial = buscarPorNombre(nombre);
            if (editorial == null) {
                editorial = new Editorial();
                editorial.setNombre(nombre);
                DAO.guardar(editorial);
                System.out.println("Editorial " + nombre + " insertada correctamente.");
            } else {
                System.out.println("Editorial " + nombre + " ya existe en la base de datos.");
            }
        }
    }

    public Editorial crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
        try {
            // Verifica si ya existe un editorial con el mismo nombre
            if (buscarPorNombre(nombre) != null) {
                System.out.println("Error al crear editorial: La editorial con el nombre '" + nombre + "' ya existe.");
                return null;
            } else {
                System.out.println("Editorial cargada con exito.");
            }

            editorial.setNombre(nombre);
            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println("Error al crear editorial: " + e.getMessage());
            return null;
        }
    }

    public void editar(Integer id) {
        try {
            Editorial editorial = buscarPorId(id);
            if (editorial == null) {
                System.out.println("El ID ingresado no corresponde a una editorial.");
            } else {
                System.out.println("Ingrese el nuevo nombre:");
                String nombre = leer.next();
                editorial.setNombre(nombre);
                DAO.editar(editorial);
                System.out.println("Editorial Modificada.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar editorial: " + e.getMessage());
        }
    }

    public void eliminarPorId(Integer id) {
        try {
            Editorial editorial = buscarPorId(id);
            if (editorial == null) {
                System.out.println("El ID ingresado no corresponde a una editorial.");
            } else {
                DAO.eliminarEditorial(id);
                System.out.println("Editorial Eliminada.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudo eliminar la editorial");
        }
    }

    public void eliminarTodos() {
        try {
            DAO.eliminarTodasEditoriales();
            System.out.println("Todas las editoriales fueron Eliminados (dadas de baja).");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudieron eliminar las editoriales");
        }
    }

    public Editorial buscarPorId(Integer id) {
        try {
            return DAO.buscarPorId(id);
        } catch (javax.persistence.NoResultException e) {
            System.out.println("No se encontró una editorial con el ID: '" + id + "'.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar editorial por id: " + e.getMessage());
            return null;
        }
    }

    public Editorial buscarPorNombre(String nombre) {
        try {
            return DAO.buscarPorNombre(nombre);
        } catch (javax.persistence.NoResultException e) {
            System.out.println("No se encontró una editorial con el nombre '" + nombre + "'.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar editorial por nombre: " + e.getMessage());
            return null;
        }
    }

    public List<Editorial> listarEditoriales() {
        try {
            List<Editorial> listaEditoriales = DAO.listarEditoriales();
            if (listaEditoriales.isEmpty()) {
                System.out.println("No se encontraron editoriales en la Base de Datos.");
                JOptionPane.showMessageDialog(null, "No se encontraron editoriales.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return listaEditoriales;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar editoriales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void mostrarEditorial(Editorial editorial) {
        if (editorial != null) {
            System.out.println("-" + editorial);
        }
    }

    public void mostrarEditoriales() {
        List<Editorial> editoriales = listarEditoriales();
        if (editoriales != null) {
            for (Editorial aux : editoriales) {
                System.out.println("-" + aux);
            }
        }
    }

}//class
