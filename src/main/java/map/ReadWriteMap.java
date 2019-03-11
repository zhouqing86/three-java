package map;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteMap<K,V> {

    private final Map<K,V> map;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock wlock = lock.writeLock();

    private final Lock rlock = lock.readLock();

    public ReadWriteMap(Map map) {
        this.map = map;
    }

    //对remove(), putAll(),clear()等方法执行相同的操作
    public V put(K key, V value) {
        wlock.lock();
        try {
            return map.put(key, value);
        } finally {
            wlock.unlock();
        }
    }

    //对其他只读的方法执行同样的操作
    public V get(K key) {
        rlock.lock();

        try {
            return map.get(key);
        } finally {
            rlock.unlock();
        }
    }
}
