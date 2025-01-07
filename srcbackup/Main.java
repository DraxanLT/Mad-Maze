import java.util.Random;
import java.util.Scanner;



public class Main {

    /**
     * Class that sets variables for different colors in ANSI to use them to make a beautiful maze.
     * @author CF Cergy - L1 - Grp 9 - Draxan
     */
    public class SquareColor {
        // ---- ANSI Codes for Colors ----
        public static final String BACKGROUND_BLACK = "\u001B[40m"; // --- Background Color in Black ---
        public static final String BACKGROUND_YELLOW = "\u001B[43m"; // --- Background Color in Yellow ---
        public static final String BACKGROUND_GREEN = "\u001B[42m"; // --- Background Color in Green ---
        public static final String BACKGROUND_RED = "\u001B[41m"; // --- Background Color in Red ---
        public static final String BACKGROUND_CYAN = "\u001B[46m"; // --- Background Color in Cyan ---
        public static final String RESET = "\u001B[0m"; // --- Reset Text Status ---
        public static final String BLACK = "\u001B[30m"; // --- Text Color in Black ---
    }

//    public static int randomValue(int column) { // Old function to set random positions for
//        int valueR = (int) (Math.random() * column); // the entry E and the exit S
//        while (valueR == 0 || valueR == column - 1) {
//            valueR = (int) (Math.random() * column);
//        }
//        return valueR;
//    }

    /**
     * Void Function that generates the way values of an array of its same type are mixed in a random order.
     * @param array has an integer[] for an array to generate a random number to mix values in an array
     * @return Nothing
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static void Shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int storageId = array[index];
            array[index] = array[i];
            array[i] = storageId;
        }
    }

    /**
     * Void Function that generates a random path in the grid made by the function mazeGen to make it a maze.
     * @param array has a String[][] for an array, host of the maze
     * @param visited has a boolean[][] to assign if the position is already visited
     * @param x has an integer for the current position on the x axis
     * @param y has an integer for the current position on the y axis
     * @param line has an integer for the height of the maze to make sure the position is valid
     * @param column has an integer for the length of the maze to make sure the position is valid
     * @return Nothing
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static void mazeGen(String[][] array, boolean[][] visited, int x, int y, int line, int column) {
        visited[x][y] = true;
        array[x][y] = " ";

        int[] mixDir = {0, 1, 2, 3};
        Shuffle(mixDir);

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        for (int direction : mixDir) {
            int pDirX = (dx[direction] * 2) + x;
            int pDirY = (dy[direction] * 2) + y;
            if (pDirX > 0 && pDirY > 0 && pDirX < line - 1 && pDirY < column - 1 && !visited[pDirX][pDirY]) {
                visited[pDirX][pDirY] = true;
                array[dx[direction] + x][dy[direction] + y] = " ";
                mazeGen(array, visited, pDirX, pDirY, line, column);
            }
        }
    }

    /**
     * String[][] Function that prints a grid for it to be dug by the function mazeGen.
     * @param line has an integer for the height of the grid and the maze later
     * @param column has an integer for the length of the grid and the maze later
     * @param posE has an integer for the position of the Entry E
     * @param posS has an integer for the position of the Exit S
     * @return tab[line][column]
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static String[][] mazeGridDisplay(int line, int column, int posE, int posS) {
        String[][] tab = new String[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                tab[i][j] = "#";
            }
        }
        for (int i = 0; i < line; i++) {
            tab[i][0] = "#";
            tab[i][column - 1] = "#";
        }
        for (int j = 0; j < column; j++) {
            tab[0][j] = "#";
            tab[line - 1][j] = "#";
        }

        tab[0][posE] = "E";
        tab[line - 1][posS] = "S";

        return tab;
    }

//    public static void showMaze(String[][] maze) {
//        for (int i = 0; i < maze.length; i++) {
//            for (int j = 0; j < maze[i].length; j++) {
//                System.out.print(maze[i][j]);
//            }
//            System.out.println();
//        }
//    }

    /**
     * Void Function that prints the entire maze with beautiful colors.
     * @param maze has a String[][]
     * @return Nothing
     * @author CF Cergy - L1 - Grp 9 - Draxan
     */
    public static void showMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                // --- Set Wall Color to Black ---
                if (maze[i][j].equals("#")) {
                    System.out.print(SquareColor.BACKGROUND_BLACK+"   "+SquareColor.RESET);
                }
                // --- Set E Color to Green ---
                else if (maze[i][j].equals("E")) {
                    System.out.print(SquareColor.BACKGROUND_GREEN+SquareColor.BLACK+" E "+SquareColor.RESET);
                }
                // --- Set S Color to Red ---
                else if (maze[i][j].equals("S")) {
                    System.out.print(SquareColor.BACKGROUND_RED+SquareColor.BLACK+" S "+SquareColor.RESET);
                }
                // --- Set Solver Path Color to Yellow ---
                else if (maze[i][j].equals("V")) {
                    System.out.print(SquareColor.BACKGROUND_YELLOW+"   "+SquareColor.RESET);
                }
                // --- Set Path Color to Cyan ---
                else {
                    System.out.print(SquareColor.BACKGROUND_CYAN+"   "+SquareColor.RESET);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Bienvenue sur Mad Maze !!!");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Mad Maze est un générateur de labyrinthe avec plusieurs options de");
        System.out.println("difficulté dont une option pour créer, avec vos préférences, votre");
        System.out.println("labyrinthe.");
        System.out.println("-----------------------------------------------------------------------");
        Scanner saisie = new Scanner(System.in);

