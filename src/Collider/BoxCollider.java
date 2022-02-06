package Collider;


import Game.GamePanel;

import java.awt.*;

public class BoxCollider {
    private final Rectangle hitbox;

    private final GamePanel gamePanel;

    public BoxCollider(double x, double y, int width, int height, GamePanel gamePanel) {
        this.hitbox = new Rectangle((int)x,(int)y,width,height);
        this.gamePanel = gamePanel;
    }

    public boolean isTouching(int tileX, int tileY, double x, double y){
        double rightOfPlayer = x + gamePanel.getTileSize();
        double bottomOfPlayer = y + gamePanel.getTileSize();

        int bottomOfTile = tileY + gamePanel.getTileSize();
        int rightOfTile = tileX + gamePanel.getTileSize();

        //Used when moving down
        if(((int)rightOfPlayer == tileX) //Used when moving right
                || (rightOfTile == (int) x)) //Used when moving left
            return (tileY == (int) bottomOfPlayer) //Used when moving up
                    || (bottomOfTile == (int) y);
        return false;
    }

    public boolean isColliding(int tileX, int tileY, double x, double y){
        double rightOfPlayer = x + gamePanel.getTileSize();
        double bottomOfPlayer = y + gamePanel.getTileSize();

        int bottomOfTile = tileY + gamePanel.getTileSize();
        int rightOfTile = tileX + gamePanel.getTileSize();

        //Used when moving down
        if((rightOfTile >= rightOfPlayer && rightOfPlayer > tileX) //Used when moving right
                || (tileX <= x && x < rightOfTile)) //Used when moving left
            return (tileY <= y && y < bottomOfTile) //Used when moving up
                    || (bottomOfTile >= bottomOfPlayer && bottomOfPlayer > tileY);
        return false;
    }

    public void setX(int x){
        hitbox.x = x;
    }

    public void setY(int y){
        hitbox.y = y;
    }

    public int getX() {
        return (int)hitbox.getX();
    }

    public int getY() {
        return (int)hitbox.getY();
    }

    public int getHeight() {
        return (int)hitbox.getHeight();
    }

    public int getWidth() {
        return (int)hitbox.getWidth();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
