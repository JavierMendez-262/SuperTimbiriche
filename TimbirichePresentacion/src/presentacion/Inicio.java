package presentacion;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import objetosNegocio.Jugador;

/**
 * Clase de inicio.
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public class Inicio extends javax.swing.JFrame {
    private Jugador jugador;
    private boolean crearJuego = false;
    private Sala s;
    private String ip;
    
    /**
     * Constructor principal.
     * @param jugador 
     */
    public Inicio(Jugador jugador) {
        centraVentana();
        initComponents();
        boton3.setVisible(false);
        this.jugador = jugador;
        avatar.setIcon(jugador.getAvatar());
        loggeadoLbl.setText("Loggeado como: " + jugador.getNickname());
    }
    
    private void centraVentana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width/2, height/2);		

        setLocationRelativeTo(null);
        setIconImage (new ImageIcon(getClass().getResource("/imagenes/icon.jpg")).getImage());
    }

    public void crear(Jugador jugador, int tamanio) {     
        s = new Sala(jugador, tamanio, ip);
        s.setVisible(true);
        setVisible(false);
    }
    
    public void unirse(Jugador jugador) {
        s = new Sala(jugador, ip);
        s.setVisible(true);
        setVisible(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img = new javax.swing.JLabel();
        tituloLbl = new javax.swing.JLabel();
        nuevoJuegoBtn = new javax.swing.JButton();
        avatar = new javax.swing.JLabel();
        loggeadoLbl = new javax.swing.JLabel();
        salirBtn = new javax.swing.JButton();
        unirseJuegoBtn = new javax.swing.JButton();
        boton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Timbiriche.jpg"))); // NOI18N

        tituloLbl.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        tituloLbl.setForeground(new java.awt.Color(0, 153, 255));
        tituloLbl.setText("Timbiriche");

        nuevoJuegoBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nuevoJuegoBtn.setText("Nuevo Juego");
        nuevoJuegoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoJuegoBtnActionPerformed(evt);
            }
        });

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/avatares/Avatar1.jpg"))); // NOI18N

        loggeadoLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loggeadoLbl.setText("Loggeado como:");

        salirBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        salirBtn.setText("Salir");
        salirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBtnActionPerformed(evt);
            }
        });

        unirseJuegoBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        unirseJuegoBtn.setText("Unirse a juego");
        unirseJuegoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirseJuegoBtnActionPerformed(evt);
            }
        });

        boton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        boton3.setText("4 jugadores");
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(img)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(salirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nuevoJuegoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(unirseJuegoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loggeadoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(tituloLbl)))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(img)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevoJuegoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unirseJuegoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(loggeadoLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salirBtn))
                    .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoJuegoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoJuegoBtnActionPerformed
        if (!crearJuego) {
            crearJuego = true;
            nuevoJuegoBtn.setText("2 jugadores");
            unirseJuegoBtn.setText("3 jugadores");
            boton3.setVisible(true);
        } else {
            ip = JOptionPane.showInputDialog(null,"Introduzca la ip");
            crear(jugador, 2);
        } 
    }//GEN-LAST:event_nuevoJuegoBtnActionPerformed

    private void unirseJuegoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirseJuegoBtnActionPerformed
        if (crearJuego){
            ip = JOptionPane.showInputDialog(null,"Introduzca la ip");
            crear(jugador, 3);
        } else {
            ip = JOptionPane.showInputDialog(null,"Introduzca la ip");
            unirse(jugador);
        }
    }//GEN-LAST:event_unirseJuegoBtnActionPerformed

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
        ip = JOptionPane.showInputDialog(null,"Introduzca la ip");
        crear(jugador, 4);
    }//GEN-LAST:event_boton3ActionPerformed

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBtnActionPerformed
        Loggin.getInstancia().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_salirBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatar;
    private javax.swing.JButton boton3;
    private javax.swing.JLabel img;
    private javax.swing.JLabel loggeadoLbl;
    private javax.swing.JButton nuevoJuegoBtn;
    private javax.swing.JButton salirBtn;
    private javax.swing.JLabel tituloLbl;
    private javax.swing.JButton unirseJuegoBtn;
    // End of variables declaration//GEN-END:variables

}
