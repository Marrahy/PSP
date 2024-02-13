public class Coordinates {
    String x, y;

    public Coordinates(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(this.x, this.y);
    }
}
