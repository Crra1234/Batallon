import java.util.LinkedList;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.logging.Logger;

import co.edu.uniquindio.poo.Especializacion;
import co.edu.uniquindio.poo.Rango;
import co.edu.uniquindio.poo.Soldado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import co.edu.uniquindio.poo.Mision;

/**
 * Clase para probar la clase soldado
 * @author David Jose Bolivar Pulido, Maria Fernanda Huertas Monte, Cristian Camilo Morales Robles
 * @since 2025-14
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
public class MisionTest {

    /**
     * Instancia para el manejo de logs
     */
    private static Logger LOG = Logger.getLogger(Mision.class.getName());
    @Test
    void testAsignarSoldadoAMision() {
        LOG.info("Inicio de prueba Asignar soldado...");
        Soldado soldado = new Soldado("Juan", "S001", Rango.CABO, 26, true, Especializacion.MEDICO);
        Mision mision = new Mision("Patrullaje","A123",LocalDate.now(),"España");


        boolean asignado = mision.addPersonalAsignado(soldado);

        assertTrue(asignado);
        assertFalse(soldado.getDisponibilidad());
        LOG.info("...Finalizando prueba Asignar soldado...");
    }

    @Test
    void testLiberarSoldadosAlFinalizarMision() {
        LOG.info("Inicio de prueba Liberar soldado...");
        Soldado soldado = new Soldado("Juan", "S001", Rango.CABO, 26, true, Especializacion.MEDICO);
        Mision mision = new Mision("Patrullaje","A123",LocalDate.now(),"España");
        mision.addPersonalAsignado(soldado);
        assertFalse(soldado.getDisponibilidad());
        mision.freeSoldados();
        assertTrue(soldado.getDisponibilidad());
        LOG.info("...Finalizando prueba Liberar soldado...");
    }

    @Test
    void testBuscarSoldadosPorEspecialidad() {
        LOG.info("...Finalizando prueba BuscarSoldadoPorEspecialidad...");
        Mision mision = new Mision("Patrullaje","A123",LocalDate.now(),"España");
        mision.addPersonalAsignado(new Soldado("Ana", "S001", Rango.SARGENTO, 32 , true, Especializacion.COMUNICACION));
        mision.addPersonalAsignado(new Soldado("Sofia", "S002", Rango.CABO, 23 , true, Especializacion.MEDICO));

        LinkedList<Soldado> medicos = mision.getListaEspecialidad(Especializacion.MEDICO);

        assertEquals(1, medicos.size());
        assertEquals(Especializacion.MEDICO, medicos.get(0).getEspecializacion());
        LOG.info("...Finalizando prueba BuscarSoldadoPorEspecialidad...");
    }

    @Test
    void testObtenerSoldadosDisponiblesPorRango() {
        LOG.info("...Finalizando prueba ObtenerSoldadosDisponiblesPorRango...");
        Mision mision = new Mision("Patrullaje","A123",LocalDate.now(),"España");
        mision.addPersonalAsignado(new Soldado("Ana", "S001", Rango.CABO, 32 , true, Especializacion.COMUNICACION));
        mision.addPersonalAsignado(new Soldado("Sofia", "S002", Rango.CABO, 23 , true, Especializacion.MEDICO));


        LinkedList<Soldado> cabosDisponibles = mision.getListaRango(Rango.CABO);

        assertEquals(2, cabosDisponibles.size());
        LOG.info("...Finalizando prueba ObtenerSoldadosDisponiblesPorRango...");

    }

    @Test
    void testCalcularEdadPromedio() {
        LOG.info("Inicio de prueba calcularEdadPromedio...");
        Mision mision = new Mision("Patrullaje","A123",LocalDate.now(),"España");
        mision.addPersonalAsignado(new Soldado("S001", "Ana", Rango.SARGENTO, 32 , true, Especializacion.COMUNICACION));
        mision.addPersonalAsignado(new Soldado("Sofia", "S002", Rango.CABO, 23 , true, Especializacion.MEDICO));

        int promedio = mision.getPromedioEdadSoldados();

        assertEquals(28, promedio);
        LOG.info("...Finalizando prueba calcularEdadPromedio...");

    }

    @Test
    void testBuscarSoldadoPorId() {
        LOG.info("...Finalizando prueba BuscarSoldadoPorId...");
        Mision mision = new Mision("Patrullaje","A123",LocalDate.now(),"España");
        mision.addPersonalAsignado(new Soldado("Ana", "S001", Rango.CABO, 32 , true, Especializacion.COMUNICACION));

        Soldado encontrado = mision.getSoldadoById("S001");

        assertNotNull(encontrado);
        assertEquals("Marco", encontrado.getNombre());
        LOG.info("...Finalizando prueba BuscarSoldadoPorId...");
    }
}


