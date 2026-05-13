import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdvancedQuizApp extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton op1, op2, op3, op4;
    JButton nextButton;
    ButtonGroup group;

    int currentQuestion = 0;
    int score = 0;

    String[] questions = {
            "1. Which language is used for Android Development?",
            "2. Which keyword is used for inheritance in Java?",
            "3. Which method is entry point in Java?",
            "4. HTML stands for?",
            "5. SQL is used for?",
            "6. Which company developed Java?",
            "7. Which symbol is used for comments in Java?",
            "8. CSS is used for?",
            "9. Java is a ___ language.",
            "10. Which keyword creates object?",
            "11. JVM stands for?",
            "12. Which operator compares values?",
            "13. Which collection stores unique values?",
            "14. Which loop executes at least once?",
            "15. Which keyword stops loop?",
            "16. Java supports ___ programming.",
            "17. Which package contains Scanner class?",
            "18. Which database is popular with Java?",
            "19. Which HTML tag creates link?",
            "20. Which method prints output in Java?"
    };

    String[][] options = {
            {"Python", "Java", "PHP", "C"},
            {"extends", "implement", "super", "this"},
            {"run()", "main()", "start()", "init()"},
            {"Hyper Text Markup Language", "High Text Language", "Hyperlinks", "None"},
            {"Design", "Database", "Drawing", "Gaming"},
            {"Microsoft", "Sun Microsystems", "Google", "Apple"},
            {"//", "##", "<!--", "**"},
            {"Styling", "Database", "Programming", "Server"},
            {"Procedural", "Object Oriented", "Markup", "Machine"},
            {"new", "create", "class", "this"},
            {"Java Virtual Machine", "Java Variable Method", "Joint VM", "None"},
            {"=", "==", "+", "%"},
            {"ArrayList", "HashSet", "Vector", "LinkedList"},
            {"for", "while", "do-while", "switch"},
            {"continue", "break", "return", "exit"},
            {"OOP", "Machine", "Assembly", "Binary"},
            {"java.util", "java.awt", "java.io", "java.sql"},
            {"MySQL", "Paint", "Photoshop", "Excel"},
            {"<a>", "<p>", "<h1>", "<img>"},
            {"System.out.println()", "Scanner", "print()", "display()"}
    };

    int[] answers = {
            1,0,1,0,1,1,0,0,1,0,
            0,1,1,2,1,0,0,0,0,0
    };

    public AdvancedQuizApp() {

        setTitle("Advanced Online Quiz Application");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 600, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();

        op1.setBounds(70, 120, 300, 30);
        op2.setBounds(70, 170, 300, 30);
        op3.setBounds(70, 220, 300, 30);
        op4.setBounds(70, 270, 300, 30);

        group = new ButtonGroup();
        group.add(op1);
        group.add(op2);
        group.add(op3);
        group.add(op4);

        nextButton = new JButton("Next");
        nextButton.setBounds(250, 350, 120, 40);
        nextButton.addActionListener(this);

        add(questionLabel);
        add(op1);
        add(op2);
        add(op3);
        add(op4);
        add(nextButton);

        loadQuestion();

        setVisible(true);
    }

    void loadQuestion() {

        if (currentQuestion < questions.length) {

            questionLabel.setText(questions[currentQuestion]);

            op1.setText(options[currentQuestion][0]);
            op2.setText(options[currentQuestion][1]);
            op3.setText(options[currentQuestion][2]);
            op4.setText(options[currentQuestion][3]);

            group.clearSelection();

        } else {
            showResult();
        }
    }

    public void actionPerformed(ActionEvent e) {

        int selected = -1;

        if (op1.isSelected()) selected = 0;
        if (op2.isSelected()) selected = 1;
        if (op3.isSelected()) selected = 2;
        if (op4.isSelected()) selected = 3;

        if (selected == answers[currentQuestion]) {
            score++;
        }

        currentQuestion++;
        loadQuestion();
    }

    void showResult() {

        JFrame resultFrame = new JFrame("Quiz Result");
        resultFrame.setSize(600, 500);
        resultFrame.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel(
                "Your Score: " + score + " / " + questions.length,
                SwingConstants.CENTER
        );

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel chartPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int correct = score;
                int wrong = questions.length - score;

                int total = correct + wrong;

                int correctAngle = (int) Math.round(360.0 * correct / total);

                g.setColor(Color.GREEN);
                g.fillArc(150, 80, 200, 200, 0, correctAngle);

                g.setColor(Color.RED);
                g.fillArc(150, 80, 200, 200, correctAngle, 360 - correctAngle);

                g.setColor(Color.BLACK);
                g.drawString("Correct Answers", 120, 320);
                g.drawString("Wrong Answers", 320, 320);

                g.setColor(Color.GREEN);
                g.fillRect(90, 310, 20, 20);

                g.setColor(Color.RED);
                g.fillRect(290, 310, 20, 20);
            }
        };

        resultFrame.add(scoreLabel, BorderLayout.NORTH);
        resultFrame.add(chartPanel, BorderLayout.CENTER);

        resultFrame.setVisible(true);

        dispose();
    }

    public static void main(String[] args) {
        new AdvancedQuizApp();
    }
}