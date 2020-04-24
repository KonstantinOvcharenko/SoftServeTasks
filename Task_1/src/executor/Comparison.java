package executor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Comparison {
	List<Integer> primeNumbers = Collections.synchronizedList(new LinkedList<Integer>());
	ExecutorService service1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	ExecutorService service2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public void compare() {
		int min, max, threads;
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter value from> ");
		min = Integer.parseInt(scan.next());

		System.out.print("Enter value to> ");
		max = Integer.parseInt(scan.next());

		System.out.print("Enter value of threads> ");
		threads = Integer.parseInt(scan.next());
		scan.close();

		// section 1
		int startNumber1 = min, startNumber2 = min;
		long start1 = System.currentTimeMillis();
		for (int t = 0; t < threads; t++) {
			service1.execute(new Searcher(startNumber1, max, threads, primeNumbers));
			startNumber1 += 2;
		}
		service1.shutdown();
		long finish1 = System.currentTimeMillis();
		try {
			service1.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long ts1 = finish1 - start1;
		System.out.println("Time spent in 1st case: " + (finish1 - start1) + " ms");

		// section 2
		long start2 = System.currentTimeMillis();
		for (int t = 0; t < threads; t++) {
			service2.execute(new Searcher2(startNumber2, max, threads, primeNumbers));
			startNumber2 += 2;
		}
		service2.shutdown();
		long finish2 = System.currentTimeMillis();
		try {
			service2.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long ts2 = finish2 - start2;
		System.out.println("Time spent in 2nd case: " + (finish2 - start2) + " ms");

		if (ts1 < ts2) {
			System.out.println("First method faster than second.");
		} else if (ts1 == ts2) {
			System.out.println("First method and second are equals.");
		} else if (ts1 > ts2) {
			System.out.println("First method slower than second.");
		}
	}
}
