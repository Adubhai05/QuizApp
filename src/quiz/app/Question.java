package quiz.app;

public class Question {
    private int questionId;
    private int topicId;
    private String questionText;
    private String optionA, optionB, optionC, optionD;
    private String correctOption;

    public Question(int questionId, int topicId, String questionText,
                    String optionA, String optionB, String optionC, String optionD,
                    String correctOption) {
        this.questionId = questionId;
        this.topicId = topicId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public int getQuestionId() { return questionId; }
    public int getTopicId() { return topicId; }
    public String getQuestionText() { return questionText; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public String getCorrectOption() { return correctOption; }
}
