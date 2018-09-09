package annotation;

import annotation.model.Account;
import annotation.util.AnnotationUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationTest {
    public static void main(String[] args) throws Exception{
        Account account = new Account();
        account.setPhoneNumber("+8611111111");

        String tableName = AnnotationUtil.getTableName(account.getClass());

        AnnotationUtil.validateObject(account);
    }
}
