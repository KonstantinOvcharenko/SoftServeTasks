package thread;

import java.util.LinkedList;

class Finder_1 implements Runnable {
	Thread t;
	Numbers primeNumbers;
	private int minValue, maxValue, allThreads, currentNumber;

	public Finder_1(int minValue, int maxValue, int allThreads, Numbers primeNumbers) {
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
		currentNumber = minValue;
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
				primeNumbers.add(currentNumber);
			} else {
				isPrime = true;
			}
			currentNumber += allThreads * 2;
		}
	}
}
