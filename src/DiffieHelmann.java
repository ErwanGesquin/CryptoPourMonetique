import java.util.Random;

public class DiffieHelmann {
	
	//donnees publiques
	private long p; //nbr premier
	private long g; //generateur de(Z/pZ)*
	
	//donnees relatives a Alice
	private long a; //entier a vérifiant 1 <= a <= p-2
	private long keyA;
	
	//donnees relatives a Bob
	private long b; 
	private long keyB;
	
	//cle commune
	private long commonKey;
	private long key;
	
	//Constructors
	public DiffieHelmann(long g, long a, long b) {
		Random rnd = new Random();
		this.p = rnd.nextLong();
		this.g = g;
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

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}
	
	

	/**
	 * Génère l'élément Ka ou Kb à envoyer à l'interlocuteur.
	 * @param a
	 * @param g
	 * @param p
	 * @return Ka ou Kb
	 */
	public long generate_private_key(long a, long g, long p){
		return (long) Math.pow(a, g) % p;
	}
	


	/**
	 * Calcul la clé commune utilisée pour l'chiffrer la suite de l'échange.
	 * @param key
	 * @param pow
	 * @param p
	 * @return La clé partagée par le deux interlocuteurs
	 */
	public long generate_common_key(long key, long pow, long p){
		return (long) Math.pow(key, pow) % p;
	}
	
	/**
	 * Calcul de l'élément secret.
	 * @param a
	 * @param p
	 * @return L'élément secret
	 */
	public long generate_secret_element(long a, long p){
		Random rnd = new Random();
		a = rnd.nextLong();
		return a % (p - 2);
	}
	
	
}
