import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class DiffieHelmann {
	
	//donnees publiques
	private long p; //nbr premier
	private long g; //generateur de(Z/pZ)*
	
	//donnees relatives a Alice
	private long a; //entier a vérifiant 1 <= a <= p-1
	private long keyA;
	
	//donnees relatives a Bob
	private long b; 
	private long keyB;
	
	//cle commune
	private long commonKey;
	
	//Constructors
	public DiffieHelmann(long a, long b) {
		this.p = generate_prime();
		this.g = 51;
		this.a = a;
		this.b = b;
	}
		

	//getters/setters
	public long getP() {
		return p;
	}

	public void setP(long p) {
		this.p = p;
	}

	public long getG() {
		return g;
	}

	public void setG(long g) {
		this.g = g;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public long getKeyA() {
		return keyA;
	}

	public void setKeyA(long keyA) {
		this.keyA = keyA;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public long getKeyB() {
		return keyB;
	}

	public void setKeyB(long keyB) {
		this.keyB = keyB;
	}

	public long getCommonKey() {
		return commonKey;
	}

	public void setCommonKey(long commonKey) {
		this.commonKey = commonKey;
	}


	/**
	 * Générateur de nombres premiers.
	 * @return un nombre très probablement premier.
	 */
	private long generate_prime(){
		SecureRandom rnd = new SecureRandom();
		long ret;
		do {
			ret = rnd.nextLong();
			
		}while(!BigInteger.valueOf(ret).isProbablePrime(10000) || ret <= 0);
		
		
		return ret;
	}
	
	/**
	 * Génère l'élément Ka ou Kb à envoyer à l'interlocuteur.
	 * @param a
	 * @param g
	 * @param p
	 * @return Ka ou Kb
	 */
	public long generate_private_key(long a, long g, long p){
		return BigInteger.valueOf(g).modPow(BigInteger.valueOf(a), BigInteger.valueOf(p)).longValue();
		
	}
	
	/**
	 * Calcul la clé commune utilisée pour l'chiffrer la suite de l'échange.
	 * @param key
	 * @param pow
	 * @param p
	 * @return La clé partagée par le deux interlocuteurs
	 */
	public long generate_common_key(long key, long pow, long p){
		return BigInteger.valueOf(key).modPow(BigInteger.valueOf(pow), BigInteger.valueOf(p)).longValue();
		
	}
	
	/**
	 * Calcul de l'élément secret.
	 * @param a
	 * @param p
	 * @return L'élément secret
	 */
	public long generate_secret_element(long a, long p){
		SecureRandom rnd = new SecureRandom();
		do{
			a = rnd.nextLong();
		}while(a <= 0);
		
		return a % p;
	}
	
	
}
