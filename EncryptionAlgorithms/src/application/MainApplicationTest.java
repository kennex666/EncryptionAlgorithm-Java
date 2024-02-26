package application;

import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gui.GUI_MainApp;
import gui.SplashLoading;

public class MainApplicationTest {
	public static void main(String[] args) {
		try {
			
			UIManager.setLookAndFeel(new FlatMacLightLaf());

			new GUI_MainApp().setVisible(true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int random(int min, int max) {

		// Create an instance of Random
		Random random = new Random();

		// Generate a random number within the specified range
		return random.nextInt((max - min) + 1) + min;

		// Print the random number
	}
}