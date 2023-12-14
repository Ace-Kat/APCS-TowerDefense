import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Tower> towerList;
    ArrayList<Balloon> balloonList;
    ArrayList<Bullet> bulletList;
    Button UButton;
    Button DButton;
    int timer;
    int deathCounter;
    int cash;
    int counter;
    int finalCounter;
    int cashTimer;
    int balloonsMissed;
    int towerUPCounter;
    int towerDownCounter;


    public void settings() {
        size(600, 600);   // set the window size
    }

    public void setup() {
        cash = 5;
        deathCounter = 0;
        counter = 0;
        cashTimer = 400;
        timer = 1;
        finalCounter = 0;
        UButton = new Button(this,true);
        DButton = new Button(this, false);
        bulletList = new ArrayList<>();
        towerList = new ArrayList<>();
        balloonList = new ArrayList<>();
        balloonsMissed = 0;
        towerUPCounter = 0;
        towerDownCounter = 0;

        for (int i = 0; i < 100; i++) {
            Balloon q = new Balloon(this, -(i*100), 250);
            balloonList.add(q);
        }
    }
    public void draw() {
        background(	135, 206, 235);
        fill(150);
        rect(0, 200, 600, 150);

        fill(62,156,53);
        textSize(21);
        text("Cash: " + cash, 50, 50);

        fill(250, 0, 0);
        textSize(21);
        text("Balloons hit: " + deathCounter, 375, 50);

        fill(250, 0, 0);
        textSize(21);
        text("Lives left: " + (30 - balloonsMissed), 375, 100);

        UButton.draw(this);
        DButton.draw(this);

        cashTimer--;
        if(cashTimer == 0) {
            cashTimer = 400;
            cash += 1;
        }

        for (Tower t : towerList){
           t.draw(this);
        }

        for (Bullet b : bulletList){
            b.update();
            if(b.outOfBounds()){
                b.reset();
            }
            b.draw(this);
        }

        if(balloonsMissed <= 30 && deathCounter <= 75) {

            for (Balloon b : balloonList) {
                if (b.getInvis()) {
                    counter++;
                }
            }
            deathCounter = counter;
            counter = 0;

            for (Balloon b : balloonList) {
                if (!(b.getInvis()) && (b.getX() > 610)) {
                    counter++;
                }
            }
            balloonsMissed = counter;
            counter = 0;
        }

        for (int i = 0; i < balloonList.size(); i++) {
            if (!balloonList.isEmpty()) {
                balloonList.get(i).draw(this);
                for (Bullet b : bulletList) {
                    if (!balloonList.isEmpty()) {
                        if (balloonList.get(i).contains(b) && !(balloonList.get(i).getInvis())) {
                            balloonList.get(i).delete();
                            timer--;
                            if (timer == 0){
                                cash += 1;
                                timer = 1;
                            }
                        }
                    }
                }
                balloonList.get(i).update();
            }
        }

        for(Balloon b : balloonList){
            if(b.getX() > 650){
                finalCounter++;
            }
            if(finalCounter == 100){
                background(0);
                fill(250,0, 0);
                textSize(50);
                text("You lose!", 200, 300);
                textSize(25);
                text("Balloons hit: " + deathCounter, 200, 450);
            }
            if(balloonsMissed >= 30){
                background(0);
                fill(250,0, 0);
                textSize(50);
                text("You lose!", 200, 300);
                textSize(25);
                text("Balloons hit: " + deathCounter, 200, 450);
            }
            if(deathCounter >= 75){
                background(255);
                fill(62,156,53);
                textSize(50);
                text("You win!", 200, 300);
                textSize(25);
                text("Balloons hit: " + deathCounter, 200, 450);
            }
        }
        finalCounter = 0;
    }
    public void mousePressed() {
        for(Tower tower : towerList){
            if(tower.isUp()){
                towerUPCounter++;
            }else{
                towerDownCounter++;
            }
        }
        if (UButton.containsPoint(mouseX, mouseY) && cash >= 5 && towerUPCounter <=6) {
            UButton.respondToClick(this, towerList, bulletList);
            cash = cash - 5;
        } else if (DButton.containsPoint(mouseX, mouseY) && cash >= 5 && towerDownCounter <=6) {
            DButton.respondToClick(this, towerList, bulletList);
            cash = cash - 5;
        }
        towerDownCounter = 0;
        towerUPCounter = 0;
    }
    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
