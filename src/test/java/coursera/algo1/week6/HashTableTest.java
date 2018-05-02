package coursera.algo1.week6;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class HashTableTest {

    @Test
    public void add() {
        HashTable table = new HashTable(13);

        table.add(100);
        table.add(150);
        table.add(200);

        assertTrue(table.contains(100));
        assertTrue(table.contains(150));
        assertTrue(table.contains(200));
        assertFalse(table.contains(250));
    }

    @Test
    public void addWhenContains() {
        HashTable table = new HashTable(13);

        table.add(100);
        table.add(100);

        assertTrue(table.contains(100));
    }

    @Test
    public void remove() {
        HashTable table = new HashTable(13);
        table.add(15);
        assertTrue(table.contains(15));
        table.remove(15);
        assertFalse(table.contains(15));
    }

    @Test
    public void removeWhenNotThere() {
        HashTable table = new HashTable(13);
        table.remove(15);
        assertFalse(table.contains(15));
    }

    @Test
    public void withCollisions() {
        HashTable table = new HashTable(1);

        table.add(100);
        table.add(150);
        table.add(200);

        assertTrue(table.contains(100));
        assertTrue(table.contains(150));
        assertTrue(table.contains(200));
        assertFalse(table.contains(250));
    }

    @Test
    public void removeWithCollisions() {
        HashTable table = new HashTable(1);

        table.add(100);
        table.add(150);
        table.add(200);

        table.remove(100);
        table.remove(150);
        table.remove(200);

        assertFalse(table.contains(100));
    }
}
