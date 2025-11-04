package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import java.util.Random;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Utiliser SwingUtilities pour l'interface graphique
        SwingUtilities.invokeLater(() -> {
            testDuTimeService();
        });
    }

    private static void testDuTimeService() {
        // Instanciation du TimerService
        TimerService timerService = new DummyTimeServiceImpl();

        // Création de plusieurs Horloges console
        Horloge horloge1 = new Horloge("Console 1", timerService);
        Horloge horloge2 = new Horloge("Console 2", timerService);

        // Création d'horloges graphiques
        HorlogeGUI horlogeGUI1 = new HorlogeGUI("Graphique 1", timerService);
        
        // Vous pouvez créer plusieurs horloges graphiques si vous voulez :
        // HorlogeGUI horlogeGUI2 = new HorlogeGUI("Graphique 2", timerService);

        // Création d'un CompteARebours avec 5 secondes
        CompteARebours compte1 = new CompteARebours(5, timerService);

        // Création de 10 CompteARebours avec valeurs aléatoires
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int valeurInitiale = rand.nextInt(11) + 10; // 10-20
            new CompteARebours(valeurInitiale, timerService);
        }
        
        System.out.println("Toutes les horloges et comptes à rebours sont initialisés !");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}