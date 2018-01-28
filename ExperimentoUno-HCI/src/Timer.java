
public class Timer extends Thread {

	public Timer() {
		long startTime = System.nanoTime();
		Thread.sleep(1000 * 10);
		long difference = System.nanoTime() - startTime;
		System.out.println("Total execution time: " + String.format("%d min, %d sec",
				TimeUnit.NANOSECONDS.toHours(difference), TimeUnit.NANOSECONDS.toSeconds(difference)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(difference))));
	}

}