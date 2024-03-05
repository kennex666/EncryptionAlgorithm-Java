package algorithms;

import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JTextArea;

public class RailFenceCipher_String {

	private String originString;
	private int row, col;
	private String key;
	private static final int ALPHA_NUMBER = 26;
	private static final char A = 'A', Z = 'Z', a = 'a', z = 'z';
	private char[][] tableEnc;
	private char[] kyTuKey;
	private JTextArea console;

	public String getOriginString() {
		return originString;
	}

	public void setOriginString(String originString) {
		this.originString = originString;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
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

	public JTextArea getConsole() {
		return console;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}

	public static int getAlphaNumber() {
		return ALPHA_NUMBER;
	}

	public RailFenceCipher_String() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * For key - Advanced
	 */
	public RailFenceCipher_String(String _originString, String _key, JTextArea console) {
		super();
		this.originString = _originString.toUpperCase().replaceAll("\\s", "");
		;
		this.key = _key.toUpperCase().replaceAll("[^A-Z]", "");
		this.console = console;

        debug("------ \nKhởi tạo thuật toán Rail Fence Cipher (Key chuỗi)\n------ \n");

		col = key.length();

		debug("Khởi tạo số cột: " + col + "\n Khoá là: " + key);

		row = (int) Math.ceil((double) originString.length() / col) ;
		debug("Khởi tạo số dòng: " + row + "\n Văn bản xử lý: " + originString);

		tableEnc = new char[col][row];
		kyTuKey = new char[col];
		for (int i = 0; i < col; i++) {
			kyTuKey[i] = key.charAt(i);
		}

	}

	public void debug(String str) {
		if (console == null) {
			System.out.println(str);
			return;
		}
		console.setText(console.getText() + "\n" + str);
		console.setCaretPosition(console.getText().length());
	}

	public String encrypt() {

		int indexString = 0;
		String str = "";

		String temp = "";
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (originString.length() == indexString) {
					break;
				}
				tableEnc[j][i] = originString.charAt(indexString++);
				temp += tableEnc[j][i] + " ";
			}
			temp += "\n";
		}
		debug("Khởi tạo chuỗi cùng độ sâu\n" + temp);

		int pos[] = new int[col];
		for (int i = 0; i < col; i++) {
			pos[i] = i;
		}

		for (int i = 0; i < col - 1; i++) {
			for (int j = 0; j < col - i - 1; j++) {
				if (kyTuKey[j] > kyTuKey[j + 1]) {
					char tempCharKey = kyTuKey[j];
					kyTuKey[j] = kyTuKey[j + 1];
					kyTuKey[j + 1] = tempCharKey;
					int tempPos = pos[j];
					pos[j] = pos[j + 1];
					pos[j + 1] = tempPos;

				}
			}
		}

		temp = "";
		for (int i = 0; i < col; i++) {
			temp += " " + kyTuKey[i];
		}

		debug("Sắp xếp khoá thứ tự: " + temp);

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				indexString++;
				str += (((int) tableEnc[pos[i]][j] <= 0)) ? "" : tableEnc[pos[i]][j];
			}
		}

		debug("Kết quả chuỗi mã hoá với độ sâu = " + row + ": \n" + str);

		return str;
	}

	public String decrypt() {

		int indexString = 0;
		String str = "";

		String temp = "";

		int pos[] = new int[col];
		for (int i = 0; i < col; i++) {
			pos[i] = i;
		}

		for (int i = 0; i < col - 1; i++) {
			for (int j = 0; j < col - i - 1; j++) {
				if (kyTuKey[j] > kyTuKey[j + 1]) {
					char tempCharKey = kyTuKey[j];
					kyTuKey[j] = kyTuKey[j + 1];
					kyTuKey[j + 1] = tempCharKey;
					int tempPos = pos[j];
					pos[j] = pos[j + 1];
					pos[j + 1] = tempPos;

				}
			}
		}

		temp = "";
		for (int i = 0; i < col; i++) {
			temp += " " + kyTuKey[i];
		}
		debug("Sắp xếp khoá thứ tự: " + temp);

		temp = "";

		int maximumLastRow = col - ((row * col - originString.length()) % col) - 1;

		debug("\nƯớc tính số ký tự hàng cuối: " + (maximumLastRow + 1));
		int countRow = 0;

		for (int j = 0; j < col; j++) {
			for (int i = 0; i < row; i++) {

				if (indexString == originString.length())
					break;
				int acCol = pos[j];
				if (row - 1 == i)
					if (maximumLastRow - acCol < 0) {
						continue;
					}

				tableEnc[acCol][i] = originString.charAt(indexString++);
				temp += tableEnc[acCol][i] + " ";
			}
			temp += "\n";
		}
		debug("Tái tạo bảng: \n" + temp);

		temp = "";
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				str += ((int)tableEnc[j][i]) <= 0 ? "" : tableEnc[j][i];
				temp += tableEnc[j][i] + " ";
			}
			temp += "\n";
		}
		
		
		debug("Sắp xếp dựa vào thứ tự khoá: \n" + temp);

		debug("Kết quả chuỗi giải mã với độ sâu = " + row + ": \n" + str);

		return str;
	}

	@Override
	public String toString() {
		return "RailFenceCipher";
	}

}
