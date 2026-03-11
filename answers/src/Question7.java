import java.util.*;

class AutocompleteSystem {
    private final Map<String, Integer> frequency = new HashMap<>();

    public void updateFrequency(String query) {
        frequency.put(query, frequency.getOrDefault(query,0)+1);
    }

    public List<String> search(String prefix) {
        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(
                (a,b)-> a.getValue()==b.getValue()?a.getKey().compareTo(b.getKey()):b.getValue()-a.getValue()
        );
        for(Map.Entry<String,Integer> entry: frequency.entrySet()) {
            if(entry.getKey().startsWith(prefix)) pq.offer(entry);
        }
        List<String> result = new ArrayList<>();
        int count=0;
        while(!pq.isEmpty() && count++<10) result.add(pq.poll().getKey());
        return result;
    }
}

public class Question7 {
    public static void main(String[] args) {
        AutocompleteSystem ac = new AutocompleteSystem();
        ac.updateFrequency("java tutorial");
        ac.updateFrequency("javascript");
        ac.updateFrequency("java tutorial");
        System.out.println(ac.search("jav"));
    }
}