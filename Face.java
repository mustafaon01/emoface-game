import javax.swing.*;
import java.awt.*;

class Face {
    private int numberOfTrueComponents;
    private int numberOfWrongComponents;
    private boolean isWrongFaceVisible;
    private int gameMode = 5;

    public Face() {
        this.numberOfTrueComponents = 0;
        this.isWrongFaceVisible = false;
    }

    public void setGameMode(int mode) {
        gameMode = mode;
    }

    public void trueAnswer() {
        if (isWrongFaceVisible) {
            if (numberOfWrongComponents > 0) {
                numberOfWrongComponents--;
            } else {
                numberOfTrueComponents++;
            }
        } else {
            numberOfTrueComponents++;
        }
        checkGameState();
    }

    public void wrongAnswer() {
        if (numberOfTrueComponents > 0) {
            numberOfTrueComponents--;
        } else {
            isWrongFaceVisible = true;
            numberOfWrongComponents++;
        }
        checkGameState();
    }
    private void checkGameState() {
        if(gameMode == 5) {
            if (numberOfWrongComponents > 5) {
                JOptionPane.showMessageDialog(null, "You Lost!");
                System.exit(0);
            }
            if (numberOfTrueComponents > 5) {
                JOptionPane.showMessageDialog(null, "You Win!");
                System.exit(0);
            }
            // Reset if we have 4 wrong components and then get 4 correct answers in a row
            if (numberOfWrongComponents == 4 && numberOfTrueComponents == 4) {
                resetGame();
            }
        }
        else {
            if (numberOfWrongComponents > 9) {
                JOptionPane.showMessageDialog(null, "You Lost!");
                System.exit(0);
            }
            if (numberOfTrueComponents > 10) {
                JOptionPane.showMessageDialog(null, "You Win!");
                System.exit(0);
            }
            if (numberOfWrongComponents == 9 && numberOfTrueComponents == 9) {
                resetGame();
            }
        }
    }

    private void resetGame() {
        numberOfTrueComponents = 0;
        numberOfWrongComponents = 0;
        isWrongFaceVisible = false;
    }


    public void draw(Graphics g) {
        if (isWrongFaceVisible) {
            if (numberOfWrongComponents >= 1) {
                g.setColor(Color.RED);
                g.fillOval(100, 100, 200, 200);
            }

            g.setColor(Color.BLACK);
            if (numberOfWrongComponents >= 2) g.fillOval(140, 140, 30, 30);
            if (numberOfWrongComponents >= 3) g.fillOval(230, 140, 30, 30);
            if (numberOfWrongComponents >= 4) g.fillOval(190, 190, 20, 20);
            if (numberOfWrongComponents >= 5) g.drawArc(160, 220, 80, 40, 0, 180);
            if (gameMode == 10) {
                bowTieDraw(numberOfWrongComponents,g);
            }
        } else {
            drawFace(numberOfTrueComponents,g);

            if (gameMode == 10) {
                bowTieDraw(numberOfTrueComponents,g);
            }
        }
    }

    private void bowTieDraw(int numberComponents, Graphics g){
        if (numberComponents >= 6) {
            g.fillOval(70, 130, 30, 100);
        }
        if (numberComponents >= 7) {
            g.fillOval(300, 130, 30, 100);
        }
        if (numberComponents >= 8) {
            g.fillPolygon(new int[]{125, 200, 175}, new int[]{320, 320, 360}, 3);
        }
        if (numberComponents >= 9) {
            g.fillPolygon(new int[] { 275, 200, 225 }, new int[] { 320, 320, 360 }, 3);
        }
        if (numberComponents >= 10) {
            g.fillOval(190, 340, 20, 20);
        }
    }

    private void drawFace(int numberComponents, Graphics g){
        if (numberComponents >= 1) {
            g.setColor(Color.YELLOW);
            g.fillOval(100, 100, 200, 200);
        }

        g.setColor(Color.BLUE);
        if (numberComponents >= 2) g.fillOval(140, 140, 30, 30);
        if (numberComponents >= 3) g.fillOval(230, 140, 30, 30);
        if (numberComponents >= 4) g.fillOval(190, 190, 20, 20);
        if (numberComponents >= 5) g.drawArc(160, 220, 80, 40, 0, -180);
    }




}