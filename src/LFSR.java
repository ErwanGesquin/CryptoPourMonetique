import java.math.BigInteger;
import java.util.Random;

/**
 * @author Kenzo HOSOMI L'algorithme suivie : https://youtu.be/LgZAI3DdUA4
 */

public class LFSR {
	long VI;

	/* Initialisation à 1 */
	long LFSR1;
	long LFSR2;
	long LFSR3;

	long mask_7;
	long mask_8;
	long mask_10;
	long mask_13;
	long mask_16;
	long mask_17;
	long mask_18;
	long mask_20;
	long mask_21;
	long mask_22;
	long mask_64;

	long and_18;
	long and_21;
	long and_22;

	public LFSR(long iv) {

		/*
		 * TODO: Chiffrer un string puis un fichier
		 */
		super();

		this.VI = iv;
		this.LFSR1 = 0L;
		this.LFSR2 = 0L;
		this.LFSR3 = 0L;

		this.mask_7 = 0x40L;
		this.mask_8 = 0x80L;
		this.mask_10 = 0xA0L;
		this.mask_13 = 0x1000L;
		this.mask_16 = 0x10000L;
		this.mask_17 = 0x20000L;
		this.mask_18 = 0x40000L;
		this.mask_20 = 0x80000L;
		this.mask_21 = 0x100000L;
		this.mask_22 = 0x200000L;
		this.mask_64 = 0x8000000000000000L; /* 64eme bit a 1 */

		this.and_18 = 0xFFFFFL;
		this.and_21 = 0x7FFFFFL;
		this.and_22 = 0xFFFFFFL;
	}

	/**
	 * XOR pour la premiere LFSR
	 * 
	 * @param LFSR1
	 * @return Dernier bit de l'opération
	 */
	public long xor_LFSR1(long LFSR1) {
		return ((LFSR1 >> 18) & 1) ^ ((LFSR1 >> 17) & 1) ^ ((LFSR1 >> 16) & 1)
				^ ((LFSR1 >> 13) & 1);
	}

	/**
	 * XOR pour la seconde LFSR
	 * 
	 * @param LFSR2
	 * @return Dernier bit de l'opération
	 */
	public long xor_LFSR2(long LFSR2) {
		return ((LFSR2 >> 21) & 1) ^ ((LFSR2 >> 20) & 1);
	}

	/**
	 * XOR pour la troisieme LFSR
	 * 
	 * @param LFSR3
	 * @return Dernier bit de l'opération
	 */
	public long xor_LFSR3(long LFSR3) {
		return ((LFSR3 >> 22) & 1) ^ ((LFSR3 >> 21) & 1) ^ ((LFSR3 >> 20) & 1)
				^ ((LFSR3 >> 17) & 1);
	}

	/**
	 * Décalage à gauche et ajout du dernier bit
	 * 
	 * @param LFSR
	 * @param bit
	 * @param and_mask
	 * @return
	 */
	public long shift_LFSR(long LFSR, long bit, long and_mask) {
		LFSR = LFSR << 1;
		LFSR += bit;
		LFSR = LFSR & and_mask; /* Garde les n derniers bits de droite */

		return LFSR;
	}

	/**
	 * Renvoie le resultat de l'opération "ET" du premier bit entre les 3 LFSR
	 * 
	 * @param lfsr
	 * @return
	 */
	public long get_first_bit_xor(LFSR lfsr) {
		long bit18, bit21, bit22;

		/* Stockage du bit le plus à droite */
		bit18 = (lfsr.LFSR1 >> 18) & 1;
		bit21 = (lfsr.LFSR2 >> 21) & 1;
		bit22 = (lfsr.LFSR3 >> 22) & 1;
		/* System.out.println("bits: " + bit18 + " : " + bit21 + " : " + bit22); */
		return bit18 ^ bit21 ^ bit22;
	}

