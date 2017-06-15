package model;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Works for A4 sheet only
 */
@AllArgsConstructor
class PdfCreator {
    
    private static final String LETTERS = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
    private static final int LETTER_SIZE = 80;
    private static final int COMMAND_SIZE = 40;
    private static final PDFont FONT = PDType1Font.HELVETICA_BOLD;
    private static final double PAGE_WIDTH = new PDPage().getMediaBox().getWidth();
    
    private final Color letterColor;
    private final Color commandColor;
    
    PDDocument createPdf(List<String> commandSets) throws IOException {
        PDDocument document = new PDDocument();
        for (String commandSet : commandSets) {
            document.addPage(createPage(document, commandSet));
        }
        return document;
    }
    
    private PDPage createPage(PDDocument document, String commandSet) throws IOException {
        PDPage page = new PDPage();
        
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.newLineAtOffset(0, 700);
        contentStream.setLeading(50);
        
        for (int i = 0; i < LETTERS.length(); i += 5) {
            contentStream.setFont(FONT, LETTER_SIZE);
            contentStream.setNonStrokingColor(letterColor);
            writeLetterRow(contentStream, LETTERS.substring(i, i + 5));
            contentStream.newLine();
            
            contentStream.setFont(FONT, COMMAND_SIZE);
            contentStream.setNonStrokingColor(commandColor);
            writeCommandsRow(contentStream, commandSet.substring(i, i + 5));
            contentStream.newLine();
            contentStream.newLine();
        }
        
        contentStream.endText();
        contentStream.close();
        
        return page;
    }
    
    private void writeLetterRow(PDPageContentStream contentStream, String line) throws IOException {
        writeRowOfLettersOfSize(contentStream, line, LETTER_SIZE);
    }
    
    private void writeCommandsRow(PDPageContentStream contentStream, String line) throws IOException {
        writeRowOfLettersOfSize(contentStream, line, COMMAND_SIZE);
    }
    
    private void writeRowOfLettersOfSize(PDPageContentStream contentStream, String line, int fontSize)
    throws IOException {
        line = " " + line + " ";
        double size = fontSize * FONT.getStringWidth(line) / 1000;
        double freeSpace = PAGE_WIDTH - size;
        double charSpacing = freeSpace / (line.length() - 1);
        //noinspection deprecation
        contentStream.appendRawCommands(String.format("%f Tc\n", charSpacing).replace(',', '.'));
        contentStream.showText(line);
    }
}
