package annotation.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPhoneNumber {

    //Valid phone number regex
    String regex() default "\\+8611111111";

    //Valid value
    String value() default "+8611111111";
}
