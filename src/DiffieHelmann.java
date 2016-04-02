import java.math.BigInteger;
import java.util.Random;

public class DiffieHelmann {
	
	//donnees publiques
	private BigInteger p; //nbr premier
	private BigInteger g; //generateur de(Z/pZ)*
	
	//donnees relatives a Alice
	private BigInteger a; //entier a vérifiant 1 <= a <= p-2
	private BigInteger keyA;
	
	//donnees relatives a Bob
	private BigInteger b; 
	private BigInteger keyB;
	
	//cle commune
	private BigInteger commonKey;
	private BigInteger key;
	
	//Constructors
	public DiffieHelmann(int g, int a, int b) {
		Random rnd = new Random();
		this.p = new BigInteger(64, 100, rnd);
		this.g = new BigInteger(Integer.toString(g));
		this.a = new BigInteger(Integer.toString(a));
		this.b = new BigInteger(Integer.toString(b));
	}
		

	//getters/setters
	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public BigInteger getG() {
		return g;
	}

	public void setG(BigInteger g) {
		this.g = g;
	}

	public BigInteger getA() {
		return a;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public BigInteger getKeyA() {
		return keyA;
	}

	public void setKeyA(BigInteger keyA) {
		this.keyA = keyA;
	}

	public BigInteger getB() {
		return b;
	}

	public void setB(BigInteger b) {
		this.b = b;
	}

	public BigInteger getKeyB() {
		return keyB;
	}

	public void setKeyB(BigInteger keyB) {
		this.keyB = keyB;
	}

	public BigInteger getCommonKey() {
		return commonKey;
	}

	public void setCommonKey(BigInteger commonKey) {
		this.commonKey = commonKey;
	}

	public BigInteger getKey() {
		return key;
	}

	public void setKey(BigInteger key) {
		this.key = key;
	}
	
	

	/**
	 * Génère l'élément Ka ou Kb à envoyer à l'interlocuteur.
	 * @param a
	 * @param g
	 * @param p
	 * @return Ka ou Kb
	 */
	public BigInteger generate_private_key(BigInteger a, BigInteger g, BigInteger p){
		return g.modPow(a, p);
	}
	


	/**
	 * Calcul la clé commune utilisée pour l'chiffrer la suite de l'échange.
	 * @param key
	 * @param pow
	 * @param p
	 * @return La clé partagée par le deux interlocuteurs
	 */
	public BigInteger generate_common_key(BigInteger key, BigInteger pow, BigInteger p){
		return key.modPow(pow, p);
	}
	
	/**
	 * Calcul de l'élément secret.
	 * @param a
	 * @param p
	 * @return L'élément secret
	 */
	public BigInteger generate_secret_element(BigInteger a, BigInteger p){
		Random rnd = new Random();
		a = new BigInteger(64, rnd);
		return a.mod(p);
	}
	
	
}
