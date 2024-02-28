package application;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import gui.GUI_MainApplication;

import gui.SplashLoading;

public class MainApplicationTest {

    public static void main(String[] args) {
        
        try {

            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            GUI_MainApplication main = new GUI_MainApplication();
            main.setSize(900,600);
            main.setLocationRelativeTo(null);
            main.setVisible(true);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
abcdefghijklmnouprstuvwxyz
ABCDEFGHIJKLMNOUPRSTUVWXYZ
0123456789
        */

    }

    public static int random(int min, int max) {

        // Create an instance of Random
        Random random = new Random();

        // Generate a random number within the specified range
        return random.nextInt((max - min) + 1) + min;

        // Print the random number
    }
}
