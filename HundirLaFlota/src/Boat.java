public class Boat {
    private Coordinates coordinates;
    private int timesTouched;
    private boolean isAlive;

    public Boat(Coordinates coordinates, int timesTouched, boolean isAlive) {
        this.coordinates = coordinates;
        this.timesTouched = timesTouched;
        this.isAlive = isAlive;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setTimesTouched(int timesTouched) {
        this.timesTouched = timesTouched;
    }

    public int getTimesTouched() {
        return timesTouched;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public String toString() {
        return "Barco(" + getCoordinates() + ")";
    }
}
