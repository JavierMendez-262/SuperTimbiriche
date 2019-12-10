package objetosNegocio;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.Icon;

/**
 * Clase para crear jugadores.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Jugador implements Serializable {
    private String nickname;
    private Icon avatar;
    private Color color;
    private int turno;
    private boolean listo = false;

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public Jugador(String nickname, Icon avatar, Color color, int turno) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.color = color;
        this.turno = turno;
    }

    public Jugador(String nickname, Icon avatar, Color color) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.color = color;
    }

    public Jugador(String nickname, Icon avatar) {
        this.nickname = nickname;
        this.avatar = avatar;
    }
    
    public Jugador(String nickname, Color color) {
        this.nickname = nickname;
        this.color = color;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Icon getAvatar() {
        return avatar;
    }

    public void setAvatar(Icon avatar) {
        this.avatar = avatar;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
}
