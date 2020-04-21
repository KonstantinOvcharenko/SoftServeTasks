package thread;

import java.util.Collections;
import java.util.LinkedList;

class Numbers {
	private LinkedList<Integer> primeNumbers = new LinkedList<Integer>();

	public synchronized void add(int value) {
		primeNumbers.add(value);
	}

	public void printPrimeNumbers() {
		Collections.sort(primeNumbers);
		System.out.println("Prime numbers: ");
		int count = 0;
		for (int sn : primeNumbers) {
			if (count <= 15) {
				System.out.print(sn + " ");
				count++;
			} else {
				System.out.println();
				count = 0;
			}
		}
	}
}
