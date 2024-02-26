package gui;

import algorithms.CaesarCipher;

public class Test {
	public static void main(String[] args) {
		CaesarCipher cc = new CaesarCipher("abcdefg", 53);
		System.out.println(cc.encrypt());
		System.out.println(cc.decrypt(cc.encrypt(), 53));
	}
}
