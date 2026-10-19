package chessball.moteur;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EquipeTest {
    Equipe bleus;
    Equipe rouges;
    Piece att;
    

    @BeforeEach 
    void creerEquipesEtPieces() {
        bleus = new Equipe(Couleur.BLEUS);
        rouges = new Equipe(Couleur.ROUGES);
        att = new Piece(TypePiece.ATTAQUANT);
    }

    @Test
    void testAjouter() {
        bleus.ajouter(att);
        assertEquals(att.equipe(), bleus);
        assertTrue(bleus.pieces().contains(att));
        assertEquals(1, bleus.pieces().size());
        bleus.ajouter(new Piece(TypePiece.ATTAQUANT));
        bleus.ajouter(new Piece(TypePiece.DEFENSEUR));
        bleus.ajouter(new Piece(TypePiece.DEFENSEUR));
        bleus.ajouter(new Piece(TypePiece.DEFENSEUR));
        assertThrows(IllegalStateException.class ,() -> bleus.ajouter(new Piece(TypePiece.DEFENSEUR)));
        assertThrows(IllegalStateException.class,() -> rouges.ajouter(att));
    }

}
