package chessball.moteur;
/*
Case d'un plateau
elle possède une position les informations
sur la pièce qui l'occupe
*/
import java.util.Optional;

public class Case {

    private Position p;
    private Piece piece;

    //Initialise la case avec sa position
    Case(Position position) {
        this.p = position;
    }

    //renvoie la position de la case sur le plateau
    public Position position() {
        return p;
    }

    //renvoie une pièce si la case est occupé, rien sinon
    //le resultat est un optional qui peut etre vide ou
    //contenir une piece
    public Optional<Piece> piece() {
        if (piece == null) return Optional.empty();
        else return Optional.of(piece);
    }

    //Verfie si la case est occupe par une piece
    public boolean occupee() {
        return  (piece != null);
    }
}
