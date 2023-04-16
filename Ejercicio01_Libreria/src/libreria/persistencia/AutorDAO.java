package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;

/**
 * @author scandiani
 */
public class AutorDAO extends DAO<Autor> {

    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }

    @Override
    public void editar(Autor autor) {
        super.editar(autor);
    }

    public void eliminarAutor(Integer id) throws Exception {
        Autor autor = buscarPorId(id);
//        super.eliminar(autor);
        autor.setAlta(Boolean.FALSE);
        super.editar(autor);
    }

    public void eliminarTodosAutores() throws Exception {
        //eliminarTodos(Autor.class);
        List<Autor> autores = listarAutores();
        for (Autor aux : autores) {
            eliminarAutor(aux.getId());
        }
    }

    public Autor buscarPorId(Integer id) throws Exception {
        conectar();
        Autor autor = null;
        //autor = em.find(Autor.class, id);
        autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id LIKE :id").setParameter("id", id.toString()).getSingleResult();
        desconectar();
        return autor;
    }

    public Autor buscarPorNombre(String nombre) throws Exception {
        conectar();
        Autor autor = null;
        autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre").setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return autor;
    }

    public List<Autor> listarAutores() throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a ").getResultList();
        desconectar();
        return autores;
    }

}//class
