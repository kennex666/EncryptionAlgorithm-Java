package algorithms;


public class CaesarCipher {
	private String originString;
	private int key;
	private static final int ALPHA_NUMBER = 26;
	private static final char A = 'A', Z = 'Z', a = 'a', z = 'z';
	
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
	
	public static String encrypt(String origin, int key) {
		String encryptContent = "";
		
		for (int i = 0; i < origin.length(); i++) {
			char temp = origin.charAt(i);
			if (temp >= a && temp <= z) {
				encryptContent += calcChar(temp, key, a);
			}else if (temp >= A && temp <= Z) {
				encryptContent += calcChar(temp, key, A);
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
			if (temp >= a && temp <= z) {
				decryptContent += calcCharDecrypt(temp, key, a, z);
			}else if (temp >= A && temp <= Z) {
				decryptContent += calcCharDecrypt(temp, key, A, Z);
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
			if (temp >= a && temp <= z) {
				encryptContent += calcChar(temp, key, a);
			}else if (temp >= A && temp <= Z) {
				encryptContent += calcChar(temp, key, A);
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
			if (temp >= a && temp <= z) {
				decryptContent += calcCharDecrypt(temp, key, a, z);
			}else if (temp >= A && temp <= Z) {
				decryptContent += calcCharDecrypt(temp, key, A, Z);
			} else {
				decryptContent += temp;
			}
		}
		return decryptContent;
	}
	
	public static char calcChar (int charOrigin, int indexKey, int start) {		
		int charEncrypt = (charOrigin - start + indexKey) % ALPHA_NUMBER;

		return (char) (start + charEncrypt);
	}
	
	public static char calcCharDecrypt (int charOrigin, int indexKey, int start, int end) {
		int charDecrypt = ((charOrigin - start) - (indexKey % ALPHA_NUMBER));

		if (charDecrypt < 0) {
			return (char) (end + charDecrypt);
		}
		return (char) (start + charDecrypt);
	}
	
	
	@Override
	public String toString() {
		return "CaesarCipher Algorithm";
	}
	
}
