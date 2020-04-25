package sequence;

import java.util.Scanner;

public class LongestSequence {
	public static void main(String[] args) {

		Searcher searcher = new Searcher();
		try {
			initialize(searcher);
			searcher.t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void initialize(Searcher searcher) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		int count = 0;
		boolean repeating = true;
		do {
			if (count < 1) {
				System.out.print("Enter file name to scan: ");
				searcher.filename = scan.next();
				count++;
				searcher.startThread();
				while (searcher.isFinished == false) {
					if (searcher.isFinished == true) {
						break;
					}
				}
				continue;
			}
			if (count >= 1) {
				System.out.print("Do you want to read another file? [Y/N]:");
				String answer = scan.next();
				switch (answer) {
				case "Y":
					System.out.print("Enter file name to scan: ");
					searcher.filename = scan.next();
					searcher.startThread();
					break;
				case "y":
					System.out.print("Enter file name to scan: ");
					searcher.filename = scan.next();
					searcher.startThread();
					break;
				case "N":
					scan.close();
					repeating = false;
					searcher.terminate();
					System.out.println("Program finished!");
					break;
				case "n":
					scan.close();
					repeating = false;
					searcher.terminate();
					System.out.println("Program finished!");
					break;
				}
				while (searcher.isFinished == false) {
					if (searcher.isFinished == true) {
						break;
					}
					if (repeating == false) {
						break;
					}
				}
			}
		} while (repeating);
	}
}
