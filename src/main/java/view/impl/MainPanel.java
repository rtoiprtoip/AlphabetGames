package view.impl;

import controller.UserSettingsDto;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static java.awt.Color.*;

class MainPanel {
    
    @Getter
    private JPanel mainPanel;
    @Getter
    private JButton okButton;
    private JTextField noPagesField;
    private JButton chooseFileButton;
    private JTextField fileNameField;
    private JComboBox<Color> color1Field;
    private JComboBox<Color> color2Field;
    
    UserSettingsDto getSettings() {
        int pages = Integer.parseInt(noPagesField.getText());
        File file = new File(fileNameField.getText());
        Color lettersColor = (Color) color1Field.getSelectedItem();
        Color commandsColor = (Color) color2Field.getSelectedItem();
        return new UserSettingsDto(
                pages, file, lettersColor, commandsColor
        );
    }
    
    private void createUIComponents() {
        color1Field = createColorPicker();
        color2Field = createColorPicker();
    }
    
    private JComboBox<Color> createColorPicker() {
        Color[] colors = {BLACK, RED, PINK, ORANGE, GREEN, MAGENTA, BLUE};
        JComboBox<Color> comboBox = new JComboBox<>(colors);
        comboBox.setRenderer(new ColorPickerRenderer());
        return comboBox;
    }
    
    private class ColorPickerRenderer extends JButton implements ListCellRenderer<Color> {
        
        boolean enableSetBackground = false;
        
        @Override
        public Component getListCellRendererComponent(
                JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {
            
            enableSetBackground = true;
            setText(" ");
            setBackground(value);
            enableSetBackground = false;
            return this;
        }
        
        @Override
        public void setBackground(Color bg) {
            if (enableSetBackground) {
                super.setBackground(bg);
            }
        }
    }
}
