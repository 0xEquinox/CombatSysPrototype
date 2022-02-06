package Combat;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Block {

    GamePanel gamePanel;

    final int height;
    final int width;
    int x;
    int y;

    final int laneNumber;

    BufferedImage sprite;

    public Block(GamePanel gamePanel, int laneNumber) {
        this.x = gamePanel.getWidth();
        this.y = laneNumber * gamePanel.getTileSize() + 100;
        this.gamePanel = gamePanel;
        this.width = gamePanel.getTileSize();
        this.height = gamePanel.getTileSize();
        this.laneNumber = laneNumber;

        assignSprite(laneNumber);
    }

    private void assignSprite(int laneNumber) {
        try {
            switch (laneNumber) {
                case 1 -> sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Recourses/Assets/up.png")));
                case 2 -> sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Recourses/Assets/down.png")));
                case 3 -> sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Recourses/Assets/left.png")));
                case 4 -> sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Recourses/Assets/right.png")));
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("Error reading sprite: File does not exist");
        }
    }

    //Updates the position of the blocks
    public void update() {
        this.x = x - 5;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.RED);
        graphics2D.drawImage(sprite, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLaneNumber() {
        return laneNumber;
    }
}