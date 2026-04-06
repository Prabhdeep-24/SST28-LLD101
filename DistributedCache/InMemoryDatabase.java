package DistributedCache;

import java.util.HashMap;

public class InMemoryDatabase implements Database{
    HashMap<String, Integer> db;

    InMemoryDatabase(){
        db = new HashMap<>();
    }

    public void add(String key, int val){
        db.put(key, val);
    }

    public void delete(String key) throws Exception{
        if(!db.containsKey(key)) throw new Exception("Databse do not contain this key");
        db.remove(key);
    }

    public int get(String key) throws Exception{
        if(!db.containsKey(key)) throw new Exception("Databse do not contain this key");
        return db.get(key);
    }
}
