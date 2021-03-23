package com.shris.circularbuffer;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Test;
import com.shris.domain.CircularBuffer;

public class CircularBufferUnitTest {

	final static int batchSize = 10;
	final static int bufferSize = 100;
	final static String[] alphabets = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

	@Test
	public void sample_loop() throws Exception {

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0;; i++) {
			if (i >= alphabets.length)
				i = 0;
			String ch = alphabets[i];
			list.add(ch);
			Thread.sleep(100);
			System.out.println(ch);
		}
	}

	@Test
	public void producerConsumerTest() throws Exception {
		CircularBuffer<String> buffer = new CircularBuffer<String>(bufferSize);
		String[] list = new String[batchSize];
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<?> producerList = executorService.submit(new Producer<String>(buffer));
		Future<String> consumedList = executorService.submit(new Consumer<String>(buffer, list));
		String consumedStatus = consumedList.get();
		// producerList.get();
		System.out.println("Success" + consumedStatus);
	}

	static class Producer<T> implements Runnable {

		private CircularBuffer<T> buffer;

		public Producer(CircularBuffer<T> buffer) {
			this.buffer = buffer;
		}

		@Override
		public void run() {
			try {
				int index = 0;
				while (true) {
					if (index >= alphabets.length)
						index = 0;
					String ch = alphabets[index];
					if (!buffer.put((T) ch))
						break;
					Thread.sleep(500);
					index++;

				}

			} catch (Exception e) {
				System.out.println("Producer Excp : " + e);
			}

		}
	}

	@SuppressWarnings("unchecked")
	static class Consumer<T> implements Callable<String> {
		private CircularBuffer<T> buffer;
		T[] list;

		public Consumer(CircularBuffer<T> buffer, T[] list) {
			this.buffer = buffer;
			this.list = list;
		}

		@Override
		public String call() throws Exception {
			try {
				while (true) {
					Thread.sleep(2000);

					int consumedElements = buffer.take(this.list, batchSize);
					for (int i = 0; i < consumedElements; i++) {
						System.out.print(this.list[i]);
					}
					System.out.println();

				}

			} catch (Exception e) {
				System.out.println("Consumer Excp : " + e);
			}
			return "Success";
		}
	}

}
