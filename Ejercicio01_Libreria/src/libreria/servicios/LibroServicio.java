package libreria.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

/**
 * @author scandiani
 */
public class LibroServicio {

    private LibroDAO DAO;
    private AutorServicio servAu;
    private EditorialServicio servEd;
    private Scanner leer;

    public LibroServicio() {
        this.DAO = new LibroDAO();
        this.servAu = new AutorServicio();
        this.servEd = new EditorialServicio();
        this.leer = new Scanner(System.in).useDelimiter("\n");
    }

    public void insertarListaLibrosAutomatica() {
        List<String> titulos = Arrays.asList("Libro 1", "Libro 2", "Libro 3", "Libro 4", "Libro 5", "Libro 6", "Libro 7", "Libro 8", "Libro 9", "Libro 10");

        List<Integer> anios = Arrays.asList(2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018);

        List<Integer> ejemplares = Arrays.asList(10, 20, 15, 25, 30, 5, 12, 8, 18, 22);

        List<Autor> autores = servAu.listarAutores(); // Lista de autores existentes en la base de datos
        List<Editorial> editoriales = servEd.listarEditoriales(); // Lista de editoriales existentes en la base de datos
        Long isbn = 1000000000000L; // Primer valor para el ISBN

        for (int i = 0; i < titulos.size(); i++) {
            Libro libro = new Libro();
            libro.setIsbn(isbn++);
            libro.setTitulo(titulos.get(i));
            libro.setAnio(anios.get(i));
            libro.setEjemplares(ejemplares.get(i));
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(ejemplares.get(i));
            Autor autor = autores.get(i % autores.size()); // Asignar autor de forma circular
            Editorial editorial = editoriales.get(i % editoriales.size()); // Asignar editorial de forma circular
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            try {
                if (buscarPorISBN(libro.getIsbn()) != null) {
                    System.out.println("El libro con ISBN " + libro.getIsbn() + " ya existe en la base de datos.");
                    continue; // Saltar a la siguiente iteración del bucle
                }
            } catch (Exception e) {
                System.out.println("Error al buscar el libro con ISBN " + libro.getIsbn() + ": " + e.getMessage());
                continue; // Saltar a la siguiente iteración del bucle
            }
            DAO.guardar(libro);
            System.out.println("Libro " + titulos.get(i) + " insertado correctamente.");
        }
    }

    public Libro crearLibro() {
        Libro libro = new Libro();

        try {
            System.out.println("Ingrese el ISBN del libro");
            libro.setIsbn(leer.nextLong());
            if (libro.getIsbn() == null) {
                throw new IllegalArgumentException("Debe ingresar el ISBN del libro");
            }

            System.out.println("Ingrese el título del libro");
            libro.setTitulo(leer.next());
            if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el título del libro");
            }

            System.out.println("Ingrese el año de publicación");
            int anio = leer.nextInt();
            if (anio <= 0) {
                throw new IllegalArgumentException("El año de publicación debe ser mayor que cero");
            }
            libro.setAnio(anio);

            System.out.println("Ingrese la cantidad de ejemplares");
            int ejemplares = leer.nextInt();
            if (ejemplares <= 0) {
                throw new IllegalArgumentException("La cantidad de ejemplares debe ser mayor que cero");
            }
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());

