package com.company;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JLabel labelSize;
    JSpinner sidesField; // number of sides
    JSpinner sizeField; // shape size
    JComboBox<String> colorCombo; // the color of the shape
    JComboBox<String> strokeCombo; //the stroke of the shape

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        label = new JLabel("Number of sides:");

        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);

        sizeField = new JSpinner(new SpinnerNumberModel(0, 0, 300, 1));
        sizeField.setValue(50);

        colorCombo = new JComboBox<>();
        colorCombo.addItem("Black");
        colorCombo.addItem("Random");

        strokeCombo = new JComboBox<>();
        strokeCombo.addItem("Solid");
        strokeCombo.addItem("Dashed");

        labelSize = new JLabel("Size:");

        add(label);
        add(sidesField);
        add(labelSize);
        add(sizeField);
        add(colorCombo);
        add(strokeCombo);
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
}