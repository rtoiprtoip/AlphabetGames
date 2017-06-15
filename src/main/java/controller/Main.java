package controller;

import lombok.AllArgsConstructor;
import model.BoardsGenerator;
import view.View;
import view.impl.SwingView;

import java.io.FileNotFoundException;

@AllArgsConstructor
class Main {
    
    private final View view;
    
    private void run() {
        view.addCreatePdfListener(event -> {
            UserSettingsDto settings = view.getSettings();
            try {
                new BoardsGenerator(settings.getNumberOfDataSets(), settings.getOutputFile(),
                        settings.getLettersColor(), settings.getCommandsColor())
                        .generateAndSavePdf();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    
    public static void main(String[] args) {
        new Main(new SwingView()).run();
    }
}
