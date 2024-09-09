package models;

public class Estudiante extends Persona {
    public enum Estado {
        MATRICULADO, INACTIVO, GRADUADO
    }

    private Estado estado;

    // Constructor
    public Estudiante(int id, String nombre, String apellido, java.time.LocalDate fechaDeNacimiento, Estado estado) {
        super(id, nombre, apellido, fechaDeNacimiento);
        this.estado = estado;
    }

    // Getter y Setter
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estudiante [" + super.toString() + ", estado=" + estado + "]";
    }
}
