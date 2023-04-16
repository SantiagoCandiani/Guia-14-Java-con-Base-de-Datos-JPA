package libreria.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author scandiani
 */
@Entity
public class Editorial implements Serializable {

    @Id

    /*La anotación @GeneratedValue(strategy = GenerationType.AUTO) también se utiliza
    para indicar que la clave primaria debe ser generada automáticamente por la base de datos,
    pero en este caso, se deja la elección del modo de generación en manos del proveedor de persistencia.
    En otras palabras, la estrategia de generación de la clave primaria dependerá del proveedor
    de persistencia utilizado en la aplicación.*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private Boolean alta = Boolean.TRUE;

    public Editorial() {
    }

    public Editorial(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.alta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Editorial other = (Editorial) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.alta, other.alta);
    }

    @Override
    public String toString() {
        return "Editorial= " + "Id: " + id + ", Nombre: " + nombre + ", Alta: " + alta + ".";
    }

}//class
