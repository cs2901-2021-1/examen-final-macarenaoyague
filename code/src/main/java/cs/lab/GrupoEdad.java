package cs.lab;

public class GrupoEdad {

    public int inicioRango;
    public int finalRango;
    public int cobertura;
    public int vacunadosTotalmente;
    public int vacunadosParcialmente;

    GrupoEdad(int inicioRango, int finalRango, int cobertura) {
        this.inicioRango = inicioRango;
        this.finalRango = finalRango;
        this.cobertura = cobertura;
        vacunadosParcialmente = 0;
        vacunadosTotalmente = 0;
    }

    public boolean agregarPrimeraDosis(int cantidadPersonas){
        if (vacunadosTotalmente + vacunadosParcialmente == cobertura)
            return false;
        vacunadosParcialmente += cantidadPersonas;
        return true;
    }

    public boolean agregarSegundaDosis(int cantidadPersonas){
        if (vacunadosParcialmente == 0)
            return false;
        vacunadosParcialmente -= cantidadPersonas;
        vacunadosTotalmente += cantidadPersonas;
        return true;
    }

    public float porcentajeVacunados(){
        return (float)vacunadosTotalmente*100/cobertura;
    }

    public int getVacunadosTotalmente(){
        return vacunadosTotalmente;
    }

    public int getVacunadosParcialmente(){
        return vacunadosParcialmente;
    }
}
