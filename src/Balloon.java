import processing.core.PApplet;
import processing.core.PImage;

public class Balloon extends PApplet {
    private float x,y;
    private int xSpeed, ySpeed;
    private boolean invisible;
    private static PImage balloon;

    public Balloon(PApplet g, float x, float y){
        this.x = x;
        this.y = y;
        xSpeed = 2;
        invisible = false;
        if(balloon == null){
            balloon = g.loadImage("assets/balloon.png");
        }
    }
    public float getX() {
        return x;
    }
    public void update(){
                 x = x + xSpeed;
             }
    public void draw(PApplet g){
        if(!invisible){
            balloon.resize(50, 50);
            g.image(balloon, this.x, this.y);
        } else {
            g.fill(150);
        }
    }
    public boolean contains(Bullet b) {
        return (this.getLeftSide() <= b.getX() && b.getX() <= this.getRightSide() && this.getTop() <= b.getY() && b.getY() <= this.getBottom());
    }
    private float getBottom() {
        return this.y + 20;
    }
    private float getTop() {
        return this.y- 20;
    }
    private float getLeftSide() {
        return this.x-15;
    }
    private float getRightSide() {
        return this.x+15;
    }
    public void delete(){
        this.invisible = true;
    }
    public boolean getInvis(){
                 return invisible;
    }
    }
