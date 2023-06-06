import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Wood {



    private ArrayList<String> woodsPositionsElem = new ArrayList<String>();



    public void setStartWoodsPositionsElem(){
        int startPos = 0;
        int lastPos = 1 + 1;


        if(woodsPositionsElem.size() == 0){
            woodsPositionsElem.add(0, "startTree.png");
            woodsPositionsElem.add(1, "simpleWoodElem.png");
            for(int i = 2; i <= 11; i++ ){
                int random_number = startPos + (int) (Math.random() * lastPos);
                if(random_number == 0){
                    woodsPositionsElem.add(i, "leftPlant.png");
                }
                else{
                    woodsPositionsElem.add(i, "rightPlant.png");
                }
            }
        }
        else{
            return;
        }
    }



    public void addNewWoodsPositionElem(String woodsPositionElem){
        woodsPositionsElem.add(woodsPositionElem);
    }

    public boolean removeLastWoodsElemAndAddNew(String cutDirection){

        if(cutDirection == woodsPositionsElem.get(1)) return false;

        woodsPositionsElem.remove(1);
        int startPos = 0;
        int lastPos = 1+1;
        int randomValue = startPos + (int) (Math.random() * lastPos);
        if(randomValue == 0){
            woodsPositionsElem.add("leftPlant.png");
        }
        else if (randomValue == 1){
            woodsPositionsElem.add("rightPlant.png");
        }
        return true;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i <= 11; i++){
            WoodElem woodElem = new WoodElem(woodsPositionsElem.get(i));
            woodElem.setPosition(i);
            woodElem.setWoodType(woodsPositionsElem.get(i));
            woodElem.draw(g);
        }



    }


}
