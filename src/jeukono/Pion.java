package jeukono;

public class Pion {
	private int mvt = 1;// mouvement horizontal et vertical autorisé
	private int mvtPrise = 2;// mouvement horizontal et vertical autorisé

	private CouleurPion couleur;// blanc, noir ou transparent

	public Pion(CouleurPion couleur) {
		this.couleur = couleur;
		// A completer
	}

	public int getMvt() {
		return this.mvt;
	}

	public int getMvtPrise() {
		return this.mvtPrise;
	}

	public CouleurPion getCouleur() {
		return this.couleur;
	}

	public String toString() {
		// A completer
		return "";
	}

}
