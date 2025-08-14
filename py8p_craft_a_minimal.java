import java.util.*;

public class py8p_craft_a_minimal {
    public static void main(String[] args) {
        ChatbotAnalyzer analyzer = new ChatbotAnalyzer();
        String[] conversations = {
                "User: Hello",
                "Bot: Hi, how can I assist you today?",
                "User: I need help with my order",
                "Bot: Sorry to hear that. Can you please provide me with your order number?",
                "User: It's #1234",
                "Bot: I've located your order. What seems to be the issue?",
                "User: My order is delayed",
                "Bot: I apologize for the delay. I've escalated the issue to our team. You'll receive an update soon."
        };

        analyzer.analyzeConversations(conversations);

        System.out.println(" Sentiment Analysis:");
        analyzer.printSentimentAnalysis();

        System.out.println(" \n Intent Detection:");
        analyzer.printIntentDetection();
    }
}

class ChatbotAnalyzer {
    Map<String, Integer> sentimentMap = new HashMap<>();
    Map<String, Integer> intentMap = new HashMap<>();

    public void analyzeConversations(String[] conversations) {
        for (String conversation : conversations) {
            String[] parts = conversation.split(": ");
            String type = parts[0];
            String text = parts[1];

            if (type.equals("User")) {
                analyzeUserText(text);
            } else {
                analyzeBotText(text);
            }
        }
    }

    private void analyzeUserText(String text) {
        if (text.contains("help") || text.contains("assist")) {
            incrementIntent("support");
        }

        if (text.contains("delayed") || text.contains("late")) {
            incrementSentiment("negative");
        } else {
            incrementSentiment("neutral");
        }
    }

    private void analyzeBotText(String text) {
        if (text.contains("apologize") || text.contains("sorry")) {
            incrementSentiment("negative");
        } else {
            incrementSentiment("neutral");
        }

        if (text.contains("assist") || text.contains("help")) {
            incrementIntent("support");
        }
    }

    private void incrementSentiment(String sentiment) {
        sentimentMap.put(sentiment, sentimentMap.getOrDefault(sentiment, 0) + 1);
    }

    private void incrementIntent(String intent) {
        intentMap.put(intent, intentMap.getOrDefault(intent, 0) + 1);
    }

    public void printSentimentAnalysis() {
        for (Map.Entry<String, Integer> entry : sentimentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printIntentDetection() {
        for (Map.Entry<String, Integer> entry : intentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}