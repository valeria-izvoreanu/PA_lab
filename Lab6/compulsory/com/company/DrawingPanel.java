package com.company;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.awt.Color.BLACK;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image;
    Graphics2D graphics;

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
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        Random rand = new Random();
        int radius = (int) frame.configPanel.getSizeField().getValue();
        int sides = (int) frame.configPanel.getSidesField().getValue();

        Color colorStroke = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        graphics.setPaint(colorStroke);
        if(frame.configPanel.getStrokeCombo().getSelectedItem().toString().equals("Dashed")){
            float[] dash1 = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(5.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
            graphics.setStroke(dashed);
        }else {
            graphics.setStroke(new BasicStroke(5.0f));
        }
        graphics.draw(new RegularPolygon(x, y, radius, sides));

        if(frame.configPanel.getColorCombo().getSelectedItem().toString().equals("Black")){
            graphics.setColor(BLACK);
        }else {
            Color color = new ColorUIResource(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            graphics.setColor(color);
        }

        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
