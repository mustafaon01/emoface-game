public class Questions {
    private String questionText;
    private String answer;

    public Questions(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isAnswerCorrect(String userInput) {
        return answer.equalsIgnoreCase(userInput);
    }
}
