public class Player {


    private String name;
    private int Point;

    public Player(String name,int Point) {
        this.name = name;
        this.Point = Point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.Point = point;
    }

    public int getPoint() {
        return Point;
    }
}
