package Player;

import Collider.BoxCollider;
import Combat.Block;
import Game.AudioPlayer;
import Game.GamePanel;
import Game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class Player {

    int health = 3;

    int[] x = new int[4];
    int[] y = new int[]{1, 2, 3, 4};
    int[] width = new int[4];
    int[] height = new int[4];
    BoxCollider[] colliders = new BoxCollider[4];

    KeyHandler keyHandler;
    BufferedImage sprite;
    GamePanel gamePanel;
    AudioPlayer audioPlayer;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        x = Arrays.stream(x).map(x -> gamePanel.getWidth() / 2).toArray();
        y = Arrays.stream(y).map(y -> y * gamePanel.getTileSize() + gamePanel.getHeight() / 4).toArray();
        width = Arrays.stream(width).map(width -> gamePanel.getTileSize()).toArray();
        height = Arrays.stream(height).map(height -> gamePanel.getTileSize()).toArray();

        for (int i = 0; i < colliders.length; i++) {
            colliders[i] = new BoxCollider(x[i], y[i], width[i], height[i], gamePanel);
        }

        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Recourses/Assets/Collector.png")));
        } catch (IOException | NullPointerException e) {
            System.err.println("File does not exist");
        }

        audioPlayer = new AudioPlayer();
    }

    public void update() {
        try {
            //Manage players input
            if (keyHandler.isUpPressed) gamePanel.getEnemyBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 1)
                    .filter(block -> colliders[0].isColliding(block.getX(), block.getY(), colliders[0].getX(), colliders[0].getY()))
                    .forEach(block -> {
                        gamePanel.getEnemyBlocks().remove(block);
                        //audioPlayer.playClip(0);
                    });
            if (keyHandler.isDownPressed) gamePanel.getEnemyBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 2)
                    .filter(block ->  colliders[1].isColliding(block.getX(), block.getY(), colliders[1].getX(), colliders[1].getY()))
                    .forEach(block -> {
                        gamePanel.getEnemyBlocks().remove(block);
                        //audioPlayer.playClip(1);
                    });
            if (keyHandler.isLeftPressed) gamePanel.getEnemyBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 3)
                    .filter(block ->  colliders[2].isColliding(block.getX(), block.getY(), colliders[2].getX(), colliders[2].getY()))
                    .forEach(block -> {
                        gamePanel.getEnemyBlocks().remove(block);
                        //audioPlayer.playClip(2);
                    });
            if (keyHandler.isRightPressed) gamePanel.getEnemyBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 4)
                    .filter(block ->  colliders[3].isColliding(block.getX(), block.getY(), colliders[3].getX(), colliders[3].getY()))
                    .forEach(block -> {
                        gamePanel.getEnemyBlocks().remove(block);
                        //audioPlayer.playClip(3);
                    });
        } catch (ConcurrentModificationException e) {
            System.out.println("error: " + e);
        }

        if (keyHandler.isOnePressed) {
            shoot(1);
            keyHandler.isOnePressed = false;
        } else if (keyHandler.isTwoPressed) {
            shoot(2);
            keyHandler.isTwoPressed = false;
        } else if (keyHandler.isThreePressed) {
            shoot(3);
            keyHandler.isThreePressed = false;
        } else if (keyHandler.isFourPressed) {
            shoot(4);
            keyHandler.isFourPressed = false;
        }

        //Update the health value
        health = gamePanel.getBoxCollector().getHealth();
    }

    private void shoot(int lane) {
        gamePanel.getPlayerBlocks().add(new Block(gamePanel, lane, x[1]));
    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < 4; i++) {
            graphics2D.drawImage(sprite, x[i], y[i], width[i], height[i], null);
        }

        graphics2D.setColor(Color.green);

        //Draw health bar
        for (int i = 0; i < health; i++) {
            graphics2D.fillRect((gamePanel.getWidth() - 164) + gamePanel.getTileSize() * i, 10, gamePanel.getTileSize(), gamePanel.getTileSize());
        }
    }
}
