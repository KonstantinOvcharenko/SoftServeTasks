package sequence;

import java.io.IOException;
import java.lang.module.FindException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Searcher implements Runnable {
	Thread t;
	boolean suspendFlag;
	String filename = null;
	byte[] bytes;
	LinkedList<Sequence> sequenceList = new LinkedList<Sequence>();
	boolean isRepeat = true;
	boolean isFinished = false;

	public Searcher() {
		suspendFlag = true;
		t = new Thread(this, "sequence finder");
		t.start();
	}

	public void run() {
		do {
			isFinished = false;
			synchronized (this) {
				while (suspendFlag) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			if (isRepeat == false) {
				continue;
			}

			try {
				bytes = Files.readAllBytes(Paths.get("src/sequence/" + filename + ".txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			searchFunc();

			System.out.println();
			findLongest(sequenceList);
			isFinished = true;
			clearData();
			stopThread();
		} while (isRepeat);
	}

	synchronized void stopThread() {
		suspendFlag = true;
	}

	synchronized void startThread() {
		suspendFlag = false;
		notify();
	}

	public void printSequences() {
		for (Sequence s : sequenceList) {
			System.out.print("\"" + new String(s.getBytes()) + "\" repeats: ");
			System.out.print(s.getRepeats() + ", entry indexes: " + Arrays.toString(s.getIndexes()) + ", length: ");
			System.out.println(s.getLength());
		}
	}

	public void findLongest(LinkedList<Sequence> list) {
		System.out.println("Longest repeatable sequence: ");
		int minLength = 0;
		Sequence longest = null;
		for (Sequence s : list) {
			if (s.getLength() > minLength & s.getRepeats() >= 2) {
				longest = s;
			}
		}
		System.out.println(longest);
	}

	void searchFunc() {
		// Searching algorhythm
		int length = 2;
		while (length < bytes.length) {
			for (int i = 0; i < bytes.length - length + 1; i++) {

				Sequence sq = new Sequence();
				for (int s = i; s < i + length; s++) {
					sq.addByte(bytes[s]);
				}

				if (sq.isUnique(sequenceList)) {
					sq.check(bytes, length);
					sequenceList.add(sq);
					System.out.println(sq);
				}

			}
			length++;
		}
	}

	public void terminate() {
		isRepeat = false;
		startThread();
	}

	public void clearData() {
		sequenceList.clear();
		bytes = null;
	}
}
