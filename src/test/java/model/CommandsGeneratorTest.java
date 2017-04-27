package model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CommandsGeneratorTest {
    
    private final CommandsGenerator commandsGenerator = new CommandsGenerator();
    
    @Test
    public void test() {
        commandsGenerator.generateData(50).forEach(this::testData);
    }
    
    private void testData(String dataSetAsString) {
        char[] dataSetAsArray = dataSetAsString.toCharArray();
        testIfLettersAreGenerallyCorrect(dataSetAsArray);
        testIfNoCommandIsUnderItsCorrespondingLetter(dataSetAsArray);
        testIfThereAreNoThreeSameConsecutiveCommands(dataSetAsArray);
        testIfThereAreNoTwoSameCommandsOneUnderTheOther(dataSetAsArray);
    }
    
    private void testIfLettersAreGenerallyCorrect(char[] dataSet) {
        for (char c : dataSet) {
            assertTrue(Arrays.toString(dataSet), c == 'L' || c == 'P' || c == 'O');
        }
    }
    
    private void testIfNoCommandIsUnderItsCorrespondingLetter(char[] dataSet) {
        assertNotEquals(Arrays.toString(dataSet), (long) dataSet['L' - 'A'], (long) 'L');
        assertNotEquals(Arrays.toString(dataSet), (long) dataSet['O' - 'A'], (long) 'O');
        assertNotEquals(Arrays.toString(dataSet), (long) dataSet['P' - 'A'], (long) 'P');
    }
    
    private void testIfThereAreNoThreeSameConsecutiveCommands(char[] dataSet) {
        char lastCharacter = dataSet[0];
        int count = 1;
        for (int i = 1; i < dataSet.length + 2; ++i) {
            if (dataSet[i % dataSet.length] == lastCharacter) {
                ++count;
            } else {
                count = 0;
                lastCharacter = dataSet[i % dataSet.length];
            }
            assertTrue(Arrays.toString(dataSet) + i, count < 3);
        }
        
    }
    
    private void testIfThereAreNoTwoSameCommandsOneUnderTheOther(char[] dataSet) {
        for (int i = 0; i < dataSet.length - 5; ++i) {
            assertNotEquals(Arrays.toString(dataSet) + i, dataSet[i], dataSet[(i + 5) % dataSet.length]);
        }
    }
    
}