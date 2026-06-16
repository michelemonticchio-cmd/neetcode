package neetcode.binarysearch.p0981_timebasedkeyvaluestore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private TimeMap tm;

    @BeforeEach
    void setUp() {
        tm = new TimeMap();
    }

    @Test
    void test_exact_timestamp_match() {
        tm.set("foo", "bar", 1);
        assertEquals("bar", tm.get("foo", 1));
    }

    @Test
    void test_query_between_timestamps() {
        tm.set("foo", "bar",  1);
        tm.set("foo", "bar2", 4);
        assertEquals("bar",  tm.get("foo", 3));
        assertEquals("bar2", tm.get("foo", 4));
        assertEquals("bar2", tm.get("foo", 5));
    }

    @Test
    void test_query_before_any_timestamp() {
        tm.set("foo", "bar", 2);
        assertEquals("", tm.get("foo", 1));
    }

    @Test
    void test_key_not_present() {
        assertEquals("", tm.get("missing", 10));
    }

    @Test
    void test_multiple_keys_independent() {
        tm.set("a", "valueA", 1);
        tm.set("b", "valueB", 1);
        assertEquals("valueA", tm.get("a", 5));
        assertEquals("valueB", tm.get("b", 5));
        assertEquals("",       tm.get("c", 5));
    }

    @Test
    void test_many_timestamps_same_key() {
        tm.set("k", "v1", 1);
        tm.set("k", "v2", 3);
        tm.set("k", "v3", 5);
        tm.set("k", "v4", 7);
        assertEquals("",   tm.get("k", 0));
        assertEquals("v1", tm.get("k", 1));
        assertEquals("v1", tm.get("k", 2));
        assertEquals("v2", tm.get("k", 3));
        assertEquals("v2", tm.get("k", 4));
        assertEquals("v3", tm.get("k", 5));
        assertEquals("v4", tm.get("k", 7));
        assertEquals("v4", tm.get("k", 100));
    }

    @Test
    void test_single_timestamp_queried_many_times() {
        tm.set("x", "only", 5);
        assertEquals("",     tm.get("x", 4));
        assertEquals("only", tm.get("x", 5));
        assertEquals("only", tm.get("x", 1000));
    }
}
