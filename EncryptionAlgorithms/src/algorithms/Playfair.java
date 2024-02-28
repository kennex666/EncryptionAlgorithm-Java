package algorithms;

import static algorithms.CaesarCipher.debug;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

public class Playfair {

    private String originString;
    private String stringProcess;
    private String key;
    private static final int ALPHA_NUMBER = 26;
    private static final char A = 'A', Z = 'Z', a = 'a', z = 'z';
    private char[][] tableEnc;
    private HashMap<Character, Integer> cotKyTu, hangKyTu;

    private JTextArea console;

    private static enum CASE {
        HAI_KI_TU_CUNG_HANG, HAI_KI_TU_CUNG_COT, HAI_KY_TU_KHAC_COT
    };

    public String getOriginString() {
        return originString;
    }

    public void setOriginString(String originString) {
        this.originString = originString;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public char[][] getTableEnc() {
        return tableEnc;
    }

    public void setTableEnc(char[][] tableEnc) {
        this.tableEnc = tableEnc;
    }

    public static int getAlphaNumber() {
        return ALPHA_NUMBER;
    }

    public void setConsole(JTextArea console) {
        this.console = console;
    }

    public Playfair(String _originString, String _key) throws Exception {

        initProcess(_originString, _key);
    }

    public Playfair(String _originString, String _key, JTextArea console) throws Exception {
        this.console = console;
        debug(" ------ \n Khởi tạo thuật toán Playfair\n ------ \n");

        initProcess(_originString, _key);
    }

    public void initProcess(String _originString, String _key) throws Exception {
        this.originString = _originString.toUpperCase();
        stringProcess = originString;
        stringProcess = stringProcess.replaceAll("[^A-Z]", "");
        stringProcess = stringProcess.replaceAll("J", "I");
        this.key = _key.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.replaceAll("J", "I");

        cotKyTu = new HashMap<>();
        hangKyTu = new HashMap<>();

        tableEnc = new char[5][5];
        initTable();
        printTableChar();
        initString();
    }

    public CASE analyticsWhat2Char(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);

        if (colChar1 == colChar2) {
            return CASE.HAI_KI_TU_CUNG_COT;
        }
        if (rowChar1 == rowChar2) {
            return CASE.HAI_KI_TU_CUNG_HANG;
        }
        return CASE.HAI_KY_TU_KHAC_COT;
    }

    public String twoCharSameRow_Encrypt(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);
        String str = "";

        str += (colChar1 + 1 > 4) ? tableEnc[0][rowChar1] : tableEnc[colChar1 + 1][rowChar1];
        str += (colChar2 + 1 > 4) ? tableEnc[0][rowChar2] : tableEnc[colChar2 + 1][rowChar2];

