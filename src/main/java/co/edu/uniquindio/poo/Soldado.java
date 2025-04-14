package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.LinkedList;

public class Soldado extends Batallon {
    private String identificador;
    private Rango rango;
    private Especializacion especializacion;
    private int edad;
    private boolean disponibilidad = true;
    private LinkedList<Mision> listaMisiones;

    public Soldado(String nombre,String Identificador, Rango rango, int edad, boolean disponibilidad, Especializacion especializacion) {
        super(nombre);
        this.identificador = Identificador;
        this.rango = rango;
        this.edad = edad;
        this.disponibilidad = disponibilidad;
        this.especializacion = especializacion;
    }

    public void setIdentificador(String identificador){
        this.identificador = identificador;
    }

    public void setRango(Rango rango){
        this.rango = rango;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public void setDisponibilidad(boolean disponibilidad){
        this.disponibilidad = disponibilidad;
    }

    public void setEspecializacion(Especializacion especializacion){
        this.especializacion = especializacion;
    }

    public void setListaMisiones(LinkedList<Mision> listaMisiones){
        this.listaMisiones = listaMisiones;}

    public String getIdentificador(){
        return this.identificador;}

    public Rango getRango(){
        return this.rango;
    }

    public int getEdad(){
        return this.edad;
    }

    public boolean getDisponibilidad(){
        return this.disponibilidad;
    }

    public Especializacion getEspecializacion() {
        return especializacion;
    }

    public void removerMision(Mision mision){
        this.listaMisiones.remove(mision);
    }

    //Metodo para mostrar todas las misiones, por ubicacion y rango de fecha//

    public LinkedList<Mision> obtenerListaMisiones(String ubicacion, LocalDate fechaInicio, LocalDate fechaFinal) {
        LinkedList<Mision> misionesFiltradas = new LinkedList<>();

        for (Mision mision : listaMisiones) {
            if (mision.getUbicacion().equals(ubicacion) && mision.getFechaMision().isAfter(fechaInicio) || mision.getFechaMision().equals(fechaInicio) &&
                    mision.getFechaMision().isBefore(fechaFinal) || mision.getFechaMision().equals(fechaFinal)) {
                misionesFiltradas.add(mision);

            }
        }
        return misionesFiltradas;
    }

    //Metodo para registar la mision

    public boolean registrarMision(Mision mision, Vehiculo vehiculo) {
        for (Mision misiones : this.listaMisiones) {
            if (mision.getId().equals(misiones.getId())) {
                return false;
            }
        }
        for (Vehiculo vehiculos : this.getListaVehiculos()) {
            if (vehiculos.getId().equals(vehiculo.getId())) {
                listaMisiones.add(mision);
                vehiculo.setMisionCompletadas(vehiculo.getMisionCompletadas() + 1);
                return true;
            }
            }

        return false;
    }

}



