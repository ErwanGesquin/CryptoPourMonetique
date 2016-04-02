import java.math.BigInteger;

public class TpCrypto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		// On créé l'objet Diffie-Helmann
		DiffieHelmann d = new DiffieHelmann(71, 0, 0);
		
		// On génère les éléments sevrets pour Alice et Bob.
		//Alice :
		d.setA(d.generate_secret_element(new BigInteger(Integer.toString(5)), d.getP()));
		System.out.println(d.getA());
		//Bob :
		d.setB(d.generate_secret_element(new BigInteger(Integer.toString(5)), d.getP()));
		System.out.println(d.getB());
		
		// On créé ensuite les clés a transmettre de Alice a Bob et inversement.
		// Alice : 
		d.setKeyA(d.generate_private_key(d.getA(), d.getG(), d.getP()));
		// Bob : 
		d.setKeyB(d.generate_private_key(d.getB(), d.getG(), d.getP()));
		
		// On calcule ensuite la clé secrète de chaque coté
		// Alice : 
		d.setCommonKey(d.generate_common_key(d.getKeyB(), d.getA(), d.getP()));
		System.out.println(d.getCommonKey());
		d.setCommonKey(new BigInteger(Integer.toString(0)));
		// Bob : 
		d.setCommonKey(d.generate_common_key(d.getKeyA(), d.getB(), d.getP()));
		System.out.println(d.getCommonKey());
		
		int IV = 54;
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
