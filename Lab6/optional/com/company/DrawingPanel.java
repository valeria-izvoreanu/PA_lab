package com.company;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.BLACK;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image;
    Graphics2D graphics;
    //remember shape and shape characteristics
    ArrayList<Shape> shapes = new ArrayList<>();
    ArrayList<Color> shapeColors = new ArrayList<>();
    ArrayList<Color> strokeColors = new ArrayList<>();
    ArrayList<BasicStroke> strokesTypes = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //if delete button is not selected draw shape according to type
                if (!frame.shapePanel.deleteButton.isSelected()) {
                    if (frame.shapePanel.getShapeCombo().getSelectedItem().toString().equals("Polygon")) {
                        drawPolygon(e.getX(), e.getY());
                    } else if (frame.shapePanel.getShapeCombo().getSelectedItem().toString().equals("Circle")) {
                        drawCircle(e.getX(), e.getY());
                    } else {
                        drawParallelogram(e.getX(), e.getY());
                    }
                } else {
                    //else find last shape that contains coordinates and delete it with all it's characteristics
                    for (int i = shapes.size() - 1; i >= 0; i--) {
                        if (shapes.get(i).contains(e.getX(), e.getY())) {
                            shapes.remove(i);
                            shapeColors.remove(i);
                            strokeColors.remove(i);
                            strokesTypes.remove(i);
                            break;
                        }
                    }
                }
                repaint();
            }
        });
    }

    private void drawPolygon(int x, int y) {
        Random rand = new Random();
        int radius = (int) frame.shapePanel.getConfigPanel().getSizeField().getValue();
        int sides = (int) frame.shapePanel.getConfigPanel().getSidesField().getValue();

        //set stroke type and colour
        Color colorStroke = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        strokeColors.add(colorStroke);
        if (frame.shapePanel.getConfigPanel().getStrokeCombo().getSelectedItem().toString().equals("Dashed")) {
            float[] dash1 = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(5.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
            strokesTypes.add(dashed);
        } else {
            strokesTypes.add(new BasicStroke(5.0f));
        }
        RegularPolygon polygon = new RegularPolygon(x, y, radius, sides);
        shapes.add(polygon);
        //set color
        if (frame.shapePanel.getConfigPanel().getColorCombo().getSelectedItem().toString().equals("Black")) {
            shapeColors.add(BLACK);
        } else {
            Color color = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            shapeColors.add(color);
        }
    }

    private void drawCircle(int x, int y) {
        Random rand = new Random();
        int radius = (int) frame.shapePanel.getConfigPanel().getSizeField().getValue();

        Color colorStroke = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        strokeColors.add(colorStroke);
        if (frame.shapePanel.getConfigPanel().getStrokeCombo().getSelectedItem().toString().equals("Dashed")) {
            float[] dash1 = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(5.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
            strokesTypes.add(dashed);
        } else {
            strokesTypes.add(new BasicStroke(5.0f));
        }
        Circle circle = new Circle(x, y, radius);
        shapes.add(circle);
        if (frame.shapePanel.getConfigPanel().getColorCombo().getSelectedItem().toString().equals("Black")) {
            shapeColors.add(BLACK);
        } else {
            Color color = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            shapeColors.add(color);
        }
    }

    private void drawParallelogram(int x, int y) {
        Random rand = new Random();
        int size = (int) frame.shapePanel.getConfigPanel().getSizeField().getValue();
        int direction = (int) frame.shapePanel.getConfigPanel().getDirectionField().getValue();

        Color colorStroke = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        strokeColors.add(colorStroke);
        if (frame.shapePanel.getConfigPanel().getStrokeCombo().getSelectedItem().toString().equals("Dashed")) {
            float[] dash1 = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(5.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
            strokesTypes.add(dashed);
        } else {
            strokesTypes.add(new BasicStroke(5.0f));
        }
        Parallelogram parallelogram = new Parallelogram(x, y, size, direction);
        shapes.add(parallelogram);
        if (frame.shapePanel.getConfigPanel().getColorCombo().getSelectedItem().toString().equals("Black")) {
            shapeColors.add(BLACK);
        } else {
            Color color = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            shapeColors.add(color);
        }
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, W, H);
        //draw each shape
        for (int i = 0; i < shapes.size(); i++) {
            g2d.setPaint(strokeColors.get(i));
            g2d.setStroke(strokesTypes.get(i));
            g2d.draw(shapes.get(i));
            g2d.setColor(shapeColors.get(i));
            g2d.fill(shapes.get(i));
        }
    }
}
