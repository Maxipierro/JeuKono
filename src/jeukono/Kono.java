package jeukono;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Kono {

	public static int etat; // 0 au tour du joueur de selectionner sa piece, 1 en attente de la destination
	public static CouleurPion joueur; // CouleurPion.blanc si c'est le tour du joueur blanc, CouleurPion.noir si c'est
										// celui du joueur noir

	public static Case caseDep;
	public static Case caseArr;

	public static int nbPionBlanc = 8;
	public static int nbPionNoir = 8;
	public static int nbCoupSansPrise = 0;

	public static Plateau unPlateau;
	public static StockPion stockBlanc;
	public static StockPion stockNoir;

	public static Fenetre fenetrePrincipale;
	public static JLabel scoreBlanc;
	public static JLabel scoreNoir;
	public static Border redline = BorderFactory.createLineBorder(Color.red, 10, false);
	public static Border greenline = BorderFactory.createLineBorder(Color.green, 10, false);
	public static Border magentaline = BorderFactory.createLineBorder(Color.magenta, 10, false);
	public static Border cyanline = BorderFactory.createLineBorder(Color.cyan, 10, false);
	public static Border empty = BorderFactory.createEmptyBorder();

	public static int abs(int x) {// Retourne la valeur absolue de x
		return (x < 0 ? -x : x);
	}

	public static void main(String[] args) {

		// Creation de la fenetre
		fenetrePrincipale = new Fenetre("Jeu du Kono");
		// On donne la possibilite de fermer la fenetre
		fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ajouter les composants de la fenetre
		fenetrePrincipale.ajouterComposants(fenetrePrincipale.getContentPane());
		// affichage de la fenetre.
		fenetrePrincipale.pack();
		fenetrePrincipale.setVisible(true);
		joueur = CouleurPion.blanc;
		etat = 0;

	}
}
