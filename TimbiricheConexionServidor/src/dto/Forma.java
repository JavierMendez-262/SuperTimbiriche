package dto;

import java.awt.Color;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 * Clase para las formas.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Forma extends jButtonForma{
    
     //Dice si la forma ya fue tomada.
    private boolean tomado = false;
    
    //Guarda el jugador dueño de la forma.
    private Jugador owner;
    
    public Forma (){}
    
    public Forma(boolean tomado, Jugador owner){
        this.tomado = tomado;
        this.owner = owner;
    }
    
    public boolean getTomado(){
        return tomado;
    }
    public void setTomado(boolean tomado){
        this.tomado = tomado;
    }

    public Jugador getOwner() {
        return owner;
    }

    public void setOwner(Jugador owner) {
        this.owner = owner;
    }
    
    public void llenar(){
        try{
            this.setText(Character.toString(owner.getNickname().charAt(0)));
                this.setBackground(owner.getColor());
                this.setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                    return Color.black;
                    }
                });
        }
        catch(Exception e){
            this.setText(null);
        }
    }
    
}
