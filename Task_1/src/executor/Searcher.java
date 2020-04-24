package executor;

import java.util.List;
import java.util.concurrent.Callable;

public class Searcher implements Runnable {
	int from, to, threads, current;
	List<Integer> numbers;

	public Searcher(int from, int to, int threads, List<Integer> numbers) {

		this.numbers = numbers;

		if (from == 2) {
			numbers.add(2);
			this.from = ++from;
		}

		if (from != 2 & from % 2 == 0) {
			this.from = ++from;
		} else {
			this.from = from;
		}
		current = this.from;
		this.to = to;
		this.threads = threads;
	}

	@Override
	public void run() {
		// searching algothythm
		while (current <= to) {
			boolean isPrime = true;
			for (int d = 2; d < current / 2; d++) {
				if (current % d == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				numbers.add(current);
			} else {
				isPrime = true;
			}
			current += threads * 2;
		}
	}
}
