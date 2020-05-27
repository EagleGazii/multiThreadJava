import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class Test {
	static int[] firstFligt = { 0, 0, 0, 0, 0 } ;
	
	public static void main(String[] args) throws InterruptedException {

		Lock writeLock = new ReentrantLock();
		Lock readLock = new ReentrantLock();
		 
		
		Thread r1 = new Thread(new Reader("Reader1",readLock));
		Thread r2 = new Thread(new Reader("Reader2",readLock));
		Thread r3 = new Thread(new Reader("Reader3",readLock));
		Thread r4 = new Thread(new Reader("Reader4",readLock));
		
		Random random = new Random();
		
		Writer gazi = new Writer("Gazmor", random.nextInt(5)+1,1,writeLock); 											//ilk parametre Musterinin ismi
		Writer marwane = new Writer("Marwane",random.nextInt(5)+1,1, writeLock);										//ikinci parametre Musterinin istenen koltuk numarasi
		Writer traore = new Writer("Traore", random.nextInt(5)+1,1,writeLock);										//ucuncu parametre 0 ise iptal islem / 1 ise reservasyon islem
		Writer gildas = new Writer("Gildas", random.nextInt(5)+1,1,writeLock);										//dortuncu parametre lock parametrsi gonderilir
		
		Thread w1 = new Thread(gazi);
		Thread w2 = new Thread(marwane);
		Thread w3 = new Thread(traore);
		Thread w4 = new Thread(gildas);

		Thread readerArray[] = new Thread[]{r1,r2,r3,r4};
		Thread writerArray[] = new Thread[]{w1,w2,w3,w4};
		int i;
		for(i = 0;i<readerArray.length;i++) {
			readerArray[i].start();
			writerArray[i].start();
			
		}
		for(i = 0;i<writerArray.length;i++) {
			readerArray[i].join();
			writerArray[i].join();
			
		}
		
		//marwane.cancelReservation();
		marwane.queryReservation();
		gazi.queryReservation();
		gildas.queryReservation();
		traore.queryReservation();
		traore.setChooseYourActivity(0);
		Thread w5 = new Thread(traore);
		w5.start();
		w5.join();
		Flights.getFlightInfo();
	}
}
