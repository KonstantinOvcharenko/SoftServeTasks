package thread;

import java.util.LinkedList;

// Class-finder which uses local container at first
class Finder_2 implements Runnable {
	Thread t;
	Numbers primeNumbers;
	LinkedList<Integer> localNumbers = new LinkedList<Integer>();
	private int minValue, maxValue, allThreads, currentNumber;

	public Finder_2(int minValue, int maxValue, int allThreads, Numbers primeNumbers) {
		this.primeNumbers = primeNumbers;

		if (minValue == 2) {
			primeNumbers.add(minValue);
			this.minValue = ++minValue;
		}

		if (minValue != 2 & minValue % 2 == 0) {
			this.minValue = ++minValue;
		} else {
			this.minValue = minValue;
		}

		this.maxValue = maxValue;
		this.allThreads = allThreads;
		currentNumber = this.minValue;
		t = new Thread(this, "finder");
	}

	public void run() {
		// searching algorhythm
		while (currentNumber <= maxValue) {
			boolean isPrime = true;
			for (int d = 2; d <= currentNumber / 2; d++) {
				if (currentNumber % d == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				localNumbers.add(currentNumber);
			} else {
				isPrime = true;
			}
			currentNumber += allThreads * 2;
		}

		for (int n : localNumbers) {
			primeNumbers.add(n);
		}
	}
}