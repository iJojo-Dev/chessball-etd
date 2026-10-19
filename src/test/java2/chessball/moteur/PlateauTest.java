package chessball.moteur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlateauTest {
    Plateau plateau;
    Piece att;

    @BeforeEach 
    void initialiserPlateau() {
        plateau = new Plateau();
        att = new Piece(TypePiece.ATTAQUANT);
    }

    @Test 
    void testConstructeurPlateau() {
        assertTrue(plateau.caseA(Position.of("a1")) != null);
        assertTrue(plateau.caseA(Position.of("g6")) != null);
        
        
    }

    @Test 
    void testVerifierPresencePiece() {
        assertTrue(plateau.pieceEn(Position.of("d4")).isEmpty());
    }

    @Test 
    void testPlacerPiece() {
        plateau.placer(att, Position.of("c5"));
        assertEquals(att, plateau.pieceEn(Position.of("c5")).get());
        assertEquals(Position.of("c5"), plateau.positionDe(att).get());

        Piece p = new Piece(TypePiece.ATTAQUANT);
        assertThrows(IllegalStateException.class,  () -> plateau.placer(p, Position.of("c5")));
    }

    
}
