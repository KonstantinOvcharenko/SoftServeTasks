package executor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeNumbers {
	List<Integer> primeNumbers = Collections.synchronizedList(new LinkedList<Integer>());
	ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public void find() {
		int min, max, threads;
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter value from> ");
		min = Integer.parseInt(scan.next());

		System.out.print("Enter value to> ");
		max = Integer.parseInt(scan.next());

		System.out.print("Enter value of threads> ");
		threads = Integer.parseInt(scan.next());
		scan.close();

		long start = System.currentTimeMillis();
		for (int t = 0; t < threads; t++) {
			service.execute(new Searcher(min, max, threads, primeNumbers));
			min += 2;
		}
		service.shutdown();
		try {
			service.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finish = System.currentTimeMillis();
		System.out.println("Time spent: " + (finish - start) + " ms");

		this.showPrimeNumbers();
	}

	public void showPrimeNumbers() {
		Collections.sort(primeNumbers);
		System.out.println("Prime numbers: ");
		int count = 0;
		for (int n : primeNumbers) {
			System.out.print(n + " ");
			count++;
			if (count == 15) {
				System.out.println();
			}
		}
	}
}
