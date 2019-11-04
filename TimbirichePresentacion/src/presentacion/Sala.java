package presentacion;

import interfaces.IMarcador;
import interfaces.ITurnos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import objetosNegocio.Forma;
import objetosNegocio.Jugador;
import objetosNegocio.Marcador;
import objetosNegocio.Tablero;

/**
 * Clase para crear el tablero.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Sala extends javax.swing.JFrame implements IMarcador, ITurnos{
    private JColorChooser selColor = new JColorChooser();
    private Forma[][] matriz;
    private Jugador jugador1, jugador2, jugador3, jugador4;
    private int tamanio;
    private Tablero tablero;
    private Marcador marcador;
    private int[] turnos;
    private ArrayList <Jugador> jugadores;
    
    /**
     * Constructor principal.
     * @param jugador 
     */
    public Sala(Jugador jugador, int tamanio) {
        centraVentana();
        initComponents();
        jugador1 = jugador;
        this.tamanio = tamanio;
        
        crearJugadoresProvisionales();
        coloresDefecto();
        crearMarcador();
        sortearTurnos();
        crearTablero();
        crearPanel();
    }
    
    private void centraVentana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width/2, height/2);		

        setLocationRelativeTo(null);
        setIconImage (new ImageIcon(getClass().getResource("/imagenes/icon.jpg")).getImage());
    }
    
    /**
     * Crea los jugadores mientras no hay conexión remota.
     */
    public void crearJugadoresProvisionales (){
        jugador1.setColor(Color.blue);
        avatar1.setIcon(jugador1.getAvatar());
        nickJugador1.setText(jugador1.getNickname());
        jugador2 = new Jugador("Lorem", avatar2.getIcon(), Color.red);
        jugador3 = new Jugador("Ipsum", avatar3.getIcon(), Color.green);
        jugador4 = new Jugador("Dolor", avatar4.getIcon(), Color.yellow);
        nickJugador2.setText(jugador2.getNickname());
        nickJugador3.setText(jugador3.getNickname());
        nickJugador4.setText(jugador4.getNickname());
        if (tamanio == 2) {
            avatar3.setVisible(false);
            nickJugador3.setVisible(false);
            puntajeJ3.setVisible(false);
            colorBtn3.setVisible(false);
            
            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        } else if (tamanio == 3){
            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        }
    }
    
    /**
     * Pone los colores por defecto a los jugadores.
     */
    public void coloresDefecto (){
        colorBtn1.setBackground(jugador1.getColor());
        colorBtn2.setBackground(jugador2.getColor());
        colorBtn3.setBackground(jugador3.getColor());
        colorBtn4.setBackground(jugador4.getColor());
    }
    
    /**
     * Crea un nuevo marcador para la sala.
     */
    public void crearMarcador (){
        int [] puntajes = new int [4];
        for (int i = 0; i < puntajes.length; i++) {
            puntajes[i] = 0;
        }
        marcador = new Marcador(puntajes);
    }
    
    /**
     * Sortea los turnos de los jugadores.
     */
    public void sortearTurnos (){
        List<Integer> num = new ArrayList<>(tamanio);
        for (int i=0;i<tamanio;i++){
            num.add(i);
        }
        Random random = new Random();
        turnos = new int [tamanio];
        
        int i = 0;
        while (num.size()>=1){
            int randomIndex = random.nextInt(num.size());
            turnos[i] = num.get(randomIndex);
            num.remove(randomIndex);
            i++;
        }
        
        jugadores = new ArrayList<>(turnos.length);
        
        if (tamanio == 2) {
            jugadores.add(jugador1);
            jugadores.add(jugador2);        
        } else if (tamanio == 3){
            jugadores.add(jugador1);
            jugadores.add(jugador2);
            jugadores.add(jugador3);
        } else {
            jugadores.add(jugador1);
            jugadores.add(jugador2);
            jugadores.add(jugador3);
            jugadores.add(jugador4);
        }
        
        for (int j = 0; j < turnos.length; j++) {
            jugadores.get(j).setTurno(turnos[j]);
        }
       
    }
    
    /**
     * Crea un nuevo tablero para la sala.
     */
    public void crearTablero (){
        if (tamanio == 2) {
            matriz=new Forma[19][19];
        } else if (tamanio == 3){
            matriz=new Forma[39][39];
        } else {
            matriz=new Forma[79][79];
        }
        
        tablero = new Tablero(jugador1, jugadores, turnos, tamanio, matriz, marcador, this, this);
    }
    
    /**
     * Crea un nuevo panel de tablero.
     */
    public void crearPanel (){
        this.setResizable(false);
        setBackground(Color.WHITE);
      
        for (Forma[] f : tablero.acomodar(true)) {
            for (Forma l : f) {
                pnlTablero.add(l);
            }
        }
    }
    
    @Override
    public void puntajes() {
        int[] pun = marcador.getPuntajes();
        
        puntajeJ1.setText("Puntaje: " + Integer.toString(marcador.getPuntajes()[0]));
        puntajeJ2.setText("Puntaje: " + Integer.toString(marcador.getPuntajes()[1]));
        puntajeJ3.setText("Puntaje: " + Integer.toString(marcador.getPuntajes()[2]));
        puntajeJ4.setText("Puntaje: " + Integer.toString(marcador.getPuntajes()[3]));
    }
 
    @Override
    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }
    
    @Override
    public void turno(Jugador jugador) {
        String nombreJug = jugador.getNickname();
        if (nickJugador1.getText().equalsIgnoreCase(nombreJug)) {
            nickJugador1.setForeground(Color.red);
            nickJugador2.setForeground(Color.black);
            nickJugador3.setForeground(Color.black);
            nickJugador4.setForeground(Color.black);
        } else if (nickJugador2.getText().equalsIgnoreCase(nombreJug)){
            nickJugador1.setForeground(Color.black);
            nickJugador2.setForeground(Color.red);
            nickJugador3.setForeground(Color.black);
            nickJugador4.setForeground(Color.black);
        } else if (nickJugador3.getText().equalsIgnoreCase(nombreJug)){
            nickJugador1.setForeground(Color.black);
            nickJugador2.setForeground(Color.black);
            nickJugador3.setForeground(Color.red);
            nickJugador4.setForeground(Color.black);
        } else if (nickJugador4.getText().equalsIgnoreCase(nombreJug)){
            nickJugador1.setForeground(Color.black);
            nickJugador2.setForeground(Color.black);
            nickJugador3.setForeground(Color.black);
            nickJugador4.setForeground(Color.red);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avatar1 = new javax.swing.JLabel();
        colorBtn1 = new javax.swing.JButton();
        avatar2 = new javax.swing.JLabel();
        colorBtn2 = new javax.swing.JButton();
        avatar3 = new javax.swing.JLabel();
        colorBtn3 = new javax.swing.JButton();
        avatar4 = new javax.swing.JLabel();
        colorBtn4 = new javax.swing.JButton();
        nickJugador1 = new javax.swing.JLabel();
        nickJugador2 = new javax.swing.JLabel();
        nickJugador3 = new javax.swing.JLabel();
        nickJugador4 = new javax.swing.JLabel();
        pnlTablero = new javax.swing.JPanel();
        puntajeJ1 = new javax.swing.JLabel();
        puntajeJ2 = new javax.swing.JLabel();
        puntajeJ3 = new javax.swing.JLabel();
        puntajeJ4 = new javax.swing.JLabel();
        iniciarBtn = new javax.swing.JButton();
        rendirseBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Timbiriche");

        avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/avatares/Avatar1.jpg"))); // NOI18N

        colorBtn1.setText("Color");
        colorBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn1ActionPerformed(evt);
            }
        });

        avatar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/avatares/Avatar2.jpg"))); // NOI18N

        colorBtn2.setText("Color");
        colorBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn2ActionPerformed(evt);
            }
        });

        avatar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/avatares/Avatar3.jpg"))); // NOI18N

        colorBtn3.setText("Color");
        colorBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn3ActionPerformed(evt);
            }
        });

        avatar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/avatares/Avatar4.jpg"))); // NOI18N

        colorBtn4.setText("Color");
        colorBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorBtn4ActionPerformed(evt);
            }
        });

        nickJugador1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador1.setText("Jugador 1");

        nickJugador2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador2.setText("Jugador 2");

        nickJugador3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador3.setText("Jugador 3");

        nickJugador4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador4.setText("Jugador 4");

        pnlTablero.setBorder(new javax.swing.border.MatteBorder(null));
        pnlTablero.setPreferredSize(new java.awt.Dimension(746, 746));

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );

        puntajeJ1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        puntajeJ1.setText("Puntaje: 0");

        puntajeJ2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        puntajeJ2.setText("Puntaje: 0");

        puntajeJ3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        puntajeJ3.setText("Puntaje: 0");

        puntajeJ4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        puntajeJ4.setText("Puntaje: 0");

        iniciarBtn.setText("Iniciar");
        iniciarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarBtnActionPerformed(evt);
            }
        });

        rendirseBtn.setText("Rendirse");
        rendirseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rendirseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(iniciarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rendirseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nickJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(colorBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(puntajeJ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(avatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(colorBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nickJugador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(puntajeJ2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(avatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(colorBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nickJugador3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(puntajeJ3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(avatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(colorBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nickJugador4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(puntajeJ4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(colorBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avatar1)
                    .addComponent(avatar2)
                    .addComponent(colorBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avatar3)
                    .addComponent(colorBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avatar4)
                    .addComponent(colorBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickJugador1)
                    .addComponent(nickJugador2)
                    .addComponent(nickJugador3)
                    .addComponent(nickJugador4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puntajeJ1)
                    .addComponent(puntajeJ2)
                    .addComponent(puntajeJ3)
                    .addComponent(puntajeJ4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rendirseBtn)
                    .addComponent(iniciarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colorBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn1ActionPerformed
        jugador1.setColor(selColor.showDialog(null, "Seleccione un Color", jugador1.getColor()));
        colorBtn1.setBackground(jugador1.getColor());
        tablero.reColorear();
    }//GEN-LAST:event_colorBtn1ActionPerformed

    private void colorBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn2ActionPerformed
        jugador2.setColor(selColor.showDialog(null, "Seleccione un Color", jugador2.getColor()));
        colorBtn2.setBackground(jugador2.getColor());
        tablero.reColorear();
    }//GEN-LAST:event_colorBtn2ActionPerformed

    private void colorBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn3ActionPerformed
        jugador3.setColor(selColor.showDialog(null, "Seleccione un Color", jugador3.getColor()));
        colorBtn3.setBackground(jugador3.getColor());
        tablero.reColorear();
    }//GEN-LAST:event_colorBtn3ActionPerformed

    private void colorBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn4ActionPerformed
        jugador4.setColor(selColor.showDialog(null, "Seleccione un Color", jugador4.getColor()));
        colorBtn4.setBackground(jugador4.getColor());
        tablero.reColorear();
    }//GEN-LAST:event_colorBtn4ActionPerformed

    private void iniciarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarBtnActionPerformed
        turno(jugadores.get(turnos[0]));
        JOptionPane.showMessageDialog(this, "Empieza " + jugadores.get(turnos[0]).getNickname());
        tablero.iniciar();
        iniciarBtn.setEnabled(false);
    }//GEN-LAST:event_iniciarBtnActionPerformed

    private void rendirseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rendirseBtnActionPerformed
       
    }//GEN-LAST:event_rendirseBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatar1;
    private javax.swing.JLabel avatar2;
    private javax.swing.JLabel avatar3;
    private javax.swing.JLabel avatar4;
    private javax.swing.JButton colorBtn1;
    private javax.swing.JButton colorBtn2;
    private javax.swing.JButton colorBtn3;
    private javax.swing.JButton colorBtn4;
    private javax.swing.JButton iniciarBtn;
    private javax.swing.JLabel nickJugador1;
    private javax.swing.JLabel nickJugador2;
    private javax.swing.JLabel nickJugador3;
    private javax.swing.JLabel nickJugador4;
    private javax.swing.JPanel pnlTablero;
    private javax.swing.JLabel puntajeJ1;
    private javax.swing.JLabel puntajeJ2;
    private javax.swing.JLabel puntajeJ3;
    private javax.swing.JLabel puntajeJ4;
    private javax.swing.JButton rendirseBtn;
    // End of variables declaration//GEN-END:variables

}
