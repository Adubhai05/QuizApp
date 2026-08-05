package quiz.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {

    public static List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        String query = "SELECT * FROM topics";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("topic_id");
                String name = rs.getString("topic_name");
                topics.add(new Topic(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }
    public static void main(String[] args) {
        List<Topic> topics = getAllTopics();
        for (Topic t : topics) {
            System.out.println(t.getTopicId() + " - " + t.getTopicName());
        }
    }
}
