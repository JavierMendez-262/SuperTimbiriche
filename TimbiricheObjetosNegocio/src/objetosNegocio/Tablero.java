package objetosNegocio;

import interfaces.IMarcador;
import interfaces.ITurnos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que hace la funcionalidad del tablero.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Tablero implements ActionListener {
    
    private ArrayList <Jugador> jugadores;
    private Jugador jugadorC;
    private int i = 0, tamanio, iter = 0;
    private int[] turnos;
    private boolean cont1=false, comenzado = false;
    private Linea[][] matriz;
    private Marcador marcador;
    private IMarcador iM;
    private ITurnos iT;

    /**
     * Constructor principal.
     * @param jugadorC
     * @param jugadores
     * @param turnos 
     */
    public Tablero(Jugador jugadorC, ArrayList <Jugador> jugadores, int[] turnos, int tamanio, Linea[][] matriz, Marcador marcador, IMarcador iM, ITurnos iT){
        this.jugadores = jugadores;
        this.turnos = turnos;
        this.jugadorC = jugadorC;
        this.tamanio = tamanio;
        this.matriz = matriz;
        this.marcador = marcador;
        this.iM = iM;
        this.iT = iT;
        iteraciones();
    }

    /**
     * Recibe la matriz y acomoda el tablero.
     * @param add 
     */
    public Linea[][] acomodar(boolean add){
        int n1=0, n2=0, n3=0, n4=0;
        if (tamanio == 2) {
            n1 = 0;
            n2 = 11;
            n3 = 73;
            n4 = 81;
        } else if (tamanio == 3){
            n1 = 0;
            n2 = 5;
            n3 = 35;
            n4 = 39;
        } else {
            n1 = 0;
            n2 = 3;
            n3 = 17;
            n4 = 19;
        }
        
        for(int x=0;x<iter;x++){
            for(int y=0;y<iter;y++){
                if(add){
                    matriz[x][y]=new Linea();
                    matriz[x][y].addActionListener(this);
                }else{
                    matriz[x][y].setOwner(null);
                    matriz[x][y].setTomado(false);
                }
                if(x%2==0){
                    if(y%2==0){
                        matriz[x][y].setBounds(n1+(n4*(y/2)),n1+(n4*(x/2)),n2,n2);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setTomado(true);
                        matriz[x][y].setOwner(new Jugador ("Nulo", Color.black));
                        matriz[x][y].setEnabled(false);
                    }else{
                        matriz[x][y].setBounds(n2+(n4*(y/2)),n1+(n4*(x/2)),n3,n2);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                    }
                }else{
                    if(y%2==0){
                        matriz[x][y].setBounds(n1+(n4*(y/2)),n2+(n4*(x/2)),n2,n3);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                    }else{
                        matriz[x][y].setBounds(n2+(n4*(y/2)),n2+(n4*(x/2)),n3,n3);
                        matriz[x][y].setCuadro(true);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                        matriz[x][y].setEnabled(false);
                    }
                }    
            }
        }
        return matriz;
    }
    
    /**
     * Revisa si se han creado cuadros y otorga puntos a los jugadores, si se 
     * completa un cuadro, el jugador que lo hizo vuelve a tener el turno.
     * @param player
     * @param matriz
     */
    public void check(Jugador player, Linea[][] matriz){
        boolean doble = false;
        for(int x=0;x<iter;x++){
            for(int y=0;y<iter;y++){
                if(matriz[x][y].getCuadro() && !matriz[x][y].getTomado()){
                    if(matriz[x-1][y].getTomado() && matriz[x+1][y].getTomado() 
                            && matriz[x][y-1].getTomado() 
                            && matriz[x][y+1].getTomado()){
                        matriz[x][y].setOwner(player);
                        matriz[x][y].setTomado(true);
                        int v = 0;
                        for (Jugador j : jugadores) {
                            if (player.getNickname().equalsIgnoreCase(j.getNickname())) {
                                if (v == 0) {
                                    marcador.setpJ1(marcador.getpJ1()+1);
                                } else if (v == 1){
                                    marcador.setpJ2(marcador.getpJ2()+1);
                                } else if (v == 2){
                                    marcador.setpJ3(marcador.getpJ3()+1);
                                } else if (v == 3){
                                    marcador.setpJ4(marcador.getpJ4()+1);
                                }
                            } else {
                                v++;
                            }
                        }
                        if (!doble) {
                            i--;
                        }
                        doble = true;
                        int i2 = i+1;
                        if (i2 >= tamanio) {
                            i2 = 0;
                        }
                        iT.turno(jugadores.get(turnos[i2]));
                    }
                }
            }
        }
        iM.setMarcador(marcador);
        iM.puntajes();
    }
    
//    /**
//     * Regresa el marcador actual.
//     * @return 
//     */
//    public Marcador getMarcador (){
//        return marcador;
//    }
    
    /**
     * Termina el juego.
     */
    public void finJuego(){
        int [] puntajes =  new int [4];
        puntajes[0] = marcador.getpJ1();
        puntajes[1] = marcador.getpJ2();
        puntajes[2] = marcador.getpJ3();
        puntajes[3] = marcador.getpJ4();
        boolean empate = false;
        int pMayor = 0, jMayor = 0;
        
        for (int p : puntajes) {
            if (p > pMayor) {
                pMayor = p;
                empate = false;
                jMayor++;
            } else if (p == pMayor){
                empate = true;
                jMayor++;
            }
        }
        
        String punt = "Puntajes: ";
        if (tamanio == 2) {
            punt = punt + jugadores.get(0).getNickname() + ": " + Integer.toString(marcador.getpJ1()) + " " + jugadores.get(1).getNickname() + ": " + Integer.toString(marcador.getpJ2());
        } else if (tamanio == 3){
            punt = punt + jugadores.get(0).getNickname() + ": " + Integer.toString(marcador.getpJ1()) + " " + jugadores.get(1).getNickname() + ": " + Integer.toString(marcador.getpJ2())
            + " " + jugadores.get(2).getNickname() + ": " + Integer.toString(marcador.getpJ3());
        } else if (tamanio == 4){
            punt = punt + jugadores.get(0).getNickname() + ": " + Integer.toString(marcador.getpJ1()) + " " + jugadores.get(1).getNickname() + ": " + Integer.toString(marcador.getpJ2())
            + " " + jugadores.get(2).getNickname() + ": " + Integer.toString(marcador.getpJ3()) + " " + jugadores.get(3).getNickname() + ": " + Integer.toString(marcador.getpJ4());
        }
        
        
        
        if (empate) {
            JOptionPane.showMessageDialog(null, "Empate \n" + punt);
        } else {
            JOptionPane.showMessageDialog(null, "¡Felicidades " + jugadores.get(jMayor-1).getNickname() + "!\n" + punt);
        }
        
        System.exit(0); 
        System.exit(0); 
    }
    
//    /*
//    Regresa un boolean con la respuesta si el juego ya comenzó.
//    */
//    public boolean comenzado (){
//        return comenzado;
//    }
    
    /**
     * Cambia la variable de iteraciones según el tamaño del tablero.
     */
    public void iteraciones (){
        if (tamanio == 2) {
            iter = 19;
        } else if (tamanio == 3){
            iter = 39;
        } else {
            iter = 79;
        }
    }
    
//    /**
//     * Define la matriz del tablero.
//     * @param matriz 
//     */
//    public void setMatriz (Linea[][] matriz){
//        this.matriz = matriz;
//    }
//    
//    public Linea[][] getMatriz (){
//        return matriz;
//    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (comenzado){
            if (i == tamanio) {
                i = 0;
            }
            
            int i2 = i+1;
            if (i2 >= tamanio) {
                i2 = 0;
            }
            
            Integer x=0;
            Integer y=0;
            boolean ban=false, tomado = false;
            for(x=0;x<iter;x++){
                for(y=0;y<iter;y++){
                    if(ae.getSource()== getLinea(x, y)){
                        if (getLinea(x, y).getTomado()) {
                            tomado = true;
                        } else {
                            getLinea(x, y).setTomado(true);
                            getLinea(x, y).setBackground(jugadores.get(turnos[i]).getColor());
                            getLinea(x, y).setOwner(jugadores.get(turnos[i]));
                            iT.turno(jugadores.get(turnos[i2]));
                            ban=true;
                            break;
                        }
                    }
                }
                if(ban)
                    break;
            }
            if (!tomado) {
                check(jugadores.get(turnos[i]), matriz);
                i++;
                if(ganador()){
                    finJuego();
                }          
            }
        }
    }
    
    /**
     * Verifica si alguien ganó
     * @return 
     */
    public boolean ganador(){
        for(int x=0;x<iter;x++){
            for(int y=0;y<iter;y++){
                if(!getLinea(x,y).getTomado())
                    return false;
            }
        }
        return true;
    }

    /**
     * Regresa la línea correspondiente a los ejes X y Y.
     * @param x
     * @param y
     * @return Linea en X y Y.
     */
    public Linea getLinea(int x, int y) {
        return matriz[x][y];
    }
    
    public void reColorear() {
        for(int x=0;x<iter;x++)
            for(int y=0;y<iter;y++){
                if(matriz[x][y].getTomado()){            
                getLinea(x, y).setTomado(matriz[x][y].getTomado());
                getLinea(x, y).setOwner(matriz[x][y].getOwner());
                getLinea(x, y).setBackground(getLinea(x, y).getOwner().getColor()); 
                }
            }
    }

//    public void reAcomodar(int x, int y, Jugador jugador2) {
//        iNegocio.nombres(jugadorC.getNickname(), jugador2.getNickname());
//        try{
//            iNegocio.getLinea(x, y).setTomado(true);
//            iNegocio.getLinea(x, y).setBackground(Color.blue);
//            check(jugador2,iNegocio.getMatriz());
//        }
//        catch(Exception e){
//        }
//    }

    /**
     * Cambia el booleano comenzado para saber que el juego ha iniciado.
     */
    public void iniciar() {
        comenzado = true;
    }    
    
}
