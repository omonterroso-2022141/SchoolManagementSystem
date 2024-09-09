package models;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripcionesCurso;

    public GestorAcademico() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        inscripcionesCurso = new HashMap<>();
    }

}
