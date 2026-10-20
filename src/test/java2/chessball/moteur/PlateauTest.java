package chessball.moteur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void caseVideEstLibre(){
        Position p = new Position(0,0);
        assertTrue(plateau.estLibre(p));
    }

    @Test
    public void caseDevientLibreApresRetraitPiece() {
        Position p = new Position(1, 1);
        plateau.placer(att, p);
        assertFalse(plateau.estLibre(p));
        plateau.retirer(att);
        assertTrue(plateau.estLibre(p));
}

    @Test
    void caseAvecPieceNonLibre(){
        Position p = new Position(0, 0);
        plateau.placer(att, p);
        assertFalse(plateau.estLibre(p));
    }
    @Test
    void caseAvecBallonNonLibre(){
        Position p = new Position(0,0);
        Ballon ballon = new Ballon(plateau, p);
        assertFalse(plateau.caseA(p).occupee());
        assertTrue(plateau.caseA(p).porteBallon());
        assertFalse(plateau.estLibre(p));
    }
}
