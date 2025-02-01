import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addNumbersCorrected {
    //Initialize sum and count to zero to start
    private static int count = 0; 

    //Use StringBuilder to store 5 numbers  
    private static StringBuilder enteredNumbers = new StringBuilder(); 

    public static void main(String[] args) {
        
        //Build Frame
        JFrame numFrame = new JFrame("Add 5 Numbers");
        numFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        numFrame.setSize(825, 125); 

        //Container with yellow background and border layout to hold panel
        JPanel numContainer = new JPanel(new BorderLayout(10, 10));
        numContainer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        numContainer.setBackground(Color.YELLOW);

        //Used Flowlayout for a simple panel
        JPanel numPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        numPanel.setBackground(Color.YELLOW);

        numContainer.add(numPanel, BorderLayout.CENTER);
        numFrame.add(numContainer);

        //Implement components
        numGUI(numPanel);

        //Show frame
        numFrame.setVisible(true);
    }

    private static void numGUI(JPanel numPanel) {
        //Enter number s label
        JLabel numLabel = new JLabel("Enter 5 numbers:");
        numLabel.setFont (new Font("Arial", Font.BOLD, 14)); 
        numPanel.add(numLabel);

        //Input field for numbers
        JTextField numField = new JTextField(8); 
        numField.setBackground(Color.WHITE);
        numField.setPreferredSize(new Dimension(100, 25)); 
        numPanel.add(numField);

        //Button for sum, set to blue
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);

        addButton.setPreferredSize(new Dimension(80, 25)); 
        numPanel.add(addButton);

        //Input numbers label
        JLabel enteredNumbersLabel = new JLabel("Numbers: ");
        enteredNumbersLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        numPanel.add(enteredNumbersLabel);

        //Number display
        JLabel enteredNumbersDisplay = new JLabel("");
        numPanel.add(enteredNumbersDisplay);

        //Label for sum
        JLabel numResults = new JLabel("");
        numPanel.add(numResults);

        //Add numbers and clear field for next using listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputNumber  = Double.parseDouble(numField.getText());
                    numField.setText(""); 
                    addNumInput(inputNumber, numResults, enteredNumbersDisplay);

                //Error Handling for non-number input
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(numPanel, "Please only enter numbers.");
                }
            }
        });
    }

    private static void addNumInput(double inputNumber, JLabel numResults, JLabel enteredNumbersDisplay) {
        
        //Add input to list
        if (enteredNumbers.length() > 0) {
            enteredNumbers.append(", ");
        }
        enteredNumbers.append(String.format("%.2f", inputNumber));

        enteredNumbersDisplay.setText(enteredNumbers.toString());//Update the display with entered numbers
        count++;//Increment count

        //Check for 5 nmbers
        if (count < 5) {
            numResults.setText("Number " + count + " added.");
        } else {

            //Call recursive function for sum
            double sum = recursiveSumCalc(0.0, 0);
            numResults.setText("Sum of Numbers: " + String.format("%.2f", sum)); 
        }}

    //Recursive function for sum
    private static double recursiveSumCalc(double currentSum, int index) {
        //Recursive Base case to stop if 5 numbers have been processd
        if (index >= count) {
            return currentSum;
        }

        //Get the current number from the enteredNumbers
        String[] numbers = enteredNumbers.toString().split(", ");//toString for array of numbers
        double number = Double.parseDouble(numbers[index]); 

        //Recursive function call to add the number and move to the next
        return recursiveSumCalc(currentSum + number, index + 1);
    }
}