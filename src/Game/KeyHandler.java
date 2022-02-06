package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean isUpPressed, isDownPressed, isRightPressed, isLeftPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code){
            case KeyEvent.VK_UP -> {
                System.out.println("UP pressed");
                isUpPressed = true;
            }
            case KeyEvent.VK_DOWN -> {
                System.out.println("Down Pressed");
                isDownPressed = true;
            }
            case KeyEvent.VK_LEFT -> {
                System.out.println("Left Pressed");
                isLeftPressed = true;
            }
            case KeyEvent.VK_RIGHT -> {
                System.out.println("Right pressed");
                isRightPressed = true;
            }
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code){
            case KeyEvent.VK_UP -> {
                System.out.println("UP pressed");
                isUpPressed = false;
            }
            case KeyEvent.VK_DOWN -> {
                System.out.println("Down Pressed");
                isDownPressed = false;
            }
            case KeyEvent.VK_LEFT -> {
                System.out.println("Left Pressed");
                isLeftPressed = false;
            }
            case KeyEvent.VK_RIGHT -> {
                System.out.println("Right pressed");
                isRightPressed = false;
            }
            default -> {}
        }
    }
}
