package DistributedCache;

public interface DistributionStrategy {
    int getCacheIdx(int totalCaches, String key);
}