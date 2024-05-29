package jeukono;

import static java.lang.String.valueOf;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plateau extends JPanel {
	// instance variables - replace the example below with your own
	private Case[][] monPlateau;

	/**
	 * Constructor for objects of class Echiquier
	 */
	public Plateau() {
		this.setLayout(new GridLayout(4, 8));
		monPlateau = new Case[4][8];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i < 2) {
					this.monPlateau[i][j] = new Case(Color.white, i, j, 0);
					this.monPlateau[i][j].setPion(new Pion(CouleurPion.blanc));
					this.add(this.monPlateau[i][j]);
				} else {
					this.monPlateau[i][j] = new Case(Color.white, i, j, 0);
					this.monPlateau[i][j].setPion(new Pion(CouleurPion.noir));
					this.add(this.monPlateau[i][j]);
				}
			}
		}
	}

	boolean isIn(Case C) {
		if (C.isOccupe() == true) {
			return true;
		} else
			return false;
	}

	public int coupValide(Case dep, Case arr) {
		int resultat = 0;
		int x1 = dep.getAbscisse(); // depart ligne
		int x2 = arr.getAbscisse(); // arrivé ligne
		int y1 = dep.getOrdonnee(); // depart colonne
		int y2 = arr.getOrdonnee(); // arrivé colonne
		int dy = Kono.abs(dep.getAbscisse() - arr.getAbscisse());
		int dx = Kono.abs(dep.getOrdonnee() - arr.getOrdonnee());
		int CaseDevantx = ((dep.getAbscisse() + arr.getAbscisse()) / 2);
		int CaseDevanty = ((dep.getOrdonnee() + arr.getOrdonnee()) / 2);

		System.out
				.println("mov pion:" + x1 + "," + y1 + "--->" + x2 + "," + y2 + " delta_x : " + dx + " delta_y :" + dy);

		if ((dx == 2 && dy == 0) || (dy == 2 && dx == 0)) {
			if (verifieDeplacementHorPrise(dep, arr) == true) {
				System.out.println("case Devant : " + CaseDevanty + "dy");
				// trouver le problème avec la case de devant qui doit être de la même équipe
				System.out.println("CoupValideDeplacementHorizontalePrise");
				resultat = 2;
			}
			if (verifieDeplacementVertPrise(dep, arr) == true) {
				System.out.println("case Devant : " + CaseDevantx + "dx");
				System.out.println("CoupValideDeplacementVerticalePrise");

				resultat = 2;
			}
		} else if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1)) {
			if (verifieDeplacementHor(dep, arr) == true) {
				System.out.println("CoupValideDeplacementHorizontale");
				resultat = 1;
			}
			if (verifieDeplacementVert(dep, arr) == true) {
				System.out.println("CoupValideDeplacementVerticale");
				resultat = 1;
			}
		} else {
			resultat = 3;
		}
		return resultat;
	}

	public boolean verifieDeplacementHorPrise(Case dep, Case arr) {
		int dx = Kono.abs(dep.getOrdonnee() - arr.getOrdonnee());
		if ((dx == 2) && (arr.isOccupe() == true)) {
			Pion arrPion = arr.getPion(); // on regarde la couleur du pion présent sur la case pour savoir de qu'elle
			// équipe il appartient
			Pion depPion = dep.getPion();
			if (arrPion.getCouleur() == depPion.getCouleur()) {
				return false;
			} else {

				return true;
			}
		} else {
			return false;
		}
	}

	public boolean verifieDeplacementVertPrise(Case dep, Case arr) {
		int dy = Kono.abs(dep.getAbscisse() - arr.getAbscisse());
		if ((dy == 2) && (arr.isOccupe() == true)) {
			Pion arrPion = arr.getPion(); // on regarde la couleur du pion présent sur la case pour savoir de qu'elle
			// équipe il appartient
			Pion depPion = dep.getPion();
			if (arrPion.getCouleur() == depPion.getCouleur()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean verifieDeplacementHor(Case dep, Case arr) {
		int dx = Kono.abs(dep.getOrdonnee() - arr.getOrdonnee());
		if ((dx == 1) && (arr.isOccupe() == false)) { // on vérifie qu'il n 'y a pas de pion deja présent // sur la case
			return true;
		} else {
			return false;
		}
	}

	public boolean verifieDeplacementVert(Case dep, Case arr) {
		int dy = Kono.abs(dep.getAbscisse() - arr.getAbscisse());
		if ((dy == 1) && (arr.isOccupe() == false)) {
			return true;
		} else {
			return false;
		}
	}

	public void jouerCoup(Case dep, Case arr) {
		arr.setPion(dep.getPion());
		dep.setBorder(Kono.empty);
		dep.setPion(null);
		Kono.nbCoupSansPrise = Kono.nbCoupSansPrise + 1;
		System.out.println("NbCoupSansPrise : " + Kono.nbCoupSansPrise);
		Fenetre.boutonAnnuler.setEnabled(true);

	}

	public void jouerCoupPrise(Case dep, Case arr) {
		// Pion p = Kono.caseArr.getPion();
		if (Kono.joueur == CouleurPion.blanc) {
			Kono.stockBlanc.ajouterPion(arr.getPion());
			Kono.nbPionNoir--;
		} else {
			Kono.stockNoir.ajouterPion(arr.getPion());
			Kono.nbPionBlanc--;
		}
		arr.setPion(dep.getPion()); // récupérer la valeur de case du depart et la
		// mettre dans// l'arrivée
		dep.setBorder(Kono.empty);

		dep.setPion(null);
		Kono.nbCoupSansPrise = 0;

		// A completer
		Fenetre.boutonAnnuler.setEnabled(true);
	}

	public boolean testDeplacement() {
		// A completer
		return true;
	}

	public void annulerCoup(Case dep, Case arr) {

		Pion temp = null;
		int nb_colonne = Kono.abs(dep.getAbscisse() - arr.getAbscisse());
		int nb_ligne = Kono.abs(dep.getOrdonnee() - arr.getOrdonnee());

		if ((nb_ligne == 0 && nb_colonne == 1) || (nb_ligne == 1 && nb_colonne == 0)) // si dernier coup simple
		{
			System.out.println("Coup simple");
			temp = dep.getPion();
			dep.setPion(arr.getPion());
			arr.setPion(temp);
		}

		if ((nb_ligne == 0 && nb_colonne == 2) || (nb_ligne == 2 && nb_colonne == 0)) // si dernier coup avec prise
		{
			System.out.println("Coup prise");
			if (Kono.joueur == CouleurPion.blanc) {
				System.out.println("On annule le coup du joueur noir");
				dep.setPion(arr.getPion());
				arr.setPion(Kono.stockNoir.enleverPion());
				Kono.nbPionBlanc++;
			} else {
				System.out.println("On annule le coup du joueur blanc");

				dep.setPion(arr.getPion());
				arr.setPion(Kono.stockBlanc.enleverPion());
				Kono.nbPionNoir++;
			}

		}

		Kono.etat = 0;
		if (Kono.joueur == CouleurPion.blanc)
			Kono.joueur = CouleurPion.noir;
		else
			Kono.joueur = CouleurPion.blanc;

		Kono.scoreBlanc.setText("Score Joueur Blanc \n " + valueOf(Kono.nbPionBlanc));
		Kono.scoreNoir.setText("Score Joueur Noir \n" + valueOf(Kono.nbPionNoir));

		Kono.fenetrePrincipale.repaint();
		Kono.fenetrePrincipale.validate();

		Fenetre.boutonAnnuler.setEnabled(false);

	}

	public void reinitialiser() {

		System.out.println("dans reinitialiser");
		Kono.fenetrePrincipale.dispose();
		Kono.fenetrePrincipale = new Fenetre("Jeu du Kono");
		// On donne la possibilite de fermer la fenetre
		Kono.fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Kono.fenetrePrincipale.ajouterComposants(Kono.fenetrePrincipale.getContentPane());
		Kono.joueur = CouleurPion.blanc;
		Kono.etat = 0;
		Kono.nbPionBlanc = 8;
		Kono.nbPionNoir = 8;
		Kono.nbCoupSansPrise = 0;

		Kono.scoreBlanc.setText("Score Joueur Blanc \n " + valueOf(Kono.nbPionBlanc));
		Kono.scoreNoir.setText("Score Joueur Noir \n" + valueOf(Kono.nbPionNoir));

		Kono.fenetrePrincipale.pack();
		Kono.fenetrePrincipale.setVisible(true);
		Kono.fenetrePrincipale.repaint();
		Kono.fenetrePrincipale.validate();

		Fenetre.boutonAnnuler.setEnabled(false);

	}

}
