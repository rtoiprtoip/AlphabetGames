package model;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class BoardsGenerator {
    
    private final int numberOfDataSets;
    private final File outputFile;
    private final Color lettersColor;
    private final Color commandsColor;
    
    public void generateAndSavePdf() throws FileNotFoundException {
        List<String> commandSets = new CommandsGenerator().generateData(numberOfDataSets);
        
        try {
            PDDocument document = new PdfCreator(lettersColor, commandsColor).createPdf(commandSets);
            document.save(outputFile);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
