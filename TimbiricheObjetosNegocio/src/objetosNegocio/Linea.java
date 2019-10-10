/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 * Clase para crear líneas.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Linea extends JButton{
    
    public Linea(){}
    
    public Linea(boolean cuadro){
        this.cuadro = cuadro;
    }
    
    //Dice si la línea ya fue tomada.
    private boolean tomado = false;
    
    //Dice si ya se completó un cuadro.
    private boolean cuadro = false;
    
    //Guarda el jugador dueño del cuadro.
    private Jugador owner;
    
    public boolean getTomado(){
        return tomado;
    }
    public void setTomado(boolean tomado){
        this.tomado = tomado;
    }
    
    public Jugador getOwner(){
        return owner;
    }
    public void setOwner(Jugador owner){
        this.owner = owner;
        try{
            if (cuadro) {
                this.setText(Character.toString(owner.getNickname().charAt(0)));
                this.setBackground(owner.getColor());
                this.setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                    return Color.black;
                    }
                });
            }
        }
        catch(Exception e){
            this.setText(null);
        }
    }
    
    public boolean getCuadro(){
        return cuadro;
    }
    public void setCuadro(boolean cuadro){
        this.cuadro = cuadro;
    }
    
}
