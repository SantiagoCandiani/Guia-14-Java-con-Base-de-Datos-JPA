package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author scandiani
 */
public class DAO<T> {

    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("Ejercicio01_LibreriaPU");
    protected EntityManager em = EMF.createEntityManager();

    protected void conectar() {
        try {
            if (!em.isOpen()) {
                em = EMF.createEntityManager();
            }
        } catch (Exception e) {
            System.out.println("Error al conectar --> " + e.getMessage());
        }
    }

    protected void desconectar() {
        try {
            if (em.isOpen()) {
                em.close();
            }
        } catch (Exception e) {
            System.out.println("Error al desconectar --> " + e.getMessage());
        }
    }

    protected void guardar(T objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar --> " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    protected void editar(T objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al editar --> " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    protected void eliminar(T objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar --> " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public void eliminarTodos(Class<T> clase) {
        try {
            conectar();
            em.getTransaction().begin();
            em.createQuery("DELETE FROM " + clase.getSimpleName()).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar todos --> " + e.getMessage());
        } finally {
            desconectar();
        }
    }

}//class
