package DistributedCache;

public class LRUEvictionPolicyFactory implements EvictionPolicyFactory{
    public EvictionPolicy create(){
        return new LRU();
    }
}
