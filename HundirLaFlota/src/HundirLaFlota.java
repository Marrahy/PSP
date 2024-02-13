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
            Coordinates coordinates = entry.getKey();
            Boat boat = entry.getValue();
            System.out.println("Coordinates: " + coordinates.getX() + coordinates.getY() + ", Boat: " + boat.toString());
        }
    }
}
