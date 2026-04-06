package DistributedCache;

public interface Database {
    void add(String key, int val);
    void delete(String key) throws Exception;
    int get(String key) throws Exception;
}