/**
 * @author Kenzo HOSOMI L'algorithme suivie : https://youtu.be/LgZAI3DdUA4
 */


public class LFSR {
	public LFSR(int iv) {
		
		/*TODO:
			Chiffrer un string puis un fichier
		*/
		super();
		
		this.VI = iv;
		this.LFSR1 = 0;
		this.LFSR2 = 0;
		this.LFSR3 = 0;
		this.mask_7 = 0x40;
		this.mask_8 = 0x80;
		this.mask_10 = 0xA0;
		this.mask_13 = 0x1000;
		this.mask_16 = 0x10000;
		this.mask_17 = 0x20000;
		this.mask_18 = 0x40000;
		this.mask_20 = 0x80000;
		this.mask_21 = 0x100000;
		this.mask_22 = 0x200000;
		this.and_18 = 0xFFFFF;
		this.and_21 = 0x7FFFFF;
		this.and_22 = 0xFFFFFF;
	}

	int VI = 54;

	/* Initialisation à 1*/
	int LFSR1 = 0x3FFFFF;
	int LFSR2 = 0x3FFFFF;
	int LFSR3 = 0x3FFFFF;

	int mask_7 = 0x40;
	int mask_8 = 0x80;
	int mask_10 = 0xA0;
	int mask_13 = 0x1000;
	int mask_16 = 0x10000;
	int mask_17 = 0x20000;
	int mask_18 = 0x40000;
	int mask_20 = 0x80000;
	int mask_21 = 0x100000;
	int mask_22 = 0x200000;
	
	int and_18 = 0xFFFFF;
	int and_21 = 0x7FFFFF;
	int and_22 = 0xFFFFFF;
	

	/**
	 * XOR pour la premiere LFSR
	 * @param LFSR1
	 * @return Dernier bit de l'opération
	 */
	public int xor_LFSR1(int LFSR1) {
		return ((LFSR1 & mask_18)>>18) ^ ((LFSR1 & mask_17)>>17) ^ ((LFSR1 & mask_16)>>16)
				^ ((LFSR1 & mask_13)>>13);
	}
	
	/**
	 * XOR pour la seconde LFSR
	 * @param LFSR2
	 * @return Dernier bit de l'opération
	 */
	public int xor_LFSR2(int LFSR2) {
		return ((LFSR2 & mask_21)>>21) ^ ((LFSR2 & mask_20)>>20);
	}
	
	/**
	 * XOR pour la troisieme LFSR
	 * @param LFSR3
	 * @return Dernier bit de l'opération
	 */
	public int xor_LFSR3(int LFSR3) {
		return ((LFSR3 & mask_22)>>22) ^ ((LFSR3 & mask_21)>>21) ^ ((LFSR3 & mask_20)>>20)
				^ ((LFSR3 & mask_17)>>17);
	}
	
	/**
	 * Décalage à gauche et ajout du dernier bit
	 * @param LFSR
	 * @param bit
	 * @param and_mask
	 * @return
	 */
	public int shift_LFSR(int LFSR, int bit, int and_mask){
		LFSR = LFSR << 1;
		LFSR = bit;		
		LFSR = LFSR & and_mask;
		
		return LFSR;
	}

	/**
	 * 
	 * @return
	 */
	public int CypherAFive (){
		int bit18, bit21, bit22;
		int result;
		
		bit18 = (LFSR1 & and_18)>>18;
		bit21 = (LFSR2 & and_21)>>21;
		bit22 = (LFSR3 & and_22)>>22;
		result = bit18 & bit21 & bit22;
		
		return result;
	}
}