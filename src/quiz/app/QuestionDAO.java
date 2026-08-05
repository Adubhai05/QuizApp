package quiz.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public static List<Question> getQuestionsByTopic(int topicId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE topic_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, topicId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("question_id"),
                        rs.getInt("topic_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option")
                );
                questions.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    // Quick test
    public static void main(String[] args) {
        List<Question> questions = getQuestionsByTopic(1); // Java Programming
        for (Question q : questions) {
            System.out.println(q.getQuestionId() + ": " + q.getQuestionText());
            System.out.println("  A) " + q.getOptionA());
            System.out.println("  B) " + q.getOptionB());
            System.out.println("  C) " + q.getOptionC());
            System.out.println("  D) " + q.getOptionD());
            System.out.println("  Correct: " + q.getCorrectOption());
        }
    }
}