	/**
	 * Implémente le schéma du TP
	 * 
	 * @return
	 */
	public LFSR cypher_afive(LFSR lfsr) {
		long xor1, xor2, xor3;
		/* XOR sur les 3 LFSR selon les cases désignés */
		xor1 = xor_LFSR1(lfsr.LFSR1);
		xor2 = xor_LFSR2(lfsr.LFSR2);
		xor3 = xor_LFSR3(lfsr.LFSR3);

		/* Gestion des 4 cas cherchant la mojorité des bits */
		/* Tous identiques */
		if ((xor1 == xor2) && (xor1 == xor3)) {
			lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
			lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor1, and_21);
			lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor1, and_22);
		}
		/* LFSR 1 et 2 */
		else if ((xor1 == xor2) && (xor1 != xor3)) {
			lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
			lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor1, and_21);
		}
		/* LFSR 1 et 3 */
		else if ((xor1 != xor2) && (xor1 == xor3)) {
			lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
			lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor1, and_22);
		}
		/* LFSR 2 et 3 */
		else if ((xor2 == xor3) && (xor1 != xor3)) {
			lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor2, and_21);
			lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor2, and_22);
		}

		return lfsr;
	}

	/**
	 * Initialisation des trois LFSR (64 cycle)
	 * 
	 * @param lfsr
	 * @return
	 */
	public LFSR cycle_64(LFSR lfsr) {
		int i;
		long xor1, xor2, xor3;
		long init_mask = mask_64;

		for (i = 64; i > 0; i--) {
			/* Traitement XOR LFSR1 */
			xor1 = xor_LFSR1(lfsr.LFSR1) ^ (init_mask & lfsr.VI) >> i;
			lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);

			/* Traitement XOR LFSR2 */
			xor2 = xor_LFSR2(lfsr.LFSR2) ^ (init_mask & lfsr.VI) >> i;
			lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor2, and_21);

			/* Traitement XOR LFSR3 */
			xor3 = xor_LFSR3(lfsr.LFSR3) ^ (init_mask & lfsr.VI) >> i;
			lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor3, and_22);

			/* Décallage du masque vers la droite */
			init_mask /= 2;
		}
		return lfsr;
	}

	/**
	 * Effectue 22 cycle de rotation basé sur le nombre de "frame"
	 * 
	 * @param lfsr
	 * @param frame_nb
	 *            (1 frame = 228 bits), frame_nb sur 22 bits
	 * @return
	 */
	public LFSR cycle_22(LFSR lfsr, int frame_nb) {
		int i;
		long xor1, xor2, xor3;
		long init_mask = mask_64;

		for (i = 22; i > 0; i--) {
			/* Traitement XOR LFSR1 */
			xor1 = xor_LFSR1(lfsr.LFSR1) ^ (init_mask & frame_nb) >> i;
			lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);

			/* Traitement XOR LFSR2 */
			xor2 = xor_LFSR2(lfsr.LFSR2) ^ (init_mask & frame_nb) >> i;
			lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor2, and_21);

			/* Traitement XOR LFSR3 */
			xor3 = xor_LFSR3(lfsr.LFSR3) ^ (init_mask & frame_nb) >> i;
			lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor3, and_22);

			/* Décallage du masque vers la droite */
			init_mask /= 2;
		}
		return lfsr;
	}

	/**
	 * Effectue 100 cycle de rotation en "interne"
	 * 
	 * @param lfsr
	 * @return
	 */
	public LFSR cycle_100(LFSR lfsr) {
		long xor1, xor2, xor3;
		int i;

		for (i = 0; i < 100; i++) {
			/* XOR sur les 3 LFSR selon les cases désignés */
			xor1 = xor_LFSR1(lfsr.LFSR1);
			xor2 = xor_LFSR2(lfsr.LFSR2);
			xor3 = xor_LFSR3(lfsr.LFSR3);

			/* Gestion des 4 cas cherchant la mojorité des bits */
			/* Tous identiques */
			if ((xor1 == xor2) && (xor1 == xor3)) {
				lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
				lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor1, and_21);
				lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor1, and_22);
			}
			/* LFSR 1 et 2 */
			else if ((xor1 == xor2) && (xor1 != xor3)) {
				lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
				lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor1, and_21);
			}
			/* LFSR 1 et 3 */
			else if ((xor1 != xor2) && (xor1 == xor3)) {
				lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
				lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor1, and_22);
			}
			/* LFSR 2 et 3 */
			else if ((xor2 == xor3) && (xor1 != xor3)) {
				lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor2, and_21);
				lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor2, and_22);
			}
		}
		return lfsr;
	}

	/**
	 * Renvoie la clef (228 bits)
	 * 
	 * @param lfsr
	 * @return
	 */
	public BigInteger cycle_228(LFSR lfsr) {
		long xor1, xor2, xor3;
		int i;

		Random rnd = new Random(1);
		BigInteger and_228 = new BigInteger(228, rnd); /* 228 * 1 */
		BigInteger key;

		/* Crée un binaire de 228 "1" */
		for (i = 0; i < 228; i++) {
			and_228 = and_228.setBit(i);
		}

		key = new BigInteger("0");
		for (i = 0; i < 228; i++) {
			key = key.shiftLeft(1);
			key = key.add(BigInteger.valueOf(lfsr.get_first_bit_xor(lfsr)));
			System.out
					.println(BigInteger.valueOf(lfsr.get_first_bit_xor(lfsr)));
			/* XOR sur les 3 LFSR selon les cases désignés */
			xor1 = xor_LFSR1(lfsr.LFSR1);
			xor2 = xor_LFSR2(lfsr.LFSR2);
			xor3 = xor_LFSR3(lfsr.LFSR3);

			/* Gestion des 4 cas cherchant la mojorité des bits */
			/* Tous identiques */
			if ((xor1 == xor2) && (xor1 == xor3)) {
				lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
				lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor1, and_21);
				lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor1, and_22);
			}
			/* LFSR 1 et 2 */
			else if ((xor1 == xor2) && (xor1 != xor3)) {
				lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
				lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor1, and_21);
			}
			/* LFSR 1 et 3 */
			else if ((xor1 != xor2) && (xor1 == xor3)) {
				lfsr.LFSR1 = shift_LFSR(lfsr.LFSR1, xor1, and_18);
				lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor1, and_22);
			}
			/* LFSR 2 et 3 */
			else if ((xor2 == xor3) && (xor1 != xor3)) {
				lfsr.LFSR2 = shift_LFSR(lfsr.LFSR2, xor2, and_21);
				lfsr.LFSR3 = shift_LFSR(lfsr.LFSR3, xor2, and_22);
			}

		}
		System.out.println(and_228.toString(2));
		System.out.println(key.toString(2));

		return key.shiftRight(1).and(and_228);
	}

	public BigInteger cypher_xor(BigInteger key, BigInteger msg) {
		BigInteger cyphered;
		int i;
		Random rnd = new Random(1);
		BigInteger and_228 = new BigInteger(228, rnd); /* 228 * 1 */

		/* Crée un binaire de 228 "1" */
		for (i = 0; i < 228; i++) {
			and_228 = and_228.setBit(i);
		}

		cyphered = key;

		cyphered = cyphered.xor(msg);
		cyphered = cyphered.and(and_228);

		return cyphered;
	}
}