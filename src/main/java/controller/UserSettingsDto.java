package controller;

import lombok.Value;

import java.awt.*;
import java.io.File;

@Value
public class UserSettingsDto {
    
    int numberOfDataSets;
    File outputFile;
    Color lettersColor;
    Color commandsColor;
}
