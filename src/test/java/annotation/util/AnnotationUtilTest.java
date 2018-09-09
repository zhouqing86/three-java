package annotation.util;

import annotation.model.Account;
import annotation.result.ValidateResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnnotationUtilTest {
    @Test
    public void validateAccountPhoneNumber() throws Exception {
        Account account = new Account();
        account.setAccountid("13232244224");
        account.setPhoneNumber("+8611111112");

        ValidateResult result = AnnotationUtil.validateObject(account);

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

        ValidateResult result = AnnotationUtil.validateObject(account);

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

        ValidateResult result = AnnotationUtil.validateObject(account);

        assertFalse(result.isValid());
        assertEquals("ACCOUNTID", result.getIdColumn());
        assertEquals("13232244224", result.getIdValue());
        assertEquals(1, result.getErrFieldList().size());
        assertEquals("ADDRESS", result.getErrFieldList().get(0).getFieldName());
    }

    @Test
    public void validateAccoutList() throws Exception {
        Account account1 = new Account();
        account1.setAccountid("13232244224");

    }
}