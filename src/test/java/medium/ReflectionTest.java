package medium;


import org.junit.Test;

import java.io.File;
import java.io.Writer;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ReflectionTest {

    @Test
    public void testGetClassName() throws Exception {
        assertEquals("java.lang.Object", Object.class.getName());
        assertEquals("Object", Object.class.getSimpleName());
    }

    @Test
    public void testGetModifier() throws Exception {
       assertEquals(false, Modifier.isAbstract(Object.class.getModifiers()));
       assertEquals(true, Modifier.isAbstract(Writer.class.getModifiers()));

    }

    @Test
    public void testGetPackageInfo() throws Exception {
        assertEquals("java.lang", Object.class.getPackage().getName());
    }

    @Test
    public void testGetSuperClassInfo() throws Exception {
        assertEquals("java.lang.Object", String.class.getSuperclass().getName());
        assertEquals(null,Object.class.getSuperclass());
    }

    @Test
    public void testGetIntefaces() throws Exception {
        Class [] interfaces = Writer.class.getInterfaces();
        assertEquals(3, interfaces.length);
        assertEquals("java.lang.Appendable",interfaces[0].getName());
    }

    @Test
    public void testConstructors() throws Exception {
        Constructor [] constructors = File.class.getConstructors();
        assertEquals(4, constructors.length);
        Constructor constructor1 = String.class.getConstructor(new Class[]{String.class});
        Constructor constructor2 = String.class.getConstructor(String.class);
        String testStr = (String) constructor1.newInstance("hello");
        assertEquals("hello", testStr);
        testStr = (String) constructor2.newInstance("world");
        assertEquals("world", testStr);
    }

    @Test
      public void testGetFields() throws Exception {
        Field [] fields = File.class.getFields();
        assertEquals(4, fields.length);
        fields = File.class.getDeclaredFields();
        assertEquals(14, fields.length);
        Stream.of(fields).map(field -> field.getName()).forEach(System.out::println);
    }

    @Test(expected = IllegalAccessException.class)
    public void testSetStaticFinalFieldWillThrowException() throws Exception {
        Field field = File.class.getField("separator");
        File file = new File("test.txt");
        field.setAccessible(true);
        assertEquals("/", field.get(file));
        field.set(file, "/test");
    }

    @Test
    public void testSetPrivateField() throws Exception {
        Field field = File.class.getDeclaredField("path");
        field.setAccessible(true);
        File file = new File("test.txt");
        field.set(file, "test1.txt");
        assertEquals("test1.txt", file.getPath());
    }

    @Test
    public void testGetMethods() throws Exception {
        Method [] methods = Object.class.getMethods();
        assertEquals(9, methods.length);
        Stream.of(methods).map(method -> method.getName()).forEach(System.out::println);
    }

    @Test
    public void testSetMethods() throws Exception {
        Method method = People.class.getMethod("setName", new Class[]{String.class});
        People people = new People();
        method.invoke(people, "Sam");
        assertEquals("Sam", people.getName());
    }

    @Test
    public void testMethodGetParameters() throws Exception {
        Method method = People.class.getMethod("setName", new Class[]{String.class});
        assertEquals(1, method.getParameterTypes().length);
        assertEquals("java.lang.String", method.getParameterTypes()[0].getName());
    }

    @Test
    public void testMethodGetReturnType() throws Exception {
        Method method = People.class.getMethod("getName", null);
        assertEquals(0, method.getParameterTypes().length);
        assertEquals("java.lang.String", method.getReturnType().getName());
    }

    @Test
    public void testAnnotations() throws Exception {
        Annotation[] annotations = ReflectionTest.class.getAnnotations();
        assertTrue(annotations.length<1);
    }

    @Test
    public void testClassAnnotations() throws Exception {
        TestClassAnnotation annotation = People.class.getAnnotation(TestClassAnnotation.class);
        assertEquals("someClass",annotation.name());
        assertEquals("Hello World",annotation.value());
    }

    @Test
    public void testMethodAnnotations() throws Exception {
        Method method = People.class.getMethod("setName", new Class[]{String.class});
        TestMethodAnnotation annotation = method.getAnnotation(TestMethodAnnotation.class);
        assertEquals("someMethod",annotation.name());
        assertEquals("Hello World",annotation.value());
    }

    @Test
    public void testFieldAnnotations() throws Exception {
        Field field = People.class.getDeclaredField("name");
        TestFieldAnnotation annotation = field.getAnnotation(TestFieldAnnotation.class);
        assertEquals("someField",annotation.name());
        assertEquals("Hello World",annotation.value());
    }

    @Test
    public void testParameterAnnotations() throws Exception {
        Method method = People.class.getMethod("setName", new Class[]{String.class});
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for(Annotation[] annotations : parameterAnnotations){
            for(Annotation annotation : annotations){
                if(annotation instanceof TestParameterAnnotation){
                    assertEquals("someParameter", ((TestParameterAnnotation) annotation).name());
                    assertEquals("Hello World",((TestParameterAnnotation)annotation).value());
                }
            }
        }
    }

    @Test
    public void testGenericFieldTypes() throws Exception {
        Field field = People.class.getDeclaredField("stringList");
        Type genericFieldType = field.getGenericType();
        ParameterizedType aType = (ParameterizedType) genericFieldType;
        Type[] fieldArgTypes = aType.getActualTypeArguments();
        assertEquals(1, fieldArgTypes.length);
        assertEquals("java.lang.String", fieldArgTypes[0].getTypeName());
    }

    @Test
    public void testArrayReflection() throws Exception {
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);
        assertEquals(123, Array.get(intArray, 0));
        assertEquals(456, Array.get(intArray, 1));
        assertEquals(789, Array.get(intArray, 2));
    }

    @Test
    public void testObtainingStringArray() throws Exception {
        Class stringArrayClass = String[].class;
        assertEquals("java.lang.String", stringArrayClass.getComponentType().getName());
        Class intArray = Class.forName("[I");
        assertEquals("int", intArray.getComponentType().getName());
        stringArrayClass = Class.forName("[Ljava.lang.String;");
        assertEquals("java.lang.String", stringArrayClass.getComponentType().getName());
        int[] intArray2 = (int[]) Array.newInstance(int.class, 3);
        assertEquals(3, intArray2.length);
    }

    @Test
    public void testDynamicProxy() throws Exception {
        MyInterfaceImpl myInterfaceImpl = new MyInterfaceImpl();
        InvocationHandler handler = new MyInvocationHandler(myInterfaceImpl);
        MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(myInterfaceImpl.getClass().getClassLoader(), myInterfaceImpl.getClass().getInterfaces(), handler);
        myInterface.method1(5);
        System.out.println();
        myInterface.method2(10);
    }
}

