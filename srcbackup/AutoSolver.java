import java.util.Random;


public class AutoSolver {
    public static void main(String[] args) {

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

    }
    static boolean realisable(String[][] tab){

        int x=0;
        int y=0;
        boolean fin=false;
        boolean realisable=true;

        for (int i = 0; i < tab[0].length; i++) {
            if (tab[0][i]=="E"){
                x=i;
                y=1;
            }
        }

        while (!fin && tab[x][y] != "S") {

            //System.out.println(tab[x][y]);

            if (tab[x][y + 1] == " " || tab[x][y + 1] == "S") {
                tab[x][y] = "V";
                y += 1;
            }
            else if (tab[x + 1][y] == " " || tab[x + 1][y] == "S") {
                tab[x][y] = "V";
                x += 1;
            }
            else if (tab[x][y - 1] == " " || tab[x][y - 1] == "S") {
                tab[x][y] = "V";
                y -= 1;
            }
            else if (tab[x - 1][y] == " " || tab[x - 1][y] == "S") {
                tab[x][y] = "V";
                x -= 1;
            }
            else if (tab[x][y + 1] == "V") {
                tab[x][y] = "C";
                y += 1;
            }
            else if (tab[x + 1][y] == "V") {
                tab[x][y] = "C";
                x += 1;
            }
            else if (tab[x][y - 1] == "V") {
                tab[x][y] = "C";
                y -= 1;
            }
            else if (tab[x - 1][y] == "V") {
                tab[x][y] = "C";
                x -= 1;
            }
            else {
                realisable=false;
                fin = true;
            }
        }
        return realisable;
    }


    static void affiche(String[][] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j]=="\uD83d\uDE21"){
                    System.out.print(tab[i][j]+"");
                }
                else {
                    System.out.print(tab[i][j]+" ");
                }
            }
            System.out.println();
        }
    }


    static void chemin(String[][] tab){

        int x=0;
        int y=0;
        boolean fin=false;
        boolean realisable=true;

        for (int i = 0; i < tab[0].length; i++) {
            if (tab[0][i]=="E"){
                x=1;
                y=i;
            }
        }
        long start = System.currentTimeMillis();

        while (!fin && tab[x][y] != "S") {

//            System.out.println(x+"+"+y);

            if (tab[x + 1][y] == " " || tab[x + 1][y] == "S") {
                tab[x][y] = "V";
                x += 1;
            }

            else if (tab[x][y + 1] == " " || tab[x][y + 1] == "S") {
                tab[x][y] = "V";
                y += 1;
            }

            else if (tab[x - 1][y] == " " || tab[x - 1][y] == "S") {
                tab[x][y] = "V";
                x -= 1;
            }

            else if (tab[x][y - 1] == " " || tab[x][y - 1] == "S") {
                tab[x][y] = "V";
                y -= 1;
            }

            else if (tab[x + 1][y] == "V") {
                tab[x][y] = "C";
                x += 1;
            }

            else if (tab[x][y + 1] == "V") {
                tab[x][y] = "C";
                y += 1;
            }

            else if (tab[x - 1][y] == "V") {
                tab[x][y] = "C";
                x -= 1;
            }

            else if (tab[x][y - 1] == "V") {
                tab[x][y] = "C";
                y -= 1;
            }

            else {
                realisable=false;
                fin = true;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Le solver s'est terminé en "+((end-start))+" millisecondes");
        if (!realisable){
            System.out.println("Non réalisable");
        }
        else{
            for (int i = 1; i < tab.length-1; i++) {
                for (int j = 1; j < tab[i].length-1; j++) {
                    if (tab[i][j]=="C") {
                        tab[i][j] = " ";
                    }
                }
            }
            Main.showMaze(tab);
        }
    }
}