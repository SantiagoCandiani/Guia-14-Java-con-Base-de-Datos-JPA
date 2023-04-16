package libreria.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

/**
 * @author scandiani
 */
public class AutorServicio {

    private final AutorDAO DAO;
    private Scanner leer;

    public AutorServicio() {
        this.DAO = new AutorDAO();
        this.leer = new Scanner(System.in).useDelimiter("\n");
    }

    public void insertarListaAutoresAutomatica() {
        List<String> nombres = Arrays.asList("Autor 1", "Autor 2", "Autor 3", "Autor 4", "Autor 5", "Autor 6", "Autor 7", "Autor 8", "Autor 9", "Autor 10");

        for (String nombre : nombres) {
            Autor autor = buscarPorNombre(nombre);
            if (autor == null) {
                autor = new Autor();
                autor.setNombre(nombre);
                DAO.guardar(autor);
                System.out.println("Autor " + nombre + " insertado correctamente.");
            } else {
                System.out.println("Autor " + nombre + " ya existe en la base de datos.");
            }
        }
    }

    public Autor crearAutor(String nombre) {
        Autor autor = new Autor();
        try {
            // Verifica si ya existe un autor con el mismo nombre
            if (buscarPorNombre(nombre) != null) {
                System.out.println("Error al crear autor: El autor con el nombre '" + nombre + "' ya existe.");
                return null;
            } else {
                System.out.println("Autor cargado con exito.");
            }

            autor.setNombre(nombre);
            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println("Error al crear autor: " + e.getMessage());
            return null;
        }
    }

    public void editar(Integer id) {
        try {
            Autor autor = buscarPorId(id);
            if (autor == null) {
                System.out.println("El ID ingresado no corresponde a un autor.");
            } else {
                System.out.println("Ingrese el nuevo nombre:");
                String nombre = leer.next();
                autor.setNombre(nombre);
                DAO.editar(autor);
                System.out.println("Autor Modificado.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar autor: " + e.getMessage());
        }
    }

    public void eliminarPorId(Integer id) {
        try {
            Autor autor = buscarPorId(id);
            if (autor == null) {
                System.out.println("El ID ingresado no corresponde a un autor.");
            } else {
                DAO.eliminarAutor(id);
                System.out.println("Autor Eliminado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudo eliminar el autor");
        }
    }

    public void eliminarTodos() {
        try {
            DAO.eliminarTodosAutores();
            System.out.println("Todos los autores fueron Eliminados (dados de baja).");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudo eliminar los autores");
        }
    }

    public Autor buscarPorId(Integer id) {
        try {
            return DAO.buscarPorId(id);
        } catch (javax.persistence.NoResultException e) {
            System.out.println("No se encontró un autor con el ID: '" + id + "'.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar autor por id: " + e.getMessage());
            return null;
        }
    }

    public Autor buscarPorNombre(String nombre) {
        try {
            return DAO.buscarPorNombre(nombre);
        } catch (javax.persistence.NoResultException e) {
            System.out.println("No se encontró un autor con el nombre '" + nombre + "'.");
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar autor por nombre: " + e.getMessage());
            return null;
        }
    }

    public List<Autor> listarAutores() {
        try {
            List<Autor> listaAutores = DAO.listarAutores();
            if (listaAutores.isEmpty()) {
                System.out.println("No se encontraron autores en la Base de Datos.");
                JOptionPane.showMessageDialog(null, "No se encontraron autores.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return listaAutores;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar autores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void mostrarAutor(Autor autor) {
        if (autor != null) {
            System.out.println(autor);
        }
    }

    public void mostrarAutores() {
        List<Autor> autores = listarAutores();
        if (autores != null) {
            for (Autor aux : autores) {
                System.out.println(aux);
            }
        }
    }

}//class
