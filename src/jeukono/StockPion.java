package jeukono;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class StockPion extends JPanel {
	private Case[][] monStockPion;
	private CouleurPion couleur;
	private int indi, indj;

	/**
	 * Constructor for objects of class StockPion
	 */
	public StockPion(CouleurPion couleur) {
		this.couleur = couleur;
		this.indi = 0;
		this.indj = 0;
		this.setLayout(new GridLayout(4, 2));
		// A completer

	}

	public void ajouterPion(Pion p) {

		// A completer

	}

	public Pion enleverPion() {
		if (indj == 0) {
			indi--;
			indj = monStockPion[0].length - 1;
		} else
			indj--;
		Pion p = monStockPion[indi][indj].getPion();
		System.out.println(monStockPion[indi][indj].getPion().toString());
		monStockPion[indi][indj].setPion(null);
		return p;
	}

}
