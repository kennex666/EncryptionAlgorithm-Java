package algorithms;

import javax.swing.JTextArea;

public class CaesarCipher {

    private String originString;
    private int key;
    private static final int ALPHA_NUMBER = 26;
    private static final char A = 'A', Z = 'Z', a = 'a', z = 'z';
    private static JTextArea console;

    public CaesarCipher(String originString, int key) {
        super();
        setOriginString(originString);
        setKey(key);
    }

    public CaesarCipher(String originString, int key, JTextArea console) {
        super();
        this.console = console;
        debug("------ \nKhởi tạo thuật toán Caesar Cipher\n------ \n");
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

    public static void debug(String str) {
        if (console == null) {
            return;
        }
        console.setText(console.getText() + "\n" + str);
        console.setCaretPosition(console.getText().length());
    }




    public String encrypt() {
        debug("------ \n Quy trình mã hoá \n------ \n" );
        
        String encryptContent = "";
        char convert;
        for (int i = 0; i < originString.length(); i++) {
            char temp = originString.charAt(i);
            if (temp >= a && temp <= z) {
                convert = calcChar(temp, key, a, z);
                debug(temp + " -> " + convert);
                encryptContent += convert;
            } else if (temp >= A && temp <= Z) {
                convert = calcChar(temp, key, A, Z);
                debug(temp + " -> " + convert);
                encryptContent += convert;
            } else {
                debug(temp + " -> " + temp);
                encryptContent += temp;
            }
        }
        return encryptContent;
    }

    public String decrypt() {
        debug("------ \n Quy trình giải mã \n------ \n");
        String decryptContent = "";
        char convert;
        for (int i = 0; i < originString.length(); i++) {
            char temp = originString.charAt(i);
            if (temp >= a && temp <= z) {
                convert = calcCharDecrypt(temp, key, a, z);
                debug(temp + " -> " + convert);
                decryptContent += convert;
            } else if (temp >= A && temp <= Z) {
                convert = calcCharDecrypt(temp, key, A, Z);
                debug(temp + " -> " + convert);
                decryptContent += convert;
            } else {
                debug(temp + " -> " + temp);
                decryptContent += temp;
            }
        }
        return decryptContent;
    }

    public static char calcChar(int charOrigin, int indexKey, int start, int end) {
        int charEncrypt = (charOrigin - start + indexKey) % ALPHA_NUMBER;

        return (char) (start + charEncrypt);
    }

    public static char calcCharDecrypt(int charOrigin, int indexKey, int start, int end) {
        int charDecrypt = (charOrigin - start - indexKey) % ALPHA_NUMBER;

        if (charDecrypt < 0) {
            return (char) (end + charDecrypt + 1);
        }
        return (char) (start + charDecrypt);
    }

    @Override
    public String toString() {
        return "CaesarCipher Algorithm";
    }

}
