import processing.core.PApplet;
import processing.core.PImage;

public class Tower extends PApplet{
    private float x,y;
    private PImage tower;
    private boolean up;

    public Tower(PApplet g, float x, float y, boolean up){
        this.x = x;
        this.y = y;
        this.up = up;
        if(tower == null){
            tower = g.loadImage("assets/tower.png");
        }
    }
    public void draw(PApplet g) {
        int length = 60;
        int width = 50;
        tower.resize(width, length);
        g.image(tower, this.x, this.y);
    }
    public boolean isUp() {
        return up;
    }
    public Bullet Shoot(PApplet p, Tower t, int speedX, int speedY){
        return new Bullet(p, t.x+20,t.y,speedX*(int)(Math.random()*2 +1),speedY*(int)(Math.random()*4 +2));
    }
    public boolean contains(Bullet b) {
        return (this.getLeftSide() <= b.getX() && b.getX() <= this.getRightSide() && this.getTop() <= b.getY() && b.getY() <= this.getBottom());
    }
    private float getBottom() {
        return this.y + 25;
    }
    private float getTop() {
        return this.y- 25;
    }
    private float getLeftSide() {
        return this.x-20;
    }
    private float getRightSide() {
        return this.x+20;
    }
}
