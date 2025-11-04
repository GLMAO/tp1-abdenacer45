package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HorlogeGUI implements TimerChangeListener {
    private TimerService timerService;
    private JFrame frame;
    private JLabel timeLabel;
    private String name;

    public HorlogeGUI(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        createGUI();
        System.out.println("HorlogeGUI " + name + " initialized!");
    }

    private void createGUI() {
        // Création de la fenêtre
        frame = new JFrame("Horloge - " + name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null); // Centrer la fenêtre
        
        // Configuration du label pour l'heure
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.BLUE);
        
        // Mise à jour initiale
        updateTimeDisplay();
        
        // Ajout du label à la fenêtre
        frame.add(timeLabel, BorderLayout.CENTER);
        
        // Bouton pour fermer l'horloge
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> {
            timerService.removeTimeChangeListener(this);
            frame.dispose();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private void updateTimeDisplay() {
        if (timerService != null) {
            String time = String.format("%02d:%02d:%02d", 
                timerService.getHeures(), 
                timerService.getMinutes(), 
                timerService.getSecondes());
            timeLabel.setText(time);
        }
    }

    // Implémentation de PropertyChangeListener (nouvelle méthode)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::updateTimeDisplay);
        }
    }

    // Implémentation de l'ancienne méthode pour compatibilité
    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            SwingUtilities.invokeLater(this::updateTimeDisplay);
        }
    }
}