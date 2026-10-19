package chessball.moteur;
import java.util.List;
import java.util.ArrayList;


/**
 * 
 * Equipe
 * 
 * Modelise les deux ensembles de pieces en jeu.
 * Chaque joueur controle une equipe, soit l'equipe
 * bleu, soit l'equipe rouge. Une equipe est constitue de cinq {@link Piece}
 * Chaque equipe a deux pieces de {@link TypePiece} attaquant
 * et trois defenseurs.
 */
public final class Equipe {
    public static final int EFFECTIF = 5;
    private Couleur couleur;
    private List<Piece> equipe;

    /**
     * Initialise une equipe avec sa {@link Couleur}
     * @param couleur la couleur de l'equipe. soit rouge, soit bleu
     */
    public Equipe(Couleur couleur) {
        this.couleur = couleur;
        equipe = new ArrayList<>();
    }

    /**
     * Donnes la {@link Couleur} de l'equipe
     * @return la couleur de l'equipe
     */
    public Couleur couleur() {
        return this.couleur;
    }

    /**
     * Donnes les {@link Piece} dans l'equipe
     * @return la liste de piece de l'equipe
     */
    public List<Piece> pieces() {
        return new ArrayList<>(equipe);
    }

    /**
     * Ajoute une piece dans l'equipe si elle n'en a pas
     * @param piece la piece a ajouter.
     * @throws IllegalStateException lance une exception si la piece est deja dans une 
     * equipe ou l'equipe a deja cinq pieces.
     */
    public void ajouter(Piece piece) 
    throws IllegalStateException {
        if (piece.equipe() == this || piece == null) return;

        if (this.equipe.size() >= EFFECTIF) 
            throw new IllegalStateException("equipe pleine");

        if (piece.equipe() != null) 
            throw new IllegalStateException("La piece est deja dans une equipe");

        equipe.add(piece);
        piece.setEquipe(this);
        }
}
