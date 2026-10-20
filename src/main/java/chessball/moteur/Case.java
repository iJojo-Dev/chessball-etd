package chessball.moteur;

import java.util.Optional;

/**
*Case d'un {@link Plateau}.
*elle possède une {@link Position} et peut contenir
*une {@link Piece}.
*/
public class Case {

    private Position p;
    private Piece piece;
    private boolean porteBal;

    /**
     * Initialise la case avec sa position sur le plateau
     * @param position
     */
    Case(Position position) {
        this.p = position;
    }

    /**
     * donnes la position de la case
     * @return un objet qui représente la position de la case
     */
    public Position position() {
        return p;
    }

    /**
     * Indique si la case est occupee ou pas
     * @return vrai si la case est occupee, faux sinon
     */
    public boolean occupee() {
        return  (piece != null);
    }

    /**
     * Si la case est occupee, donne la {@link Piece} qui l'occupe
     * @return un objet optionnel qui contient la piece, rien sinon
     */
    public Optional<Piece> piece() {
        if (!occupee()) return Optional.empty();
        else return Optional.of(piece);
    }

    /**
     * Place une piece dans la case si elle est vide
     * @param p la piece a placer dans la case
     * @throws IllegalStateException lance une erreur si la case est occupee
     */
    public void placer(Piece p) throws IllegalStateException{
        if (occupee()) 
            throw new IllegalStateException("Case occupee");
        this.piece = p;
    }

    /**
     * Supprime la {@link Piece} dans la case
     */
    public void vider() {
        this.piece = null;
    }

    /**
     * Permet de poser ou retirer le {@link Ballon} sur la case
     * @param b le nouvel etat de la case. true si le 
     * ballon est posee sur la case, false si le ballon est retiree de la case
     */
    void poserBallon(boolean b) {
        this.porteBal = b;
    }

    public boolean porteBallon() {
        return this.porteBal;
    }

}
