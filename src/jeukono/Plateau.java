package jeukono;

import static java.lang.String.valueOf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Plateau extends JPanel
{
  // instance variables - replace the example below with your own
  private Case[][] monPlateau;

  /**
   * Constructor for objects of class Echiquier
   */
  public Plateau ()
  {
    this.setLayout (new GridLayout (4, 8));
    monPlateau = new Case[4][8];
    for (int i = 0; i < 4; i++)
      {
        for (int j = 0; j < 2; j++)
          {
            monPlateau[i][j] = new Case (Color.white, i, j, 1);
            this.add (monPlateau[i][j]);
          }
        for (int j = 2; j < 6; j++)
          {
            monPlateau[i][j] = new Case (Color.gray, i, j, 0);
            this.add (monPlateau[i][j]);
          }
        for (int j = 6; j < 8; j++)
          {
            monPlateau[i][j] = new Case (Color.black, i, j, 1);
            this.add (monPlateau[i][j]);
          }
      }

    monPlateau[0][2].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[0][3].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[0][4].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[0][5].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[1][2].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[1][3].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[1][4].setPion ((new Pion (CouleurPion.blanc)));
    monPlateau[1][5].setPion ((new Pion (CouleurPion.blanc)));

    monPlateau[2][2].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[2][3].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[2][4].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[2][5].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[3][2].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[3][3].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[3][4].setPion ((new Pion (CouleurPion.noir)));
    monPlateau[3][5].setPion ((new Pion (CouleurPion.noir)));
  }

  public int
  coupValide (Case dep, Case arr)
  {
    int resultat = 0;

    int x1 = dep.getAbscisse (); // depart ligne
    int x2 = arr.getAbscisse (); // arrivé ligne
    int y1 = dep.getOrdonnee (); // depart colonne
    int y2 = arr.getOrdonnee (); // arrivé colonne
    int dy = Kono.abs (dep.getAbscisse () - arr.getAbscisse ());
    int dx = Kono.abs (dep.getOrdonnee () - arr.getOrdonnee ());

    System.out.println ("mov pion:" + x1 + "," + y1 + "--->" + x2 + "," + y2
                        + " delta_x : " + dx + " delata_y :" + dy);

    if (dx == 2 || dy == 2)
      {
        resultat = 1;
      }
    else
      {
        resultat = 0;
      }
    return resultat;
  }

  public boolean
  verifieDeplacementHorPrise (Case dep, Case arr)
  {
    return (true);
  }

  public boolean
  verifieDeplacementVertPrise (Case dep, Case arr)
  {
    return (true);
  }

  public boolean
  verifieDeplacementHor (Case dep, Case arr)
  {
    int nombre_colonne = Kono.abs (dep.getOrdonnee () - arr.getOrdonnee ());
    if (dep.getOrdonnee () < arr.getOrdonnee ())
      {
        for (int i = 1; i < nombre_colonne; i++)
          {
            if (monPlateau[dep.getAbscisse ()][dep.getOrdonnee () + i]
                    .isOccupe ())
              return false;
          }
        return true;
      }
    else
      {
        for (int j = 1; j < nombre_colonne; j++)
          {
            if (monPlateau[dep.getAbscisse ()][dep.getOrdonnee () - j]
                    .isOccupe ())
              return false;
          }
        return true;
      }
  }

  public boolean
  verifieDeplacementVert (Case dep, Case arr)
  {
    int dx = Kono.abs (dep.getAbscisse () - arr.getAbscisse ());
    if (dep.getAbscisse () < arr.getAbscisse ())
      {
        for (int i = 1; i < dx; i++)
          {
            if (monPlateau[dep.getAbscisse () + i][dep.getOrdonnee ()]
                    .isOccupe ())
              {
                return false;
              }
          }
        return true;
      }
    else
      {
        for (int j = 1; j < dx; j++)
          {
            if (monPlateau[dep.getAbscisse () - j][dep.getOrdonnee ()]
                    .isOccupe ())
              {
                return false;
              }
          }
        return true;
      }
  }

  public void
  jouerCoup (Case dep, Case arr)
  {
    // A completer
    Fenetre.boutonAnnuler.setEnabled (true);
  }

  public void
  jouerCoupPrise (Case dep, Case arr)
  {
    // A completer
    Fenetre.boutonAnnuler.setEnabled (true);
  }

  public boolean
  testDeplacement ()
  {
    // A completer
    return false;
  }

  public void
  annulerCoup (Case dep, Case arr)
  {

    Pion temp = null;
    int nb_colonne = Kono.abs (dep.getAbscisse () - arr.getAbscisse ());
    int nb_ligne = Kono.abs (dep.getOrdonnee () - arr.getOrdonnee ());

    if ((nb_ligne == 0 && nb_colonne == 1)
        || (nb_ligne == 1 && nb_colonne == 0)) // si dernier coup simple
      {
        System.out.println ("Coup simple");
        temp = dep.getPion ();
        dep.setPion (arr.getPion ());
        arr.setPion (temp);
      }

    if ((nb_ligne == 0 && nb_colonne == 2)
        || (nb_ligne == 2 && nb_colonne == 0)) // si dernier coup avec prise
      {
        System.out.println ("Coup prise");
        if (Kono.joueur == CouleurPion.blanc)
          {
            System.out.println ("On annule le coup du joueur noir");
            dep.setPion (arr.getPion ());
            arr.setPion (Kono.stockNoir.enleverPion ());
            Kono.nbPionBlanc++;
          }
        else
          {
            System.out.println ("On annule le coup du joueur blanc");

            dep.setPion (arr.getPion ());
            arr.setPion (Kono.stockBlanc.enleverPion ());
            Kono.nbPionNoir++;
          }
      }

    Kono.etat = 0;
    if (Kono.joueur == CouleurPion.blanc)
      Kono.joueur = CouleurPion.noir;
    else
      Kono.joueur = CouleurPion.blanc;

    Kono.scoreBlanc.setText ("Score Joueur Blanc \n "
                             + valueOf (Kono.nbPionBlanc));
    Kono.scoreNoir.setText ("Score Joueur Noir \n"
                            + valueOf (Kono.nbPionNoir));

    Kono.fenetrePrincipale.repaint ();
    Kono.fenetrePrincipale.validate ();

    Fenetre.boutonAnnuler.setEnabled (false);
  }

  public void
  reinitialiser ()
  {

    System.out.println ("dans reinitialiser");
    Kono.fenetrePrincipale.dispose ();
    Kono.fenetrePrincipale = new Fenetre ("Jeu du Kono");
    // On donne la possibilite de fermer la fenetre
    Kono.fenetrePrincipale.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    Kono.fenetrePrincipale.ajouterComposants (
        Kono.fenetrePrincipale.getContentPane ());
    Kono.joueur = CouleurPion.blanc;
    Kono.etat = 0;
    Kono.nbPionBlanc = 8;
    Kono.nbPionNoir = 8;
    Kono.nbCoupSansPrise = 0;

    Kono.scoreBlanc.setText ("Score Joueur Blanc \n "
                             + valueOf (Kono.nbPionBlanc));
    Kono.scoreNoir.setText ("Score Joueur Noir \n"
                            + valueOf (Kono.nbPionNoir));

    Kono.fenetrePrincipale.pack ();
    Kono.fenetrePrincipale.setVisible (true);
    Kono.fenetrePrincipale.repaint ();
    Kono.fenetrePrincipale.validate ();

    Fenetre.boutonAnnuler.setEnabled (false);
  }
}