@TestClassAnnotation(name="someClass",  value = "Hello World")
class People {

    @TestFieldAnnotation(name="someField",  value = "Hello World")
    private String name;

    private Integer age;

    public List<String> stringList = new ArrayList<String>();

    public String getName() {
        return name;
    }

    @TestMethodAnnotation(name="someMethod",  value = "Hello World")
    public void setName(@TestParameterAnnotation(name="someParameter", value="Hello World") String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestClassAnnotation {
    public String name();
    public String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestMethodAnnotation {
    public String name();
    public String value();
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface TestFieldAnnotation {
    public String name();
    public String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface TestParameterAnnotation {
    public String name();
    public String value();
}

interface MyInterface {
    void method1(int i);
    void method2(int i);
}

class MyInterfaceImpl implements MyInterface{

    @Override
    public void method1(int i) {
        System.out.println("method1 start");
        System.out.println(i);
        System.out.println("method1 end");
    }

    @Override
    public void method2(int i) {
        System.out.println("method2 start");
        System.out.println(i);
        System.out.println("method2 end");
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object myInterface;

    public MyInvocationHandler(Object myInterface) {
        this.myInterface = myInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Invoke a method start");
        Object object = method.invoke(myInterface, args);
        System.out.println("Invoke a method end");
        return object;
    }
}