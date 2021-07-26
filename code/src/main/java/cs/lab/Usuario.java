package cs.lab;

public class Usuario {

    public String nombre;
    private final String contrasena;

    Usuario(String nombre) {
        this.nombre = nombre;
        var nombreReves = new StringBuilder();
        nombreReves.append(nombre);
        this.contrasena = nombreReves.reverse().toString();
    }

    public boolean validarContrasena(String contrasena) {
        return contrasena.equals(this.contrasena);
    }



}
