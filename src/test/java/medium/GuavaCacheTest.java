package medium;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * guava cache采用了类似ConcurrentHashMap的思想，分段加锁，在每个段里面各自负责自己的淘汰的事情。
 * 在Guava根据一定的算法进行分段，这里要说明的是，如果段太少那竞争依然很严重，如果段太多会容易出现随机淘汰
 * 相比于LRUMap多了两种过期时间，一个是写后多久过期expireAfterWrite，一个是读后多久过期expireAfterAccess。
 * 很有意思的事情是，在guava cache中对于过期的Entry并没有马上过期(也就是并没有后台线程一直在扫)，而是通过进行读写操作的时候进行过期处理，这样做的好处是避免后台线程扫描的时候进行全局加锁。
 * 在Guava cache中，key和value都能进行虚引用的设定，在Segment中的有两个引用队列:keyReferenceQueue和valueReferenceQueue
 * 细细品读guava cache的源码总结下来，其实就是一个性能不错的，api丰富的LRU Map。
 * guava cache的功能的确是很强大，满足了绝大多数的人的需求，但是其本质上还是LRU的一层封装,所以在众多其他较为优良的淘汰算法中就相形见绌了。而caffeine cache实现了W-TinyLFU(LFU+LRU算法的变种)。
 */
public class GuavaCacheTest {
    @Test
    public void testCache() throws Exception {
        LoadingCache<Integer,Student> studentCache = CacheBuilder.newBuilder().concurrencyLevel(8)
                                                            .expireAfterWrite(80, TimeUnit.MILLISECONDS)
                                                            .initialCapacity(10)
                                                            .maximumSize(100)
                                                            .recordStats()
                                                            .removalListener(new RemovalListener<Object, Object>() {
                                                                @Override
                                                                public void onRemoval(RemovalNotification<Object, Object> notification) {
                                                                    System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                                                                }
                                                            })
                                                            .build(
                                                                    new CacheLoader<Integer, Student>() {
                                                                        @Override
                                                                        public Student load(Integer key) throws Exception {
                                                                            //我这里配置的是直接返回Key，在业务中通常配置从数据库中查询。
                                                                            System.out.println("load student " + key);
                                                                            Student student = new Student();
                                                                            student.setId(key);
                                                                            student.setName("name"+key);
                                                                            return student;
                                                                        }
                                                                    }
                                                            );
        Student student = studentCache.get(1);
        assertEquals("name1",student.getName());
        for (int i=0;i<4;i++) {
            student = studentCache.get(1);
            TimeUnit.MILLISECONDS.sleep(20);
        }
        assertEquals(4, studentCache.stats().hitCount());
    }
}

class Student {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}