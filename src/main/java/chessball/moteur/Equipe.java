package chessball.moteur;
import java.util.List;
import java.util.ArrayList;

public final class Equipe {
    public static final int EFFECTIF = 5;
    private Couleur couleur;
    private List<Piece> equipe;

    public Equipe(Couleur couleur) {
        this.couleur = couleur;
        equipe = new ArrayList<>();
    }
    public Couleur couleur() {
        return this.couleur;
    }
    public List<Piece> pieces() {
        return new ArrayList<>(equipe);
    }
    public void ajouter(Piece piece) {
        if (piece.equipe() == this || piece == null) return;
        
        if (this.equipe.size() >= EFFECTIF) 
            throw new IllegalStateException("equipe pleine");

        if (piece.equipe() != null) 
            throw new IllegalArgumentException("La piece est deja dans une equipe");

        equipe.add(piece);
        piece.setEquipe(this);
        }
}
