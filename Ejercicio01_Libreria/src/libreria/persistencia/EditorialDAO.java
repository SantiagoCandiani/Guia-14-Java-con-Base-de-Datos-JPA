package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

/**
 * @author scandiani
 */
public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial edit) {
        super.guardar(edit);
    }

    @Override
    public void editar(Editorial edit) {
        super.editar(edit);
    }

    public void eliminarEditorial(Integer id) throws Exception {
        Editorial editorial = buscarPorId(id);
//        super.eliminar(editorial);
        editorial.setAlta(Boolean.FALSE);
        super.editar(editorial);
    }

    public void eliminarTodasEditoriales() throws Exception {
        //eliminarTodos(Editorial.class);
        List<Editorial> editoriales = listarEditoriales();
        for (Editorial aux : editoriales) {
            eliminarEditorial(aux.getId());
        }
    }

    public Editorial buscarPorId(Integer id) throws Exception {
        conectar();
        Editorial editorial = null;
        //editorial = em.find(Editorial.class, id);
        editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id LIKE :id").setParameter("id", id.toString()).getSingleResult();
        desconectar();
        return editorial;
    }

    public Editorial buscarPorNombre(String nombre) throws Exception {
        conectar();
        Editorial editorial = null;
        editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre").setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return editorial;
    }

    public List<Editorial> listarEditoriales() throws Exception {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e ").getResultList();
        desconectar();
        return editoriales;
    }

}//class
