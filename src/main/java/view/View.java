package view;

import controller.UserSettingsDto;

import java.awt.event.ActionListener;

public interface View {
    
    UserSettingsDto getSettings();
    
    void displayResult(String message);
    
    void addCreatePdfListener(ActionListener listener);
    
    void addCloseResultMessageListener(ActionListener listener);
}
