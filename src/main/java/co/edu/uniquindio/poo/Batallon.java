package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.LinkedList;

public class Batallon {
    private String nombre;
    private LinkedList<Vehiculo> listaVehiculos;

    public Batallon(String nombre) {
        this.nombre = nombre;
        this.listaVehiculos = new LinkedList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(LinkedList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public String toString() {
        return "DATOS DEL BATALLON \n"
                + " Nombre" + nombre;
    }

    //-------------------Realizacion del CRUD------------------

    //Metodo para buscarVehiculo
    public Vehiculo buscarVehiculo(String id) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getId().equals(id)) {
                return vehiculo;
            }
        }
        return null;
    }

    //Metodo para Almacenar
    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    //Metodo para Obtener
    public LinkedList<Vehiculo> obtenerVehiculo() {
        return listaVehiculos;
    }

    //Metodo para Eliminar
    public boolean eliminarVehiculo(String id) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getId().equals(id)) {
                listaVehiculos.remove(vehiculo);
                return true;
            }
        }
        return false;
    }

    //Metodo para Actualizar
    public boolean actualizarVehiculo(String id, String modelo, int fechaFabricacion, double kilometraje, EstadoOperativo estadoOperativo) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getId().equals(id)) {
                vehiculo.setId(id);
                vehiculo.setModelo(modelo);
                vehiculo.setFechaFabricacion(fechaFabricacion);
                vehiculo.setKilometraje(kilometraje);
                vehiculo.setEstadoOperativo(estadoOperativo);
                return true;

            }
        }
        return false;
    }

    //------------Finalizacion del CRUD------------------

    //Metodo para obtener la lista de los Vehiculos

    public LinkedList<Vehiculo> obtenerVehiculosConMisiones() {
        LinkedList<Vehiculo> vehiculosMisionesCompleto = new LinkedList<>();

        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getMisionCompletadas() > 50) {
                vehiculosMisionesCompleto.add(vehiculo);
            }
        }
        return vehiculosMisionesCompleto;
    }

    //--------------Inicio de metodos del taller Batallon--------------------



    //Metodo para calcular el promedio del kilometraje de cada vehiculo
    public String obtenerPromedioKilometrajeTipo() {
        int contarVehiculoTropas = 0;
        int contarVehiculoBlindado = 0;
        int contarVehiculoApoyo = 0;
        double sumarKilometrajeTropas = 0.0;
        double sumarKilometrajeBlindado = 0.0;
        double sumarKilometrajeApoyo = 0.0;
        double promedioTropas = 0.0;
        double promedioBlindado = 0.0;
        double promedioApoyo = 0.0;

        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo instanceof VehiculoTransporteTropa) {
                contarVehiculoTropas++;
                sumarKilometrajeTropas += vehiculo.getKilometraje();
            } else if (vehiculo instanceof VehiculoBlindado) {
                contarVehiculoBlindado++;
                sumarKilometrajeBlindado += vehiculo.getKilometraje();
            } else if (vehiculo instanceof VehiculoApoyo) {
                contarVehiculoApoyo++;
                sumarKilometrajeApoyo += vehiculo.getKilometraje();
            }

        }
        if (contarVehiculoTropas > 0) {
            promedioTropas = sumarKilometrajeTropas / contarVehiculoTropas;
        }
        if (contarVehiculoBlindado > 0) {
            promedioBlindado = sumarKilometrajeBlindado / contarVehiculoBlindado;
        }
        if (contarVehiculoApoyo > 0) {
            promedioApoyo = sumarKilometrajeApoyo / contarVehiculoApoyo;
        }
        String resultado = "Promedio de Vehiculo Tropas " + promedioTropas + "\n";
        resultado += "Promedio de Vehiculo Blindado " + promedioBlindado + "\n";
        resultado += "Promedio de Vehiculo Apoyo " + promedioApoyo;

        return resultado;
    }

    //Metodo para retornar el vehiculo
    public LinkedList<Vehiculo> listaVehiculosConMayorMisiones(int misionesCompletadas) {
        LinkedList<Vehiculo> vehiculoConMasMisiones = new LinkedList<>();
        int maximoMisiones = 0;
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getMisionCompletadas() > maximoMisiones) {
                maximoMisiones = vehiculo.getMisionCompletadas();
                vehiculoConMasMisiones.clear();
                vehiculoConMasMisiones.add(vehiculo);
            } else if (vehiculo.getMisionCompletadas() == maximoMisiones) {
                vehiculoConMasMisiones.add(vehiculo);
            }
        }
        return vehiculoConMasMisiones;

    }

    //Metodo para ordenar los vehiculos por tipo y modelo

    public LinkedList<Vehiculo> ordenarVehiculoPorTipoModelo(LinkedList<Vehiculo> listaVehiculos, int anioFabricacion) {
        LinkedList<Vehiculo> vehiculoFiltrado = new LinkedList<>();
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getFechaFabricacion() == anioFabricacion) {
                vehiculoFiltrado.add(vehiculo);
            }
        }
        for (int i = 0; i < vehiculoFiltrado.size() - 1; i++) {
            for (int j = 0; j < vehiculoFiltrado.size() - 1 - i; j++) {
                Vehiculo v1 = vehiculoFiltrado.get(j);
                Vehiculo v2 = vehiculoFiltrado.get(j + 1);

                if (v1.getEstadoOperativo().compareTo(v2.getEstadoOperativo()) > 0 ||
                        (v1.getEstadoOperativo().compareTo(v2.getEstadoOperativo()) == 0 && v1.getModelo().compareTo(v2.getModelo()) > 0)) {
                    Vehiculo temporal = v1;
                    vehiculoFiltrado.set(j, v2);
                    vehiculoFiltrado.set(j + 1, temporal);
                }
            }

        }
        return vehiculoFiltrado;

    }
    //Metodo para ordenar la lista de vehiculos por misiones completadas

    public LinkedList<Vehiculo> ordenarVehiculoMision(LinkedList<Vehiculo> listaVehiculos) {
        for (int i = 0; i < listaVehiculos.size() - 1; i++) {
            for (int j = 0; j < listaVehiculos.size() - 1 - i; j++) {
                Vehiculo v1 = listaVehiculos.get(j);
                Vehiculo v2 = listaVehiculos.get(j + 1);

                if (v1.getMisionCompletadas() < v2.getMisionCompletadas()) {
                    Vehiculo temporal = v1;
                    listaVehiculos.set(j, v2);
                    listaVehiculos.set(j + 1, temporal);
                }
            }
        }
        return listaVehiculos;

        //-----------------Fin de metodos del taller Batallon

    }
}



//---------------------------------------------------------
    /*
    public static void ordenarPorPlaca(ArrayList<Vehiculo> vehiculos) {
        int n = vehiculos.size();
        for (int i = 0; i < n - 1; i++) {
            // En cada pasada, se comparan los elementos adyacentes
            for (int j = 0; j < n - i - 1; j++) {
                // Si la placa del vehículo actual es mayor que la del siguiente, se intercambian
                if (vehiculos.get(j).getPlaca().compareTo(vehiculos.get(j + 1).getPlaca()) > 0) {
                    Vehiculo temp = vehiculos.get(j);
                    vehiculos.set(j, vehiculos.get(j + 1));
                    vehiculos.set(j + 1, temp);
                }
            }
        }
    }

    public boolean estaDisponible(String id){
        for(Vehiculo vehiculo:listaVehiculos){
            if(vehiculo.getId().equals(id) && vehiculo.estadoOperativo.equals(EstadoOperativo.DISPONIBLE)){
                return true;
            }
        }
        return false;
    }
}
*/