package cs.lab;

import java.util.ArrayList;
import java.util.List;

public class Vacunacion {

    public static final int cobertura = 22935533;
    private static Vacunacion singleton = null;

    public static synchronized Vacunacion getInstance () {
        if (singleton == null)
            singleton = new Vacunacion();
        return singleton;
    }

    List<GrupoEdad> grupos = new ArrayList<>();
    List<CentroVacunacion> centros = new ArrayList<>();

    public void agregarGrupo(int inicioRango, int finalRango, int cobertura) {
        grupos.add(new GrupoEdad (inicioRango, finalRango, cobertura));
    }

    public CentroVacunacion darDeAlta(String nombre){
        var centro = new CentroVacunacion (nombre);
        centros.add(centro);
        return centro;
    }

    public void darDeBaja(String nombre){
        var centro = buscarCentro(nombre);
        if (centro != null) centro.darDeBaja();
    }

    private GrupoEdad buscarGrupo(int inicioRango, int finRango){
        for (GrupoEdad grupo : grupos)
            if (grupo.inicioRango == inicioRango && grupo.finalRango == finRango)
                return grupo;
        return null;
    }

    private CentroVacunacion buscarCentro(String nombre){
        for (CentroVacunacion centro : centros)
            if (centro.nombre.equals(nombre))
                return centro;
        return null;
    }

    public void primeraDosis(int inicioRango, int finRango, int cantidadPersonas){
        var grupo = buscarGrupo(inicioRango, finRango);
        if (grupo != null) grupo.agregarPrimeraDosis(cantidadPersonas);
    }

    public void segundaDosis(int inicioRango, int finRango, int cantidadPersonas){
        var grupo = buscarGrupo(inicioRango, finRango);
        if (grupo != null) grupo.agregarSegundaDosis(cantidadPersonas);
    }

    public int numeroPersonasVacunadasTotalmente(){
        var total = 0;
        for (GrupoEdad grupo : grupos)
            total += grupo.getVacunadosTotalmente();
        return total;
    }

    public int numeroPersonasVacunadasParcialmente(){
        var parcial = 0;
        for (GrupoEdad grupo : grupos)
            parcial += grupo.getVacunadosParcialmente();
        return parcial;
    }

    public float avanceTotalVacunacion(){
        var total = numeroPersonasVacunadasTotalmente();
        float avanceTotal = 0;
        if (total != 0)
            avanceTotal = (float)total*100/cobertura;
        return avanceTotal;
    }

    public float avanceParcialVacunacion(){
        var parcial = numeroPersonasVacunadasParcialmente();
        float avanceParcial = 0;
        if (parcial != 0)
            avanceParcial = (float)parcial*100/cobertura;
        return avanceParcial;
    }

    public int coberturaVacunacion(){
        return cobertura;
    }

    public int numeroCentroVacunacion(){
        return centros.size();
    }

    public int numeroGrupos(){
        return grupos.size();
    }

    public float porcentajeGrupo(int edadInicio, int edadFinal){
        var grupo = buscarGrupo(edadInicio, edadFinal);
        if (grupo == null) throw new NullPointerException();
        return grupo.porcentajeVacunados();
    }

}
