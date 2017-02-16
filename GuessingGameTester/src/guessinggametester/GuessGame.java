/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessinggametester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessGame extends JFrame {

    private JTextField userInput;
    private JLabel comment = new JLabel("What is your destiny?");
    private JLabel comment2 = new JLabel(" ");

    private int randomNumber;

    public GuessGame() {
        super("Guessing Game!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creates components
        JButton newButton = new JButton("New Game");
        JButton quitButton = new JButton("Quit");
        JLabel prompt1 = new JLabel("I have a number between 1 and 1000.");
        JLabel prompt2 = new JLabel("Can you guess my number? Enter your Guess:");

        comment = new JLabel("Keep Trying?");
        comment2 = new JLabel(" ");
        userInput = new JTextField(5);

        //content pane
        Container c = getContentPane();
        setLayout(new FlowLayout());

        //adding component to the pane
        c.add(prompt1);
        c.add(prompt2);
        c.add(userInput);
        c.add(comment2);
        c.add(newButton);
        c.add(quitButton);
        c.add(comment);

        newButton.setMnemonic('G');
        quitButton.setMnemonic('Q');

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        initializeNumber();

        //creating the handler
        GuessButtonHandler ghandler = new GuessButtonHandler(); //instantiate new object
        userInput.addActionListener(ghandler); // add event listener

        actionListener listener = new actionListener();
        newButton.addActionListener(listener);

        QuitButtonHandler qhandler = new QuitButtonHandler();
        quitButton.addActionListener(qhandler);
    }
    //Generate random number
    private void initializeNumber() {
        randomNumber = new Random().nextInt(1000) + 1;
    }
    //Used to quit project
    class QuitButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    class GuessButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int getUserInput;
            int diff;
            int Difference;
            try {
                getUserInput = Integer.parseInt(userInput.getText().trim());
                if (getUserInput == randomNumber) {
                    JOptionPane.showMessageDialog(null, "CONGRATULATIONS! You got it!!",
                            "Random Number: " + randomNumber,
                            JOptionPane.INFORMATION_MESSAGE);
                    initializeNumber();
                    return;
                }
                if (getUserInput > randomNumber) {
                    comment.setText("Too High. Try a lower number.");
                    diff = getUserInput - randomNumber;
                    Difference = Math.abs(diff);
                } else {
                    comment.setText("Too Low. Try a higher number.");
                    diff = randomNumber - getUserInput;
                    Difference = Math.abs(diff);
                }

                if (Difference >= 100) {
                    comment2.setText("Cold");
                    GuessGame.this.setBackgroundColor(Color.blue);
                }

                if (Difference <= 100) {
                    comment2.setText("Warm");
                    GuessGame.this.setBackgroundColor(Color.red);
                }
            } catch (NumberFormatException ex) {
                comment.setText("Enter a VALID number!");
            }
        }

    }

    
    private void setBackgroundColor(Color color) {
        getContentPane().setBackground(color);
    }

class actionListener implements ActionListener {
        //Default color is set to white
        public void actionPerformed(ActionEvent e) {
            getContentPane().setBackground(Color.WHITE);
            initializeNumber();
            }
        }
    }
