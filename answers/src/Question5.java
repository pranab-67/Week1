import java.util.*;

class AnalyticsDashboard {
    private final Map<String, Integer> pageViews = new HashMap<>();
    private final Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private final Map<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void printDashboard() {
        System.out.println("Top Pages:");
        pageViews.entrySet().stream()
                .sorted((a,b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " views ("
                        + uniqueVisitors.get(e.getKey()).size() + " unique)"));
        System.out.println("\nTraffic Sources:");
        trafficSources.forEach((k,v) -> System.out.println(k + ": " + v));
    }
}

public class Question5 {
    public static void main(String[] args) {
        AnalyticsDashboard dashboard = new AnalyticsDashboard();
        dashboard.processEvent("/article/breaking-news", "user_123", "Google");
        dashboard.processEvent("/article/breaking-news", "user_456", "Facebook");
        dashboard.processEvent("/sports/championship", "user_789", "Direct");
        dashboard.printDashboard();
    }
}