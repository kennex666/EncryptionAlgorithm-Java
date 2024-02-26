package application;

import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gui.SplashLoading;

public class MainApplication {
	public static void main(String[] args) {
		try {
			
			UIManager.setLookAndFeel(new FlatMacLightLaf());

			SplashLoading spl = new SplashLoading();
			spl.setLocationRelativeTo(null);
			spl.setVisible(true);

			spl.processBarUpdate(0, "Đang khởi động");

			for (int i = 0; i < 101; i++) {
				Thread.sleep(random(10,50));
				spl.processBarUpdate(i);
			}
			spl.dispose();

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
