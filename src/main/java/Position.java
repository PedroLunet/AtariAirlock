public class Position {
    private int x;
    private int y;

    //Default Constructor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getters & Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Methods
    public boolean samePosition(Position p1, Position p2) {
        return p1.getX() == p2.getX() && p1.getY() == p2.getY();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
