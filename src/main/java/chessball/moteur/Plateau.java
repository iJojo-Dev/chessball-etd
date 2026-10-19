package chessball.moteur;

import java.util.Map;
import java.util.Optional;

/*
Représente le plateau de jeu de chess ball
Elle est composé de 42 cases enregistré dans un map
Chaque case est associe a sa position 
Le plateau enregistre aussi les positions des pieces
*/
public class Plateau {

    private Map<Position, Case> cases;
    private Map<Piece, Position> positions_pieces;

    /**
     * Initialise le plateau de jeu
     */
    public Plateau() {
        for (int i = 0; i < Position.COLONNES; i++) {
            for (int k = 0; k < Position.RANGEES; k++) {
                Position p = new Position(i, k);
                cases.put(p, new Case(p));
            }
        }
    }

    /**
     * Donne la case situee a une position donnee
     * @param p la position de la case
     * @return l'objet case assoocié à la position 
     */
    public Case caseA(Position p) {
        return cases.get(p);
    }

    /**
     * Si la position donnee contient une piece, renvoie une piece
     * @param p la position supposee de la piece
     * @return Une piece optionnel, rien sinon
     */
    public Optional<Piece> pieceEn(Position p) {
        Case c = cases.get(p);
        return c.piece();
    }

    /**
     * Si la piece est sur plateau, donnes sa position
     * @param p la piece que l'on cherche sur le plateau
     * @return la position de la piece
     */
    public Optional<Position> positionDe(Piece p) {
        return; //A FAIRE
    }
}
