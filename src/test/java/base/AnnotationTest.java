package base;


import org.junit.Test;

import java.lang.annotation.Annotation;

public class AnnotationTest {

    @Test
    public void testAnnotation() throws Exception {
        Class<ToDoObject> toDoObject = ToDoObject.class;
        if(toDoObject.isAnnotationPresent(ToDo.class)) {
            System.out.println("To Do Interface");
        }
    }
}
