package chessball.moteur;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Optional;

import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test 
    void testPosition() {
        assertThrows(IllegalArgumentException.class, () -> new Position(38, 0) );
    }

    @Test
    void testOf() {
        assertThrows(IllegalArgumentException.class, () -> Position.of("KVM"));
    }

    @Test
    void testVoisine() {
        Direction d = Direction.NORD;
        Position p = Position.of("a6");
        Optional<Position> op = p.voisine(d);
        assertTrue(op.isEmpty());
    }

    @Test
    void testEstZoneDeTouche() {
        Position p = Position.of("a5");
        assertTrue(p.estZoneDeTouche());
        Position p2 = Position.of("d4");
        assertFalse(p2.estZoneDeTouche());
    }

    @Test 
    void testEstLigneDeBut() {
        Position p = Position.of("b1");
        assertTrue(p.estLigneDeBut());
        Position p2 = Position.of("d4");
        assertFalse(p2.estLigneDeBut());
    }

    @Test
    void testEquals() {
        Position p = Position.of("d4");
        Position p2 = p;
        Position p3 = Position.of("d5");
        Position p4 = Position.of("d4");
        assertTrue(p.equals(p2));
        assertFalse(p.equals(p3));
        assertTrue(p.equals(p4));
    }
}
