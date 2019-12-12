package objetosNegocio;

import interfaces.IMarcador;
import interfaces.ITurnos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que hace la funcionalidad del tablero.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Tablero {
    
    private Jugadores jugadores;
    private int i = 0, tamanio, iter;
    private int[] turnos;
    private boolean cont1=false, conectado=false;;
    private Forma[][] matriz;
    private Marcador marcador;
    private IMarcador iM;
    private ITurnos iT;

    /**
     * Constructor principal.
     * @param jugadorC
     * @param jugadores
     * @param turnos 
     */
    public Tablero(Jugadores jugadores, int[] turnos, int tamanio, Forma[][] matriz, Marcador marcador, int iter, IMarcador iM, ITurnos iT){
        this.jugadores = jugadores;
        this.turnos = turnos;
        this.tamanio = tamanio;
        this.matriz = matriz;
        this.marcador = marcador;
        this.iter = iter;
        this.iM = iM;
        this.iT = iT;
    }
    
    /**
     * Revisa si se han creado cuadros y otorga puntos a los jugadores, si se 
     * completa un cuadro, el jugador que lo hizo vuelve a tener el turno.
     * @param player
     * @param matriz
     */
    public boolean check(Jugador player, Forma[][] matriz){
        boolean doble = false;
        for(int x=0;x<iter;x++){
            for(int y=0;y<iter;y++){
                if(matriz[x][y].getClass().toString().equals("class objetosNegocio.Cuadro") && !matriz[x][y].getTomado()){
                    if(matriz[x-1][y].getTomado() && matriz[x+1][y].getTomado() 
                            && matriz[x][y-1].getTomado() 
                            && matriz[x][y+1].getTomado()){
                        matriz[x][y].setTomado(true);
                        matriz[x][y].setOwner(player);
                        matriz[x][y].llenar();
                        int v = 0;
                        for (int j = 0; j < jugadores.size(); j++) {
                            int [] pun = marcador.getPuntajes();
                            if (player.getNickname().equalsIgnoreCase(jugadores.get(j).getNickname())) {
                                pun[v] = pun[v]+1;
                                marcador.setPuntajes(pun);
//                                if (v == 0) {
//                                    marcador.setpJ1(marcador.getpJ1()+1);
//                                } else if (v == 1){
//                                    marcador.setpJ2(marcador.getpJ2()+1);
//                                } else if (v == 2){
//                                    marcador.setpJ3(marcador.getpJ3()+1);
//                                } else if (v == 3){
//                                    marcador.setpJ4(marcador.getpJ4()+1);
//                                }
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
//                        iT.turno(jugadores.get(turnos[i2]));
                    }
                }
            }
        }
        iM.setMarcador(marcador);
        iM.puntajes();
        return doble;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setMatriz(Forma[][] matriz) {
        this.matriz = matriz;
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
        int [] puntajes =  marcador.getPuntajes();
//        puntajes[0] = marcador.getpJ1();
//        puntajes[1] = marcador.getpJ2();
//        puntajes[2] = marcador.getpJ3();
//        puntajes[3] = marcador.getpJ4();
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
        
        String punt = "Puntajes: \n";
        for (int j = 0; j < jugadores.size(); j++) {
            punt = punt + jugadores.get(j).getNickname() + ": " + Integer.toString(puntajes[j]) + "\n";
        }
        
//        if (tamanio == 2) {
//            punt = punt + jugadores.get(0).getNickname() + ": " + Integer.toString(marcador.getpJ1()) + " " + jugadores.get(1).getNickname() + ": " + Integer.toString(marcador.getpJ2());
//        } else if (tamanio == 3){
//            punt = punt + jugadores.get(0).getNickname() + ": " + Integer.toString(marcador.getpJ1()) + " " + jugadores.get(1).getNickname() + ": " + Integer.toString(marcador.getpJ2())
//            + " " + jugadores.get(2).getNickname() + ": " + Integer.toString(marcador.getpJ3());
//        } else if (tamanio == 4){
//            punt = punt + jugadores.get(0).getNickname() + ": " + Integer.toString(marcador.getpJ1()) + " " + jugadores.get(1).getNickname() + ": " + Integer.toString(marcador.getpJ2())
//            + " " + jugadores.get(2).getNickname() + ": " + Integer.toString(marcador.getpJ3()) + " " + jugadores.get(3).getNickname() + ": " + Integer.toString(marcador.getpJ4());
//        }
        
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
    public Forma getLinea(int x, int y) {
        return matriz[x][y];
    }
    
    public void reColorear() {
        for(int x=0;x<iter;x++)
            for(int y=0;y<iter;y++){
                if(matriz[x][y].getTomado()){            
                getLinea(x, y).setTomado(matriz[x][y].getTomado());
                getLinea(x, y).setOwner(matriz[x][y].getOwner());
                //Obtiene las lineas en la posicion x & y, & establece su color. Al de si misma con el nuevo color de poseedor de la linea
                getLinea(x, y).setBackground(getLinea(x, y).getOwner().getColor()); 
                }
            }
    }

    public void reAcomodar(int x, int y, Jugador jugador) {
        try{
            getLinea(x, y).setTomado(true);
            getLinea(x, y).setBackground(jugador.getColor());
            check(jugador,matriz);
        }
        catch(Exception e){
        }
        reColorear();
    }

    public void setJugadores(Jugadores jugadores) {
        this.jugadores = jugadores;
    }

}
