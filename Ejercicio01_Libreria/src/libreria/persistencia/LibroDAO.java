package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

/**
 * @author scandiani
 */
public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    @Override
    public void editar(Libro libro) {
        super.editar(libro);
    }

    public void eliminarLibro(Long isbn) throws Exception {
        Libro libro = buscarPorISBN(isbn);
//        eliminar(libro);
        libro.setAlta(Boolean.FALSE);
        editar(libro);
    }

    public void eliminarTodosLibros() throws Exception {
        //eliminarTodos(Autor.class);
        List<Libro> autores = listarLibros();
        for (Libro aux : autores) {
            eliminarLibro(aux.getIsbn());
        }
    }

    public Libro buscarPorISBN(Long isbn) throws Exception {
        conectar();
        Libro libro = null;
        //libro = em.find(Libro.class, isbn);
        libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn").setParameter("isbn", isbn.toString()).getSingleResult();
        desconectar();
        return libro;
    }

    public Libro buscarPorNombre(String nombre) throws Exception {
        conectar();
        Libro libro = null;
        libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").setParameter("titulo", nombre).getSingleResult();
        desconectar();
        return libro;
    }

    public Libro buscarPorAutor(String nombre) throws Exception {
        conectar();
        Libro libro = null;
        libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre LIKE :nombre").setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return libro;
    }

    public Libro buscarPorEditorial(String nombre) throws Exception {
        conectar();
        Libro libro = null;
        libro = (Libro) em.createQuery("SELECT e FROM Libro e WHERE e.editorial.nombre LIKE :nombre").setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return libro;
    }

    public List<Libro> listarLibros() throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l ").getResultList();
        desconectar();
        return libros;
    }

}//class
