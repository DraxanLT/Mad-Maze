import java.io.*;

public class SaveLabgen {
    //    public static void main(String[] args) throws IOException {
//                String tableau[][]=
//                        {       {"#","E","#","#","#","#","#","#","#","#"},
//                                {"#"," "," ","#"," "," "," ","#"," ","#"},
//                                {"#","#"," ","#"," ","#","#","#"," ","#"},
//                                {"#"," "," "," "," ","#"," ","#"," ","#"},
//                                {"#"," ","#","#","#"," "," "," "," ","#"},
//                                {"#"," ","#"," "," "," ","#","#"," ","#"},
//                                {"#"," ","#"," ","#"," ","#"," "," ","#"},
//                                {"#"," ","#"," ","#"," ","#"," ","#","#"},
//                                {"#"," "," "," ","#"," ","#"," "," ","#"},
//                                {"#","#","#","#","#","#","#","#","S","#"},};
//        String tableaudeux[][]=
//                {       {"#","E","#","#","#","#","#","#","#","#"},
//                        {"#"," "," "," "," "," "," "," "," ","#"},
//                        {"#","#","#","#","#","#","#","#"," ","#"},
//                        {"#"," "," "," "," "," "," ","#"," ","#"},
//                        {"#"," ","#","#","#"," "," "," "," ","#"},
//                        {"#"," ","#"," ","#"," ","#","#"," ","#"},
//                        {"#"," ","#"," ","#"," ","#"," "," ","#"},
//                        {"#"," ","#","#","#"," ","#"," "," ","#"},
//                        {"#"," "," "," ","#"," ","#"," "," ","#"},
//                        {"#","#","#","#","#","#","#","#","S","#"},};
//
//        Enregistre(tableau);
//        String[][] truc=readLabFile("C:/Users/liamd/Documents/Labyrinthes/lab01.labgen");
//        affiche(truc);
//
//    }
//    static void affiche(String[][] tab){
//        for (int i = 0; i < tab.length; i++) {
//            for (int j = 0; j < tab[i].length; j++) {
//                if (tab[i][j]=="\uD83d\uDE21"){
//                    System.out.print(tab[i][j]+"");
//                }
//                else {
//                    System.out.print(tab[i][j]+" ");
//                }
//            }
//            System.out.println();
//        }
//    }

    static public void SaveMaze(String[][] tab) throws IOException {

        String Nom = System.getProperty("user.name");
        String directoryPath = "C:/Users/"+Nom+"/Documents/";
        String fileName = "Labyrinthes";

        File file = new File(directoryPath + fileName);
        if (!file.exists()){
            file.mkdirs();
        }
        int nombreFich = 2;
        String nomFich="lab01.labgen";
        while (new File("C:/Users/"+Nom+"/Documents/Labyrinthes/"+nomFich).isFile()){
            if (nombreFich<10){
                nomFich="lab"+"0"+nombreFich+".labgen";
            }
            else {
                nomFich="lab"+nombreFich+".labgen";
            }
            nombreFich++;
        }

        File labyrinthes = new File("C:/Users/"+Nom+"/Documents/Labyrinthes/"+nomFich);
        FileWriter fileReader = new FileWriter(labyrinthes); // A stream that connects to the text file
        BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
// Write your data
        System.out.println("File saved as "+nomFich);
        for (int i=0;i< tab.length;i++) {
            for (int j = 0; j < tab[i].length; j++) {
                bufferedWriter.write(tab[i][j]);
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }


    static public String[][] readLabFile(String filepath) throws IOException {
        BufferedReader bf= null;
        bf = new BufferedReader(new FileReader(filepath));
        System.out.println(filepath);
        System.out.println("Reading the file using readLine() method:");
        String contentLine = bf.readLine();
        String tab[][]= new String[contentLine.length()][contentLine.length()];
        int i;
        int l=0;
        while (contentLine != null) {
            i=0;

            while (i<contentLine.length()){
                tab[l][i]=contentLine.charAt(i)+"";
                i++;
            }
            contentLine = bf.readLine();
            l+=1;
        }
        return tab;
    }



}