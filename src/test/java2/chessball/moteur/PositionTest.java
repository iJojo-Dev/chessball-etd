package chessball.moteur;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void 
}
