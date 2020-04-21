package sequence;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Sequence {
	private byte[] byteList = new byte[0];
	private int length = 0;
	private int repeats = 0;
	private int[] indexes = new int[2];

	public void addByte(byte value) {
		byte[] tmp = new byte[byteList.length + 1];
		System.arraycopy(byteList, 0, tmp, 0, byteList.length);
		tmp[tmp.length - 1] = value;
		byteList = tmp;
		length++;
	}

	public void check(byte[] array, int length) {
		int count = 0;
		for (int p = 0; p < array.length - length + 1; p++) {
			boolean b = true;
			int n = 0;
			for (int k = p; k < p + length; k++) {
				if (this.getBytes()[n++] != array[k]) {
					b = false;
					break;
				}
			}

			if (b) {
				if (count < 2) {
					indexes[count++] = p;
				}
				repeats++;
			}
		}
	}

	public boolean isUnique(LinkedList<Sequence> list) {
		boolean b = true;
		for (Sequence s : list) {
			if (length == s.getLength()) {
				if (this.equals(s)) {
					b = false;
					break;
				}
			}
		}
		return b;
	}

	public boolean equals(Sequence s) {
		boolean e = true;
		for (int i = 0; i < byteList.length; i++) {
			if (byteList[i] != s.getBytes()[i]) {
				e = false;
				break;
			}
		}
		return e;
	}

	public int getLength() {
		return length;
	}

	public int getRepeats() {
		return repeats;
	}

	public byte[] getBytes() {
		return byteList;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public String toString() {
		return "\""+new String(byteList) + "\", repeats: " + getRepeats() + ", entry indexes: "
				+ Arrays.toString(getIndexes()) + ", length: " + getLength();
	}
}