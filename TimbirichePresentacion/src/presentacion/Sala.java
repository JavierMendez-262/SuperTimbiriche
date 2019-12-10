package presentacion;

import interfaces.IMarcador;
import interfaces.ITurnos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import objetosNegocio.Cuadro;
import objetosNegocio.Forma;
import objetosNegocio.Jugador;
import objetosNegocio.Linea;
import objetosNegocio.Marcador;
import objetosNegocio.Tablero;
import objetosNegocio.Jugadores;
import proxy.ClientProxy;

/**
 * Frame que crea la sala y desarrolla su funcionalidad.
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique
 * Mendoza
 */
public class Sala extends javax.swing.JFrame implements IMarcador, ITurnos, ActionListener {

    private JColorChooser selColor = new JColorChooser();
    private Forma[][] matriz;
    private Jugador jugador1, jugador2, jugador3, jugador4;
    private int tamanio, iter = 0;
    private Tablero tablero;
    private Marcador marcador;
    private int[] turnos;
    private Jugadores jugadores;
    private ArrayList <Jugador> jugadoresAux;
    private boolean comenzado = false;
    private int portNumber = 777;
    private ClientProxy clientProxy;
    private int jugadorNumero;

    /**
     * Constructor principal.
     *
     * @param jugador
     */
    public Sala(Jugador jugador, int tamanio, String ip) {
        this.tamanio = tamanio;
        centraVentana();
        initComponents();
        jugadores = Jugadores.getInstance();
        jugadores.setMaxSize(tamanio);
        try {
            clientProxy = new ClientProxy(ip, portNumber, this);
            clientProxy.establecerTamanio(tamanio);
            clientProxy.unirSala(jugador);
        } catch (IOException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        jugadorNumero = 0;
        coloresDefecto();
        crearMarcador();
        iteraciones();
        crearTablero();
        crearPanel();
        jugadoresAux = jugadores.getJugadores();
    }

    /**
     * Constructor para un jugador que entra a un juego.
     * @param jugador
     * @param ip 
     */
    public Sala(Jugador jugador, String ip) {
        centraVentana();
        initComponents();
        jugadores = Jugadores.getInstance();
        try {
            clientProxy = new ClientProxy(ip, portNumber, this);
            clientProxy.unirSala(jugador);
        } catch (IOException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (jugadores.maxSize() == 0) {
            System.out.print(jugadores.maxSize());
        }
        tamanio = jugadores.maxSize();
        jugadorNumero = jugadores.size() - 1;
        coloresDefecto();
        crearMarcador();
        iteraciones();
        crearTablero();
        crearPanel();
        jugadoresAux = jugadores.getJugadores();
    }

    /**
     * Método para centrar la ventana.
     */
    private void centraVentana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width / 2, height / 2);

        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icon.jpg")).getImage());
    }

