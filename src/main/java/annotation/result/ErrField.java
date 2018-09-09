package annotation.result;

public class ErrField {
    private String fieldName;

    private Object fieldValue;

    private String err;

    public ErrField(String fieldName, Object fieldValue, String err) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.err = err;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
