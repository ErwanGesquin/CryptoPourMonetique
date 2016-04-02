public class TpCrypto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		// On créé l'objet Diffie-Helmann
		DiffieHelmann d = new DiffieHelmann(71, 0, 0);
		
		// On génère les éléments sevrets pour Alice et Bob.
		//Alice :
		d.setA(d.generate_secret_element(5, d.getP()));
		System.out.println(d.getA());
		//Bob :
		d.setB(d.generate_secret_element(6, d.getP()));
		System.out.println(d.getB());
		
		// On créé ensuite les clés a transmettre de Alice a Bob et inversement.
		// Alice : 
		d.setKeyA(d.generate_private_key(d.getA(), d.getG(), d.getP()));
		System.out.println("Alice: key= "+d.getKeyA());
		// Bob : 
		d.setKeyB(d.generate_private_key(d.getB(), d.getG(), d.getP()));
		System.out.println("Bob: key= "+d.getKeyB());
		
		// On calcule ensuite la clé secrète de chaque coté
		// Alice : 
		d.setCommonKey(d.generate_common_key(d.getKeyB(), d.getA(), d.getP()));
		System.out.println("Alice: Common key= "+d.getCommonKey());
		//d.setCommonKey(0);
		// Bob : 
		d.setCommonKey(d.generate_common_key(d.getKeyA(), d.getB(), d.getP()));
		System.out.println("Bob: Common key= "+d.getCommonKey());
		System.out.println("p= "+d.getP());
		
		/*=================================================*/
		long iv;
		LFSR lfsr;
				
		iv = d.getCommonKey();
		lfsr = new LFSR(iv);
		long result = lfsr.cypher_afive();
		
		System.out.println("VI = "+Long.toBinaryString(lfsr.VI));
		System.out.println("LFSR 1 = "+Long.toBinaryString(lfsr.LFSR1));
		System.out.println("LFSR 2 = "+Long.toBinaryString(lfsr.LFSR2));
		System.out.println("LFSR 3 = "+Long.toBinaryString(lfsr.LFSR3));
		System.out.println("Chiffré = "+result);

		// Impossible d'imprimer le binaire formaté
		//strLFSR1 = String.format("%5s", Integer.toBinaryString(LFSR1)).replace(' ', '0');
	    //System.out.println("%2d", Integer.toBinaryString(1));
	}
}
