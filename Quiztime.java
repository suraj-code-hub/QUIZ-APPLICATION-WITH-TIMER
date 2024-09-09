import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiztime extends JFrame implements ActionListener {
    private String[] questions = {
        "1. What is the capital of France?",
        "2. Who is the current president of the United States?",
        "3. What is the largest mammal in the world?",
        "4. Which planet is known as the Red Planet?",
        "5. Who is the author of the book 'To Kill a Mockingbird'?",
        "6. What is the chemical symbol for gold?",
        "7. Which music group's original members were John, Paul, George, and Ringo?",
        "8. What is the smallest country in the world?",
        "9. Who is the main character in the book 'The Catcher in the Rye'?",
        "10. What is the largest living species of lizard?",
        "11. Who is the CEO of Tesla?",
        "12. What is the chemical symbol for silver?",
        "13. Which element is the lightest in the periodic table?"
    };
    private String[][] options = {
        {"A. Paris", "B. London", "C. Berlin", "D. Rome"},
        {"A. Barack Obama", "B. Donald Trump", "C. Joe Biden", "D. George W. Bush"},
        {"A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Lion"},
        {"A. Earth", "B. Mars", "C. Jupiter", "D. Saturn"},
        {"A. F. Scott Fitzgerald", "B. Harper Lee", "C. Jane Austen", "D. J.K. Rowling"},
        {"A. Ag", "B. Au", "C. Hg", "D. Pb"},
        {"A. The Rolling Stones", "B. The Beatles", "C. Queen", "D. Led Zeppelin"},
        {"A. Vatican City", "B. Monaco", "C. Nauru", "D. Tuvalu"},
        {"A. Holden Caulfield", "B. Huckleberry Finn", "C. Tom Sawyer", "D. Scout Finch"},
        {"A. Komodo dragon", "B. Saltwater crocodile", "C. Black mamba", "D. Green anaconda"},
        {"A. Elon Musk", "B. Mark Zuckerberg", "C. Jeff Bezos", "D. Bill Gates"},
        {"A. Ag", "B. Au", "C. Hg", "D. Pb"},
        {"A. Hydrogen", "B. Helium", "C. Oxygen", "D. Nitrogen"}
    };
    private String[] answers = {"A", "C", "B", "B", "B", "B", "B", "A", "A", "A", "A", "A", "A"};
    
    private int currentQuestion = 0;
    private int score = 0;
    private int timer = 90;
    
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JLabel timerLabel;
    
    private Timer countdownTimer;

    public Quiztime() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set up the layout
        setLayout(new BorderLayout());
        
        // Question panel
        JPanel questionPanel = new JPanel(new GridLayout(0, 1));
        questionLabel = new JLabel(questions[currentQuestion]);
        questionPanel.add(questionLabel);
        
        // Options panel
        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton(options[currentQuestion][i]);
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        
        // Timer label
        timerLabel = new JLabel("Time left: " + timer + " seconds");
        add(timerLabel, BorderLayout.NORTH);
        
        // Next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);
        
        // Add question and options to the main frame
        add(questionPanel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        
        // Timer logic
        countdownTimer = new Timer(1000, e -> updateTimer());
        countdownTimer.start();
    }
    
    private void updateTimer() {
        if (timer > 0) {
            timer--;
            timerLabel.setText("Time left: " + timer + " seconds");
        } else {
            countdownTimer.stop();
            JOptionPane.showMessageDialog(this, "Time's up! Your final score is: " + score + "/" + questions.length);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check the selected answer
        String selectedOption = "";
        for (JRadioButton optionButton : optionButtons) {
            if (optionButton.isSelected()) {
                selectedOption = optionButton.getText().substring(0, 1); // Get the first character (A, B, C, or D)
                break;
            }
        }

        if (selectedOption.equalsIgnoreCase(answers[currentQuestion])) {
            score++;
        }

        currentQuestion++;

        // Check if there are more questions
        if (currentQuestion < questions.length) {
            // Update question and options
            questionLabel.setText(questions[currentQuestion]);
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[currentQuestion][i]);
                optionButtons[i].setSelected(false);
            }
        } else {
            countdownTimer.stop();
            JOptionPane.showMessageDialog(this, "Quiz complete! Your final score is: " + score + "/" + questions.length);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Quiztime quizApp = new Quiztime();
            quizApp.setVisible(true);
        });
    }
}
