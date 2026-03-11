import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, long ttlMillis) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

class DNSCache {
    private final Map<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0, misses = 0;

    public String resolve(String domain, long ttlMillis) {
        DNSEntry entry = cache.get(domain);
        if (entry != null && !entry.isExpired()) {
            hits++;
            return entry.ip;
        } else {
            misses++;
            String ip = queryUpstream(domain);
            cache.put(domain, new DNSEntry(ip, ttlMillis));
            return ip;
        }
    }

    private String queryUpstream(String domain) {
        return "127.0.0.1"; // mock upstream
    }

    public String getStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        return String.format("Hit Rate: %.2f%%, Hits: %d, Misses: %d", hitRate, hits, misses);
    }
}

public class Question3 {
    public static void main(String[] args) throws InterruptedException {
        DNSCache cache = new DNSCache();
        System.out.println(cache.resolve("google.com", 2000));
        Thread.sleep(100);
        System.out.println(cache.resolve("google.com", 2000));
        Thread.sleep(2100);
        System.out.println(cache.resolve("google.com", 2000));
        System.out.println(cache.getStats());
    }
}