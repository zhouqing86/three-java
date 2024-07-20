package base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AnnotationTest {

    @Test
    public void testAnnotation() throws Exception {
        Class<ToDoObject> toDoObject = ToDoObject.class;
        if(toDoObject.isAnnotationPresent(ToDo.class)) {
            System.out.println("To Do Interface");
        }
    }
}
