import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Button {
    private boolean up;
    private int upCounter;
    private int downCounter;
    private static PImage button;

    public Button(PApplet p, boolean up){
        this.up = up;
        upCounter = 0;
        downCounter = 0;
        if(button == null){
            button = p.loadImage("assets/button.png");
        }
    }

    public void draw(PApplet game){
        if(up) {
            button.resize(50, 50);
            game.image(button, 275, 50);
        }
        button.resize(50, 50);
        game.image(button, 275, 450);

    }

    public boolean containsPoint(int mouseX, int mouseY) {
        if(up){
            return (mouseX > 275) && (mouseX < 325) && (mouseY > 50) && (mouseY < 100);
        }
        return (mouseX > 275) && (mouseX < 325) && (mouseY > 450) && (mouseY < 500);
    }

    public void respondToClick(PApplet game, ArrayList<Tower> towerList, ArrayList<Bullet> bulletList) {
        if(up && upCounter < 6){
            Tower t = new Tower(game, upCounter*100+30, 150, true);
            towerList.add(t);
            Bullet b = t.Shoot(game, t,0, 1);
            bulletList.add(b);
            b.draw(game);
            upCounter++;
        } else if (!(up) && downCounter < 6) {
            Tower t = new Tower(game, downCounter*100+30, 350, false);
            towerList.add(t);
            Bullet b = t.Shoot(game, t,0, -1);
            bulletList.add(b);
            b.draw(game);
            downCounter++;
        }
    }
}
