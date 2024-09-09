import models.Curso;
import models.Estudiante;
import models.Estudiante.Estado;
import services.GestorAcademico;
import exceptions.EstudianteYaInscritoException;
import exceptions.EstudianteNoInscritoEnCursoException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de Estudiante
        Estudiante estudiante1 = new Estudiante(1, "Oscar", "Monterroso", LocalDate.of(2006, 4, 23), Estado.MATRICULADO);
        Estudiante estudiante2 = new Estudiante(2, "Maria", "Monterroso", LocalDate.of(2000, 10, 2), Estado.INACTIVO);

        // Crear instancias de Curso
        Curso curso1 = new Curso(101, "Programacion", "Curso de Java Springboot", 5, "1.0");
        Curso curso2 = new Curso(102, "Matematica", "Muchos numeros", 4, "1.1");

        // Instanciar GestorAcademico
        GestorAcademico gestor = new GestorAcademico();

        // Matricular estudiantes
        gestor.matricularEstudiante(estudiante1);
        gestor.matricularEstudiante(estudiante2);
        gestor.matricularEstudiante(estudiante1); // Intentar matricular nuevamente

        // Agregar cursos
        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);
        gestor.agregarCurso(curso1); // Intentar agregar nuevamente

        // Inscribir estudiantes en cursos
        try {
            gestor.inscribirEstudianteCurso(estudiante1, 101);
            gestor.inscribirEstudianteCurso(estudiante1, 101); // Intentar inscribir nuevamente
        } catch (EstudianteYaInscritoException e) {
            System.err.println(e.getMessage());
        }

        try {
            gestor.inscribirEstudianteCurso(estudiante2, 102);
        } catch (EstudianteYaInscritoException e) {
            System.err.println(e.getMessage());
        }

        // Desinscribir estudiantes de cursos
        try {
            gestor.desinscribirEstudianteCurso(1, 101);
            gestor.desinscribirEstudianteCurso(1, 101); // Intentar desinscribir nuevamente
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.err.println(e.getMessage());
        }

        try {
            gestor.desinscribirEstudianteCurso(3, 101); // Estudiante no existe
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.err.println(e.getMessage());
        }

        // Listar información
        gestor.listarEstudiantes();
        gestor.listarCursos();
        gestor.listarInscripciones();
    }
}
