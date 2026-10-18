package chessball.moteur;

public final class Piece {
    private TypePiece type;
    private Equipe equipe;
    public Piece(TypePiece type){
        this.type = type;
    }
    public void setEquipe(Equipe e){
        this.equipe = e;
    }
    public TypePiece type(){
        return type;
    }
    public Equipe equipe(){
        return equipe;
    }
    public Couleur couleur(){
        if(equipe != null) return equipe.couleur();
        return null;
    }
}
