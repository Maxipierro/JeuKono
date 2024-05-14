package jeukono;

import static java.lang.String.valueOf;

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
		this.setLayout(new GridLayout(4, 4));
		// A completer
	}

	public int coupValide(Case dep, Case arr) {// Teste si la Pion qui est en dep peut de deplacer en arr : renvoie 0 si
												// deplacement interdit, 1 si deplacement valide, 2 si prise valide
		// A completer
		return 0;
	}

	public boolean verifieDeplacementHorPrise(Case dep, Case arr) {
		// A completer
		return true;
	}

	public boolean verifieDeplacementVertPrise(Case dep, Case arr) {
		// A completer
		return true;
	}

	public boolean verifieDeplacementHor(Case dep, Case arr) {
		// A completer
		return true;
	}

	public boolean verifieDeplacementVert(Case dep, Case arr) {
		// A completer
		return true;
	}

	public void jouerCoup(Case dep, Case arr) {
		// A completer
		Fenetre.boutonAnnuler.setEnabled(true);

	}

	public void jouerCoupPrise(Case dep, Case arr) {
		// A completer
		Fenetre.boutonAnnuler.setEnabled(true);
	}

	public boolean testDeplacement() {
		// A completer
		return false;
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