    /**
     * Crea los jugadores mientras no hay conexión remota.
     */
    public void crearJugadoresProvisionales() {
        jugador1.setColor(Color.blue);
        avatar1.setIcon(jugador1.getAvatar());
        nickJugador1.setText(jugador1.getNickname());
        if (tamanio == 2) {
            avatar3.setVisible(false);
            nickJugador3.setVisible(false);
            puntajeJ3.setVisible(false);
            colorBtn3.setVisible(false);

            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        } else if (tamanio == 3) {
            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        }
    }

    
    /**
     * Método que modifica los jugadores en la sala al momento de que alguien
     * entra.
     */
    public void entradaJugador() {
//        jugadores = (ArrayList<Jugador>) clientProxy.actualizarJugadores();
        if (jugadores.size() == 1) {
            jugador1 = jugadores.get(0);
            jugador1.setColor(Color.blue);
            avatar1.setIcon(jugador1.getAvatar());
            nickJugador1.setText(jugador1.getNickname());

            avatar2.setVisible(false);
            nickJugador2.setVisible(false);
            puntajeJ2.setVisible(false);
            colorBtn2.setVisible(false);

            avatar3.setVisible(false);
            nickJugador3.setVisible(false);
            puntajeJ3.setVisible(false);
            colorBtn3.setVisible(false);

            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        } else if (jugadores.size() == 2) {
            jugador1 = jugadores.get(0);
            jugador1.setColor(Color.blue);
            avatar1.setIcon(jugador1.getAvatar());
            nickJugador1.setText(jugador1.getNickname());

            jugador2 = jugadores.get(1);
            jugador2.setColor(Color.red);
            avatar2.setIcon(jugador2.getAvatar());
            nickJugador2.setText(jugador2.getNickname());

            avatar2.setVisible(true);
            nickJugador2.setVisible(true);
            puntajeJ2.setVisible(true);
            colorBtn2.setVisible(true);

            avatar3.setVisible(false);
            nickJugador3.setVisible(false);
            puntajeJ3.setVisible(false);
            colorBtn3.setVisible(false);

            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        } else if (jugadores.size() == 3) {
            jugador1 = jugadores.get(0);
            jugador1.setColor(Color.blue);
            avatar1.setIcon(jugador1.getAvatar());
            nickJugador1.setText(jugador1.getNickname());

            jugador2 = jugadores.get(1);
            jugador2.setColor(Color.red);
            avatar2.setIcon(jugador2.getAvatar());
            nickJugador2.setText(jugador2.getNickname());

            jugador3 = jugadores.get(2);
            jugador3.setColor(Color.green);
            avatar3.setIcon(jugador2.getAvatar());
            nickJugador3.setText(jugador2.getNickname());

            avatar2.setVisible(true);
            nickJugador2.setVisible(true);
            puntajeJ2.setVisible(true);
            colorBtn2.setVisible(true);

            avatar3.setVisible(true);
            nickJugador3.setVisible(true);
            puntajeJ3.setVisible(true);
            colorBtn3.setVisible(true);

            avatar4.setVisible(false);
            nickJugador4.setVisible(false);
            puntajeJ4.setVisible(false);
            colorBtn4.setVisible(false);
        } else if (jugadores.size() == 4) {
            jugador1 = jugadores.get(0);
            jugador1.setColor(Color.blue);
            avatar1.setIcon(jugador1.getAvatar());
            nickJugador1.setText(jugador1.getNickname());

            jugador2 = jugadores.get(1);
            jugador2.setColor(Color.red);
            avatar2.setIcon(jugador2.getAvatar());
            nickJugador2.setText(jugador2.getNickname());

            jugador3 = jugadores.get(2);
            jugador3.setColor(Color.green);
            avatar3.setIcon(jugador3.getAvatar());
            nickJugador3.setText(jugador3.getNickname());

            jugador4 = jugadores.get(3);
            jugador4.setColor(Color.green);
            avatar4.setIcon(jugador4.getAvatar());
            nickJugador4.setText(jugador4.getNickname());

            avatar2.setVisible(true);
            nickJugador2.setVisible(true);
            puntajeJ2.setVisible(true);
            colorBtn2.setVisible(true);

            avatar3.setVisible(true);
            nickJugador3.setVisible(true);
            puntajeJ3.setVisible(true);
            colorBtn3.setVisible(true);

            avatar4.setVisible(true);
            nickJugador4.setVisible(true);
            puntajeJ4.setVisible(true);
            colorBtn4.setVisible(true);
        }

        if (tamanio == jugadores.size()) {
            sortearTurnos();
        }
    }

    /**
     * Pone los colores por defecto a los jugadores.
     */
    public void coloresDefecto() {
        colorBtn1.setBackground(Color.blue);
        colorBtn2.setBackground(Color.red);
        colorBtn3.setBackground(Color.green);
        colorBtn4.setBackground(Color.yellow);
    }

    /**
     * Crea un nuevo marcador para la sala.
     */
    public void crearMarcador() {
        int[] puntajes = new int[4];
        for (int i = 0; i < puntajes.length; i++) {
            puntajes[i] = 0;
        }
        marcador = new Marcador(puntajes);
    }