        int line = 0;
        int column = 0;
        int posE = 0;
        int posS = 0;

        int diff = 0;
        while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6) {
            System.out.println("Choisis la difficulté :");
            System.out.println("[ 1 = Facile | 2 = Moyenne | 3 = Difficile | 4 = Extrême | 5 = Impossible | 6 = Personnalisée ]");

            if (saisie.hasNextInt()) {
                diff = saisie.nextInt();
            } else {
                System.out.println("⚠ Saisie incorrecte (merci de respecter les consignes indiquées) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                saisie.next();
            }
        }
        if (diff == 1) { // Facile
            line = 7;
            column = 7;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Facile");
        }
        if (diff == 2) { // Moyenne
            line = 13;
            column = 13;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Moyenne");
        }
        if (diff == 3) { // Difficile
            line = 19;
            column = 19;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Difficile");
        }
        if (diff == 4) { // Extrême
            line = 25;
            column = 25;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Extrême");
        }
        if (diff == 5) { // Impossible
            line = 31;
            column = 31;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Impossible");
            System.out.println("-----------------------------------------------------------------------");
        }
        if (diff == 6) { // Personnalisée
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Personnalisée");
            System.out.println("-----------------------------------------------------------------------");

            // Saisie de la largeur (doit être un entier impair)
            do {
                System.out.println("Saisissez la largeur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                column = saisie.nextInt();
                if (column % 2 == 0) {
                    System.out.println("⚠ La largeur doit être un entier impair ⚠");
                }
            } while (column % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la hauteur (doit être un entier impair)
            do {
                System.out.println("Saisissez la hauteur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                line = saisie.nextInt();
                if (line % 2 == 0) {
                    System.out.println("⚠ La hauteur doit être un entier impair ⚠");
                }
            } while (line % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la position de l'entrée (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de l'entrée (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posE = saisie.nextInt();
                if (posE % 2 == 0 || posE < 1 || posE > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posE % 2 == 0 || posE < 1 || posE > column - 2);

            // Saisie de la position de la sortie (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de la sortie (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posS = saisie.nextInt();
                if (posS % 2 == 0 || posS < 1 || posS > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posS % 2 == 0 || posS < 1 || posS > column - 2);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Génération du labyrinthe");
        System.out.println("-----------------------------------------------------------------------");

        String[][] lab = new String[line][column];  // Initialisation du tableau lab
        boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

        lab = mazeGridDisplay(line, column, posE, posS);

        Main m = new Main();
        m.mazeGen(lab, visited, 1, 1, line, column);

        System.out.println(mazeGridDisplay(line, column, posE, posS));  // Affichage du labyrinthe

        showMaze(lab);
        AutoSolver.chemin(lab);
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* Fonctionnel :

public static void main(String[] args) {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Bienvenue sur Mad Maze !!!");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Mad Maze est un générateur de labyrinthe avec plusieurs options de");
        System.out.println("difficulté dont une option pour créer, avec vos préférences, votre");
        System.out.println("labyrinthe.");
        System.out.println("-----------------------------------------------------------------------");
        Scanner saisie = new Scanner(System.in);

        int line = 0;
        int column = 0;
        int posE = 0;
        int posS = 0;

        int diff = 0;
        while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6) {
            System.out.println("Choisis la difficulté :");
            System.out.println("[ 1 = Facile | 2 = Moyenne | 3 = Difficile | 4 = Extrême | 5 = Impossible | 6 = Personnalisée ]");

            if (saisie.hasNextInt()) {
                diff = saisie.nextInt();
            } else {
                System.out.println("⚠ Saisie incorrecte (merci de respecter les consignes indiquées) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                saisie.next();
            }
        }
        if (diff == 1) { // Facile
            line = 7;
            column = 7;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Facile");
        }
        if (diff == 2) { // Moyenne
            line = 13;
            column = 13;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Moyenne");
        }
        if (diff == 3) { // Difficile
            line = 19;
            column = 19;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Difficile");
        }
        if (diff == 4) { // Extrême
            line = 25;
            column = 25;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Extrême");
        }
        if (diff == 5) { // Impossible
            line = 31;
            column = 31;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Impossible");
            System.out.println("-----------------------------------------------------------------------");
        }
        if (diff == 6) { // Personnalisée
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Personnalisée");
            System.out.println("-----------------------------------------------------------------------");

            // Saisie de la largeur (doit être un entier impair)
            do {
                System.out.println("Saisissez la largeur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                column = saisie.nextInt();
                if (column % 2 == 0) {
                    System.out.println("⚠ La largeur doit être un entier impair ⚠");
                }
            } while (column % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la hauteur (doit être un entier impair)
            do {
                System.out.println("Saisissez la hauteur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                line = saisie.nextInt();
                if (line % 2 == 0) {
                    System.out.println("⚠ La hauteur doit être un entier impair ⚠");
                }
            } while (line % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la position de l'entrée (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de l'entrée (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posE = saisie.nextInt();
                if (posE % 2 == 0 || posE < 1 || posE > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posE % 2 == 0 || posE < 1 || posE > column - 2);

            // Saisie de la position de la sortie (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de la sortie (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posS = saisie.nextInt();
                if (posS % 2 == 0 || posS < 1 || posS > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posS % 2 == 0 || posS < 1 || posS > column - 2);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Génération du labyrinthe");
        System.out.println("-----------------------------------------------------------------------");

        String[][] lab = new String[line][column];  // Initialisation du tableau lab
        boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

        lab = mazeDisplay(line, column, posE, posS);

        Main m = new Main();
        m.mazeGen(lab, visited, 1, 1, line, column);

        System.out.println(mazeDisplay(line, column, posE, posS));  // Affichage du labyrinthe

        showMaze(lab);

    }
}
 */

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
A NOUS :
if (diff == 6) { // Personnalisée
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Personnalisée");
            System.out.println("-----------------------------------------------------------------------");


            // saisie de la largeur
            System.out.println("Saisissez la largeur (entier impair) :");
            column = saisie.nextInt();
            while (line % 2 == 0) {
                if (!saisie.hasNextInt()) {
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("Saisissez la largeur (entier impair) :");
                    column = saisie.nextInt();
                }
                if (column % 2 == 0) {
                    column = saisie.nextInt();
                } else {
                    System.out.println("⚠ Saisie incorrecte (merci de respecter les consignes indiquées) ⚠");
                    System.out.println("-----------------------------------------------------------------------");
                    saisie.next();
                }
            }

            // saisi de la hauteur
            System.out.println("Saisissez la hauteur (entier impair) :");
            line = saisie.nextInt();
            while (!saisie.hasNextInt()) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Saisissez la hauteur (entier impair) :");

                line = saisie.nextInt();
            }

            // saisi de la position de l'entrée
            System.out.println("Saisissez la position de l'entrée (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
            posE = saisie.nextInt();
            while (!saisie.hasNextInt() || posE % 2 == 0) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("⚠ Saisie incorrecte (entier impair requis) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Saisissez la position de l'entrée (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
                posE = saisie.nextInt();
            }

            // saisi de la position de la sortie
            System.out.println("Saisissez la position de la sortie (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
            posS = saisie.nextInt();
            while (!saisie.hasNextInt() || posS % 2 == 0) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("⚠ Saisie incorrecte (entier impair requis) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Saisissez la position de la sortie (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
                posS = saisie.nextInt();
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Génération du labyrinthe");
            System.out.println("-----------------------------------------------------------------------");

            String[][] lab = new String[line][column];  // Initialisation du tableau lab
            boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

            lab = mazeDisplay(line, column, posE, posS);

            Main m = new Main();
            m.mazeGen(lab, visited, 1, 1, line, column);

            System.out.println(mazeDisplay(line, column, posE, posS));  // Affichage du labyrinthe

            showMaze(lab);
        }
    }
}
 */