package DistributedCache;

import java.util.ArrayList;
import java.util.List;

public class DistributedCache {
    Database db;
    DistributionStrategy strategy;
    List<CacheNode> cacheNodes;
    int totalCaches;

    DistributedCache(int totalCaches, int capactiy, Database db, EvictionPolicyFactory factory, DistributionStrategy strategy){
        this.db = db;
        this.strategy = strategy;
        this.totalCaches = totalCaches;
        cacheNodes = new ArrayList<>();

        for(int i=0; i<totalCaches; i++){
            cacheNodes.add(new CacheNode(capactiy, factory.create()));
        }
    }

    public boolean containsKey(List<CacheNode> cacheNodes, String key){
        int cacheIdx = strategy.getCacheIdx(totalCaches, key);
        return cacheNodes.get(cacheIdx).check(key);
    }

    void add(String key, int val){
        db.add(key, val);

        int cacheIdx = strategy.getCacheIdx(totalCaches, key);
        cacheNodes.get(cacheIdx).add(key, val);
    }

    int get(String key) throws Exception{
        int cacheIdx = strategy.getCacheIdx(totalCaches, key);
        if(containsKey(cacheNodes, key)) return cacheNodes.get(cacheIdx).get(key);

        int val = db.get(key);
        cacheNodes.get(cacheIdx).add(key, val);

        return val;
    }

    void deleteKey(String key) throws Exception{
        if(!containsKey(cacheNodes, key)) return;
        int cacheIdx = strategy.getCacheIdx(totalCaches, key);

        cacheNodes.get(cacheIdx).remove(key);
        db.delete(key);
    }
}
