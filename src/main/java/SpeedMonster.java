public class SpeedMonster extends Monster {
    private int direction;
    private long lastTimeMoved;
    private long speed;

    public SpeedMonster(int x, int y) {
        super(x,y,"\u3020");
        direction = Math.random()<0.5 ? -1 : 1 ;
        lastTimeMoved = System.currentTimeMillis() ;
    }
    @Override
    public void move(Arena arena){
        speed = direction >0 ? 10 : 250 ;
        if(System.currentTimeMillis()-lastTimeMoved < speed) return ;
        boolean moved= false;
        while (!moved) {
            int newX = getPosition().getX() + direction;
            Position newP = new Position(newX, getPosition().getY());
            if(!arena.checkAllWalls(new Position(newP.getX(),newP.getY()+1)) && !arena.isOnElevator(newP)){
                direction *=-1;
                setPosition(new Position(newX+direction , getPosition().getY()));
                return ;
            }
            if (!arena.checkAllWalls(newP)) {
                setPosition(newP);
                moved = true;
                lastTimeMoved = System.currentTimeMillis();
            } else {
                direction *= -1;
            }
        }
    }

}
