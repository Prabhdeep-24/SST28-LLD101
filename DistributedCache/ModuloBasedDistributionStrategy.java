package DistributedCache;

public class ModuloBasedDistributionStrategy implements DistributionStrategy{
    public int getCacheIdx(int totalCaches, String key){
        return key.length() % totalCaches;
    }
}