    /**
     * Sortea los turnos de los jugadores.
     */
    public void sortearTurnos() {
        List<Integer> num = new ArrayList<>(tamanio);
        for (int i = 0; i < tamanio; i++) {
            num.add(i);
        }
        Random random = new Random();
        turnos = new int[tamanio];

        int i = 0;
        while (num.size() >= 1) {
            int randomIndex = random.nextInt(num.size());
            turnos[i] = num.get(randomIndex);
            num.remove(randomIndex);
            i++;
        }

        if (tamanio == 2) {
            jugadores.add(jugador1);
            jugadores.add(jugador2);
        } else if (tamanio == 3) {
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
    public void crearTablero() {
        if (tamanio == 2) {
            matriz = new Forma[19][19];
        } else if (tamanio == 3) {
            matriz = new Forma[39][39];
        } else {
            matriz = new Forma[79][79];
        }

        tablero = new Tablero(jugadores, turnos, tamanio, matriz, marcador, iter, this, this);
    }

    /**
     * Crea un nuevo panel de tablero.
     */
    public void crearPanel() {
        this.setResizable(false);
        setBackground(Color.WHITE);

        for (Forma[] f : acomodar(true)) {
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

    public void turno(Jugador jugador) {
        String nombreJug = jugador.getNickname();
        if (nickJugador1.getText().equalsIgnoreCase(nombreJug)) {
            nickJugador1.setForeground(Color.red);
            nickJugador2.setForeground(Color.black);
            nickJugador3.setForeground(Color.black);
            nickJugador4.setForeground(Color.black);
        } else if (nickJugador2.getText().equalsIgnoreCase(nombreJug)) {
            nickJugador1.setForeground(Color.black);
            nickJugador2.setForeground(Color.red);
            nickJugador3.setForeground(Color.black);
            nickJugador4.setForeground(Color.black);
        } else if (nickJugador3.getText().equalsIgnoreCase(nombreJug)) {
            nickJugador1.setForeground(Color.black);
            nickJugador2.setForeground(Color.black);
            nickJugador3.setForeground(Color.red);
            nickJugador4.setForeground(Color.black);
        } else if (nickJugador4.getText().equalsIgnoreCase(nombreJug)) {
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
        nickJugador1.setText("Esperando");

        nickJugador2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador2.setText("Esperando");

        nickJugador3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador3.setText("Esperando");

        nickJugador4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nickJugador4.setText("Esperando");

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
        jugadoresAux = jugadores.getJugadores();
        jugador1.setColor(selColor.showDialog(null, "Seleccione un Color", jugador1.getColor()));
        jugadoresAux.get(0).setColor(jugador1.getColor());
        colorBtn1.setBackground(jugador1.getColor());
        tablero.reColorear();
    }//GEN-LAST:event_colorBtn1ActionPerformed

    private void colorBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorBtn2ActionPerformed
        jugadoresAux = jugadores.getJugadores();
        jugador2.setColor(selColor.showDialog(null, "Seleccione un Color", jugador2.getColor()));
        jugadoresAux.get(1).setColor(jugador2.getColor());
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
        try {
            clientProxy.listo(jugadorNumero);
        } catch (IOException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean estanListos = false;
        while (!estanListos) {
            for (Jugador jugador : jugadores.getJugadores()) {
                if (jugador.isListo() == false) {
                    estanListos = false;
                    break;
                } else {
                    estanListos = true;
                }
            }
        }
        
        turnos = clientProxy.getTurnos();
        turno(jugadores.get(turnos[0]));
        JOptionPane.showMessageDialog(this, "Empieza " + jugadores.get(turnos[0]).getNickname());
        iniciar();
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

    /**
     * Recibe la matriz y acomoda el tablero.
     *
     * @param add
     */
    public Forma[][] acomodar(boolean add) {
        int n1 = 0, n2 = 0, n3 = 0, n4 = 0;
        if (tamanio == 2) {
            n1 = 0;
            n2 = 11;
            n3 = 73;
            n4 = 81;
        } else if (tamanio == 3) {
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

        for (int x = 0; x < iter; x++) {
            for (int y = 0; y < iter; y++) {
                if (add) {
                    matriz[x][y] = new Linea(false, null);
                    matriz[x][y].addActionListener(this);
                } else {
                    matriz[x][y].setOwner(null);
                    matriz[x][y].setTomado(false);
                }
                if (x % 2 == 0) {
                    if (y % 2 == 0) {
                        matriz[x][y].setBounds(n1 + (n4 * (y / 2)), n1 + (n4 * (x / 2)), n2, n2);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setTomado(true);
                        matriz[x][y].setOwner(new Jugador("Nulo", Color.black));
                        matriz[x][y].setEnabled(false);
                    } else {
                        matriz[x][y].setBounds(n2 + (n4 * (y / 2)), n1 + (n4 * (x / 2)), n3, n2);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                    }
                } else {
                    if (y % 2 == 0) {
                        matriz[x][y].setBounds(n1 + (n4 * (y / 2)), n2 + (n4 * (x / 2)), n2, n3);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                    } else {
                        matriz[x][y] = new Cuadro();
                        matriz[x][y].setBounds(n2 + (n4 * (y / 2)), n2 + (n4 * (x / 2)), n3, n3);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                        matriz[x][y].setEnabled(false);
                    }
                }
            }
        }
        tablero.setMatriz(matriz);
        return matriz;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (comenzado) {
            if (tablero.getI() == tamanio) {
                tablero.setI(0);
            }

            int i2 = tablero.getI() + 1;
            if (i2 >= tamanio) {
                i2 = 0;
            }
            
            Integer x = 0;
            Integer y = 0;
            boolean ban = false, tomado = false;
            for (x = 0; x < iter; x++) {
                for (y = 0; y < iter; y++) {
                    if (ae.getSource() == tablero.getLinea(x, y)) {
                        if (tablero.getLinea(x, y).getTomado()) {
                            tomado = true;
                        } else {
                            try {
                                tablero.getLinea(x, y).setTomado(true);
                                tablero.getLinea(x, y).setBackground(jugadores.get(turnos[tablero.getI()]).getColor());
                                tablero.getLinea(x, y).setOwner(jugadores.get(turnos[tablero.getI()]));
                                turno(jugadores.get(turnos[i2]));
                                ban = true;
                                String xS = Integer.toString(x);
                                String yS = Integer.toString(y);
                                clientProxy.pintarLinea(jugadorNumero, xS, yS);
                                break;
                            } catch (IOException ex) {
                                Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
                if (ban) {
                    break;
                }
            }
            if (!tomado) {
                tablero.check(jugadores.get(turnos[tablero.getI()]), matriz);
                tablero.setI(tablero.getI() + 1);
                if (tablero.ganador()) {
                    tablero.finJuego();
                }
            }
        }
    }

    /**
     * Cambia la variable de iteraciones según el tamaño del tablero.
     */
    public void iteraciones() {
        if (tamanio == 2) {
            iter = 19;
        } else if (tamanio == 3) {
            iter = 39;
        } else {
            iter = 79;
        }
    }

    /**
     * Cambia el booleano comenzado para saber que el juego ha iniciado.
     */
    public void iniciar() {
        comenzado = true;
    }
    
    /**
     * Método que recibe los datos de un movimiento y lo registra en el 
     * tablero.
     * @param mov datos del movimiento
     */
    public void movimiento (String mov){
        String numJugador = mov.substring(mov.indexOf("|")+1,mov.indexOf("|")+2);
        mov = mov.substring(mov.indexOf("|"));
        mov = mov.substring(3);
        String equis = mov.substring(0,mov.indexOf("|"));
        mov = mov.substring(2);
        String ye = mov;
        Jugador jugador;
     
        int noJugador = Integer.parseInt(numJugador);
        int x = Integer.parseInt(equis);
        int y = Integer.parseInt(ye);
        
        jugador = jugadoresAux.get(noJugador);
        
        tablero.reAcomodar(x, y, jugador);
    }
}
