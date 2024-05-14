package jeukono;

public class Pion {
	private int mvt = 1;// mouvement horizontal et vertical autorisé
	private int mvtPrise = 2;// mouvement horizontal et vertical autorisé
	private String typePion = ""; 
	private CouleurPion couleur;// blanc, noir ou transparent

	public Pion(CouleurPion couleur) {
		this.couleur = couleur;
		if(this.couleur == CouleurPion.blanc){
			typePion = "guepard";
		}else{
			typePion = "zebre";
		}
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
		return typePion;
	}

}
