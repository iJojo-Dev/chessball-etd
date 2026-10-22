package chessball.moteur;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MiseEnPlaceTest {
    private Plateau plateau;
    private Equipe bleus;
    private Equipe rouges;
    private Ballon ballon;
    private Piece bleuD1;
    private Piece bleuD2;
    private Piece bleuD3;
    private Piece bleuA1;
    private Piece bleuA2;

    private Piece rougeA1;
    private Piece rougeA2;
    private Piece rougeD1;
    private Piece rougeD2;
    private Piece rougeD3;
    


    @BeforeEach 
    void initialiser() {
        plateau = new Plateau();
        bleus = new Equipe(Couleur.BLEUS);
        rouges = new Equipe(Couleur.ROUGES);

        bleuD1 = new Piece(TypePiece.DEFENSEUR);
        bleus.ajouter(bleuD1);
        plateau.placer(bleuD1, Position.of("b6"));

        bleuD2 = new Piece(TypePiece.DEFENSEUR);
        bleus.ajouter(bleuD2);
        plateau.placer(bleuD2, Position.of("d6"));

        bleuD3 = new Piece(TypePiece.DEFENSEUR);
        bleus.ajouter(bleuD3);
        plateau.placer(bleuD3, Position.of("f6"));

        // 4. Attaquants bleus : c5, e5
        bleuA1 = new Piece(TypePiece.ATTAQUANT);
        bleus.ajouter(bleuA1);
        plateau.placer(bleuA1, Position.of("c5"));

        bleuA2 = new Piece(TypePiece.ATTAQUANT);
        bleus.ajouter(bleuA2);
        plateau.placer(bleuA2, Position.of("e5"));

        // 5. Attaquants rouges : c2, e2
        rougeA1 = new Piece(TypePiece.ATTAQUANT);
        rouges.ajouter(rougeA1);
        plateau.placer(rougeA1, Position.of("c2"));

        rougeA2 = new Piece(TypePiece.ATTAQUANT);
        rouges.ajouter(rougeA2);
        plateau.placer(rougeA2, Position.of("e2"));

        // 6. Défenseurs rouges : b1, d1, f1
        rougeD1 = new Piece(TypePiece.DEFENSEUR);
        rouges.ajouter(rougeD1);
        plateau.placer(rougeD1, Position.of("b1"));

        rougeD2 = new Piece(TypePiece.DEFENSEUR);
        rouges.ajouter(rougeD2);
        plateau.placer(rougeD2, Position.of("d1"));

        rougeD3 = new Piece(TypePiece.DEFENSEUR);
        rouges.ajouter(rougeD3);
        plateau.placer(rougeD3, Position.of("f1"));

        ballon = new Ballon(plateau, Position.of("d4"));
    }

    @Test
    public void dixPiecesAuTotal() {
        assertEquals(10, bleus.pieces().size() + rouges.pieces().size(),
                "Il doit y avoir 10 pièces au total");
    }

    @Test
    public void cinqPiecesParEquipe() {
        assertEquals(5, bleus.pieces().size(),
                "L'équipe bleue doit avoir 5 pièces");
        assertEquals(5, rouges.pieces().size(),
                "L'équipe rouge doit avoir 5 pièces");
    }

    @Test
    public void troisDefenseursBleus() {
        long nombre = bleus.pieces().stream()
                .filter(p -> p.type() == TypePiece.DEFENSEUR)
                .count();

        assertEquals(3, nombre,
                "Les Bleus doivent avoir 3 défenseurs");
    }

    @Test
    public void deuxAttaquantsBleus() {
        long nombre = bleus.pieces().stream()
                .filter(p -> p.type() == TypePiece.ATTAQUANT)
                .count();

        assertEquals(2, nombre,
                "Les Bleus doivent avoir 2 attaquants");
    }

    @Test
    public void defenseursBleusSurLeurLigneDeBut() {
        int rangee = Couleur.BLEUS.rangeeDeDepart();

        assertEquals(new Position(1, rangee),
                plateau.positionDe(bleuD1).orElseThrow(),
                "Le défenseur bleu doit être en b6");

        assertEquals(new Position(3, rangee),
                plateau.positionDe(bleuD2).orElseThrow(),
                "Le défenseur bleu doit être en d6");

        assertEquals(new Position(5, rangee),
                plateau.positionDe(bleuD3).orElseThrow(),
                "Le défenseur bleu doit être en f6");
    }

    @Test
    public void aucunePieceEnZoneDeTouche() {
        for (Piece piece : bleus.pieces()) {
            Position p = plateau.positionDe(piece).orElseThrow();
            assertFalse(p.estZoneDeTouche(),
                    "Une pièce bleue est en zone de touche : " + p);
        }

        for (Piece piece : rouges.pieces()) {
            Position p = plateau.positionDe(piece).orElseThrow();
            assertFalse(p.estZoneDeTouche(),
                    "Une pièce rouge est en zone de touche : " + p);
        }
    }

    @Test
    public void ballonEnD4() {
        assertEquals(Position.of("d4"), ballon.position(),
                "Le ballon doit être en d4");

        assertTrue(plateau.caseA(Position.of("d4")).porteBallon(),
                "La case d4 doit porter le ballon");
    }
}