        return str;

    }

    public String twoCharSameCol_Encrypt(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);
        String str = "";

        str += (rowChar1 + 1 > 4) ? tableEnc[colChar1][0] : tableEnc[colChar1][rowChar1 + 1];
        str += (rowChar2 + 1 > 4) ? tableEnc[colChar1][0] : tableEnc[colChar2][rowChar2 + 1];
        return str;

    }

    public String twoCharInASquare_Encrypt(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);
        String str = "";

        str += tableEnc[colChar1][rowChar2];
        str += tableEnc[colChar2][rowChar1];
        return str;

    }

    public String twoCharSameRow_Decrypt(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);
        String str = "";

        str += (colChar1 - 1 < 0) ? tableEnc[4][rowChar1] : tableEnc[colChar1 - 1][rowChar1];
        str += (colChar2 - 1 < 0) ? tableEnc[4][rowChar2] : tableEnc[colChar2 - 1][rowChar2];

        return str;

    }

    public String twoCharSameCol_Decrypt(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);
        String str = "";

        str += (rowChar1 - 1 < 0) ? tableEnc[colChar1][4] : tableEnc[colChar1][rowChar1 - 1];
        str += (rowChar2 - 1 < 0) ? tableEnc[colChar1][4] : tableEnc[colChar2][rowChar2 - 1];
        return str;

    }

    public String twoCharInASquare_Decrypt(char char1, char char2) {
        int colChar1 = cotKyTu.get(char1), colChar2 = cotKyTu.get(char2);
        int rowChar1 = hangKyTu.get(char1), rowChar2 = hangKyTu.get(char2);
        String str = "";

        str += tableEnc[colChar1][rowChar2];
        str += tableEnc[colChar2][rowChar1];
        return str;

    }

    public String decrypt() {
        debug("------ \n Quy trình giải mã \n------ \n");

        String str = "", strFinal = "";
        String encContent = this.originString.replaceAll("[^A-Z]", "");
        for (int i = 0; i < encContent.length(); i = i + 2) {
            char char1 = encContent.charAt(i), char2 = encContent.charAt(i + 1);
            CASE caseChar = analyticsWhat2Char(char1, char2);
            switch (caseChar) {
                case HAI_KI_TU_CUNG_COT -> {
                    str += twoCharSameCol_Decrypt(char1, char2);
                }
                case HAI_KI_TU_CUNG_HANG -> {
                    str += twoCharSameRow_Decrypt(char1, char2);
                }
                default -> {
                    str += twoCharInASquare_Decrypt(char1, char2);
                }
            }
        }

        int indexStr = 0;

        for (int i = 0; i < originString.length(); i++) {
            if (originString.charAt(i) < A || originString.charAt(i) > Z) {
                strFinal += originString.charAt(i);
            } else {
                strFinal += str.charAt(indexStr++);
            }
        }

        while (indexStr < str.length()) {
            strFinal += str.charAt(indexStr++);
        }

        while (true) {
            if (!strFinal.equalsIgnoreCase(strFinal.replaceAll("XWX", "XX"))) {
                strFinal = strFinal.replaceAll("XWX", "XX");
                continue;
            }
            if (strFinal.equalsIgnoreCase(strFinal.replaceAll("(.)(X)\\1", "$1$1"))) {
                break;
            }
            strFinal = strFinal.replaceAll("(.)(X)\\1", "$1$1");
        }
        return strFinal;

    }

    public void initString() throws Exception {
        while (true) {
            if (stringProcess.contains("XX")) {
                stringProcess = stringProcess.replaceAll("XX", "XWXW");
            }
            stringProcess = stringProcess.replaceAll("(.)\\1", "$1X$1");

            if (stringProcess.replaceAll("(.)\\1", "$1X$1").equalsIgnoreCase(stringProcess)) {
                if (stringProcess.length() % 2 != 0) {
                    stringProcess += "X";
                } else {
                    break;
                }
            }
        }
        debug("::Thực hiện bỏ phần thừa, chuyển sang chữ in hoa::");
        debug(stringProcess);
    }

    public void debug(String str) {
        if (console == null) {
            return;
        }
        console.setText(console.getText() + "\n\n" + str);
        console.setCaretPosition(console.getText().length());
    }

    public String encrypt() {
        debug("------ \n Quy trình mã hoá \n------ \n");

        String str = "", strFinal = "";
        for (int i = 0; i < stringProcess.length(); i = i + 2) {
            char char1 = stringProcess.charAt(i), char2 = stringProcess.charAt(i + 1);
            CASE caseChar = analyticsWhat2Char(char1, char2);
            switch (caseChar) {
                case HAI_KI_TU_CUNG_COT -> {
                    str += twoCharSameCol_Encrypt(char1, char2);
                }
                case HAI_KI_TU_CUNG_HANG -> {
                    str += twoCharSameRow_Encrypt(char1, char2);
                }
                default -> {
                    str += twoCharInASquare_Encrypt(char1, char2);
                }
            }
        }

        int indexStr = 0;

        for (int i = 0; i < originString.length(); i++) {
            if (originString.charAt(i) < A || originString.charAt(i) > Z) {
                strFinal += originString.charAt(i);
            } else {
                strFinal += str.charAt(indexStr++);
            }
        }

        while (indexStr < str.length()) {
            strFinal += str.charAt(indexStr++);
        }

        return strFinal;

    }

    public void initTable() {
        Map<Character, Integer> isUse = new HashMap<Character, Integer>();
        String keyNotDuplicate = new String();

        for (int i = A; i <= Z; i++) {
            isUse.put((char) i, 0);
        }
        isUse.put('J', 1);

        for (int i = 0; i < key.length(); i++) {
            char thisChar = key.charAt(i);
            if (isUse.get(thisChar) == 0) {
                isUse.put(thisChar, 1);
                keyNotDuplicate += thisChar;
            }
        }

        int currentKeyIndex = 0, maximumKeyIndex = keyNotDuplicate.length();

        char currentAlpha = A;

        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {

                while ((currentKeyIndex < maximumKeyIndex)
                        && (keyNotDuplicate.charAt(currentKeyIndex) < A || keyNotDuplicate.charAt(currentKeyIndex) > Z)) {
                    currentKeyIndex++;

                }

                if (currentKeyIndex < maximumKeyIndex) {
                    tableEnc[col][row] = keyNotDuplicate.charAt(currentKeyIndex);
                    cotKyTu.put(keyNotDuplicate.charAt(currentKeyIndex), col);
                    hangKyTu.put(keyNotDuplicate.charAt(currentKeyIndex), row);
                    ++currentKeyIndex;
                } else if (isUse.get(currentAlpha) == 0) {
                    isUse.put(currentAlpha, 1);
                    tableEnc[col][row] = currentAlpha;
                    cotKyTu.put(currentAlpha, col);
                    hangKyTu.put(currentAlpha, row);
                    ++currentAlpha;
                } else {
                    for (char temp = A; temp <= Z; temp++) {
                        if (isUse.get(temp) == 0) {
                            currentAlpha = temp;
                            isUse.put(currentAlpha, 1);
                            tableEnc[col][row] = currentAlpha;
                            cotKyTu.put(currentAlpha, col);
                            hangKyTu.put(currentAlpha, row);
                            ++currentAlpha;
                            break;
                        }
                    }
                }
            }
        }

    }

    public void printTableChar() {
        debug("::Khởi tạo bảng Playfair::");
        String str = "";
        for (int col = 0; col < 5; col++) {
            str += "| " + tableEnc[col][0] + " | " + tableEnc[col][1] + " | " + tableEnc[col][2] + " | "
                    + tableEnc[col][3] + " | " + tableEnc[col][4] + " |\n";
        }
        debug(str);
    }

    public Playfair() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Playfair Algorithm";
    }

}
