package view.impl;

import controller.UserSettingsDto;
import view.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SwingView implements View {
    
    private JFrame mainFrame;
    
    public SwingView(){
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }
    
    private void createAndShowGUI() {
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.pack();
    }
    
    @Override
    public UserSettingsDto getSettings() {
        //TODO: auto-generated method stub
        return null;
    }
    
    @Override
    public void displayResult(String message) {
        //TODO: auto-generated method stub
        
    }
    
    @Override
    public void addCreatePdfListener(ActionListener listener) {
        //TODO: auto-generated method stub
        
    }
    
    @Override
    public void addCloseResultMessageListener(ActionListener listener) {
        //TODO: auto-generated method stub
        
    }
}
