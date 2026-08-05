package quiz.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBSetup {
    public static void main(String[] args) {
        String createTopics = "CREATE TABLE IF NOT EXISTS topics (" +
                "topic_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "topic_name TEXT NOT NULL)";

        String createQuestions = "CREATE TABLE IF NOT EXISTS questions (" +
                "question_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "topic_id INTEGER NOT NULL," +
                "question_text TEXT NOT NULL," +
                "option_a TEXT NOT NULL," +
                "option_b TEXT NOT NULL," +
                "option_c TEXT NOT NULL," +
                "option_d TEXT NOT NULL," +
                "correct_option TEXT NOT NULL," +
                "FOREIGN KEY (topic_id) REFERENCES topics(topic_id))";

        String insertTopics = "INSERT INTO topics (topic_name) VALUES " +
                "('Java Programming'), ('General Knowledge'), ('Computer Networks'), ('Mathematics')";

        String insertQuestions = "INSERT INTO questions " +
                "(topic_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES " +
                "(1, 'Which keyword is used to inherit a class in Java?', 'implements', 'extends', 'inherits', 'super', 'B')," +
                "(1, 'Which method is the entry point of a Java program?', 'start()', 'run()', 'main()', 'init()', 'C')," +
                "(2, 'What is the capital of Bangladesh?', 'Chittagong', 'Dhaka', 'Khulna', 'Sylhet', 'B')," +
                "(2, 'Who wrote the national anthem of Bangladesh?', 'Kazi Nazrul Islam', 'Jasimuddin', 'Rabindranath Tagore', 'Sukanta Bhattacharya', 'C')," +
                "(3, 'What does IP stand for?', 'Internet Protocol', 'Internal Process', 'Internet Provider', 'Instant Protocol', 'A')," +
                "(3, 'Which device connects multiple networks together?', 'Switch', 'Router', 'Hub', 'Modem', 'B')," +
                "(4, 'What is the value of pi rounded to 2 decimal places?', '3.10', '3.14', '3.16', '3.41', 'B')," +
                "(4, 'What is the square root of 144?', '10', '11', '12', '13', 'C')";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTopics);
            stmt.execute(createQuestions);

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM topics");
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.executeUpdate(insertTopics);
                System.out.println("Topics inserted.");
            }

            ResultSet rsQ = stmt.executeQuery("SELECT COUNT(*) FROM questions");
            rsQ.next();
            if (rsQ.getInt(1) == 0) {
                stmt.executeUpdate(insertQuestions);
                System.out.println("Questions inserted.");
            }

            System.out.println("Database setup complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}