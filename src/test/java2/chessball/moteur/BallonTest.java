package chessball.moteur;

import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BallonTest {
    Ballon ballon;
    Plateau plateau;
    Position depart;
    Position posPiece;
    Piece att;

    @BeforeEach
    void initialisation() {
        depart = Position.of("d4");
        plateau = new Plateau();
        ballon = new Ballon(plateau, depart);
        att = new Piece(TypePiece.ATTAQUANT);
        posPiece = Position.of("b4");
        plateau.placer(att, posPiece);
    }

    @Test
    void testLeBallonEstPoseeSurLaCase() {
        assertEquals(depart, ballon.position());
        assertTrue(plateau.caseA(depart).porteBallon());
    }

    @Test 
    void testDeplacerBallonVers() {
        Position nouvPosittion = Position.of("e3");
        ballon.deplacerVers(nouvPosittion);
        assertTrue(plateau.caseA(nouvPosittion).porteBallon());
        assertFalse(plateau.caseA(depart).porteBallon());
        assertThrows(IllegalStateException.class, () -> ballon.deplacerVers(posPiece));
        assertThrows(IllegalArgumentException.class, () -> ballon.deplacerVers(Position.of("a3")));
    }
}
