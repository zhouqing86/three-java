package annotation.util;

import annotation.model.Account;
import annotation.result.ValidateResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ValidateAnnotationUtilTest {
    @Test
    public void validateAccountPhoneNumber() throws Exception {
        Account account = new Account();
        account.setAccountid("13232244224");
        account.setPhoneNumber("+8611111112");

        ValidateResult result = ValidateAnnotationUtil.validateObject(account);

        assertFalse(result.isValid());
        assertEquals("ACCOUNTID", result.getIdColumn());
        assertEquals("13232244224", result.getIdValue());
        assertEquals(1, result.getErrFieldList().size());
        assertEquals("PHONE_NUMBER", result.getErrFieldList().get(0).getFieldName());
    }


    @Test
    public void validateAccountName() throws Exception {
        Account account = new Account();
        account.setAccountid("13232244224");
        account.setName("账户名");

        ValidateResult result = ValidateAnnotationUtil.validateObject(account);

        assertFalse(result.isValid());
        assertEquals("ACCOUNTID", result.getIdColumn());
        assertEquals("13232244224", result.getIdValue());
        assertEquals(1, result.getErrFieldList().size());
        assertEquals("NAME", result.getErrFieldList().get(0).getFieldName());
    }


    @Test
    public void validateAccountAddress() throws Exception {
        Account account = new Account();
        account.setAccountid("13232244224");
        account.setAddress("地址");

        ValidateResult result = ValidateAnnotationUtil.validateObject(account);

        assertFalse(result.isValid());
        assertEquals("ACCOUNTID", result.getIdColumn());
        assertEquals("13232244224", result.getIdValue());
        assertEquals(1, result.getErrFieldList().size());
        assertEquals("ADDRESS", result.getErrFieldList().get(0).getFieldName());
    }

    @Test
    public void validateAccountList() throws Exception {
        Account account1 = new Account();
        account1.setAccountid("13232244224");
        account1.setPhoneNumber("+8611111112");

        Account account2 = new Account();
        account2.setAccountid("13232244225");
        account2.setName("账户名");

        Account account3 = new Account();
        account3.setAccountid("13232244225");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);

        List<ValidateResult> resultList = ValidateAnnotationUtil.validateObjectList(accountList);

        assertEquals(3, resultList.size());
//        resultList.stream().filter(result -> !result.isValid()).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(2, resultList.stream().filter(result -> !result.isValid()).count());

    }
}