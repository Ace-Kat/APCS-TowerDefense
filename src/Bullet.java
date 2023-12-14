import processing.core.PApplet;
import processing.core.PImage;

public class Bullet extends PApplet {
    private int xSpeed;
    private int ySpeed;
    private float x;
    private float y;
    private final float ogX;
    private final float ogY;
    private static PImage bullet;

    public Bullet(PApplet p, float x, float y, int xSpeed, int ySpeed){
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.ogX = x;
        this.ogY = y;
        if (bullet == null){
            bullet = p.loadImage("assets/arrow.png");
        }
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public void update(){
        this.x = x + xSpeed;
        this.y = y +ySpeed;
    }
    public void draw(PApplet g){
        bullet.resize(20, 20);
        g.image(bullet, this.x, this.y);
    }
    public void reset() {
            this.x = ogX;
            this.y = ogY;
    }
    public boolean outOfBounds(){
        return this.y < 0 || this.y > 600;
    }
}
