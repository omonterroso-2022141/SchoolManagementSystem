package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Curso;
import models.Estudiante;
import exceptions.EstudianteYaInscritoException;
import exceptions.EstudianteNoInscritoEnCursoException;

public class GestorAcademico implements ServiciosAcademicosI {
    private List<Estudiante> estudiantes;
    private List<Curso> cursos;
    private Map<Integer, List<Estudiante>> inscripciones; // key: idCurso

    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            System.out.println("Estudiante matriculado: " + estudiante);
        } else {
            System.out.println("El estudiante ya está matriculado: " + estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            System.out.println("Curso agregado: " + curso);
        } else {
            System.out.println("El curso ya existe: " + curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        Curso curso = buscarCursoPorId(idCurso);
        if (curso == null) {
            System.out.println("Curso con ID " + idCurso + " no encontrado.");
            return;
        }

        inscripciones.putIfAbsent(idCurso, new ArrayList<>());
        List<Estudiante> inscritos = inscripciones.get(idCurso);

        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en el curso: " + curso.getNombre());
        } else {
            inscritos.add(estudiante);
            System.out.println("Estudiante " + estudiante.getNombre() + " inscrito en el curso " + curso.getNombre());
        }
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        Curso curso = buscarCursoPorId(idCurso);
        if (curso == null) {
            throw new EstudianteNoInscritoEnCursoException("Curso con ID " + idCurso + " no existe.");
        }

        List<Estudiante> inscritos = inscripciones.get(idCurso);
        if (inscritos == null) {
            throw new EstudianteNoInscritoEnCursoException("No hay estudiantes inscritos en el curso: " + curso.getNombre());
        }

        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        if (estudiante == null || !inscritos.contains(estudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en el curso: " + curso.getNombre());
        }

        inscritos.remove(estudiante);
        System.out.println("Estudiante " + estudiante.getNombre() + " desinscrito del curso " + curso.getNombre());
    }

    private Curso buscarCursoPorId(int idCurso) {
        for (Curso curso : cursos) {
            if (curso.getId() == idCurso) {
                return curso;
            }
        }
        return null;
    }

    private Estudiante buscarEstudiantePorId(int idEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == idEstudiante) {
                return estudiante;
            }
        }
        return null;
    }

    public void listarEstudiantes() {
        System.out.println("Lista de Estudiantes:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    public void listarCursos() {
        System.out.println("Lista de Cursos:");
        for (Curso curso : cursos) {
            System.out.println(curso);
        }
    }

    public void listarInscripciones() {
        System.out.println("Inscripciones por Curso:");
        for (Map.Entry<Integer, List<Estudiante>> entry : inscripciones.entrySet()) {
            Curso curso = buscarCursoPorId(entry.getKey());
            System.out.println("Curso: " + (curso != null ? curso.getNombre() : "Desconocido"));
            for (Estudiante estudiante : entry.getValue()) {
                System.out.println("\t" + estudiante);
            }
        }
    }
}

