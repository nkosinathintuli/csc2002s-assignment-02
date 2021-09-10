# csc2002s-assignment-02

## Overview
The following repo contains the source code for the WordApp(Falling Words) Game.

## Guidelines

1. Clone this repository.

2. Go to main directory and run the Makefile to compile the java files.

3. After compiling you can run the WordApp program from a terminal using this command $ make run.

4. The $ make run has an option input param to include 3 params as follows, the total number of words for the game, the number of word faliing on the current screen, the text file containing the words e.g. 
```bash
	make run input="20 3 example_dict.txt".
```

5. Once the WordApp app starts, click on Start button, which should start start the game by releasing the falling words.  

4. You can then play the game by type the word falling on the screen, you get a point for typing the word before it reaches the bottom.

5. Depending on the number you entered for the total number of words, the game will end after that number of words has passed through the screen or matched.

6. Click "End" button to end the game. 

7. Click "Quit" button to quit the game and close the program window. 
