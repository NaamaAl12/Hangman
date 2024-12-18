# 👻 Evil Hangman Game

This project is a Java-based console application that plays a deceptive version of the classic Hangman game, nicknamed **"Evil Hangman."** The game dynamically adjusts the word list during play to maximize the user's difficulty, delaying the final word selection until necessary.

---

## **Features**

1. **Dynamic Word Management**:
   - Adapts the word list in real-time based on user guesses.
   - Keeps as many word options open as possible, making it harder for the player to guess correctly.

2. **Interactive Console Game**:
   - User-friendly menu for setting word length and allowed wrong guesses.
   - Displays the current pattern, guessed letters, and remaining guesses.

3. **Debug Mode**:
   - Optional feature to display the number of possible words left after each guess for development or testing purposes.

4. **Robust Error Handling**:
   - Validates input for word length, number of allowed guesses, and repeated guesses.

---

## **How It Works**

### 🎯 **Gameplay**
- The player selects a word length and the maximum number of incorrect guesses.
- The program:
  1. Chooses a possible word list from a provided dictionary.
  2. Dynamically shifts the current word pattern to avoid being guessed.
- The player guesses letters, and the program updates the word pattern or deducts guesses if incorrect.

### 🛠 **Cheating Mechanism**
- Instead of selecting a single word upfront, the program keeps shifting its "target word" by reorganizing the word list into **families** based on the guessed letter.
- The largest family is selected to maximize the chance of avoiding correct guesses.

---

## **Requirements**

1. **Java Development Kit (JDK)** 8 or higher.
2. **Dictionary File**:
   - A text file (`dictionary.txt`) with one word per line.

---

## **How to Run**

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/EvilHangman.git
   cd EvilHangman
