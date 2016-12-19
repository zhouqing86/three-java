package medium;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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