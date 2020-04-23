package executor;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Comparison {
	private Finder_1[] finders1;
	private Finder_2[] finders2;

	public void compare() {
		int min = 0, max = 0, threads = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Comparison of 2 methods of data management in finders:");

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

		Numbers primeNumbers1 = new Numbers();
		Numbers primeNumbers2 = new Numbers();
		finders1 = new Finder_1[threads];
		finders2 = new Finder_2[threads];
		int startNumber1 = min;
		int startNumber2 = min;

		// 1st case
		for (int f = 0; f < threads; f++) {
			finders1[f] = new Finder_1(startNumber1, max, threads, primeNumbers1);
			startNumber1 += 2;
		}
		// Executor section 1
		ExecutorService executor1 = Executors.newFixedThreadPool(threads);
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < finders1.length; i++) {
			executor1.execute(finders1[i]);
		}
		executor1.shutdown();
		long finish1 = System.currentTimeMillis();
		long time_spent1 = finish1 - start1;
		System.out.println("Time spent in 1st case: " + (time_spent1) + " ms");

		// 2nd case
		for (int f = 0; f < threads; f++) {
			finders2[f] = new Finder_2(startNumber2, max, threads, primeNumbers2);
			startNumber2 += 2;
		}
		// Executor section
		ExecutorService executor2 = Executors.newFixedThreadPool(threads);
		long start2 = System.currentTimeMillis();
		for (int i = 0; i < finders2.length; i++) {
			executor2.execute(finders2[i]);
		}
		executor2.shutdown();
		long finish2 = System.currentTimeMillis();
		long time_spent2 = finish2 - start2;
		System.out.println("Time spent in 1st case: " + (time_spent2) + " ms");

		if (time_spent1 < time_spent2) {
			System.out.println("First method faster than second.");
		} else if (time_spent1 == time_spent2) {
			System.out.println("First method and second are equals.");
		} else if (time_spent1 > time_spent2) {
			System.out.println("First method slower than second.");
		}

	}
}
