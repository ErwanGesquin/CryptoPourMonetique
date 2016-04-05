import java.math.BigInteger;

public class TpCrypto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 // On créé l'objet Diffie-Helmann 
		 DiffieHelmann d = new DiffieHelmann(0, 0);
		 
		 // On génère les éléments sevrets pour Alice et Bob. 
		 // Alice :
		 d.setA(d.generate_secret_element(5, d.getP()));
		 System.out.println("Aléa de Alice = "+d.getA()); 
		 // Bob :
		 d.setB(d.generate_secret_element(6, d.getP()));
		 System.out.println("Aléa de Bob   = "+d.getB());
		 
		 // On créé ensuite les clés a transmettre de Alice a Bob et inversement.
		 //Alice : 
	     d.setKeyA(d.generate_private_key(d.getA(),	 d.getG(), d.getP()));
		 System.out.println("\nAlice: public key = " + d.getKeyA()); 
		 //Bob : 
	     d.setKeyB(d.generate_private_key(d.getB(), d.getG(), d.getP()));
	     System.out.println("Bob  : public key = " + d.getKeyB());
		 
		 // On calcule ensuite la clé secrète de chaque coté // Alice :
		 d.setCommonKey(d.generate_common_key(d.getKeyB(), d.getA(),
		 d.getP())); System.out.println("\nCommon key = " + d.getCommonKey()); 
		 // d.setCommonKey(0); 
		 // Bob :
		 d.setCommonKey(d.generate_common_key(d.getKeyA(), d.getB(), d.getP()));
		 System.out.println("Nombre premier, p = " + d.getP());



		/* ================================================= */
		long iv;
		LFSR lfsr;


		iv = d.getCommonKey();
		//iv = 999999999999L;

		lfsr = new LFSR(iv);
		long result = lfsr.get_first_bit_xor(lfsr);
		lfsr = lfsr.cypher_afive(lfsr);
		System.out.println("\nVI = " + Long.toBinaryString(lfsr.VI));
		
		System.out.println("\n--- Initialisation des LFSR ---");
		System.out.println("LFSR 1 = " + Long.toBinaryString(lfsr.LFSR1));
		System.out.println("LFSR 2 = " + Long.toBinaryString(lfsr.LFSR2));
		System.out.println("LFSR 3 = " + Long.toBinaryString(lfsr.LFSR3));

		lfsr = lfsr.cycle_64(lfsr);

		System.out.println("\n--- Affichage des LFSR après les 64 cycles ---");
		System.out.println("LFSR 1 = " + Long.toBinaryString(lfsr.LFSR1));
		System.out.println("LFSR 2 = " + Long.toBinaryString(lfsr.LFSR2));
		System.out.println("LFSR 3 = " + Long.toBinaryString(lfsr.LFSR3));

		lfsr = lfsr.cycle_100(lfsr);
		
		System.out.println("\n--- Affichage des LFSR après les 100 cycles ---");
		System.out.println("LFSR 1 = " + Long.toBinaryString(lfsr.LFSR1));
		System.out.println("LFSR 2 = " + Long.toBinaryString(lfsr.LFSR2));
		System.out.println("LFSR 3 = " + Long.toBinaryString(lfsr.LFSR3));

		BigInteger key = lfsr.cycle_228(lfsr);
		BigInteger cyphered = lfsr.cypher_xor(key, new BigInteger("898454354"));

		/* Lecture d'un message depuis un fichier */
		BigInteger read = lfsr.file_to_bigint("./msg.txt");
		/* Chiffrement */ 
		cyphered = lfsr.cypher_xor(key, read);

		
		/* Ecriture du chiffre dans un fichier */
		/* !!! Partie qui bug attention !!!*/
		//lfsr.bigint_to_file("./chiffre.txt", cyphered);
		//read = lfsr.file_to_bigint("./chiffre.txt");
		
		
		/* Déchiffrement du chiffré */
		BigInteger unphered = lfsr.cypher_xor(key, read);
		
		/* Version avec byte[] */
		byte[] key_byte = key.toByteArray();
		byte[] read_byte = lfsr.file_to_byte("./msg.txt");
		
		byte[] cyphered_byte = lfsr.byte_xor(key_byte, read_byte);

		System.out.println("-----------------------------");
		System.out.println("\n--- Génération de la clef après 228 cycles ---");
		System.out.println("Clef de chiffrement = "+new BigInteger(key_byte).toString(2));
		System.out.println("Taille de la clef   = "+new BigInteger(key_byte).bitLength());
		
		System.out.println("\nLecture du fichier (Binaire)  = " + new BigInteger(read_byte).toString(2));
		System.out.println("Lecture du fichier (Décimal)  = " + new BigInteger(read_byte).toString());
		System.out.println("Lecture du fichier (Alphabet) = " + new String(new BigInteger(read_byte).toByteArray()));
		
		System.out.println("\nChiffré (Binaire)   = " + new BigInteger(cyphered_byte).toString(2));
		System.out.println("Chiffré (Décimal)   = " + new BigInteger(cyphered_byte).toString());
		System.out.println("Chiffré (Alphabet)  = " + new String(new BigInteger(cyphered_byte).toByteArray()));

		lfsr.byte_to_file("./chiffre.txt", cyphered_byte);
		
		cyphered_byte = lfsr.file_to_byte("./chiffre.txt");
		byte[] result_byte = lfsr.byte_xor(key_byte, cyphered_byte);
		System.out.println("\nDéchiffré (Binaire)  = " + new BigInteger(result_byte).toString(2));
		System.out.println("Déchiffré (Décimal)  = " + new BigInteger(result_byte).toString());
		System.out.println("Déchiffré (Alphabet) = " + new String(new BigInteger(result_byte).toByteArray()));
	}
}
