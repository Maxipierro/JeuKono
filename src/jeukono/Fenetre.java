package jeukono;

import static java.lang.String.valueOf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Fenetre extends JFrame
{

  public static JButton boutonAnnuler = new JButton ("Annuler");
  public static JButton boutonReinit = new JButton ("Reinitialiser");

  public Fenetre (String name)
  {
    super (name);
    this.setSize (2500, 2500);
  }

  public void
  ajouterComposants (final Container pane)
  {

    FlowLayout structureBouton = new FlowLayout (FlowLayout.CENTER);
    FlowLayout structurePlateau = new FlowLayout (FlowLayout.CENTER);
    FlowLayout structureScore = new FlowLayout (FlowLayout.CENTER);

    JPanel fondBouton = new JPanel ();
    fondBouton.setLayout (structureBouton);
    fondBouton.add (boutonAnnuler);
    fondBouton.add (boutonReinit);

    // Définition de l'action du bouton2
    boutonAnnuler.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent event)
      {
        Kono.unPlateau.annulerCoup (Kono.caseDep, Kono.caseArr);
      }
    });

    boutonReinit.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent event)
      {
        Kono.unPlateau.reinitialiser ();
      }
    });
    pane.add (fondBouton, BorderLayout.NORTH);

    JPanel fondPlateau = new JPanel ();
    fondPlateau.setLayout (structurePlateau);

    Kono.unPlateau = new Plateau ();
    Kono.stockBlanc = new StockPion (CouleurPion.blanc);
    Kono.stockNoir = new StockPion (CouleurPion.noir);
    fondPlateau.add (Kono.stockBlanc);
    fondPlateau.add (Kono.unPlateau);
    fondPlateau.add (Kono.stockNoir);
    pane.add (fondPlateau, BorderLayout.CENTER);

    JPanel score = new JPanel ();
    score.setLayout (structureScore);
    Kono.scoreBlanc
        = new JLabel ("Score Joueur Blanc \n " + valueOf (Kono.nbPionBlanc));
    Kono.scoreNoir
        = new JLabel ("Score Joueur Noir \n" + valueOf (Kono.nbPionNoir));
    score.add (Kono.scoreBlanc);
    score.add (Kono.scoreNoir);
    pane.add (score, BorderLayout.SOUTH);
  }
}
