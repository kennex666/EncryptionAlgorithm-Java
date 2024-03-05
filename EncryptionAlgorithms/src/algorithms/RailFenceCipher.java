package algorithms;

import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JTextArea;

public class RailFenceCipher {

	private String originString;
	private int row, col;
	private String key;
	private static final int ALPHA_NUMBER = 26;
	private static final char A = 'A', Z = 'Z', a = 'a', z = 'z';
	private char[][] tableEnc;
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

	public RailFenceCipher() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * For key - Advanced
	 */
	public RailFenceCipher(String originString, String key, JTextArea console) {
		super();
		this.originString = originString;
		this.key = key;
		this.console = console;
        debug("------ \nKhởi tạo thuật toán Rail Fence Cipher (Key số)\n------ \n");


		col = key.length();

		row = (int) Math.ceil((double) originString.length() / col) ;

		tableEnc = new char[col][row];

		for (int i = 0; i < key.length(); i++) {
			tableEnc[i][0] = key.charAt(i);
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
	
	/*
	 * Độ sâu
	 */
	public RailFenceCipher(String _originString, int row, JTextArea console) throws Exception{
		super();
		this.originString = _originString.toUpperCase().replaceAll("\\s", "");
		this.console = console;

		this.row = row;

		if (row == 0)
			throw new Exception("Độ sâu phải lớn hơn 0");
		debug("Khởi tạo độ sâu: " + row);
		this.col = originString.length() / row + 1;
		debug("Khởi tạo chiều dài 1 hàng: " + col);
		tableEnc = new char[col][row];

	}


	public String encrypt() {
		
		
		int indexString = 0;
		String str = "";
		
		String temp = "";
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (originString.length() == indexString) {
					break;
				}
				tableEnc[i][j] = originString.charAt(indexString++);
				temp += tableEnc[i][j] + " ";
			}

			temp += "\n";
		}
		debug("Khởi tạo chuỗi cùng độ sâu\n" + temp);
		
		indexString = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (indexString == originString.length())
					break;
				indexString++;
				str += tableEnc[j][i];
			}
		}
		
		debug("Kết quả chuỗi mã hoá với độ sâu = " + row + ": \n" + str);
		
		
		return str;
	}
	
	public String decrypt() {
		
		
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
		
		indexString = 0;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (indexString == originString.length())
					break;
				indexString++;
				str += tableEnc[i][j];
			}
		}
		
		debug("Kết quả chuỗi giải hoá với độ sâu = " + row + ": \n" + str);
		
		
		return str;
	}

	@Override
	public String toString() {
		return "RailFenceCipher";
	}

}
