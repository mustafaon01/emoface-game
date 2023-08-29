import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Screen extends JPanel {
    private Face face;
    private JTextField answerField;
    private JButton answerButton;
    private JLabel questionLabel;
    private int mode = 5;

    private JButton gameMode5Button = new JButton("Game Mode = 5");
    private JButton gameMode10Button = new JButton("Game Mode = 10");

    private int nextQuestionIndex = 0;
    Questions[] questions = {
            new Questions("What is the capital of France?", "Paris"),
            new Questions("Who wrote 'Romeo and Juliet'?", "William Shakespeare"),
            new Questions("Which planet is known as the 'Red Planet'?", "Mars"),
            new Questions("In which year did World War II end?", "1945"),
            new Questions("Who painted the 'Mona Lisa'?", "Leonardo da Vinci"),
            new Questions("What is the largest mammal?", "Blue whale"),
            new Questions("Which element has the symbol 'Au' on the periodic table?", "Gold"),
            new Questions("In Greek mythology, who turned everything he touched into gold?", "King Midas"),
            new Questions("Which country is known as the Land of the Rising Sun?", "Japan"),
            new Questions("Which river is the longest in the world?", "Nile"),
            new Questions("What is the smallest prime number?", "2"),
            new Questions("In which country is the Great Pyramid of Giza?", "Egypt"),
            new Questions("Which instrument did Ludwig van Beethoven play?", "Piano"),
            new Questions("Which bird is often associated with delivering babies?", "Stork"),
            new Questions("Who wrote 'The Odyssey'?", "Homer"),
            new Questions("What is the currency of Japan?", "Yen"),
            new Questions("Which organ in the human body produces insulin?", "Pancreas"),
            new Questions("What's the smallest planet in our solar system?", "Mercury"),
            new Questions("Which famous ship sank on its maiden voyage in 1912?", "Titanic"),
            new Questions("Who is credited with the invention of the telephone?", "Alexander Graham Bell"),
            new Questions("Which gas do plants absorb from the atmosphere?", "Carbon Dioxide"),
            new Questions("In literature, who owned a cat named Dinah?", "Alice from Alice in Wonderland"),
            new Questions("Which sport takes place in a velodrome?", "Cycling"),
            new Questions("What is the tallest mountain in the world when measured from base to peak?", "Mauna Kea"),
            new Questions("Who was the first woman to win a Nobel Prize?", "Marie Curie")
    };


    public Screen(Face face) {
        this.face = face;


        gameMode5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(5);
            }
        });

        gameMode10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(10);
            }
        });

        add(gameMode5Button);
        add(gameMode10Button);
    }

    private void startGame(int mode) {
        face.setGameMode(mode);

        removeAll();
        setupGameUI();

        revalidate();
        repaint();
    }

    private void setupGameUI() {
        questionLabel = new JLabel(questions[nextQuestionIndex].getQuestionText());
        answerField = new JTextField(20);
        answerButton = new JButton("Answer");
        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAnswer = answerField.getText().trim();
                if (questions[nextQuestionIndex].isAnswerCorrect(userAnswer)) {
                    face.trueAnswer();
                } else {
                    face.wrongAnswer();
                }
                nextQuestion();
                repaint();
            }
        });
        add(answerField);
        add(answerButton);
        add(questionLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        face.draw(g);
    }

    private void nextQuestion() {
        nextQuestionIndex++;
        if (nextQuestionIndex < questions.length) {
            questionLabel.setText(questions[nextQuestionIndex].getQuestionText());
        } else {
            JOptionPane.showMessageDialog(null, "All questions are done!");
            System.exit(0);
        }
    }


}