package RepasoExamenes.examen_alvaro_quispe_tcp_udp;

import java.io.Serializable;

public class Asignatura implements Serializable {
    int id;
    String nombreAsignatura;

    public int getId() {
        return id;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public Asignatura() {

    }

    public Asignatura(int id, String nombreAsignatura) {
        this.id = id;
        this.nombreAsignatura = nombreAsignatura;

    }
}
