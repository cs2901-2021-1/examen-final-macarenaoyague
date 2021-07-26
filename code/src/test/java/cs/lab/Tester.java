package cs.lab;

import org.testng.Assert;
import org.testng.annotations.Test;

class Tester {

    @Test
    public void cobertura() {
        var v = Vacunacion.getInstance();
        Assert.assertEquals(v.coberturaVacunacion(), 22935533);
    }

    @Test
    public void grupos() {
        var v = Vacunacion.getInstance();
        v.agregarGrupo(80, 120, 647355);
        v.agregarGrupo(70, 79, 1271842);
        v.agregarGrupo(60, 69, 2221241);
        v.agregarGrupo(50, 59, 3277134);
        v.agregarGrupo(40, 49, 4183174);
        v.agregarGrupo(30, 39, 5031117);
        v.agregarGrupo(18, 29, 6303670);
        Assert.assertEquals(v.numeroGrupos(), 7);
    }

    @Test
    public void login() {
        var v = Vacunacion.getInstance();
        var exito = v.login("macarena", "aneracam");
        Assert.assertTrue(exito);
        exito = v.login("valeria", "valeria");
        Assert.assertFalse(exito);
    }

    @Test
    public void logout() {
        var v = Vacunacion.getInstance();
        var exito = v.logout("macarena");
        Assert.assertTrue(exito);
        exito = v.logout("valeria");
        Assert.assertFalse(exito);
    }

    @Test
    public void avance() {
        var v = Vacunacion.getInstance();
        var parcial = v.avanceParcialVacunacion();
        var total = v.avanceTotalVacunacion();
        Assert.assertEquals(parcial, 0);
        Assert.assertEquals(total, 0);
    }

    @Test
    public void centros(){
        var v = Vacunacion.getInstance();
        var centro = v.darDeAlta("CentroBarranco");
        var primeraDosis= centro.agregarPrimeraDosis(18, 29, 3);
        Assert.assertTrue(primeraDosis);
        var segundaDosis= centro.agregarSegundaDosis(18, 29, 3);
        Assert.assertTrue(segundaDosis);
        var cantidadCentros = v.numeroCentroVacunacion();
        Assert.assertEquals(cantidadCentros, 1);
        v.darDeBaja("CentroBarranco");
        primeraDosis = centro.agregarPrimeraDosis(18, 29, 3);
        Assert.assertFalse(primeraDosis);
        segundaDosis = centro.agregarSegundaDosis(18, 29, 3);
        Assert.assertFalse(segundaDosis);
    }

    @Test
    public void porcentaje(){
        var v = Vacunacion.getInstance();
        var centro = v.darDeAlta("CentroLaMolina");
        centro.agregarPrimeraDosis(80, 120, 647355);
        centro.agregarSegundaDosis(80, 120, 647355);
        var porcentaje = v.porcentajeGrupo(80, 120);
        Assert.assertEquals(porcentaje, 100);
    }

    @Test(threadPoolSize = 50)
    public void stresTest(){
        var v = Vacunacion.getInstance();
        var centro = v.darDeAlta("CentroParalelo");
        var primeraDosis= centro.agregarPrimeraDosis(70, 79, 1);
        Assert.assertTrue(primeraDosis);
        var segundaDosis= centro.agregarSegundaDosis(78, 79, 1);
        Assert.assertTrue(segundaDosis);
    }

    @Test(invocationCount = 10)
    public void responseTimeTestInfo() throws Exception {
        long time = System.currentTimeMillis();
        var v = Vacunacion.getInstance();
        v.porcentajeGrupo(80, 120);
        long responseTime = (System.currentTimeMillis() - time);
        Assert.assertTrue(responseTime < 2000);
    }

    @Test(invocationCount = 10)
    public void responseTimeTestNotificar() throws Exception {
        long time = System.currentTimeMillis();
        var v = Vacunacion.getInstance();
        var centro = v.darDeAlta("CentroParalelo");
        var primeraDosis= centro.agregarPrimeraDosis(70, 79, 1);
        long responseTime = (System.currentTimeMillis() - time);
        Assert.assertTrue(responseTime < 3000);
    }
}