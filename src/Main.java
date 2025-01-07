import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int counter = 0;

    /**
     * Classe qui définit des variables pour différentes couleurs en ANSI pour les utiliser pour créer un beau labyrinthe.
     * @author CF Cergy - L1 - Grp 9 - Draxan
     */
    public class SquareColor {
        // ---- ANSI Codes for Colors ----
        public static final String BACKGROUND_BLACK = "\u001B[40m"; // --- Background Color in Black ---
        public static final String BACKGROUND_YELLOW = "\u001B[43m"; // --- Background Color in Yellow ---
        public static final String BACKGROUND_GREEN = "\u001B[42m"; // --- Background Color in Green ---
        public static final String BACKGROUND_RED = "\u001B[41m"; // --- Background Color in Red ---
        public static final String BACKGROUND_CYAN = "\u001B[46m"; // --- Background Color in Cyan ---
        public static final String BACKGROUND_PURPLE = "\033[95m"; // --- Background Color in Purple ---
        public static final String RESET = "\u001B[0m"; // --- Reset Text Status ---
        public static final String BLACK = "\u001B[30m"; // --- Text Color in Black ---
    }

    public static final Scanner entry = new Scanner(System.in);

//    public static int randomValue(int column) { // Old function to set random positions for
//        int valueR = (int) (Math.random() * column); // the entry E and the exit S
//        while (valueR == 0 || valueR == column - 1) {
//            valueR = (int) (Math.random() * column);
//        }
//        return valueR;
//    }

    /**
     * Fonction Void qui génère la manière dont les valeurs d'un tableau du même type sont mélangées dans un ordre aléatoire.
     * @param array a un entier [] pour un tableau pour générer un nombre aléatoire pour mélanger des valeurs dans un tableau
     * @return Rien
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static void Shuffle(int[] array) { // Fonction qui permet de mélanger les éléments d'un tableau
        Random rand = new Random(); // Création d'un objet de type rand
        for (int i = array.length - 1; i > 0; i--) { // Initialisation de la boucle qui va parcourir le tableau et mélanger les éléments
            int index = rand.nextInt(i + 1); // Création d'une varibale qui génère un chiffre entre 0 et i
            int storageId = array[index]; // Création d'une valeur temporel qui prend la valeur d'un élément du tabelau
            array[index] = array[i]; // Inversion des deux éléments du tableau
            array[i] = storageId;
        }
    }

    /**
     * Fonction void qui génère un chemin aléatoire dans la grille faite par la fonction mazeGen pour le transformer en labyrinthe
     * @param array en tant que String[][] servant de tableau, hôte du labyrinthe
     * @param visited en tant que boolean[][] pour assigner si une position est déjà visitée
     * @param x en tant qu'entier servant de position actuelle sur l'axe-x
     * @param y en tant qu'entier servant de position actuelle sur l'axe-y
     * @param line en tant qu'entier pour la hauteur de la grille et du labyrinthe plus tard
     * @param column en tant qu'entier pour la largeur de la grille et du labyrinthe plus tard
     * @param Complexite en tant que boolean pour mesurer le temps de génération du labyrinthe
     * @return Rien
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static void mazeGen(String[][] array, boolean[][] visited, int x, int y, int line, int column, boolean Complexite) {
        visited[x][y] = true; // Marque la position (x,y) comme visitée
        array[x][y] = " "; // Place un espace dans la grille a la position (x,y)
        long startTimeEnd = 0; // Initialise la variable de temps de fin à 0 (utilisée si Complexite est false)
        long startTime = 0;// Initialise la variable de temps de début à 0 (utilisée si Complexite est false)
        if (!Complexite) { // Si la complexité n'est pas activée (Complexite est false), démarre la mesure du temps
            startTime = System.nanoTime();// Enregistre le temps actuel en nanosecondes

        }

        int[] mixDir = {0, 1, 2, 3};// Tableau qui représente les 4 directions possibles (haut, bas, gauche, droite) mélangées
        Shuffle(mixDir);// Mélange les directions pour ajouter de l'aléatoire dans la génération du labyrinthe

        int[] dx = {0, -1, 0, 1};// Changements de position en x
        int[] dy = {1, 0, -1, 0};// Changements de position en y

        for (int direction : mixDir) {
            // Calcule la position après avoir fait un saut de deux cases dans la direction donnée
            int pDirX = (dx[direction] * 2) + x;
            int pDirY = (dy[direction] * 2) + y;
            if (pDirX > 0 && pDirY > 0 && pDirX < line - 1 && pDirY < column - 1 && !visited[pDirX][pDirY]) {
                visited[pDirX][pDirY] = true;// Marque la nouvelle position comme visitée
                array[dx[direction] + x][dy[direction] + y] = " "; // Crée un chemin entre la position actuelle et la nouvelle position en mettant un espace
                counter += 1;// Incrémente le compteur (indique le nombre d'appels récursifs)
                mazeGen(array, visited, pDirX, pDirY, line, column, true); // Appelle récursivement la fonction pour continuer la génération du labyrinthe
            }
        }
        if (!Complexite) {
            startTimeEnd = System.nanoTime();// Enregistre le temps de fin
            System.out.print("le labyrinthe s'est générer en " + ((startTimeEnd - startTime) / 10000000.0) + " milliseconde "); // Affiche le temps écoulé pour générer le labyrinthe en millisecondes
            System.out.println("et s'est appeler " + counter + " fois");// Affiche combien de fois la fonction mazeGen a été appelée
        }
    }

    /**
     * Fonction String[][] qui imprime une grille pour qu'elle soit ensuite creusée par la fonction mazeGen.
     * @param line en tant qu'entier pour la hauteur de la grille et du labyrinthe plus tard
     * @param column en tant qu'entier pour la largeur de la grille et du labyrinthe plus tard
     * @param posE en tant qu'entier pour la position de l'entrée E
     * @param posS en tant qu'entier pour la position de la sortie S
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

    /**
     * Fonction void qui imprime l'entièreté du labyrinthe avec de belles couleurs.
     * @param maze en tant que String[][]
     * @return Rien
     * @author CF Cergy - L1 - Grp 9 - Draxan
     */
    public static void showMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                // --- Définie la couleur des murs en noir ---
                if (maze[i][j].equals("#")) {
                    System.out.print(SquareColor.BACKGROUND_BLACK+"   "+SquareColor.RESET);
                }
                // --- Définie la couleur de l'entrée E en Vert et la lettre E en noir ---
                else if (maze[i][j].equals("E")) {
                    System.out.print(SquareColor.BACKGROUND_GREEN+SquareColor.BLACK+" E "+SquareColor.RESET);
                }
                // --- Définie la couleur de la sortie S en rouge et la lettre S en noir ---
                else if (maze[i][j].equals("S")) {
                    System.out.print(SquareColor.BACKGROUND_RED+SquareColor.BLACK+" S "+SquareColor.RESET);
                }
                // --- Définie la couleur du chemin du Solver en Jaune ---
                else if (maze[i][j].equals("V")) {
                    System.out.print(SquareColor.BACKGROUND_YELLOW+"   "+SquareColor.RESET);
                }
                // --- Définie la couleur du joueur en Jaune ---
                else if (maze[i][j].equals("\uD83D\uDE21")) {
                    System.out.print(SquareColor.BACKGROUND_YELLOW+"   "+SquareColor.RESET);
                }
                // --- Définie la couleur du chemin en Cyan ---
                else {
                    System.out.print(SquareColor.BACKGROUND_CYAN+"   "+SquareColor.RESET);
                }
            }
            System.out.println();
        }
    }

    /**
     * Fonction void qui permet d'afficher et de demander à l'user de choisir un mode. Cette fonction affiche aussi la fonction mazeDifficulty
     * @param choiceF en tant que String servant à choisir le mode au démarrage (IMPORT ou CREATE)
     * @param save en tant que String servant à entrer le nom de la sauvegarde .labgen d'un labyrinthe
     * @param play en tant que String servant à choisir si l'on souhaite jouer de nous même ou laisser le solver le faire
     * @param diff en tant qu'entier servant à définir la difficulté du labyrinthe
     * @param line en tant qu'entier pour la hauteur de la grille et du labyrinthe plus tard
     * @param column en tant qu'entier pour la largeur de la grille et du labyrinthe plus tard
     * @param posE en tant qu'entier pour la position de l'entrée E
     * @param posS en tant qu'entier pour la position de la sortie S
     * @throws IOException
     */
    public static void importOrCreate(String choiceF, String save, String play, int diff, int line, int column, int posE, int posS) throws IOException {
        while (!choiceF.equals("IMPORT") && !choiceF.equals("CREATE")) { // Choix du mode

            System.out.println("Choose a mode :");
            System.out.println("[ IMPORT = Import a maze's save | CREATE = Create a new maze ]");
            System.out.println("------------------------------------------------------------------------");

            if (entry.hasNextLine()) {
                choiceF = entry.nextLine();
            } else {
                System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                System.out.println("------------------------------------------------------------------------");
                entry.next();
            }
        }
        if (choiceF.equals("IMPORT")) { // Choisir le fichier .labgen à charger
            String Nom = System.getProperty("user.name");
            String directoryPath = "C:/Users/"+Nom+"/Documents/Labyrinthes/";
            while (!(new File(directoryPath+save+".labgen")).isFile()) {
                System.out.println("Choose your save file (example : lab01) :");
                System.out.println("------------------------------------------------------------------------");

                if (entry.hasNextLine()) {
                    save = entry.nextLine();
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    System.out.println("------------------------------------------------------------------------");
                    entry.next();
                }
            }
            String[][] array = SaveLabgen.readLabFile(directoryPath+save+".labgen");

            System.out.println("------------------------------------------------------------------------");
            System.out.println("Maze generated");
            System.out.println("------------------------------------------------------------------------");

            showMaze(array); // Affiche le labyrinthe
            while (!play.equals("PLAY") && !play.equals("SOLVE")) { // Jouer ou Solver

                System.out.println("Do you want to play or let the Solver solve it ? :");
                System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook ]");
                System.out.println("------------------------------------------------------------------------");

                if (entry.hasNextLine()) {
                    play = entry.nextLine();
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    System.out.println("------------------------------------------------------------------------");
                    entry.next();
                }
            }
            if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                System.out.println("Let's play then !");
                System.out.println("------------------------------------------------------------------------");
                Move.move(array);
            }
            if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                AutoSolver.pathSolver(array);
            }
        }
        if (choiceF.equals("CREATE")){ // Créer un labyrinthe
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Mode chosen : Create");
            System.out.println("------------------------------------------------------------------------");
            mazeDifficulty(diff, play, line, column, posE, posS);
        }
    }


    /**
     * Fonction void affiche et demande à l'user de choisir une difficulté.
     * @param diff en tant qu'entier servant à définir la difficulté du labyrinthe
     * @param play en tant que String servant à choisir si l'on souhaite jouer de nous même, laisser le solver le faire ou sauvegarder le labyrinthe
     * @param line en tant qu'entier pour la hauteur de la grille et du labyrinthe plus tard
     * @param column en tant qu'entier pour la largeur de la grille et du labyrinthe plus tard
     * @param posE en tant qu'entier pour la position de l'entrée E
     * @param posS en tant qu'entier pour la position de la sortie S
     * @throws IOException
     */
    public static void mazeDifficulty(int diff, String play, int line, int column, int posE, int posS) throws IOException {
        while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6) {

            System.out.println("Choose the difficulty :");
            System.out.println("[ 1 = Easy | 2 = Medium | 3 = Hard | 4 = Extreme | 5 = Impossible | 6 = Custom ]");

            if (entry.hasNextInt()) {
                diff = entry.nextInt();
            } else {
                System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                System.out.println("------------------------------------------------------------------------");
                entry.next();
            }
        }
        if (diff == 1) { // Easy - Facile
            line = 7;
            column = 7;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Easy");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {
                        System.out.println("Do you want to play, let the Solver solve it or save the maze ? :");
                        System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook | SAVE = Save the maze ]");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 2) { // Medium - Moyenne
            line = 13;
            column = 13;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Medium");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {
                        System.out.println("Do you want to play, let the Solver solve it or save the maze ? :");
                        System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook | SAVE = Save the maze ]");                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 3) { // Hard - Difficile
            line = 19;
            column = 19;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Hard");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play, let the Solver solve it or save the maze ? :");
                        System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook | SAVE = Save the maze ]");                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 4) { // Extreme - Extrême
            line = 25;
            column = 25;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Extreme");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play, let the Solver solve it or save the maze ? :");
                        System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook | SAVE = Save the maze ]");                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 5) { // Impossible - Impossible
            line = 31;
            column = 31;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Impossible");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play, let the Solver solve it or save the maze ? :");
                        System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook | SAVE = Save the maze ]");                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 6) { // Custom - Personnalisée
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Custom");
            System.out.println("------------------------------------------------------------------------");
            String mazeS = "";
            do {
                // Saisie de la largeur (doit être un entier impair)
                do {
                    System.out.println("Enter the width (odd integer) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    column = entry.nextInt();
                    if (column % 2 == 0) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (column % 2 == 0); // Boucle tant que ce n'est pas un entier impair

                // Saisie de la hauteur (doit être un entier impair)
                do {
                    System.out.println("Enter the height (odd integer) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    line = entry.nextInt();
                    if (line % 2 == 0) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (line % 2 == 0); // Boucle tant que ce n'est pas un entier impair

                // Saisie de la position de l'entrée (entier impair entre 1 et column-2)
                do {
                    System.out.println("Enter the position of the entry (odd integer between 1 included and " + (column - 2) + " included) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    posE = entry.nextInt();
                    if (posE % 2 == 0 || posE < 1 || posE > column - 2) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (posE % 2 == 0 || posE < 1 || posE > column - 2); // Boucle tant que ce n'est pas un entier impair
                // et que la position sur la grille n'est pas valide

                // Saisie de la position de la sortie (entier impair entre 1 et column-2)
                do {
                    System.out.println("Enter the position of the exit (odd integer between 1 included and " + (column - 2) + " included) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    posS = entry.nextInt();
                    if (posS % 2 == 0 || posS < 1 || posS > column - 2) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (posS % 2 == 0 || posS < 1 || posS > column - 2); // Boucle tant que ce n'est pas un entier impair
                // et que la position sur la grille n'est pas valide

                mazeS = entry.nextLine();
                // Saisie de la commande que veut lancer l'utilisateur
                do {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("What do you want to do (in full maj) ?");
                    System.out.println("[ GEN = Generate the maze | CONF = configure parameters of the maze | QUIT = Quit the program ]");
                    while (!entry.hasNextLine()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    mazeS = entry.nextLine();
                    if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Maze generated");
                        System.out.println("------------------------------------------------------------------------");

                        String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                        boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                        array = mazeGridDisplay(line, column, posE, posS);

                        Main m = new Main();
                        m.mazeGen(array, visited, 1, 1, line, column, false);

                        System.out.println(mazeGridDisplay(line, column, posE, posS));

                        showMaze(array); // Affiche le labyrinthe
                        while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                            System.out.println("Do you want to play, let the Solver solve it or save the maze ? :");
                            System.out.println("[ PLAY = Move in the maze | SOLVE = Let the Solver cook | SAVE = Save the maze ]");                        System.out.println("------------------------------------------------------------------------");
                            System.out.println("------------------------------------------------------------------------");

                            if (entry.hasNextLine()) {
                                play = entry.nextLine();
                            } else {
                                System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                                System.out.println("------------------------------------------------------------------------");
                                entry.next();
                            }
                        }
                        if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                            System.out.println("Let's play then !");
                            System.out.println("------------------------------------------------------------------------");
                            Move.move(array);
                        }
                        if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                            AutoSolver.pathSolver(array);
                        }
                        if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                            SaveLabgen.SaveMaze(array);
                        }
                    } else if (mazeS.equals("CONF")) { // Commande pour reconfigurer les paramètres de génération
                        System.out.println("Reconfiguring the maze :");

                    } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Mad Maze is closing, thank you for using it !");
                        System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                        System.out.println("------------------------------------------------------------------------");
                        System.exit(0);
                    } else {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (!mazeS.equals("GEN") && !mazeS.equals("CONF") && !mazeS.equals("QUIT"));
            } while (true);
        }
    }

    /**
     * Permet d'afficher la totalité du programme.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("------------------------------------------------------------------------");
        System.out.println("  ◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤————Welcome to Mad Maze————◥◣◥◣◥◣◥◣◥◣◥◣◥◣◥");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Mad Maze is a generator of mazes with several difficulty options ");
        System.out.println("including an option to create your own maze with your preferences !!");
        System.out.println("------------------------------------------------------------------------");

        String choiceF = "";
        String save = "";
        String playOrSolve = "";
        int line = 0;
        int column = 0;
        int posE = 0;
        int posS = 0;
        int diff = 0;

        importOrCreate(choiceF, save, playOrSolve, diff, line, column, posE, posS);
    }
}