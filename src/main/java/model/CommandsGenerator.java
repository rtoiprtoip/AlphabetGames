package model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CommandsGenerator {
    
    private static final int LETTERS_ON_BOARD = 25;
    private static final int LETTERS_IN_ROW = 5;
    private static final Random RANDOM_GENERATOR = new Random();
    private static final ImmutableList<Character> POSSIBLE_VALUES =
            new ImmutableList.Builder<Character>().add('L', 'P', 'O').build();
    
    List<String> generateData(int numberOfDataSets) {
        return IntStream.range(0, numberOfDataSets)
                .mapToObj(i -> getBoardDataForOnePage())
                .collect(Collectors.toCollection(() -> new ArrayList<>(numberOfDataSets)));
    }
    
    private String getBoardDataForOnePage() {
        while (true) {
            try {
                return tryToGetBoardDataForOnePage();
            } catch (FailedToCreateBoardException ignored) {
            }
        }
    }
    
    private String tryToGetBoardDataForOnePage() throws FailedToCreateBoardException {
        char[] data = new char[LETTERS_ON_BOARD];
        for (int i = 0; i < LETTERS_ON_BOARD; ++i) {
            data[i] = getValueForIndex(data, i);
        }
        return new String(data);
    }
    
    private char getValueForIndex(char[] data, int i) throws FailedToCreateBoardException {
        List<Character> possibleValues = getPossibleValuesForIndex(data, i);
        return possibleValues.get(RANDOM_GENERATOR.nextInt(Integer.MAX_VALUE) % possibleValues.size());
    }
    
    private List<Character> getPossibleValuesForIndex(char[] data, int i) throws FailedToCreateBoardException {
        
        List<Character> possibleValues = new ArrayList<>(POSSIBLE_VALUES);
        
        //the letter one row above
        possibleValues.remove((Character) data[(i + LETTERS_ON_BOARD - LETTERS_IN_ROW) % LETTERS_ON_BOARD]);
        
        //the letter just above
        possibleValues.remove((Character) (char) (i + 'A'));
        
        //three consecutive letters
        if (compareCharactersAtIndices(i - 2, i - 1, data)) {
            possibleValues.remove((Character) data[(i + LETTERS_ON_BOARD - 2) % LETTERS_ON_BOARD]);
        }
        if (compareCharactersAtIndices(i - 1, i + 1, data)) {
            possibleValues.remove((Character) data[(i + LETTERS_ON_BOARD - 1) % LETTERS_ON_BOARD]);
        }
        if (compareCharactersAtIndices(i + 1, i + 2, data)) {
            possibleValues.remove((Character) data[(i + LETTERS_ON_BOARD + 1) % LETTERS_ON_BOARD]);
        }
        
        if (possibleValues.isEmpty()) {
            throw new FailedToCreateBoardException();
        } else {
            return possibleValues;
        }
    }
    
    private boolean compareCharactersAtIndices(int index1, int index2, char[] data) {
        return Objects.equals(data[(index1 + LETTERS_ON_BOARD) % LETTERS_ON_BOARD],
                data[(index2 + LETTERS_ON_BOARD) % LETTERS_ON_BOARD]);
    }
    
    private class FailedToCreateBoardException extends Exception {
        
    }
}