            //Autor
            System.out.println("Ingrese el nombre del autor");
            String nombreAutor = leer.next();
            if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el nombre del autor del libro");
            }
            Autor autor = servAu.buscarPorNombre(nombreAutor);
            if (autor == null) {
                autor = servAu.crearAutor(nombreAutor);
            }
            libro.setAutor(autor);

            // Editorial
            System.out.println("Ingrese el nombre de la editorial");
            String nombreEditorial = leer.next();
            if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar el nombre de la editorial del libro");
            }
            Editorial editorial = servEd.buscarPorNombre(nombreEditorial);
            if (editorial == null) {
                editorial = servEd.crearEditorial(nombreEditorial);
            }
            libro.setEditorial(editorial);

            DAO.guardar(libro);
            System.out.println("Libro cargado con exito.");
            return libro;
        } catch (Exception e) {
            System.out.println("Error al crear libro " + e.getMessage());
            return null;
        }
    }

    public void editar(Long isbn) {
        try {
            Libro libro = buscarPorISBN(isbn);
            if (libro == null) {
                System.out.println("El ISBN ingresado no corresponde a un libro.");
            } else {
                System.out.println("Ingrese el título del libro");
                libro.setTitulo(leer.next());
                if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
                    throw new IllegalArgumentException("Debe ingresar el título del libro");
                }

                System.out.println("Ingrese el año de publicación");
                int anio = leer.nextInt();
                if (anio <= 0) {
                    throw new IllegalArgumentException("El año de publicación debe ser mayor que cero");
                }
                libro.setAnio(anio);

                System.out.println("Ingrese la cantidad de ejemplares");
                int ejemplares = leer.nextInt();
                if (ejemplares <= 0) {
                    throw new IllegalArgumentException("La cantidad de ejemplares debe ser mayor que cero");
                }
                libro.setEjemplares(ejemplares);

                System.out.println("Ingrese la cantidad de ejemplares prestados");
                int ejemplaresPrest = leer.nextInt();
                libro.setEjemplaresPrestados(ejemplaresPrest);

                libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());

                //Autor
                System.out.println("Ingrese el nombre del autor");
                String nombreAutor = leer.next();
                if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
                    throw new IllegalArgumentException("Debe ingresar el nombre del autor del libro");
                }
                Autor autor = servAu.buscarPorNombre(nombreAutor);
                if (autor == null) {
                    autor = servAu.crearAutor(nombreAutor);
                }
                libro.setAutor(autor);

                // Editorial
                System.out.println("Ingrese el nombre de la editorial");
                String nombreEditorial = leer.next();
                if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
                    throw new IllegalArgumentException("Debe ingresar el nombre de la editorial del libro");
                }
                Editorial editorial = servEd.buscarPorNombre(nombreEditorial);
                if (editorial == null) {
                    editorial = servEd.crearEditorial(nombreEditorial);
                }
                libro.setEditorial(editorial);

                DAO.editar(libro);
                System.out.println("Editorial Modificada.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar editorial: " + e.getMessage());
        }
    }

    public void eliminarPorISBN(Long isbn) {
        try {
            Libro libro = buscarPorISBN(isbn);
            if (libro == null) {
                System.out.println("El ISBN ingresado no corresponde a un libro.");
            } else {
                DAO.eliminarLibro(isbn);
                System.out.println("Libro Eliminado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudo eliminar el libro");
        }
    }
    
        public void eliminarTodos() {
        try {
            DAO.eliminarTodosLibros();
            System.out.println("Todas los libros fueron Eliminados (dados de baja).");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudieron eliminar los libros");
        }
    }

    public Libro buscarPorISBN(Long isbn) {
        try {
            return DAO.buscarPorISBN(isbn);
        } catch (Exception e) {
            System.out.println("Error al buscar libro por isbn" + e.getMessage());
            return null;
        }
    }

    public Libro buscarPorNombre(String nombre) {
        try {
            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println("Error al buscar libro por titulo " + e.getMessage());
            return null;
        }
    }

    public Libro buscarPorAutor(String nombre) {
        try {
            return DAO.buscarPorAutor(nombre);
        } catch (Exception e) {
            System.out.println("Error al buscar libro por autor " + e.getMessage());
            return null;
        }
    }

    public Libro buscarPorEditorial(String nombre) {
        try {
            return DAO.buscarPorAutor(nombre);
        } catch (Exception e) {
            System.out.println("Error al buscar libro por editorial " + e.getMessage());
            return null;
        }
    }

    public List<Libro> listarLibros() {
        try {
            List<Libro> listaLibros = DAO.listarLibros();
            if (listaLibros.isEmpty()) {
                System.out.println("No se encontraron libros en la Base de Datos.");
                JOptionPane.showMessageDialog(null, "No se encontraron libros.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return listaLibros;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void mostrarLibro(Libro libro) {
        if (libro != null) {
            System.out.println("-" + libro);
        }
    }

    public void mostrarLibros() {
        List<Libro> libros = listarLibros();
        if (libros != null) {
            for (Libro aux : libros) {
                System.out.println("-" + aux);
            }
        }
    }

}//class
