package thread;

import java.util.Scanner;

class PrimeNumbers {
	private Finder_1[] finders;

	public void find() {
		int min = 0, max = 0, threads = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to program, which finds prime numbers!\nPlease, choose parameters of search:");

		do {
			System.out.print("Enter Start value >>> ");
			min = Integer.parseInt(scan.next());
		} while (min < 2);

		do {
			System.out.print("Enter Max value >>> ");
			max = Integer.parseInt(scan.next());
		} while (max < 2 | max > Integer.MAX_VALUE);

		do {
			System.out.print("Enter number of threads from 1 to 8 >>> ");
			threads = Integer.parseInt(scan.next());
		} while (threads < 1 & threads > 8);

		scan.close();

		Numbers primeNumbers = new Numbers();
		finders = new Finder_1[threads];
		int startNumber = min;

		for (int f = 0; f < threads; f++) {
			finders[f] = new Finder_1(startNumber, max, threads, primeNumbers);
			startNumber += 2;
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < finders.length; i++) {
			finders[i].t.start();
			try {
				finders[i].t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long finish = System.currentTimeMillis();
		System.out.println("Time spent: " + (finish - start) + " ms");

		primeNumbers.printPrimeNumbers();
	}
}
