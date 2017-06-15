package view.impl;

import lombok.Getter;

import javax.swing.*;

class MainPanel {
    
    @Getter
    private JPanel mainPanel;
    private JButton okButton;
    private JTextField color1Field;
    private JTextField color2Field;
    private JTextField noPagesField;
    private JButton chooseFileButton;
}
