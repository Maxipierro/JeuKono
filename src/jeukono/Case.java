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

		this.abscisse = abs;
		this.ordonnee = ord;
		this.typeCase = typeCase;
		this.couleurFond = couleur;
		this.setOpaque(true);
		this.setBackground(couleur);
		this.setPreferredSize(new Dimension(100, 100));
		addActionListener(this);
	}

	public Pion getPion() {
		return this.pion;
	}

	public void setPion(Pion p) {
		if (p != null) {
			this.pion = p;
			this.occupe = true;
			this.imagePion = new ImageIcon("/Users/pierre/eclipse-workspace/JeuKono/" + pion.toString() + ".png");
			System.out.println("/Users/pierre/eclipse-workspace/JeuKono/" + pion.toString() + ".png");
			this.setIcon(imagePion);
		} else {
			this.occupe = false;
			imagePion = null;
			pion = null;
			setIcon(imagePion);
		}
	}

	public int getAbscisse() {
		// A completer
		return this.abscisse;

	}

	public int getOrdonnee() {
		// A completer
		return this.ordonnee;
	}

	public int getTypeCase() {
		// A completer
		return this.typeCase;
	}

	public boolean isOccupe() {
		// A completer
		return this.occupe;
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

			if (Kono.caseDep.getTypeCase() == 0) {
				if (Kono.caseDep.isOccupe() == true) {
					if (Kono.caseDep.getPion().getCouleur() == Kono.joueur) {

						Kono.caseDep.setBorder(Kono.redline);
						Kono.etat = 1;
					}
				}
			}
		} else {
			if (Kono.etat == 1) {
				// identification de la case possible d'arrivée
				Kono.caseArr = ((Case) e.getSource());

				boolean finPartieBloque = false;
				boolean finPartieNbPion = false;
				boolean finPartieSansPrise = false;
				if (Kono.caseArr.getTypeCase() == 0) {

					if (Kono.caseArr.isOccupe() == true) {
						if (Kono.caseArr.getPion().getCouleur() == Kono.joueur) {
							Kono.caseDep.setBorder(Kono.empty);
							Kono.caseDep = Kono.caseArr;
							Kono.caseDep.setBorder(Kono.redline);
						}
					}

					// si la case selectionnee est bien dns le plateau et que le coup est valide
					if (Kono.unPlateau.coupValide(Kono.caseDep, Kono.caseArr) == 1) {
						// si c'est un deplacement simple
						// Kono.caseArr.setPion(Kono.caseDep.getPion());
						// Kono.caseDep.setBorder(null);
						// Kono.caseDep.setPion(null);

						// 1. on deplace le pion
						Kono.unPlateau.jouerCoup(Kono.caseDep, Kono.caseArr);
						// 2. on verifie que le nombre de coups sans prise est inferieur a 25
						if (Kono.nbCoupSansPrise == 25)
							finPartieSansPrise = true;

						// changer de joueur
						if (Kono.joueur == CouleurPion.blanc)
							Kono.joueur = CouleurPion.noir;
						else
							Kono.joueur = CouleurPion.blanc;

						// 3. on verifie que le prochain joueur pourra jouer (c'est Kono.joueur)
						if (Kono.unPlateau.testDeplacement() == false)
							finPartieBloque = true;

						Kono.etat = 0;

					} else {
						if (Kono.unPlateau.coupValide(Kono.caseDep, Kono.caseArr) == 2) {
							// si c'est un deplacement avec prise
							// 1. on deplace le pion

							Kono.unPlateau.jouerCoupPrise(Kono.caseDep, Kono.caseArr);

							// changer de joueur
							if (Kono.joueur == CouleurPion.blanc) {
								Kono.joueur = CouleurPion.noir;
								if (Kono.nbPionNoir <= 1)
									finPartieNbPion = true;
							} else {
								Kono.joueur = CouleurPion.blanc;
								if (Kono.nbPionBlanc <= 1)
									finPartieNbPion = true;

							}

							if (Kono.unPlateau.testDeplacement() == false)
								finPartieBloque = true;

							Kono.etat = 0;
							// Joueur = !Joueur; // si Joueur = true c'est joureur 1 sinon joueur 2
							// 2. on verifie que le prochain joueur a plus d'un pion
							// 3. on verifie que le prochain joueur pourra jouer
						} /*
							 * else { // la case d'arrivee selectionnee n'est pas valide
							 * Kono.caseDep.setBorder(null); if (Kono.caseArr.getPion() != null) {// si elle
							 * contient un pion de la couleur du joueur, Kono.etat = 0; // elle devient la
							 * case d'arrivee } Kono.etat = 0; }
							 */
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
