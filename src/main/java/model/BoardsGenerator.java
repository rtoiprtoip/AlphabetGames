package model;

import controller.UserSettingsDto;
import lombok.experimental.UtilityClass;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class BoardsGenerator {
    
    public static void generateAndSavePdf(UserSettingsDto settings) throws FileNotFoundException {
        List<String> commandSets = new CommandsGenerator().generateData(settings.getNumberOfDataSets());
        
        try {
            PDDocument document = new PdfCreator(settings.getLettersColor(), settings.getCommandsColor())
                    .createPdf(commandSets);
            document.save(settings.getOutputFile());
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
