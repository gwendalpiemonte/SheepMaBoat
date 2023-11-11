package ch.heigvd.Game;

import java.util.HashMap;
import java.util.Map;

public class Canon {
    private static Map<Character, Integer> mapPositions = new HashMap<>();

    public static void tire(Terrain terrain, char colonne, int ligne, char obj){
        mapPositions.put('A', 1);
        mapPositions.put('B', 2);
        mapPositions.put('C', 3);
        mapPositions.put('D', 4);
        mapPositions.put('E', 5);
        mapPositions.put('F', 6);
        mapPositions.put('G', 7);
        mapPositions.put('H', 8);
        mapPositions.put('I', 9);
        mapPositions.put('J', 10);

        int x = mapPositions.get(colonne);
        x = x * 4 + 1;

        int y = ligne;
        y += y;

        terrain.tire(x,y,obj);
    }







}
