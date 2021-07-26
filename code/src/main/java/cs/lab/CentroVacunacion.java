package cs.lab;

public class CentroVacunacion {

    private String nombre;
    boolean deBaja;

    CentroVacunacion(String nombre){
        this.nombre = nombre;
        deBaja = false;
    }

    public void darDeBaja(){
        deBaja = true;
    }

    public boolean agregarPrimeraDosis(int inicioRango, int finRango, int cantidadPersonas){
        if (deBaja) return false;
        var vacunacion = Vacunacion.getInstance();
        vacunacion.primeraDosis(inicioRango, finRango, cantidadPersonas);
        return true;
    }

    public boolean agregarSegundaDosis(int inicioRango, int finRango, int cantidadPersonas){
        if (deBaja) return false;
        var vacunacion = Vacunacion.getInstance();
        vacunacion.segundaDosis(inicioRango, finRango, cantidadPersonas);
        return true;
    }

    public String getNombre(){
        return nombre;
    }

}
