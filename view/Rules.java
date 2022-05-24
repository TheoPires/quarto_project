package view;

public enum Rules {

    BUT("But du jeu :\n" +
            "Il faut être le premier joueur à former un Quarto, c’est-à-dire une ligne de 4 pièces qui partagent au moins un point \n" +
            "commun : la couleur, la taille, la forme, le remplissage. \n\n"),
    GENERAL_RULES(
            "Comment jouer ?\n" +
            "- On commence le jeu avec le plateau vide. Les pièces du jeu n’appartiennent à aucun joueur en particulier, elles sont la\n" +
            "propriété de chacun;\n" +
            "- A ton tour, prends une pièce et place-la sur une case vide du plateau, puis c’est au tour de ton adversaire de jouer;\n" +
            "- A la fin de ton tour, tu choisis la pièce que devra poser ton adversaire;\n" +
            "- On pose des pièces chacun son tour;\n" +
            "- Si personne n’a réalisé de Quarto et qu’il n’y a plus de pièce à poser, fin de la partie, personne n'a gagné;\n\n"),
    LEVEL1("Niveau 1 : " +
            "\tLe Quarto peut être aligné horizontalement, verticalement ou en diagonale.\n\n"),
    LEVEL2("Niveau 2 :" +
            "\tCelles du niveau 1, ou quatre pièce avec un caractère en commun placées sur un petit carré.\n\n"),
    LEVEL3("Niveau 3 :" +
            "\tCelles du niveau 2, ou quatre pièce avec un caractère en commun placées sur un grand carré (Quatre coin du grand carrée de longueur 3 cases).\n\n"),
    LEVEL4("Niveau 4 :" +
            "\tCelles du niveau 3, ou quatre pièce avec un caractère en commun placées sur un carrée tournant.\n\n");
    private String str;

    Rules(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
