package algorithms;

import java.util.Arrays;

public class CaesarCipher {
	private final String[] lowerAlpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K","L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private String originString;
	private int key;
	
	public CaesarCipher(String originString, int key) {
		super();
		setOriginString(originString);
		setKey(key);
	}
	public CaesarCipher() {
		super();
	}
	public String getOriginString() {
		return originString;
	}
	public void setOriginString(String originString) {
		this.originString = originString;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String[] getLowerAlpha() {
		return lowerAlpha;
	}
	
	public static String encrypt(String origin, int key) {
		String encryptContent = "";
		
		for (int i = 0; i < origin.length(); i++) {
			char temp = origin.charAt(i);
			int charEncrypt = temp + key % 26;
			if (temp >= 65 && temp <= 90) {
				encryptContent += calcChar(temp, key, 65, 90);
			}else if (temp >= 97 && temp <= 122) {
				encryptContent += calcChar(temp, key, 97, 122);
			} else {
				encryptContent += temp;
			}
		}
		return encryptContent;
	}
	
	public static String decrypt(String origin, int key) {
		String decryptContent = "";
		
		for (int i = 0; i < origin.length(); i++) {
			char temp = origin.charAt(i);
			int charEncrypt = temp - key % 26;
			
			if (temp >= 65 && temp <= 90) {
				decryptContent += calcCharDecrypt(temp, key, 65, 90);
			}else if (temp >= 97 && temp <= 122) {
				decryptContent += calcCharDecrypt(temp, key, 97, 122);
			} else {
				decryptContent += temp;
			}
		}
		return decryptContent;
	}
	
	public String encrypt() {
		String encryptContent = "";
		
		for (int i = 0; i < originString.length(); i++) {
			char temp = originString.charAt(i);
			int charEncrypt = temp + key % 26;
			if (temp >= 65 && temp <= 90) {
				encryptContent += calcChar(temp, key, 65, 90);
			}else if (temp >= 97 && temp <= 122) {
				encryptContent += calcChar(temp, key, 97, 122);
			} else {
				encryptContent += temp;
			}
		}
		return encryptContent;
	}
	
	public String decrypt() {
		String decryptContent = "";
		
		for (int i = 0; i < originString.length(); i++) {
			char temp = originString.charAt(i);
			int charEncrypt = temp - key % 26;
			
			if (temp >= 65 && temp <= 90) {
				decryptContent += calcCharDecrypt(temp, key, 65, 90);
			}else if (temp >= 97 && temp <= 122) {
				decryptContent += calcCharDecrypt(temp, key, 97, 122);
			} else {
				decryptContent += temp;
			}
		}
		return decryptContent;
	}
	
	public static char calcChar (int charOrigin, int indexKey, int start, int end) {
		int charEncrypt = charOrigin + indexKey % 26;

		if (charEncrypt <= end) {
			return (char) (charEncrypt);
		}
		return (char) (start + (charEncrypt - end - 1));
	}
	
	public static char calcCharDecrypt (int charOrigin, int indexKey, int start, int end) {
		int charDecrypt = charOrigin - indexKey % 26;

		if (charDecrypt >= start) {
			return (char) (charDecrypt);
		}
		return (char) (end - start + charDecrypt + 1 );
	}
	
	
	@Override
	public String toString() {
		return "CaesarCipher";
	}
	
	
	
	
}
