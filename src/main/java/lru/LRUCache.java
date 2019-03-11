package lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 这个是非并行版本
 * 如果使用并行版本，锁竞争严重，可以看见我的代码中，Lock是全局锁，在方法级别上面的，当调用量较大时，性能必然会比较低。
 * 不支持过期时间，不支持自动刷新所以谷歌的大佬们对于这些问题，按捺不住了，发明了Guava cache
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V>  extends LinkedHashMap<K,V>{

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}
