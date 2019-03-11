package thread;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}

/**
 * Memoizer1的问题是每次只有一个线程能够执行compute
 *
 * @param <A>
 * @param <V>
 */
class Memoizer1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

/**
 * Memoizer2比Memoizer1有更好的并发行为，但是不足的是-两个线程同时调用compute时存在一个漏洞，可能导致计算得到相同的值。我们希望
 * 通过某种方法来表达"线程正在计算"这种情况
 *
 * @param <A>
 * @param <V>
 */
class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}


/**
 * Memoizer3的实现几乎是完美的，只有一个缺陷，即仍然存在两个线程计算出相同值的漏洞，因为if代码块仍然是非原子的先执行后检查操作
 * @param <A>
 * @param <V>
 */
class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); //在这里调用c.compute
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

/**
 * 使用了ConcurrentHashMap的原子方法putIfAbsent,避免Memoizer3的漏洞
 * 缓存的是Future可能会导致缓存污染问题，因为某个计算可能被取消或失败，为了避免这种情况，如果发现计算被取消，就要把其从缓存中移除。
 * @param <A>
 * @param <V>
 */
class Memoizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MemoizerlTest {
}
