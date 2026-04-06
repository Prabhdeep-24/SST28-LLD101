package DistributedCache;

public interface EvictionPolicy {
    void update(String key);
    String getLeastRecentlyUsed();
    void removeNode(String key);
}
