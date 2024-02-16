import java.util.*;

public class HundirLaFlota {
    public static void main(String[] args) {
        Map<Coordinates, Boat> map = new HashMap<>();
        for (char x = 'A'; x <= 'E'; x++) {
            for (int y = 1; y <= 5; y++) {
                Coordinates coordinates = new Coordinates(String.valueOf(x), String.valueOf(y));
                Boat boat = new Boat(coordinates, 0, true);
                map.put(coordinates, boat);
            }
        }

        for (Map.Entry<Coordinates, Boat> entry : map.entrySet()) {
            Boat boat = entry.getValue();
            System.out.println("Boat is alive: " + boat.isAlive() + " \nBoat times tocuhed: " + boat.getTimesTouched() + " \nBoat coordenates: (" + boat.getCoordinates().getX() + ", " + boat.getCoordinates().getY() + ")");
            System.out.println();
        }
    }
}
