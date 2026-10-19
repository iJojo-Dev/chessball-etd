package chessball.moteur;

/**
 * 
 * Ballon
 * 
 * Represente le ballon de chessball.
 * Elle occupe une seul et unique {@link Case}
 * Elle ne peut pas etre dans la meme case qu'une
 * {@link Piece}. Elle ne peut pas occuper une zone
 * de touche.
 */
public final class Ballon {

    Case caseOccupee;
    /**
     * Place le ballon sur le {@link Plateau} de jeu
     * @param plateau le plateau ou le ballon est place
     * @param depart la position de depart du ballon
     * @throws IllegalStateException lance une exception si la case de depart est non libre
     * @throws IllegalArgumentException lance une exception si la case de depart est une zone de touche
     */
    public Ballon(Plateau plateau, Position depart) 
    throws IllegalStateException, IllegalArgumentException {
        caseOccupee = plateau.caseA(depart);
        if (caseOccupee.occupee()) 
            throw new IllegalStateException("Le ballon ne peut pas etre pose sur une case occupee.");
        if (depart.estZoneDeTouche())
            throw new IllegalArgumentException("Le ballon ne peut pas etre pose sur une ligne de but.");
        caseOccupee.poserBallon(true);
    }

    /**
     * Donnes la {@link Position} du ballon
     * @return la position du ballon
     */
    public Position position() {
        return caseOccupee.position();
    }

    /**
     * Deplace le ballon vers une nouvelle {@link Position}
     * si elle n'est pas occupee et n'est pas une zone de touche
     * @param plateau le plateau de jeu
     * @param p la nouvellle position du ballon
     * @throws IllegalStateException lance une exception si la nouvelle position est occupee par 
     * une piece
     * @throws IllegalArgumentException lance une exception si la nouvelle position est une zone 
     * de touche
     */
    public void deplacerVers(Plateau plateau, Position p)
    throws IllegalStateException, IllegalArgumentException {
        if (p.estZoneDeTouche())
            throw new IllegalArgumentException("Le ballon ne peut etre deplace dans une zone de touche");
        Case cible = plateau.caseA(p);
        if (cible.occupee())
            throw new IllegalStateException("Le ballon ne peut etre deplace vers une case occupee");

        caseOccupee.poserBallon(false);
        caseOccupee = cible;
        caseOccupee.poserBallon(true);
    }
}
