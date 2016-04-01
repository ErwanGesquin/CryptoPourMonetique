
public class DiffieHelmann {
	
	//donnees publiques
	private int p; //nbr premier
	private int g; //generateur de(Z/pZ)*
	//donnees relatives a Alice
	private int a; //entier a vérifiant 1 <= a <= p-2
	//donnees relatives a Bob
	private int b; 
	
	//Constructors
	public DiffieHelmann(int p, int g, int a, int b) {
		this.p = p;
		this.g = g;
		this.a = a;
		this.b = b;
	}
	
	public DiffieHelmann() {
		this.p = 0;
		this.g = 0;
		this.a = 0;
		this.b = 0;
	}
	
	//getters/setters
	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	

	//Calcul de la cle privee
	public int generate_private_key(int a, int g, int p){
		return (int) Math.pow(g, a) % p;
	}
	
	//Calcul de la cle commune
	public int generate_common_key(int key, int pow, int p){
		return (int) Math.pow(key, pow) % p;
	}
	
	
}
