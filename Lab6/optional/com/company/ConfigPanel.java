package com.company;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JLabel labelSize;
    JSpinner sidesField; // number of sides
    JSpinner sizeField; // shape size
    JComboBox<String> colorCombo; // the color of the shape
    JComboBox<String> strokeCombo; //the stroke of the shape
    JSpinner directionField;

    public ConfigPanel(MainFrame frame, String type) {
        this.frame = frame;
        if (type.equals("Polygon")) {
            initPolygon();
        } else if (type.equals("Circle")) {
            initCircle();
        }else{
            initParallelogram();
        }
    }

    private void initCircle() {

        addSizeComponent();
        addColorComponent();
        addStrokeComponent();
        revalidate();
        repaint();
    }

    private void initPolygon() {

        addSidesComponent();
        addSizeComponent();
        addColorComponent();
        addStrokeComponent();

        revalidate();
        repaint();
    }

    private void initParallelogram(){
        addSizeComponent();
        addColorComponent();
        addStrokeComponent();
        addDirectionComponent();

        revalidate();
        repaint();
    }

    public void addSizeComponent() {
        labelSize = new JLabel("Size:");
        sizeField = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));
        sizeField.setValue(50);
        add(labelSize);
        add(sizeField);
    }

    public void addSidesComponent() {
        label = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);
        add(label);
        add(sidesField);
    }

    public void addColorComponent() {
        colorCombo = new JComboBox<>();
        colorCombo.addItem("Black");
        colorCombo.addItem("Random");
        add(colorCombo);
    }

    public void addStrokeComponent() {
        strokeCombo = new JComboBox<>();
        strokeCombo.addItem("Solid");
        strokeCombo.addItem("Dashed");
        add(strokeCombo);
    }

    public void addDirectionComponent(){
        //add parallelogram type if -1 then parallelogram is leaning right
        //if 1 left and if it is 0 it's a rectangle
        label = new JLabel("Leaning Direction:");
        directionField = new JSpinner(new SpinnerNumberModel(0, -1, 1, 1));
        add(label);
        add(directionField);
    }

    public JSpinner getSidesField() {
        return sidesField;
    }

    public JSpinner getSizeField() {
        return sizeField;
    }

    public JComboBox<String> getColorCombo() {
        return colorCombo;
    }

    public JComboBox<String> getStrokeCombo() {
        return strokeCombo;
    }

    public JSpinner getDirectionField() {
        return directionField;
    }
}