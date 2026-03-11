import java.util.*;

class PlagiarismDetector {
    private final Map<String, Set<String>> ngramMap = new HashMap<>();
    private final int n;

    PlagiarismDetector(int n) { this.n = n; }

    public void indexDocument(String docId, String text) {
        String[] words = text.split("\\s+");
        for (int i = 0; i <= words.length - n; i++) {
            String ngram = String.join(" ", Arrays.copyOfRange(words, i, i + n));
            ngramMap.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
    }

    public Map<String, Integer> analyzeDocument(String docId, String text) {
        Map<String, Integer> similarity = new HashMap<>();
        String[] words = text.split("\\s+");
        for (int i = 0; i <= words.length - n; i++) {
            String ngram = String.join(" ", Arrays.copyOfRange(words, i, i + n));
            Set<String> docs = ngramMap.getOrDefault(ngram, Collections.emptySet());
            for (String other : docs) {
                if (!other.equals(docId)) similarity.put(other, similarity.getOrDefault(other, 0) + 1);
            }
        }
        return similarity;
    }
}

public class Question4 {
    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector(5);
        detector.indexDocument("essay_089.txt", "This is a sample essay text for plagiarism testing");
        detector.indexDocument("essay_092.txt", "Another essay text for plagiarism testing sample");
        Map<String, Integer> result = detector.analyzeDocument("essay_123.txt", "This is a sample essay text");
        System.out.println(result);
    }
}