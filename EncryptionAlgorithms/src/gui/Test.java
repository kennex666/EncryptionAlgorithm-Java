package gui;

import algorithms.CaesarCipher;
import algorithms.Playfair;
import algorithms.RailFenceCipher;

public class Test {
	public static void main(String[] args) {
//		try {
//			Playfair cc = new Playfair("tree stump", "tinhoc");
//			System.out.println(cc.encrypt());
//			Playfair cc1 = new Playfair(cc.encrypt(), "tinhoc");
//
//			System.out.println(cc1.decrypt());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//System.out.println(cc.encrypt());
		//System.out.println(cc.decrypt(cc.encrypt(), 199));
		
		try {
			RailFenceCipher cc = new RailFenceCipher("meet me after the toga party", 2, null);
			System.out.println(cc.encrypt());
			

			RailFenceCipher cc1 = new RailFenceCipher(cc.encrypt(), 2, null);
			System.out.println(cc1.decrypt());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
