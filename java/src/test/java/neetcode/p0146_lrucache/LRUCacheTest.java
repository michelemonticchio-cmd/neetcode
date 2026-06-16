package neetcode.linkedlist.p0146_lrucache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LRUCacheTest {

    @Test
    void test_basic_example() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));    // returns 1
        cache.put(3, 3);                  // evicts key 2
        assertEquals(-1, cache.get(2));   // not found
        cache.put(4, 4);                  // evicts key 1
        assertEquals(-1, cache.get(1));   // not found
        assertEquals(3, cache.get(3));    // returns 3
        assertEquals(4, cache.get(4));    // returns 4
    }

    @Test
    void test_get_missing_key() {
        LRUCache cache = new LRUCache(1);
        assertEquals(-1, cache.get(42));
    }

    @Test
    void test_update_existing_key() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(1, 20);   // update
        assertEquals(20, cache.get(1));
    }

    @Test
    void test_eviction_order() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 1 becomes MRU, 2 is LRU
        cache.put(3, 3);    // evicts 2
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
        assertEquals(3, cache.get(3));
    }

    @Test
    void test_capacity_one() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 1);
        cache.put(2, 2);    // evicts 1
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    void test_put_updates_recency() {
        // put on existing key should also update recency
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);   // update 1 → 1 is MRU, 2 is LRU
        cache.put(3, 3);    // evicts 2
        assertEquals(-1, cache.get(2));
        assertEquals(10, cache.get(1));
        assertEquals(3, cache.get(3));
    }

    @Test
    void test_repeated_gets_preserve_entry() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.get(1);
        cache.get(1);       // 1 is MRU, 2 is LRU
        cache.put(3, 3);    // evicts 2
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
    }
}
