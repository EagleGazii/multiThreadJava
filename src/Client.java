public class Client {

	private String fullName;
	private int seatNo;
	private int want_seatNo;

	public int getSeatNo() {
		return seatNo;
	}

	public Client(String fullName , int want_seatNo) {
		this.fullName = fullName;
		this.want_seatNo = want_seatNo;
	}

	void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	int getWantSeatNo() {
		return want_seatNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	void queryReservation() {
		if (this.seatNo == 0) {
			System.out.println(this.fullName + " daha once bir rezervasiyon yapilmamistir ya da iptal edilmis.");

		} else {

			System.out.println(
					this.fullName + " isimli Samsundan - Istanbula ucusunuz var, koltuk numarasi " + this.seatNo);

		}

	}

}
