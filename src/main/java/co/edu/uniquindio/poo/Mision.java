package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.LinkedList;

public class Mision extends Batallon{
    private String id;
    private LocalDate fechaMision;
    private String ubicacion;
    private LinkedList<Soldado> listaSoldados;

    public Mision(String Nombre, String id,LocalDate fechaMision, String ubicacion) {
        super(Nombre);
        this.id = id;
        this.fechaMision = fechaMision;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaMision() {
        return fechaMision;
    }

    public void setFechaMision(LocalDate fechaMision) {
        this.fechaMision = fechaMision;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public boolean addPersonalAsignado(Soldado soldado){
        for (Soldado soldados: listaSoldados){
            if (soldados.getIdentificador().equals(soldado.getIdentificador()) || !(soldado.getDisponibilidad())){
                return false;
            }
        }
        soldado.setDisponibilidad(false);
        this.listaSoldados.add(soldado);
        return true;
    }

    public void freeSoldados(){
        for (Soldado soldados: listaSoldados){
            soldados.setDisponibilidad(true);
            soldados.removerMision(this);
        }
        listaSoldados.clear();

        }

    public Soldado getSoldado(String identificador){
        for (Soldado soldados: listaSoldados) {
            if (soldados.getIdentificador() == identificador){
                return soldados;
            }
        }
        return null;
    }

    public LinkedList<Soldado> getListaEspecialidad(Especializacion especializacion) {
        LinkedList<Soldado> soldadosfiltrados = new LinkedList<>();
        for (Soldado soldados: listaSoldados){
           if (soldados.getEspecializacion().equals(especializacion)){
               soldadosfiltrados.add(soldados);
           }
       }
        return soldadosfiltrados;
    }

    public LinkedList<Soldado> getListaRango(Rango rango) {
        LinkedList<Soldado> soldadosfiltrados = new LinkedList<>();
        for (Soldado soldados: listaSoldados){
            if (soldados.getRango().equals(rango)){
                soldadosfiltrados.add(soldados);
            }
        }
        return soldadosfiltrados;
    }

    public int getPromedioEdadSoldados(){
        int promedioEdad = 0;
        for (Soldado soldados: listaSoldados){
            promedioEdad += soldados.getEdad();
        }
        return promedioEdad / listaSoldados.size();
    }

    public Soldado getSoldadoById(String identificador) {
        for (Soldado soldados: listaSoldados){
            if (soldados.getIdentificador().equals(identificador)){
                return soldados;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "fechaMision=" + fechaMision +
                ", ubicacion='" + ubicacion + '\'' +
                ", personalAsignado='" + listaSoldados + '\'' +
                '}';
    }
}
