package numberguessinggame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NumberguessingGame {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Word Guessing Game");
        frame.setSize(480, 300);
        frame.setLocation(530, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel as the content pane for the background
       
            
            JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background color or draw an image here
                g.setColor(new Color(173, 216, 230)); // Light blue color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
        
        
        
        int lowerBound = 1;
        int upperBound = 100;
        int numberToGuess = generateRandomNumber(lowerBound, upperBound);
        int maxAttempts = 10;
        int attempts = 0;
        int score = 0;
               
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + lowerBound + " and " + upperBound + ". Try to guess it!");
        
        frame.add(backgroundPanel);
  frame.setVisible(true);


        while (attempts < maxAttempts) {
            int userGuess = getUserGuess(lowerBound, upperBound);
             if (userGuess == -1) {
                 JOptionPane.showMessageDialog(null,"Goodbye! Thanks for playing.");
                
                System.out.println("Goodbye! Thanks for playing.");
                frame.setVisible(false);
                break;
            }
            
            
            attempts++;

            if (userGuess == numberToGuess) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + attempts + " attempts.");
                
                score += (maxAttempts - attempts) * 10;
                break;
            } else if (userGuess < numberToGuess) {
                JOptionPane.showMessageDialog(null, "Try a higher number.");
                System.out.println("Try a higher number.");
            } else {
                 JOptionPane.showMessageDialog(null, "Try a lower number.");
                System.out.println("Try a lower number.");
            }

            if (attempts == maxAttempts) {
                JOptionPane.showMessageDialog(null, "Out of attempts! The number was " + numberToGuess + ".");
                System.out.println("Out of attempts! The number was " + numberToGuess + ".");
            }
            
                 

        }

        System.out.println("Your score: " + score);
         frame.setVisible(false);
       
    }
     
    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    
   
    
    private static int getUserGuess(int lowerBound, int upperBound) {
        String input = JOptionPane.showInputDialog("Enter your guess between " + lowerBound + " and " + upperBound + ":");
        try {
            int guess = Integer.parseInt(input);
            if (guess == -1) {
                return -1;
            }
            if (guess < lowerBound || guess > upperBound) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number between " + lowerBound + " and " + upperBound + ".");
                return getUserGuess(lowerBound, upperBound);
            }
            return guess;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number. If you want to exit, write -1");
            
            return getUserGuess(lowerBound, upperBound);
    
        }
        
    }
}
