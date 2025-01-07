import java.util.Scanner;

public class Move {
    public static void main(String[] args) {
        String tableau[][]=
                {       {"#","E","#","#","#","#","#","#","#","#"},
                        {"#"," "," ","#"," "," "," "," "," ","#"},
                        {"#","#"," ","#"," ","#","#","#"," ","#"},
                        {"#"," "," "," "," ","#"," ","#"," ","#"},
                        {"#"," ","#","#","#"," "," "," "," ","#"},
                        {"#"," ","#"," "," "," ","#","#"," ","#"},
                        {"#"," ","#"," ","#"," ","#"," "," ","#"},
                        {"#"," ","#"," ","#"," ","#"," ","#","#"},
                        {"#"," ","#"," ","#"," ","#"," "," ","#"},
                        {"#","#","#","#","#","#","#","#","S","#"},};
        AutoSolver.affiche(tableau);
        move(tableau);
    }

    public static void move(String[][]GiveTab) {
        int x = 0;
        int y = 0;
        boolean End = false;
        for (int i = 0; i < GiveTab[0].length; i++) {
            if (GiveTab[0][i]=="E") {
                x = i;
                y = 1;
            }

        }
        GiveTab[x][y]="\uD83D\uDE21";
        while (!End){
            AutoSolver.affiche(GiveTab);

            Scanner sc = new Scanner(System.in);

            String move = sc.nextLine();

            if (move.equals("Ghazi and Liam the bg")){
                GiveTab[x][y]=" ";
                AutoSolver.chemin(GiveTab);
                End = true;
            }

            if (move.equals("d") ||move.equals("D")) {
                if (GiveTab [x][y+1]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x][y+1]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x][y+1]="\uD83D\uDE21";
                    y+=1;
                }
            }
            if (move.equals("q") ||move.equals("Q")) {
                if (GiveTab [x][y-1]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x][y-1]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x][y-1]="\uD83D\uDE21";
                    y-=1;
                }
            }
            if (move.equals("z") ||move.equals("Z")) {
                if (GiveTab [x-1][y]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x-1][y]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x-1][y]="\uD83D\uDE21";
                    x-=1;
                }
            }
            if (move.equals("s") ||move.equals("S")) {
                if (GiveTab [x+1][y]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x+1][y]=="S"){
                    End = true;
                }


                if (GiveTab[x+1][y]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x+1][y]="\uD83D\uDE21";
                    x+=1;
                }

            }

        }
        System.out.println("GG you win");

}
}