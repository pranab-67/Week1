import java.util.*;

class Transaction {
    int id, amount;
    String merchant;
    Transaction(int id, int amount, String merchant) { this.id=id; this.amount=amount; this.merchant=merchant; }
}

class TwoSumDetector {
    public List<int[]> findTwoSum(List<Transaction> transactions, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        for(Transaction t: transactions) {
            if(map.containsKey(target - t.amount)) result.add(new int[]{map.get(target - t.amount), t.id});
            map.put(t.amount, t.id);
        }
        return result;
    }
}

public class Question9 {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1,500,"A"),
                new Transaction(2,300,"B"),
                new Transaction(3,200,"C")
        );
        TwoSumDetector detector = new TwoSumDetector();
        for(int[] pair: detector.findTwoSum(transactions,500))
            System.out.println(Arrays.toString(pair));
    }
}