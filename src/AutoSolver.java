import java.sql.SQLOutput;
import java.util.Random;


public class AutoSolver {
//    public static void main(String[] args) {

//        String tableau[][]=
//                        {       {"#","E","#","#","#","#","#","#","#","#"},
//                                {"#"," ","P","#"," "," "," ","#"," ","#"},
//                                {"#","#"," ","#"," ","#","#","#"," ","#"},
//                                {"#"," "," "," "," ","#"," ","#"," ","#"},
//                                {"#"," ","#","#","#"," "," "," "," ","#"},
//                                {"#"," ","#"," "," "," ","#","#"," ","#"},
//                                {"#"," ","#"," ","#"," ","#"," "," ","#"},
//                                {"#"," ","#"," ","#"," ","#"," ","#","#"},
//                                {"#"," "," "," ","#"," ","#"," "," ","#"},
//                                {"#","#","#","#","#","#","#","#","S","#"},};
////        tableau = mazeGenerator(10, 30);
//        chemin(tableau);

    //Fonction qui récupère en argument un tableau et qui renvoie la solution à celui-ci
    static void pathSolver(String[][] tab){

        int x=1;
        int y=1;
        boolean fin=false;
        boolean realisable=true;

        //Affecte la position de départ à la sace en dessous de l'entrée
        for (int i = 0; i < tab[0].length; i++) {
            if (tab[0][i]=="E"){
                x=1;
                y=i;
            }
        }

        //début du timer
        long start = System.nanoTime();
        int loops=0;
        int comparaisons=0;

        //Boucle qui cherche autour de la case actuelle et agit en conséquence
        while (!fin && tab[x][y] != "S" && !(tab[x][y].equals("S"))) {

            //Ces 4 if cherchent des cases jamais visitées
            if (tab[x + 1][y] == " " || tab[x + 1][y] == "S" || tab[x + 1][y].equals(" ") || tab[x + 1][y].equals("S")) {
                tab[x][y] = "V";
                x += 1;
                comparaisons+=1;
            }

            else if (tab[x][y + 1] == " " || tab[x][y + 1] == "S" || tab[x][y+1].equals(" ")) {
                tab[x][y] = "V";
                y += 1;
                comparaisons+=2;
            }

            else if (tab[x - 1][y] == " " || tab[x - 1][y] == "S" || tab[x - 1][y].equals(" ")) {
                tab[x][y] = "V";
                x -= 1;
                comparaisons+=3;
            }

            else if (tab[x][y - 1] == " " || tab[x][y - 1] == "S" || tab[x][y-1].equals(" ")) {
                tab[x][y] = "V";
                y -= 1;
                comparaisons+=4;
            }


            //Ces 4 fonctions cherchent des cases déjà visitées si aucune case alentoure est non visitée
            else if (tab[x + 1][y] == "V" || tab[x+1][y].equals("V")) {
                tab[x][y] = "C";
                x += 1;
                comparaisons+=5;
            }

            else if (tab[x][y + 1] == "V" || tab[x][y+1].equals("V")) {
                tab[x][y] = "C";
                y += 1;
                comparaisons+=6;
            }

            else if (tab[x - 1][y] == "V" || tab[x-1][y].equals("V")) {
                tab[x][y] = "C";
                x -= 1;
                comparaisons+=7;
            }

            else if (tab[x][y - 1] == "V" || tab[x][y-1].equals("V")) {
                tab[x][y] = "C";
                y -= 1;
                comparaisons+=8;
            }


            //Si il n'y a plus de chemins disponibles, le code s'arrête et le labyrinthe n'est pas réalisable
            else {
                realisable=false;
                fin = true;
                comparaisons+=9;
            }
            loops++;
        }
        //Fin du timer
        long end = System.nanoTime();
        //Message si non réalisable
        if (!realisable){
            System.out.println("Non réalisable");
        }
        //Modification du tableau et affichage si réalisable
        else{
            for (int i = 1; i < tab.length-1; i++) {
                for (int j = 1; j < tab[i].length-1; j++) {
                    if (tab[i][j]=="C"||tab[i][j].equals("C")) {
                        tab[i][j] = " ";
                    }
                }
            }
            Main.showMaze(tab);
        }
        //Affichage de la complexité
        System.out.println("Le solver s'est terminé en "+((end-start)/10000000.0)+" millisecondes et a effectué "+loops+" boucles et "+comparaisons+" comparaisons.");

    }
}