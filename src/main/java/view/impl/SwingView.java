package view.impl;

import controller.UserSettingsDto;
import view.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SwingView implements View {
    
    private JFrame mainFrame;
    private MainPanel mainPanel;
    
    public SwingView() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }
    
    private void createAndShowGUI() {
        mainFrame = new JFrame();
        mainPanel = new MainPanel();
        
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel.getMainPanel());
        mainFrame.pack();
    }
    
    @Override
    public UserSettingsDto getSettings() {
        return mainPanel.getSettings();
    }
    
    @Override
    public void displayResult(String message) {
        //TODO: auto-generated method stub
        
    }
    
    @Override
    public void addCreatePdfListener(ActionListener listener) {
        SwingUtilities.invokeLater(() -> mainPanel.getOkButton().addActionListener(listener));
    }
    
    @Override
    public void addCloseResultMessageListener(ActionListener listener) {
        //TODO: auto-generated method stub
        
    }
}
