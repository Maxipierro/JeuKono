package jeukono;

import java.awt.Color;
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
		monStockPion = new Case[4][2];
		this.setOpaque(true);
		this.setLayout(new GridLayout(4, 2));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				monStockPion[i][j] = new Case(Color.black, i, j, 1);
				this.add(monStockPion[i][j]);
			}
		}
	}

	public void ajouterPion(Pion p) {
		// A completer
		if (indi > 3) {
			indi = 0;
			indj++;
		}
		monStockPion[indi][indj].setPion(p);
		indi++;
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
