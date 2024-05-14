package jeukono;

import static java.lang.String.valueOf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Case extends JButton implements ActionListener {

	private int typeCase; // si typeCase == 0, case du plateau, si typeCase ==1 case du stock

	private ImageIcon imagePion;
	private Pion pion;
	private Color couleurFond;

	private boolean occupe;
	private int abscisse;
	private int ordonnee;

	public Case(Color couleur, int abs, int ord, int typeCase) {

		this.setBackground(couleur);
		this.setPreferredSize(new Dimension(100, 100));
		this.typeCase = typeCase;

		// A completer

		addActionListener(this);
	}

	public Pion getPion() {
		// A completer
		return null;
	}

	public void setPion(Pion p) {
		// A completer
	}

	public int getAbscisse() {
		// A completer
		return 0;

	}

	public int getTypeCase() {
		// A completer
		return 0;
	}

	public int getOrdonnee() {
		// A completer
		return 0;
	}

	public boolean isOccupe() {
		// A completer
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		// A completer
		if (Kono.etat == 0) {
			// reactiver le bouton annuler
			Fenetre.boutonAnnuler.setEnabled(false);

			// si on n'a pas encore selectionne de case de depart
			Kono.caseDep = ((Case) e.getSource());

			// si la case selectionne pour le depart du deplacement est valide : elle est
			// occupee par une Pion de la couleur du joueur dont c'est le tour, on peut
			// selectionner la case d'arrivee
			Kono.caseDep.setBorder(Kono.redline);

		} else {
			if (Kono.etat == 1) {
				// identification de la case possible d'arrivée
				Kono.caseArr = ((Case) e.getSource());

				boolean finPartieBloque = false;
				boolean finPartieNbPion = false;
				boolean finPartieSansPrise = false;
				if (Kono.caseDep.getTypeCase() == 0) {
					// si la case selectionnee est bien dns le plateau et que le coup est valide
					if (Kono.unPlateau.coupValide(Kono.caseDep, Kono.caseArr) == 1) {
						// si c'est un deplacement simple
						// 1. on deplace le pion
						// 2. on verifie que le nombre de coups sans prise est inferieur a 25
						// 3. on verifie que le prochain joueur pourra jouer
					} else {
						if (Kono.unPlateau.coupValide(Kono.caseDep, Kono.caseArr) == 2) {
							// si c'est un deplacement avec prise
							// 1. on deplace le pion
							// 2. on verifie que le prochain joueur a plus d'un pion
							// 3. on verifie que le prochain joueur pourra jouer
						} else {
							// la case d'arrivee selectionnee n'est pas valide
							if (Kono.caseArr.getPion() != null) {// si elle contient un pion de la couleur du joueur,
																	// elle devient la case d'arrivee
							}
						}
					}
				}

				// on met a jour l'affichage su nombre de pions des joueurs
				Kono.scoreBlanc.setText("Score Joueur Blanc \n " + valueOf(Kono.nbPionBlanc));
				Kono.scoreNoir.setText("Score Joueur Noir \n" + valueOf(Kono.nbPionNoir));
				Kono.fenetrePrincipale.repaint();
				Kono.fenetrePrincipale.validate();
				// on reactive le bouton annuler
				Fenetre.boutonAnnuler.setEnabled(true);

				if (finPartieNbPion || finPartieBloque || finPartieSansPrise) {
					// si la partie est finie pour une des raisons de fin de partie
					// ouvrir une boite de dialogue pour signifier que le partie est finie : 2 choix
					// fermer le jeu ou recommencer
					JOptionPane d = new JOptionPane(); // les textes figurant // sur les boutons
					String lesTextes[] = { "Recommencer", "Fermer le jeu" }; // indice du bouton qui a été // cliqué ou
																				// CLOSED_OPTION

					int retour = 0;
					if (finPartieSansPrise) {
						if (Kono.nbPionBlanc == Kono.nbPionNoir)
							retour = d.showOptionDialog(this,
									"Partie terminée car plus de 25 coups sans prise. Les joueurs sont à égalité",
									"Fin de jeu", 1, 1, new ImageIcon(), lesTextes, lesTextes[0]);
						else {
							if (Kono.nbPionBlanc > Kono.nbPionNoir)
								Kono.joueur = CouleurPion.noir;
							else
								Kono.joueur = CouleurPion.blanc;
							retour = d.showOptionDialog(this,
									"Partie terminée car plus de 25 coups sans prise. Le joueur " + Kono.joueur
											+ " a perdu !",
									"Fin de jeu", 1, 1, new ImageIcon(), lesTextes, lesTextes[0]);
						}
					} else {
						if (finPartieNbPion) {
							retour = d.showOptionDialog(this,
									"Partie terminée car le joueur " + Kono.joueur
											+ " n'a plus qu'un pion. Il a perdu !",
									"Fin de jeu", 1, 1, new ImageIcon(), lesTextes, lesTextes[0]);
						} else {
							if (finPartieBloque) {
								retour = d.showOptionDialog(this,
										"Partie terminée car le joueur " + Kono.joueur + " ne est bloque. Il a perdu !",
										"Fin de jeu", 1, 1, new ImageIcon(), lesTextes, lesTextes[0]);
							}
						}
					}
					if (retour == 0) {
						Kono.unPlateau.reinitialiser();
					} else {
						Kono.fenetrePrincipale.dispose();
					}
				}

			}
		}

	}

	public String toString() {
		// A completer
		return "";
	}
}
