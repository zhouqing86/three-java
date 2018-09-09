package annotation;

import annotation.model.Account;
import annotation.util.ValidateAnnotationUtil;

public class AnnotationTest {
    public static void main(String[] args) throws Exception{
        Account account = new Account();
        account.setPhoneNumber("+8611111111");

        String tableName = ValidateAnnotationUtil.getTableName(account.getClass());

        ValidateAnnotationUtil.validateObject(account);
    }
}
