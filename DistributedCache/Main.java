package DistributedCache;

public class Main {
    public static void main(String[] args) throws Exception {
        Database  db = new InMemoryDatabase();
        EvictionPolicyFactory factory = new LRUEvictionPolicyFactory();
        DistributionStrategy strategy = new ModuloBasedDistributionStrategy();

        DistributedCache cache = new DistributedCache(5, 4, db, factory, strategy);

        cache.add("apple", 10);
        cache.add("banana", 20);
        cache.add("cherry", 30);
        cache.add("date", 40);
        cache.add("elderberry", 50);

        System.out.println(cache.get("apple"));     // should be 10 (cache hit)
        System.out.println(cache.get("banana"));    // should be 20

        try {
            System.out.println(cache.get("fig"));   // cache miss → DB fetch (if exists)
        } catch (Exception e) {
            System.out.println("fig not found in DB");
        }

        cache.add("grape", 60);
        cache.add("honeydew", 70);

        // Trigger eviction (depends on capacity per node)

        System.out.println(cache.get("cherry"));    // may be evicted → check behavior

        cache.deleteKey("banana");

        try {
            System.out.println(cache.get("banana")); // should throw exception or miss
        } catch (Exception e) {
            System.out.println("banana not found in DB");
        }
    }
}
