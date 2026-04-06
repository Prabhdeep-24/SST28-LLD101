package DistributedCache;

import java.util.HashMap;

public class CacheNode {
    int capacity;
    HashMap<String, Integer> cache;
    EvictionPolicy policy;

    CacheNode(int capacity, EvictionPolicy policy){
        this.capacity = capacity;
        this.policy = policy;
        cache = new HashMap<>();
    }

    void add(String key, int val){
        if(cache.size() == capacity){
            String temp = policy.getLeastRecentlyUsed();
            cache.remove(temp);

        }

        cache.put(key, val);
        policy.update(key);
    }

    void remove(String key){
        if(!cache.containsKey(key)) return;

        policy.removeNode(key);
        cache.remove(key);
    }

    boolean check(String key){
        return cache.containsKey(key);
    }

    int get(String key){
        if(!check(key)) return -1;
        policy.update(key);
        return cache.get(key);
    }
}
