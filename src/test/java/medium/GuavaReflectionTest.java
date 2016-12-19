package medium;

import com.google.common.collect.Lists;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GuavaReflectionTest {
    @Test
    public void testUnderstandingJavaTypeErasion() throws Exception {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        assertEquals("java.util.ArrayList",stringList.getClass().getName());
        assertEquals("java.util.ArrayList",stringList.getClass().getName());
        assertTrue(stringList.getClass().isAssignableFrom(intList.getClass()));
    }

    @Test
    public void testGuavaTypeToken() throws Exception {
        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        assertEquals("java.lang.String", genericTypeToken.getType().getTypeName());
    }

    @Test
    public void testCompareGuavaInvokableWithJava() throws Exception {
        Class clazz = File.class;
        Method method = clazz.getMethod("getName");
        assertTrue(Modifier.isPublic(method.getModifiers())); //JDK

        Invokable<?, Object> invokable = Invokable.from(method);
        assertTrue(invokable.isPublic()); //Guava Invokable
        assertTrue(invokable.isOverridable());
        assertFalse(invokable.isPackagePrivate());

        method = clazz.getMethod("setLastModified", long.class);
        assertFalse(Invokable.from(method).getParameters().get(0).isAnnotationPresent(Override.class));
    }

    @Test
    public void testGuavaDynamicProxy() throws Exception {
        InvocationHandler handler = new MyTestInvocationHandler();
        IFoo foo = Reflection.newProxy(IFoo.class, handler);
        String testStr = foo.doSomething();
        assertEquals("Proxy are doing something", testStr);
    }
}

class MyTestInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return "Proxy are doing something";
    }
}

interface IFoo {
    String doSomething();
}