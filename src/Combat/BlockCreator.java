package Combat;

import Game.GamePanel;

public class BlockCreator {

    public Block createBlock(GamePanel gamePanel) {
        return new Block(gamePanel, (int) (Math.random() * 5), gamePanel.getWidth());
    }

}
