package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapePanel extends JPanel implements ActionListener {
    final MainFrame frame;
    JLabel label;
    JComboBox<String> shapeCombo;//choose shape type
    ConfigPanel configPanel;//panel that changes according to type
    JRadioButton deleteButton;

    public ShapePanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Choose Shape");

        shapeCombo = new JComboBox<>();
        shapeCombo.addItem("Polygon");
        shapeCombo.addItem("Circle");
        shapeCombo.addItem("Parallelogram");

        deleteButton = new JRadioButton();
        deleteButton.setText("Delete");

        add(label);
        add(shapeCombo);
        add(deleteButton);

        shapeCombo.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        JComboBox shCombo = (JComboBox) e.getSource();
        String shapeType = (String) shCombo.getSelectedItem();
        //if shape type is changed change configuration panel
        addConfigPanel(shapeType);
    }

    public ShapePanel addConfigPanel(String type) {
        if (configPanel != null) {
            this.remove(configPanel);
        }
        configPanel = new ConfigPanel(this.frame, type);
        this.add(configPanel);
        revalidate();
        repaint();
        return this;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public JComboBox<String> getShapeCombo() {
        return shapeCombo;
    }
}
