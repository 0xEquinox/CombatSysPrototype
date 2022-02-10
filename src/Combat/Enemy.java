package Combat;

import Collider.BoxCollider;
import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class Enemy {

    int health = 3;

    int[] x = new int[4];
    int[] y = new int[]{1, 2, 3, 4};
    int[] width = new int[4];
    int[] height = new int[4];
    BoxCollider[] colliders = new BoxCollider[4];
    Shield shield;

    GamePanel gamePanel;

    public Enemy(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.shield = new Shield(gamePanel);

        x = Arrays.stream(x).map(x -> 10).toArray();
        y = Arrays.stream(y).map(y -> y * gamePanel.getTileSize() + gamePanel.getHeight() / 4).toArray();
        width = Arrays.stream(width).map(width -> gamePanel.getTileSize()).toArray();
        height = Arrays.stream(height).map(height -> gamePanel.getTileSize()).toArray();

        for (int i = 0; i < colliders.length; i++) {
            colliders[i] = new BoxCollider(x[i], y[i], width[i], height[i], gamePanel);
        }
    }

    public void update() {
        try {
            //Manage players input
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 1)
                    .filter(block -> colliders[0].isColliding(block.getX(), block.getY(), colliders[0].getX(), colliders[0].getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        health--;
                        //audioPlayer.playClip(0);
                    });
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 2)
                    .filter(block ->  colliders[1].isColliding(block.getX(), block.getY(), colliders[1].getX(), colliders[1].getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        health--;
                        //audioPlayer.playClip(1);
                    });
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 3)
                    .filter(block ->  colliders[2].isColliding(block.getX(), block.getY(), colliders[2].getX(), colliders[2].getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        health--;
                        //audioPlayer.playClip(2);
                    });
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 4)
                    .filter(block ->  colliders[3].isColliding(block.getX(), block.getY(), colliders[3].getX(), colliders[3].getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        health--;
                        //audioPlayer.playClip(3);
                    });
        } catch (ConcurrentModificationException ignored) {}

        shield.update();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.red);

        //Enemy hitbox
        for (int i = 0; i < 4; i++) {
            graphics2D.fillRect(x[i], y[i], width[i], height[i]);
        }

        //Health bar
        for (int i = 0; i < health; i++) {
            graphics2D.fillRect(10 + (i * gamePanel.getTileSize()), 10, gamePanel.getTileSize(), gamePanel.getTileSize());
        }

        //Enemy Shield
        shield.draw(graphics2D);
    }
}
