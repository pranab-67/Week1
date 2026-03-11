import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class TokenBucket {
    int maxTokens;
    double refillRate;
    double tokens;
    long lastRefillTime;

    TokenBucket(int maxTokens, double refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        tokens += (now - lastRefillTime)/1000.0 * refillRate;
        if(tokens > maxTokens) tokens = maxTokens;
        lastRefillTime = now;
        if(tokens >= 1) { tokens--; return true; }
        return false;
    }
}

class RateLimiter {
    private final Map<String, TokenBucket> clients = new HashMap<>();

    public void addClient(String clientId, int maxTokens, double refillRate) {
        clients.put(clientId, new TokenBucket(maxTokens, refillRate));
    }

    public boolean checkRateLimit(String clientId) {
        TokenBucket bucket = clients.get(clientId);
        return bucket != null && bucket.allowRequest();
    }
}

public class Question6 {
    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
        limiter.addClient("abc123", 5, 5); // 5 requests max, refill 5 per sec
        for(int i=0;i<7;i++) System.out.println(limiter.checkRateLimit("abc123"));
    }
}