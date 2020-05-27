import java.util.concurrent.locks.Lock;


public class Writer extends Client implements Runnable {
	private Lock lock = null;
	private int chooseYourActivity  = -1;

	public int getChooseYourActivity() {
		return chooseYourActivity;
	}

	public void setChooseYourActivity(int chooseYourActivity) {
		this.chooseYourActivity = chooseYourActivity;
	}

	public Writer(String fullName, int want_seatNo , int chooseYourActivity, Lock lock) {
		super(fullName, want_seatNo);
		this.lock = lock;
		this.chooseYourActivity = chooseYourActivity;
	}

	@Override
	public void run() {
		long millis=System.currentTimeMillis();  
		java.util.Date date=new java.util.Date(millis); 
		if (chooseYourActivity == 1) {
			System.out.println(date);
			System.out.println(this.getFullName() + " " + this.getWantSeatNo() + ". koltugu rezervasyon yapmaya deniyor.");
			makeReservation();
		} else if (chooseYourActivity == 0) {
			System.out.println(date);
			System.out.println(this.getFullName() + " koltugu iptal yapmaya deniyor.");
			cancelReservation();
		} else {
			System.out.println(date);
			System.out.println(this.getFullName() + " sectigin komut hic bir islem yapmaz.");
		}
	}

	void cancelReservation() {
		if (this.getSeatNo() != 0) {
			Test.firstFligt[this.getSeatNo()  - 1] = 0;
			this.setSeatNo(0);
			System.out.println(this.getFullName() + " koltugu iptal edildi.");

		} else {
			System.out.println(
					this.getFullName() + " daha once rezervasyon yapamadigi icin iptal edilebilecek bir sey yok.");
		}

	}

	void makeReservation() {
		lock.lock();
		try {
			if (Test.firstFligt[this.getWantSeatNo() - 1] == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				Test.firstFligt[this.getWantSeatNo() - 1] = 1;
				setSeatNo(this.getWantSeatNo());

			} else {
				System.out.println("Uzgunuz! " + this.getWantSeatNo() + " numarali koltuk daha once rezerve edilmis");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			lock.unlock();
		}
	}

}
