package chessball.moteur;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
*Représente le plateau de jeu de chess ball
*Elle est composé de 42 {@link Case} enregistré dans un map
*Chaque case est associe a sa {@link Position}
*Le plateau enregistre aussi les positions des {@link Piece} en jeu
*/
public class Plateau {

    private Map<Position, Case> cases;
    private Map<Piece, Position> positions_pieces;

    /**
     * Initialise le plateau de jeu
     */
    public Plateau() {
        cases = new HashMap<>();
        positions_pieces = new HashMap<>();
        for (int i = 0; i < Position.COLONNES; i++) {
            for (int k = 0; k < Position.RANGEES; k++) {
                Position p = new Position(i, k);
                cases.put(p, new Case(p));
            }
        }
    }

    /**
     * Donne la {@link Case} situee a une position donnee
     * @param p la position de la case
     * @return l'objet case assoocié à la position 
     */
    public Case caseA(Position p) {
        return cases.get(p);
    }

    /**
     * Si la {@link Position} donnee contient une piece, renvoie une piece
     * @param p la position supposee de la piece
     * @return Une piece optionnel, rien sinon
     */
    public Optional<Piece> pieceEn(Position p) {
        Case c = cases.get(p);
        return c.piece();
    }

    /**
     * Si la piece est sur un plateau, donnes sa position
     * @param p la piece que l'on cherche sur le plateau
     * @return la position de la piece
     */
    public Optional<Position> positionDe(Piece p) {
        Position val = positions_pieces.get(p);
        if (val == null) {
            return Optional.empty();
        } else {
            return Optional.of(val);
        }
    }

    /**
     * Place une piece sur une case du plateau
     * @param piece la piece a placer
     * @param cible la position de la case cible sur plateau
     * @throws IllegalStateException lance une exception si la case est occupee
     */
    public void placer(Piece piece, Position cible) throws IllegalStateException {
        if (piece == null) 
            return;
        if (cible == null) 
            return;

        Optional<Position> positiOptional = positionDe(piece);

        if (positiOptional.isPresent()) {
            cases.get(positiOptional.get()).vider();
            positions_pieces.remove(piece);
        }
        if(!estLibre(cible)){
            throw new IllegalStateException("La case cible n'est pas libre");
        }

        positions_pieces.put(piece, cible);
        cases.get(cible).placer(piece);
    }

    /**
     * Verifie s'il n'y a pas de {@link Piece} ou le {@link Ballon}
     * posee sur la case
     * @param p la position de la case sur le plateau
     * @return vrai s'il n'y a ni piece ni ballon, faux sinon
     */
    public boolean estLibre(Position p) {
        return !caseA(p).occupee() && !caseA(p).porteBallon();
    }

    /**
     * Retires une {@link Piece} du plateau
     * @param piece la piece a retirer
     */
    public void retirer(Piece piece){
        if(piece==null){
            return;
        }

        Optional<Position> position = positionDe(piece);
        if (position.isPresent()) {
            cases.get(position.get()).vider();
            positions_pieces.remove(piece);
        }
    }


}
