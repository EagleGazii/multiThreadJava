public class Flights {

	static int getFreeSeat() {
		int i, j = 0;
		for (i = 0; i < Test.firstFligt.length; i++) {
			if (Test.firstFligt[i] == 0) {
				j += 1;
			}
		}
		return j;
	}

	static void getFlightInfo() {
		int freeSeat = getFreeSeat();
		int i;
		if (freeSeat == 0) {

			System.out.println("Samsundan - Istanbula --- bos koltuk yok.");

		} else {
			System.out.println("Samsundan - Istanbula " + freeSeat + " tane bos koltuk var.");

			for (i = 0; i < Test.firstFligt.length; i++) {
				System.out.print("Seat No " + (i + 1) + " : " + Test.firstFligt[i] + " ");
			}

		}
		System.out.println("\n********************************************************************\n");
	}

}
