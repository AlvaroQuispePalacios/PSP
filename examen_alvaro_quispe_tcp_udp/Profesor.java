package examen_alvaro_quispe_tcp_udp;

import java.io.Serializable;

public class Profesor implements Serializable {
    int idProfesor;
    String nombre;
    Asignatura[] asignaturas;
    Especialidad espe;

    public int getIdProfesor() {
        return idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public Especialidad getEspe() {
        return espe;
    }

    public Profesor() {

    }

    public Profesor(int idProfesor, String nombre, Asignatura[] asignaturas, Especialidad espe) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.asignaturas = asignaturas;
        this.espe = espe;

    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + this.nombre + ", " + "Especialidad: " + this.espe.getId() + " - "+ this.espe.getNombreEspecialidad());
        for (Asignatura asignatura : asignaturas) {
            System.out.println("\t Asignatura: " +  asignatura.getId() + " - " + asignatura.getNombreAsignatura());
        }
    }
}
