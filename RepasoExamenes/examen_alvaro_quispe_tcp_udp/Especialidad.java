package RepasoExamenes.examen_alvaro_quispe_tcp_udp;

import java.io.Serializable;

public class Especialidad implements Serializable {
    int id;
    String nombreEspecialidad;

    public int getId() {
        return id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public Especialidad() {

    }

    public Especialidad(int id, String nombreEspecialidad) {
        this.id = id;
        this.nombreEspecialidad = nombreEspecialidad;
    }
}
