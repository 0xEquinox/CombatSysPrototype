package Game;

import Combat.Block;
import Combat.BlockCreator;
import Combat.BoxCollector;
import Combat.Enemy;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; //16x16
    final int scale = 3; //48x48

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 32;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    ArrayList<Block> enemyBlocks = new ArrayList<>();
    ArrayList<Block> playerBlocks = new ArrayList<>();
    BlockCreator blockCreator = new BlockCreator();
    BoxCollector boxCollector = new BoxCollector(this);

    final int fps = 60;

    Enemy enemy = new Enemy(this);
    Player player;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.player = new Player(this, keyHandler);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000f / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int frameCounter = 1;

    public void update() {
        if (frameCounter == 30) {
            enemyBlocks.add(blockCreator.createBlock(this));
            frameCounter = 1;
        }
        enemyBlocks.forEach(Block::update);
        playerBlocks.forEach(Block::update);
        player.update();
        enemy.update();
        boxCollector.update();

        frameCounter++;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        //Draw each enemy block
        enemyBlocks.forEach(block -> block.draw(graphics2D));
        //Draw each player block
        playerBlocks.forEach(block -> block.draw(graphics2D));
        //Draw the player squares
        player.draw(graphics2D);

        enemy.draw(graphics2D);

        boxCollector.draw(graphics2D);

        graphics2D.dispose();
    }

    public int getTileSize() {
        return tileSize;
    }

    public ArrayList<Block> getEnemyBlocks() {
        return enemyBlocks;
    }

    public ArrayList<Block> getPlayerBlocks() {
        return playerBlocks;
    }

    @Override
    public int getWidth() {
        return maxScreenCol * 48;
    }

    @Override
    public int getHeight() {
        return maxScreenRow * 48;
    }

    public BoxCollector getBoxCollector() {
        return boxCollector;
    }
}