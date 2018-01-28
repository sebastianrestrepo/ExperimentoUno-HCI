
public class Timer extends Thread {

	private int millis;

	public void run() {
		while (true) {
			try {
				millis++;
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getMillis() {
		return millis;
	}

	public void setMillis(int millis) {
		this.millis = millis;
	}

}