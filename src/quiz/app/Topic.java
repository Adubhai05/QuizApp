package quiz.app;

public class Topic {
    private int topicId;
    private String topicName;

    public Topic(int topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    @Override
    public String toString() {
        return topicName;
    }
}