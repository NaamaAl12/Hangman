// Naama Al-Musawi, CS 145
// Assignment: Evil Hangman

import java.util.*;
public class HangmanManager {
    private List<String> words;
    private int maxGuesses;
    private String currentPattern;
    private SortedSet<Character> guessedLetters;

    /**
     * Full constructor that initializes the game with a list of possible words, word length, and maximum number of incorrect guesses. 
     * @param dictionary, List of possible words.
     * @param length, Desired word length.
     * @param max, Maximum number of incorrect guesses allowed.
     */
    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException("Invalid length or max guesses");
        }

        this.words = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == length) {
                words.add(word);
            }
        }

        this.maxGuesses = max;
        this.currentPattern = repeat("-", length);
        this.guessedLetters = new TreeSet<>();
    }

    /**
     * Returns a set of words that are still possible based on the current state of the game.
     * @return set of possible words.
     */
    public Set<String> words() {
        return new HashSet<>(words);
    }

    /**
     * Returns the number of guesses remaining.
     * @return number of guesses left.
     */
    public int guessesLeft() {
        return maxGuesses;
    }

    /**
     * Returns a set of guessed letters.
     * @return sorted set of guessed letters.
     */
    public SortedSet<Character> guesses() {
        return guessedLetters;
    }

    /**
     * Returns the current pattern. Dashes represent unguessed letters, and non-dash characters are the letters correctly guessed.
     * @return current pattern of guessed and unguessed letters.
     * @throws IllegalStateException if no words are remaining.
     */
    public String pattern() {
        if (words.isEmpty()) {
            throw new IllegalStateException("No words remaining");
        }
        return currentPattern;
    }

    /**
     * Records a guess and updates the game state accordingly.
     * @param guess The letter being guessed.
     * @return the number of occurrences of the guessed letter in the new pattern, or -1 if the guess is incorrect.
     * @throws IllegalStateException if no guesses are left or if no words are remaining.
     * @throws IllegalArgumentException if the letter has already been guessed.
     */
    public int record(char guess) {
        if (maxGuesses < 1 || words.isEmpty()) {
            throw new IllegalStateException("No guesses left or words are empty");
        }

        if (guessedLetters.contains(guess)) {
            throw new IllegalArgumentException("Letter already guessed");
        }

        guessedLetters.add(guess);

        Map<String, Set<String>> families = new TreeMap<>();
        for (String word : words) {
            String newPattern = createPattern(word, guess);
            if (!families.containsKey(newPattern)) {
                families.put(newPattern, new HashSet<>());
            }
            families.get(newPattern).add(word);
        }

        int maxSize = -1;
        String largestPattern = "";
        for (Map.Entry<String, Set<String>> entry : families.entrySet()) {
            if (entry.getValue().size() > maxSize) {
                maxSize = entry.getValue().size();
                largestPattern = entry.getKey();
            }
        }

        words = new ArrayList<>(families.get(largestPattern));
        currentPattern = largestPattern;

        if (!largestPattern.contains(String.valueOf(guess))) {
            maxGuesses--;
        }

        int count = 0;
        for (char c : largestPattern.toCharArray()) {
            if (c == guess)
                count++;
        }
        return count;
    }

    /**
     * Helper method to repeat a string a certain number of times.
     * @param str, The string to repeat.
     * @param count, The number of times to repeat.
     * @return the resulting repeated string.
     */
    public static String repeat(String str, int count) {
        StringBuilder result = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            result.append(str);
        }
        return result.toString();
    }

    /**
     * Helper method to create a new pattern string based on a word and the guessed letter.
     * @param word, The word to use for creating the pattern.
     * @param guess, The guessed letter.
     * @return the new pattern string.
     */
    public String createPattern(String word, char guess) {
        char[] patternChars = currentPattern.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                patternChars[i] = guess;
            }
        }
        return new String(patternChars);
    }
}