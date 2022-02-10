package Combat;

import Collider.BoxCollider;
import Game.GamePanel;

import java.awt.*;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class Shield {

    int x;
    int y;
    int width;
    int height;
    int currentDirection = 0;

    BoxCollider collider;

    GamePanel gamePanel;

    public Shield(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        x = 10 + gamePanel.getTileSize() * 2;
        y =  gamePanel.getTileSize() + gamePanel.getHeight() / 4;
        width = gamePanel.getTileSize();
        height = gamePanel.getTileSize();

        collider = new BoxCollider(x, y, width, height, gamePanel);
    }

    public void update() {
        try {
            //Manage players input
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 1)
                    .filter(block -> collider.isColliding(block.getX(), block.getY(), collider.getX(), collider.getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        //audioPlayer.playClip(0);
                    });
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 2)
                    .filter(block ->  collider.isColliding(block.getX(), block.getY(), collider.getX(), collider.getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        //audioPlayer.playClip(1);
                    });
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 3)
                    .filter(block ->  collider.isColliding(block.getX(), block.getY(), collider.getX(), collider.getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        //audioPlayer.playClip(2);
                    });
            gamePanel.getPlayerBlocks().stream()
                    .filter(Objects::nonNull)
                    .filter(block -> block.getLaneNumber() == 4)
                    .filter(block ->  collider.isColliding(block.getX(), block.getY(), collider.getX(), collider.getY()))
                    .forEach(block -> {
                        gamePanel.getPlayerBlocks().remove(block);
                        //audioPlayer.playClip(3);
                    });
        } catch (ConcurrentModificationException ignored) {}

        if (y < gamePanel.getTileSize() + gamePanel.getHeight() / 4) {
           currentDirection = 0;
        } else if (y > 4 * gamePanel.getTileSize() + gamePanel.getHeight() / 4){
           currentDirection = 1;
        }

        if (0 == currentDirection) {
            y += 5;
        } else {
            y -= 5;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.blue);

        //Enemy hitbox
        for (int i = 0; i < 4; i++) {
            graphics2D.fillRect(x, y, width, height);
        }
    }
}
