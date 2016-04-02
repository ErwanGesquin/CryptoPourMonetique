public class TpCrypto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int IV = 60;
		LFSR lfsr;
		
		lfsr = new LFSR(IV);
		
		int result = lfsr.CypherAFive();
		
		System.out.println("LFSR 1 = "+Integer.toBinaryString(lfsr.LFSR1));
		System.out.println("LFSR 2 = "+Integer.toBinaryString(lfsr.LFSR2));
		System.out.println("LFSR 3 = "+Integer.toBinaryString(lfsr.LFSR3));
		System.out.println("Chiffré = "+result);

		// Impossible d'imprimer le binaire formaté
		//strLFSR1 = String.format("%5s", Integer.toBinaryString(LFSR1)).replace(' ', '0');
	    //System.out.println("%2d", Integer.toBinaryString(1));
	}
}
