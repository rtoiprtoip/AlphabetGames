package view.impl;

import controller.UserSettingsDto;
import lombok.Getter;

import javax.swing.*;

class MainPanel {
    
    @Getter
    private JPanel mainPanel;
    @Getter
    private JButton okButton;
    private JTextField color1Field;
    private JTextField color2Field;
    private JTextField noPagesField;
    private JButton chooseFileButton;
    
    UserSettingsDto getSettings() {
        int pages = Integer.parseInt(noPagesField.getText());
        return new UserSettingsDto(
                pages, null, null, null
        );
    }
}
