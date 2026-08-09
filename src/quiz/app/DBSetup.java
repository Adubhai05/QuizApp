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

                // ================= Java Programming (topic_id = 1) — 50 total =================
                "(1, 'Which keyword is used to inherit a class in Java?', 'implements', 'extends', 'inherits', 'super', 'B')," +
                "(1, 'Which method is the entry point of a Java program?', 'start()', 'run()', 'main()', 'init()', 'C')," +
                "(1, 'Which keyword is used to create an object in Java?', 'new', 'create', 'object', 'make', 'A')," +
                "(1, 'Which collection class allows duplicate elements and maintains insertion order?', 'HashSet', 'TreeSet', 'ArrayList', 'HashMap', 'C')," +
                "(1, 'Which keyword prevents a class from being inherited?', 'static', 'final', 'private', 'const', 'B')," +
                "(1, 'What is the default value of a boolean variable in Java?', 'true', 'false', '0', 'null', 'B')," +
                "(1, 'Which operator is used to compare two values for equality?', '=', '==', '===', '!=', 'B')," +
                "(1, 'Which exception is thrown when dividing an integer by zero?', 'NullPointerException', 'ArithmeticException', 'ArrayIndexOutOfBoundsException', 'ClassCastException', 'B')," +
                "(1, 'Which keyword is used to handle exceptions in Java?', 'throw', 'catch', 'try', 'exception', 'C')," +
                "(1, 'Which of these is NOT a Java access modifier?', 'public', 'private', 'protected', 'internal', 'D')," +
                "(1, 'Which of these is not a primitive data type in Java?', 'int', 'boolean', 'String', 'char', 'C')," +
                "(1, 'What is used to define a constant in Java?', 'final', 'const', 'static', 'immutable', 'A')," +
                "(1, 'Which of these is a checked exception?', 'IOException', 'NullPointerException', 'ArithmeticException', 'ArrayIndexOutOfBoundsException', 'A')," +
                "(1, 'Which keyword is used to prevent method overriding?', 'final', 'static', 'private', 'abstract', 'A')," +
                "(1, 'What is the size of int in Java?', '8 bits', '16 bits', '32 bits', '64 bits', 'C')," +
                "(1, 'Which interface must be implemented to make a class iterable?', 'Iterator', 'Iterable', 'Collection', 'List', 'B')," +
                "(1, 'Which of these is used for multithreading?', 'Thread class', 'Runnable interface', 'Both A and B', 'None', 'C')," +
                "(1, 'What does JVM stand for?', 'Java Virtual Machine', 'Java Verified Machine', 'Java Virtual Method', 'Java Verified Method', 'A')," +
                "(1, 'Which of these is a marker interface?', 'Serializable', 'Runnable', 'Comparable', 'Iterable', 'A')," +
                "(1, 'What is the parent class of all classes in Java?', 'Object', 'Class', 'Super', 'Main', 'A')," +
                "(1, 'Which of these is used to read input from console?', 'Scanner', 'BufferedReader', 'Both A and B', 'System.in only', 'C')," +
                "(1, 'What is the default value of an int variable in Java?', '0', 'null', 'undefined', '-1', 'A')," +
                "(1, 'Which loop is guaranteed to execute at least once?', 'for', 'while', 'do-while', 'for-each', 'C')," +
                "(1, 'Which keyword is used to import a package?', 'include', 'import', 'using', 'package', 'B')," +
                "(1, 'What is the extension of a compiled Java file?', '.java', '.class', '.jar', '.exe', 'B')," +
                "(1, 'Which of these supports multiple inheritance in Java?', 'Classes', 'Interfaces', 'Both', 'Neither', 'B')," +
                "(1, 'What is the use of the this keyword?', 'Refer to current object', 'Refer to parent class', 'Refer to static context', 'None', 'A')," +
                "(1, 'Which of these is an unchecked exception?', 'IOException', 'SQLException', 'RuntimeException', 'ClassNotFoundException', 'C')," +
                "(1, 'What is method overriding?', 'Same class same method name', 'Subclass redefining a parent method', 'Multiple methods with same name', 'None of these', 'B')," +
                "(1, 'Which of these is NOT an OOP concept?', 'Encapsulation', 'Polymorphism', 'Compilation', 'Inheritance', 'C')," +
                "(1, 'Which access modifier makes a member accessible only within the same class?', 'public', 'private', 'protected', 'default', 'B')," +
                "(1, 'What is a package in Java?', 'A group of related classes', 'A single class', 'A method', 'A variable', 'A')," +
                "(1, 'Which keyword is used to call a superclass constructor?', 'this', 'super', 'parent', 'base', 'B')," +
                "(1, 'What is the purpose of the static keyword?', 'Belongs to class not instance', 'Belongs to instance', 'Prevents inheritance', 'None', 'A')," +
                "(1, 'Which of these is a wrapper class for int?', 'Integer', 'Int', 'IntWrapper', 'Number', 'A')," +
                "(1, 'What does JDK stand for?', 'Java Development Kit', 'Java Design Kit', 'Java Deployment Kit', 'Java Debug Kit', 'A')," +
                "(1, 'Which of these is used to handle multiple exceptions in one catch block?', 'multi-catch', 'try-with', 'finally', 'throws', 'A')," +
                "(1, 'What is an abstract class?', 'A class with no objects', 'A class that cannot be instantiated', 'A class with only static methods', 'A final class', 'B')," +
                "(1, 'Which of these is a functional interface?', 'Runnable', 'ArrayList', 'HashMap', 'Scanner', 'A')," +
                "(1, 'What is the purpose of the finally block?', 'Always executes after try-catch', 'Only runs on exception', 'Only runs on success', 'None of these', 'A')," +
                "(1, 'Which data structure uses FIFO order?', 'Stack', 'Queue', 'Tree', 'Graph', 'B')," +
                "(1, 'Which of these is used to compare strings by content?', '==', 'equals()', 'compareTo() only', 'hashCode()', 'B')," +
                "(1, 'What is autoboxing in Java?', 'Converting primitive to wrapper automatically', 'Converting wrapper to primitive manually', 'None of these', 'Compiling automatically', 'A')," +
                "(1, 'Which keyword is used to create an interface?', 'interface', 'abstract', 'class', 'implements', 'A')," +
                "(1, 'What is a lambda expression used for?', 'Functional programming / anonymous functions', 'Loops', 'Exception handling', 'None of these', 'A')," +
                "(1, 'Which collection does not allow duplicate elements?', 'List', 'Set', 'Map', 'Array', 'B')," +
                "(1, 'What is the purpose of a constructor?', 'Initialize object state', 'Destroy an object', 'Define a class', 'None of these', 'A')," +
                "(1, 'Which of these is true about static methods?', 'Can be called without an object', 'Must be called with an object', 'Cannot access static variables', 'None of these', 'A')," +
                "(1, 'What is polymorphism?', 'One interface, many forms', 'Only inheritance', 'Only method overloading', 'None of these', 'A')," +
                "(1, 'Which package contains the Scanner class?', 'java.util', 'java.io', 'java.lang', 'java.net', 'A')," +

                // ================= General Knowledge (topic_id = 2) — 50 total =================
                "(2, 'What is the capital of Bangladesh?', 'Chittagong', 'Dhaka', 'Khulna', 'Sylhet', 'B')," +
                "(2, 'Who wrote the national anthem of Bangladesh?', 'Kazi Nazrul Islam', 'Jasimuddin', 'Rabindranath Tagore', 'Sukanta Bhattacharya', 'C')," +
                "(2, 'Which river is the longest in the world?', 'Amazon', 'Nile', 'Yangtze', 'Mississippi', 'B')," +
                "(2, 'Which planet is known as the Red Planet?', 'Venus', 'Jupiter', 'Mars', 'Saturn', 'C')," +
                "(2, 'What is the largest ocean on Earth?', 'Atlantic', 'Indian', 'Arctic', 'Pacific', 'D')," +
                "(2, 'Who was the first Prime Minister of Bangladesh?', 'Ziaur Rahman', 'Tajuddin Ahmad', 'Sheikh Mujibur Rahman', 'H.M. Ershad', 'B')," +
                "(2, 'Which gas do plants absorb from the atmosphere for photosynthesis?', 'Oxygen', 'Nitrogen', 'Carbon Dioxide', 'Hydrogen', 'C')," +
                "(2, 'What is the currency of Japan?', 'Yuan', 'Won', 'Yen', 'Ringgit', 'C')," +
                "(2, 'Which country hosted the 2022 FIFA World Cup?', 'Russia', 'Qatar', 'Brazil', 'USA', 'B')," +
                "(2, 'What is the national flower of Bangladesh?', 'Rose', 'Lily', 'Water Lily (Shapla)', 'Lotus', 'C')," +
                "(2, 'Which is the smallest country in the world?', 'Monaco', 'Vatican City', 'San Marino', 'Liechtenstein', 'B')," +
                "(2, 'What is the tallest mountain in the world?', 'K2', 'Everest', 'Kangchenjunga', 'Lhotse', 'B')," +
                "(2, 'Who painted the Mona Lisa?', 'Michelangelo', 'Da Vinci', 'Raphael', 'Donatello', 'B')," +
                "(2, 'Which country is known as the Land of the Rising Sun?', 'China', 'Japan', 'Korea', 'Thailand', 'B')," +
                "(2, 'What is the largest desert in the world?', 'Sahara', 'Gobi', 'Antarctic Desert', 'Arabian Desert', 'C')," +
                "(2, 'Which is the longest wall in the world?', 'Great Wall of China', 'Berlin Wall', 'Hadrian''s Wall', 'None of these', 'A')," +
                "(2, 'Who is known as the Father of the Nation of Bangladesh?', 'Ziaur Rahman', 'Sheikh Mujibur Rahman', 'Tajuddin Ahmad', 'None of these', 'B')," +
                "(2, 'Which is the national sport of Bangladesh?', 'Cricket', 'Football', 'Kabaddi', 'Hockey', 'C')," +
                "(2, 'What is the capital of France?', 'London', 'Paris', 'Rome', 'Berlin', 'B')," +
                "(2, 'Which ocean is the smallest?', 'Pacific', 'Atlantic', 'Indian', 'Arctic', 'D')," +
                "(2, 'Who discovered gravity?', 'Newton', 'Einstein', 'Galileo', 'Tesla', 'A')," +
                "(2, 'Which country has the largest population currently?', 'China', 'India', 'USA', 'Indonesia', 'B')," +
                "(2, 'What is the currency of the USA?', 'Dollar', 'Pound', 'Euro', 'Yen', 'A')," +
                "(2, 'Which is the largest continent?', 'Asia', 'Africa', 'Europe', 'North America', 'A')," +
                "(2, 'Who wrote Romeo and Juliet?', 'Shakespeare', 'Dickens', 'Hemingway', 'Austen', 'A')," +
                "(2, 'What is the national language of Bangladesh?', 'Bengali', 'English', 'Hindi', 'Urdu', 'A')," +
                "(2, 'Which planet is closest to the sun?', 'Mercury', 'Venus', 'Earth', 'Mars', 'A')," +
                "(2, 'What is the largest mammal in the world?', 'Elephant', 'Blue Whale', 'Giraffe', 'Shark', 'B')," +
                "(2, 'Which country gifted the Statue of Liberty to the USA?', 'France', 'UK', 'Germany', 'Spain', 'A')," +
                "(2, 'What is the boiling point of water in Celsius?', '90', '100', '110', '120', 'B')," +
                "(2, 'Which is the fastest land animal?', 'Lion', 'Cheetah', 'Horse', 'Leopard', 'B')," +
                "(2, 'Who was the first man on the moon?', 'Buzz Aldrin', 'Neil Armstrong', 'Yuri Gagarin', 'John Glenn', 'B')," +
                "(2, 'What is the largest organ in the human body?', 'Heart', 'Liver', 'Skin', 'Brain', 'C')," +
                "(2, 'Which country is known for the Eiffel Tower?', 'Italy', 'France', 'Spain', 'Germany', 'B')," +
                "(2, 'What is the freezing point of water in Celsius?', '-10', '0', '10', '32', 'B')," +
                "(2, 'Which is the smallest planet in the solar system?', 'Mercury', 'Mars', 'Pluto', 'Venus', 'A')," +
                "(2, 'Who invented the telephone?', 'Edison', 'Bell', 'Tesla', 'Marconi', 'B')," +
                "(2, 'Which country has the largest population of Muslims?', 'Saudi Arabia', 'Indonesia', 'Pakistan', 'Bangladesh', 'B')," +
                "(2, 'What is the national flower of Japan?', 'Rose', 'Cherry Blossom', 'Lotus', 'Tulip', 'B')," +
                "(2, 'Which sea is the saltiest in the world?', 'Red Sea', 'Dead Sea', 'Black Sea', 'Caspian Sea', 'B')," +
                "(2, 'Who is the author of the Harry Potter series?', 'J.K. Rowling', 'J.R.R. Tolkien', 'Stephen King', 'Roald Dahl', 'A')," +
                "(2, 'What is the tallest building in the world?', 'Burj Khalifa', 'Shanghai Tower', 'Taipei 101', 'Empire State Building', 'A')," +
                "(2, 'Which country is known as the Land of a Thousand Lakes?', 'Finland', 'Sweden', 'Norway', 'Canada', 'A')," +
                "(2, 'What is the largest island in the world?', 'Greenland', 'Madagascar', 'Borneo', 'Australia', 'A')," +
                "(2, 'Which gas makes up most of Earth''s atmosphere?', 'Oxygen', 'Nitrogen', 'Carbon Dioxide', 'Hydrogen', 'B')," +
                "(2, 'Which is the longest river in Asia?', 'Yangtze', 'Ganges', 'Mekong', 'Indus', 'A')," +
                "(2, 'What is the smallest bone in the human body?', 'Stapes', 'Femur', 'Tibia', 'Radius', 'A')," +
                "(2, 'Which country invented paper?', 'China', 'India', 'Egypt', 'Greece', 'A')," +
                "(2, 'What is the capital of Australia?', 'Sydney', 'Melbourne', 'Canberra', 'Perth', 'C')," +
                "(2, 'Which festival is known as the Festival of Lights in India?', 'Diwali', 'Holi', 'Navratri', 'Eid', 'A')," +

                // ================= Computer Networks (topic_id = 3) — 50 total =================
                "(3, 'What does IP stand for?', 'Internet Protocol', 'Internal Process', 'Internet Provider', 'Instant Protocol', 'A')," +
                "(3, 'Which device connects multiple networks together?', 'Switch', 'Router', 'Hub', 'Modem', 'B')," +
                "(3, 'What does DNS stand for?', 'Domain Name System', 'Data Network Service', 'Digital Name Server', 'Domain Network Service', 'A')," +
                "(3, 'Which layer of the OSI model handles routing?', 'Data Link', 'Network', 'Transport', 'Session', 'B')," +
                "(3, 'Which protocol is used to send emails?', 'FTP', 'HTTP', 'SMTP', 'SNMP', 'C')," +
                "(3, 'What is the default subnet mask for a Class C IP address?', '255.0.0.0', '255.255.0.0', '255.255.255.0', '255.255.255.255', 'C')," +
                "(3, 'Which port number does HTTP use by default?', '21', '25', '80', '443', 'C')," +
                "(3, 'What does DHCP stand for?', 'Dynamic Host Configuration Protocol', 'Direct Host Control Protocol', 'Dynamic Host Control Process', 'Data Host Configuration Protocol', 'A')," +
                "(3, 'Which topology connects all devices to a single central hub?', 'Bus', 'Ring', 'Star', 'Mesh', 'C')," +
                "(3, 'Which protocol is connection-oriented?', 'UDP', 'IP', 'TCP', 'ICMP', 'C')," +
                "(3, 'Which layer of OSI model is responsible for error detection?', 'Physical', 'Data Link', 'Network', 'Transport', 'B')," +
                "(3, 'What does LAN stand for?', 'Local Area Network', 'Large Area Network', 'Local Access Network', 'Line Area Network', 'A')," +
                "(3, 'What does WAN stand for?', 'Wide Area Network', 'Wireless Area Network', 'Web Area Network', 'None of these', 'A')," +
                "(3, 'Which protocol is used for secure web browsing?', 'HTTP', 'HTTPS', 'FTP', 'SMTP', 'B')," +
                "(3, 'What is the full form of URL?', 'Uniform Resource Locator', 'Universal Resource Locator', 'Uniform Retrieval Locator', 'None of these', 'A')," +
                "(3, 'Which device operates at the physical layer?', 'Hub', 'Switch', 'Router', 'Bridge', 'A')," +
                "(3, 'What is the maximum number of hosts in a Class C network?', '254', '255', '256', '65534', 'A')," +
                "(3, 'Which protocol translates domain names to IP addresses?', 'DHCP', 'DNS', 'ARP', 'FTP', 'B')," +
                "(3, 'What is the purpose of a firewall?', 'Block unauthorized access', 'Speed up network', 'Assign IP addresses', 'Encrypt data', 'A')," +
                "(3, 'Which of these is a private IP address?', '8.8.8.8', '192.168.1.1', '172.217.0.0', '1.1.1.1', 'B')," +
                "(3, 'What does MAC address stand for?', 'Media Access Control', 'Machine Access Control', 'Media Allocation Control', 'None of these', 'A')," +
                "(3, 'Which protocol is used to transfer files?', 'FTP', 'HTTP', 'SMTP', 'SNMP', 'A')," +
                "(3, 'What is the function of a switch?', 'Connect devices within a LAN', 'Connect different networks', 'Assign IP addresses', 'Block traffic', 'A')," +
                "(3, 'Which layer of OSI handles encryption?', 'Application', 'Presentation', 'Session', 'Transport', 'B')," +
                "(3, 'What does VPN stand for?', 'Virtual Private Network', 'Virtual Public Network', 'Verified Private Network', 'None of these', 'A')," +
                "(3, 'Which protocol is connectionless?', 'TCP', 'UDP', 'HTTP', 'FTP', 'B')," +
                "(3, 'What is the purpose of ARP?', 'Map IP to MAC address', 'Map domain to IP', 'Assign IP addresses', 'Encrypt data', 'A')," +
                "(3, 'Which topology has a single point of failure at the central node?', 'Star', 'Ring', 'Mesh', 'Bus', 'A')," +
                "(3, 'What is bandwidth?', 'Data transfer capacity', 'Physical cable length', 'Number of devices', 'Error rate', 'A')," +
                "(3, 'Which of these is an example of a Layer 2 device?', 'Router', 'Switch', 'Hub', 'Gateway', 'B')," +
                "(3, 'What does SSID stand for?', 'Service Set Identifier', 'System Set Identifier', 'Secure Set ID', 'None of these', 'A')," +
                "(3, 'Which protocol is used for network time synchronization?', 'NTP', 'FTP', 'SNMP', 'SMTP', 'A')," +
                "(3, 'What is the purpose of NAT?', 'Translate private IPs to public IPs', 'Assign MAC addresses', 'Encrypt traffic', 'Block ports', 'A')," +
                "(3, 'Which of these is a Class A IP address range?', '1-126', '128-191', '192-223', '224-239', 'A')," +
                "(3, 'What does ISP stand for?', 'Internet Service Provider', 'Internal Service Provider', 'Internet System Provider', 'None of these', 'A')," +
                "(3, 'Which protocol is used for remote login?', 'Telnet', 'FTP', 'SMTP', 'DNS', 'A')," +
                "(3, 'What is a MAC address made of?', '48 bits', '32 bits', '64 bits', '16 bits', 'A')," +
                "(3, 'Which of these devices operates at the network layer?', 'Router', 'Hub', 'Switch', 'Repeater', 'A')," +
                "(3, 'What is the purpose of a proxy server?', 'Act as intermediary between client and server', 'Assign IP addresses', 'Encrypt data only', 'Block viruses', 'A')," +
                "(3, 'Which protocol is used to send error messages in networks?', 'ICMP', 'TCP', 'UDP', 'ARP', 'A')," +
                "(3, 'What does Wi-Fi commonly stand for?', 'Wireless Fidelity', 'Wireless Fiber', 'Wide Fidelity', 'None of these', 'A')," +
                "(3, 'Which of these is used to prevent network congestion?', 'Flow control', 'Firewall', 'DNS', 'DHCP', 'A')," +
                "(3, 'What is the port number for FTP?', '21', '25', '80', '443', 'A')," +
                "(3, 'Which layer of OSI model deals with routing?', 'Network', 'Transport', 'Session', 'Data Link', 'A')," +
                "(3, 'What is the purpose of a repeater?', 'Amplify/regenerate signal', 'Block traffic', 'Assign IP addresses', 'Encrypt data', 'A')," +
                "(3, 'Which of these describes a full mesh topology?', 'Every device connected to every other', 'Single central hub', 'Linear connection', 'Ring connection', 'A')," +
                "(3, 'What is the maximum reliable length of a Cat5e Ethernet cable?', '100 meters', '500 meters', '1000 meters', '50 meters', 'A')," +
                "(3, 'Which protocol assigns IP addresses automatically?', 'DHCP', 'DNS', 'ARP', 'FTP', 'A')," +
                "(3, 'What does packet switching mean?', 'Data sent in small packets independently', 'One continuous stream', 'Only wired signal', 'None of these', 'A')," +
                "(3, 'Which OSI layer is closest to the end user?', 'Application', 'Physical', 'Network', 'Transport', 'A')," +

                // ================= Mathematics (topic_id = 4) — 50 total =================
                "(4, 'What is the value of pi rounded to 2 decimal places?', '3.10', '3.14', '3.16', '3.41', 'B')," +
                "(4, 'What is the square root of 144?', '10', '11', '12', '13', 'C')," +
                "(4, 'What is 15% of 200?', '20', '25', '30', '35', 'C')," +
                "(4, 'What is the sum of the interior angles of a triangle?', '90 degrees', '180 degrees', '270 degrees', '360 degrees', 'B')," +
                "(4, 'What is the value of 2 to the power of 5?', '16', '32', '64', '128', 'B')," +
                "(4, 'What is the next prime number after 7?', '8', '9', '10', '11', 'D')," +
                "(4, 'What is the area of a circle with radius 7 (use pi = 22/7)?', '154', '144', '164', '174', 'A')," +
                "(4, 'What is the LCM of 4 and 6?', '10', '12', '18', '24', 'B')," +
                "(4, 'If x + 5 = 12, what is x?', '5', '6', '7', '8', 'C')," +
                "(4, 'What is 9 factorial divided by 8 factorial?', '7', '8', '9', '72', 'C')," +
                "(4, 'What is 12 x 12?', '144', '132', '154', '124', 'A')," +
                "(4, 'What is the value of 0 factorial?', '0', '1', 'undefined', '-1', 'B')," +
                "(4, 'What is the derivative of x squared?', '2x', 'x', 'x squared', '2', 'A')," +
                "(4, 'What is the sum of the first 10 natural numbers?', '55', '45', '50', '60', 'A')," +
                "(4, 'What is the value of log base 10 of 100?', '1', '2', '10', '100', 'B')," +
                "(4, 'What is 7 squared?', '14', '49', '42', '56', 'B')," +
                "(4, 'What is the perimeter of a square with side 5?', '20', '25', '10', '15', 'A')," +
                "(4, 'What is 100 divided by 4?', '20', '25', '30', '15', 'B')," +
                "(4, 'What is the value of 3 to the power of 3?', '9', '27', '81', '6', 'B')," +
                "(4, 'What is the median of {2,4,6,8,10}?', '6', '4', '8', '5', 'A')," +
                "(4, 'What is the mode of {1,2,2,3,4}?', '2', '1', '3', '4', 'A')," +
                "(4, 'What is a prime number?', 'A number with exactly two factors', 'A number divisible by itself only', 'An even number', 'An odd number', 'A')," +
                "(4, 'What is the value of pi to 4 decimal places?', '3.1415', '3.1416', '3.1417', '3.1418', 'B')," +
                "(4, 'What is the area of a rectangle with length 8 and width 5?', '40', '45', '35', '30', 'A')," +
                "(4, 'What is the value of 2 + 2 x 2?', '6', '8', '4', '10', 'A')," +
                "(4, 'What is the hypotenuse of a right triangle with legs 3 and 4?', '5', '6', '7', '8', 'A')," +
                "(4, 'What is the value of the square root of 81?', '8', '9', '10', '7', 'B')," +
                "(4, 'What is an even number?', 'Divisible by 2', 'Divisible by 3', 'A prime number', 'An odd number', 'A')," +
                "(4, 'What is the sum of angles in a quadrilateral?', '180', '270', '360', '450', 'C')," +
                "(4, 'What is the value of 5 factorial?', '60', '100', '120', '90', 'C')," +
                "(4, 'What is the slope-intercept form of a line?', 'y = mx + b', 'y = mx', 'x = my + b', 'y = b', 'A')," +
                "(4, 'What is the value of 15 - 7 x 2?', '1', '16', '8', '22', 'A')," +
                "(4, 'What is the greatest common divisor of 12 and 18?', '6', '4', '3', '2', 'A')," +
                "(4, 'What is the value of 2 to the power of 10?', '1024', '512', '2048', '256', 'A')," +
                "(4, 'What is a right angle in degrees?', '90', '180', '45', '360', 'A')," +
                "(4, 'What is the value of 9 x 9?', '81', '72', '90', '99', 'A')," +
                "(4, 'What is the reciprocal of 5?', '1/5', '5', '-5', '0', 'A')," +
                "(4, 'What is the value of the square root of 64?', '6', '7', '8', '9', 'C')," +
                "(4, 'What is the formula for the area of a circle?', 'pi r squared', '2 pi r', 'pi d', 'r squared', 'A')," +
                "(4, 'What is the value of 6 x 7?', '42', '36', '48', '40', 'A')," +
                "(4, 'What type of triangle has all sides equal?', 'Equilateral', 'Isosceles', 'Scalene', 'Right', 'A')," +
                "(4, 'What is the value of 100 - 45?', '55', '65', '45', '50', 'A')," +
                "(4, 'What is the LCM of 3 and 5?', '15', '10', '8', '20', 'A')," +
                "(4, 'What is the value of 4 to the power of 3?', '12', '64', '43', '34', 'B')," +
                "(4, 'What is the highest power in a quadratic equation?', '1', '2', '3', '0', 'B')," +
                "(4, 'What is the value of 1/2 plus 1/4?', '3/4', '1/2', '1/4', '2/4', 'A')," +
                "(4, 'What is 10% of 250?', '25', '20', '30', '15', 'A')," +
                "(4, 'What is the sum of interior angles of a pentagon?', '540', '360', '720', '450', 'A')," +
                "(4, 'What is the value of 3 x (4 + 2)?', '18', '14', '20', '16', 'A')," +
                "(4, 'What is the value of the square root of 1?', '0', '1', '-1', 'undefined', 'B')";

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

            // Clear old questions and reinsert the full fresh set (50 per topic, 200 total)
            stmt.executeUpdate("DELETE FROM questions");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='questions'"); // reset autoincrement
            stmt.executeUpdate(insertQuestions);
            System.out.println("Questions inserted (200 total, 50 per topic).");

            System.out.println("Database setup complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}