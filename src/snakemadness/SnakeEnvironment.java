/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakemadness;

import environment.Environment;
import environment.Grid;
import image.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author ZackB
 */
class SnakeEnvironment extends Environment {

    private Grid grid;
    private int score = 0;
    private MadSnake snake;
    private int delay = 1;
    private int moveCounter = delay;

    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/jungleTiles.jpg"));

        this.grid = new Grid();
        this.grid.setColor(Color.green);
        this.grid.setColumns(53);
        this.grid.setRows(27);
        this.grid.setCellHeight(20);
        this.grid.setCellWidth(20);
        this.grid.setPosition(new Point(150, 100));


        this.snake = new MadSnake();
        this.snake.getBody().add(new Point(5, 5));
        this.snake.getBody().add(new Point(5, 4));
        this.snake.getBody().add(new Point(5, 3));
        this.snake.getBody().add(new Point(4, 3));
        this.snake.getBody().add(new Point(4, 2));
        this.snake.getBody().add(new Point(3, 2));
        this.snake.getBody().add(new Point(3, 1));
        this.snake.getBody().add(new Point(2, 1));

    }

    @Override
    public void timerTaskHandler() {
        System.out.println("Timer");
        if (snake != null) {
            if (moveCounter <= 0) {
                snake.move();
                checkHeadPosition();
                moveCounter = delay;
            } else {
                moveCounter--;
            }
        }
    }

    private void checkHeadPosition() {
        if (snake.getHead().x < 0) {
            snake.getHead().x = grid.getColumns() - 1;
        }
        if (snake.getHead().x > grid.getColumns() - 1) {
            snake.getHead().x = 0;
        }
        if (snake.getHead().y > grid.getRows() - 1) {
            snake.getHead().y = 0;
        }
        if (snake.getHead().y < 0) {
            snake.getHead().y = grid.getRows() - 1;

        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.score += 50;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            this.grid.setColor(Color.blue);
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            snake.move();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            snake.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            snake.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            snake.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            snake.setDirection(Direction.RIGHT);
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (this.grid != null) {
            this.grid.paintComponent(graphics);

            Point cellLocation;
            graphics.setColor(Color.red);
            if (snake != null) {
                for (int i = 0; i < snake.getBody().size(); i++) {
                    cellLocation = grid.getCellPosition(snake.getBody().get(i));
                    graphics.fillRect(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
                }
            }
        }
        graphics.setFont(new Font("Calbri", Font.ITALIC, 60));
        graphics.drawString("score:" + this.score, 50, 50);
    }
}
