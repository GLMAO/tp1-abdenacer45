package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Random;

public class CompteARebours implements TimerChangeListener {
    private int compteur;
    private TimerService timerService;

    public CompteARebours(int initial, TimerService timerService) {
        this.compteur = initial;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        System.out.println("CompteARebours initialisé avec " + initial + " secondes");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                compteur--;
                System.out.println("CompteARebours: " + compteur);
                if (compteur == 0) {
                    timerService.removeTimeChangeListener(this);
                    System.out.println("CompteARebours terminé et désinscrit");
                }
            }
        }
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            if (compteur > 0) {
                compteur--;
                System.out.println("CompteARebours: " + compteur);
                if (compteur == 0) {
                    timerService.removeTimeChangeListener(this);
                    System.out.println("CompteARebours terminé et désinscrit");
                }
            }
        }
    }
}