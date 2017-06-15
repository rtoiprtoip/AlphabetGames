package view.impl;

import controller.UserSettingsDto;
import lombok.Getter;

import javax.swing.*;
import java.io.File;

class MainPanel {
    
    @Getter
    private JPanel mainPanel;
    @Getter
    private JButton okButton;
    private JTextField color1Field;
    private JTextField color2Field;
    private JTextField noPagesField;
    private JButton chooseFileButton;
    private JTextField fileNameField;
    
    UserSettingsDto getSettings() {
        int pages = Integer.parseInt(noPagesField.getText());
        File file = new File(fileNameField.getText());
        return new UserSettingsDto(
                pages, file, null, null
        );
    }
}
