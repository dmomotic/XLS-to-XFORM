/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

/**
 *
 * @author damon
 */
public class CError {
    private String desripcion;
    private Tipo tipo;

 
    
    public static enum Tipo{
        LEXICO,
        SINTACTICO,
        SEMANTICO,
        GENERAL
    }

    public String getDesripcion() {
        return desripcion;
    }

    public void setDesripcion(String desripcion) {
        this.desripcion = desripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public CError(String desripcion, Tipo tipo) {
        this.desripcion = desripcion;
        this.tipo = tipo;
    }
}
