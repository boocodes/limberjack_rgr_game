import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreTable {


    private final int MAX_TABLE_SIZE = 10;

    private boolean isSessionWrote = false;


    public void setIsSessionWrote(boolean flag){
        isSessionWrote = flag;
    }

    public void showBestPlayers(Graphics g){
        ArrayList<String> data = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./bestTable.txt"));
            String line = reader.readLine();
            int counter = 0;
            g.drawString("Последние игры: ", 250, 650);
            while (line != null && counter <= MAX_TABLE_SIZE) {
                g.drawString(line, 250, 670 + ((counter + 1)* 15));
                line = reader.readLine();
                counter++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public HashMap<String, Integer> convertArrayBestTableToHashMap(ArrayList<String> data, String currentUsername, int currentScore){
        HashMap<String, Integer> namesAndScore = new HashMap<String, Integer>();


        for(int i = 0; i<=data.size()-1; i++){
            String userString = "";
            Integer newScore = 0;
            for(int j = 0; j<=data.get(i).length() -1; j++){
                if(data.get(i).charAt(j) == '='){
                    userString = data.get(i).substring(0, j);
                    newScore = Integer.valueOf(data.get(i).substring(j+1));
                }
            }
            namesAndScore.put(userString, newScore);
        }

        return namesAndScore;
    }






    public ArrayList<String> placeHashMyByHigherScoreOrder(HashMap<String, Integer> data){

        ArrayList<String> dataToBestTable = new ArrayList<String>();
        data.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(item -> dataToBestTable.add(String.valueOf(item)));


        return dataToBestTable;

    }



    public void saveSession(String username, int score) {
        if(!isSessionWrote) {
            ArrayList<String> data = new ArrayList<String>();

            data.add(username + "=" + score);

            try {
                BufferedReader reader = new BufferedReader(new FileReader("./bestTable.txt"));
                String line = reader.readLine();

                while (line != null) {
                    if (!line.startsWith(username)) {
                        data.add(line);

                    }
                    else{
                        for(int i = 0; i <= line.length()-1; i++){
                            if(line.charAt(i) == '='){
                                Integer oldScore = Integer.valueOf(line.substring(i+1));

                                if(oldScore < score){
                                    Integer newScoreInTable = score;
                                    String usernameInTable = line.substring(0, i);
                                    data.add(usernameInTable + "=" + newScoreInTable);
                                }
                                else if(oldScore > score){
                                    Integer newScoreInTable = Integer.valueOf(line.substring(i+1));
                                    String usernameInTable = line.substring(0, i);
                                    data.add(usernameInTable + "=" + newScoreInTable);
                                }
                                else if (oldScore == score){
                                    Integer newScoreInTable = Integer.valueOf(line.substring(i+1));
                                    String usernameInTable = line.substring(0, i);
                                    data.add(usernameInTable + "=" + newScoreInTable);
                                }
                            }
                        }
                    }



                    line = reader.readLine();
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            HashMap<String, Integer> bestTableHashMap = convertArrayBestTableToHashMap(data, username, score);
            ArrayList<String> placedByOrderBestTableArrayList = placeHashMyByHigherScoreOrder(bestTableHashMap);



            try{
                FileWriter out = new FileWriter("./bestTable.txt", false);
                int counter = 1;
                for(int i = 0; i <= placedByOrderBestTableArrayList.size() - 1; i++){
                    out.write(placedByOrderBestTableArrayList.get(i) + "\n");
                }
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }



        }

    }








    public void setSessionWrote(boolean sessionFlag){
        isSessionWrote = sessionFlag;
    }


}
