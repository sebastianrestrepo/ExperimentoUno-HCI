import java.util.concurrent.TimeUnit;

public class Timer extends Thread {

	private long startTime;
	private long difference;
	
	public Timer() {
		startTime = System.nanoTime();
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
	}
	
	public void validarTiempo() {
		difference = System.nanoTime() - startTime;
		System.out.println("Total execution time: " + String.format("%d min, %d sec",
				TimeUnit.NANOSECONDS.toHours(difference), TimeUnit.NANOSECONDS.toSeconds(difference)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(difference))));
	}
	

}