package com.mycompany.conversionotacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Pantalla extends JFrame {
    private JTextArea txtExpresion;
    private JLabel lblPrefija;
    private JLabel lblPostfija;
    private JLabel lblError;
    private final ConversorNotacion conversor = new ConversorNotacion();

    public Pantalla() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Conversor de Notaciones - Lenguajes y Autómatas II");
        setSize(700, 500); // Tamaño adecuado de ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Panel de encabezado institucional
        JPanel panelEncabezado = new JPanel(new GridLayout(0, 1));
        panelEncabezado.setBorder(BorderFactory.createTitledBorder(""));
        panelEncabezado.setBackground(new Color(220, 235, 250));

        Font fontBold = new Font("Segoe UI", Font.BOLD, 13);
        JLabel lblInstituto = new JLabel("Instituto Tecnológico de Pachuca - Lenguajes y Autómatas II", SwingConstants.CENTER);
        JLabel lblDocente = new JLabel("Docente: Dr. Arturo González Cerón", SwingConstants.CENTER);
        JLabel lblAlumnos = new JLabel("Alumnos:", SwingConstants.CENTER);
        JLabel lblAlumno1 = new JLabel("Ingrid Alison Porras Lugo           22200758", SwingConstants.CENTER);

        lblInstituto.setFont(fontBold);
        lblDocente.setFont(fontBold);
        lblAlumnos.setFont(fontBold);

        panelEncabezado.add(lblInstituto);
        panelEncabezado.add(lblDocente);
        panelEncabezado.add(lblAlumnos);
        panelEncabezado.add(lblAlumno1);
        
        // 2. Panel de entrada de expresión
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Código fuente a analizar");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtExpresion = new JTextArea(4, 20);
        txtExpresion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtExpresion.setLineWrap(true);
        txtExpresion.setWrapStyleWord(true);
        JScrollPane scrollTexto = new JScrollPane(txtExpresion);
        scrollTexto.setPreferredSize(new Dimension(0, 100));
        scrollTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        panelEntrada.add(lblTitulo);
        panelEntrada.add(Box.createVerticalStrut(5));
        panelEntrada.add(scrollTexto);

        // 3. Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnConvertir = new JButton("Analizar");
        JButton btnLimpiar = new JButton("Borrar");

        // Configurar botones más grandes
        Dimension btnDimension = new Dimension(130, 40);
        btnConvertir.setPreferredSize(btnDimension);
        btnLimpiar.setPreferredSize(btnDimension);

        btnConvertir.setBackground(new Color(102, 153, 255));
        btnConvertir.setForeground(Color.WHITE);
        btnLimpiar.setBackground(new Color(102, 153, 255));
        btnLimpiar.setForeground(Color.WHITE);
        btnConvertir.setFocusPainted(false);
        btnLimpiar.setFocusPainted(false);

        btnConvertir.addActionListener(this::accionConvertir);
        btnLimpiar.addActionListener(this::accionLimpiar);

        panelBotones.add(btnConvertir);
        panelBotones.add(btnLimpiar);

        // 4. Panel de resultados
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new GridLayout(3, 1, 5, 5));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados del análisis léxico"));

        // Ajustar textos de prefija y postfija con mayor tamaño de fuente
        Font fontResultados = new Font("Segoe UI", Font.BOLD, 14);
        lblPrefija = new JLabel("Prefija: ");
        lblPrefija.setFont(fontResultados);
        lblPostfija = new JLabel("Postfija: ");
        lblPostfija.setFont(fontResultados);

        lblError = new JLabel(" ");
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        panelResultados.add(lblPrefija);
        panelResultados.add(lblPostfija);
        panelResultados.add(lblError);

        // Ensamblar panel inferior (botones + resultados)
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.add(panelBotones, BorderLayout.NORTH);
        panelInferior.add(panelResultados, BorderLayout.CENTER);

        // Agregar todos los paneles al panel principal
        panelPrincipal.add(panelEncabezado, BorderLayout.NORTH);
        panelPrincipal.add(panelEntrada, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void accionConvertir(ActionEvent e) {
        try {
            String expresion = txtExpresion.getText().trim();

            if (expresion.isEmpty()) {
                lblError.setText("Error: La expresión no puede estar vacía");
                lblPrefija.setText("Prefija: ");
                lblPostfija.setText("Postfija: ");
                return;
            }

            String prefija = conversor.infijoAPrefija(expresion);
            String postfija = conversor.infijoAPostfija(expresion);

            lblPrefija.setText("Prefija: " + prefija);
            lblPostfija.setText("Postfija: " + postfija);
            lblError.setText("");
        } catch (Exception ex) {
            lblError.setText("Error: " + ex.getMessage());
            lblPrefija.setText("Prefija: ");
            lblPostfija.setText("Postfija: ");
        }
    }

    private void accionLimpiar(ActionEvent e) {
        txtExpresion.setText("");
        lblPrefija.setText("Prefija: ");
        lblPostfija.setText("Postfija: ");
        lblError.setText(" ");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            new Pantalla().setVisible(true);
        });
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
