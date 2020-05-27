import java.util.concurrent.locks.Lock;

public class Reader implements Runnable {
	String name;
	private Lock readLock = null;
	

	public Reader(String name, Lock readLock) {
		this.name = name;
		this.readLock = readLock;
	}

	@Override
	public void run() {
		long millis=System.currentTimeMillis();  
		java.util.Date date=new java.util.Date(millis);  
		
		try {
			readLock.lock();
			System.out.println(date);
			System.out.println(this.name + " koltuklar icin bakiyor...");
			Flights.getFlightInfo();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			readLock.unlock();
		}

	}

}
