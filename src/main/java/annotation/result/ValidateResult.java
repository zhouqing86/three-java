package annotation.result;

import java.util.ArrayList;
import java.util.List;

public class ValidateResult {

    private boolean isValid;

    private String idColumn;

    private Object idValue;

    private List<ErrField> errFieldList = new ArrayList<>();

    public ValidateResult() {
        isValid = true;
        idColumn = "";
        idValue = "";
    }

    public ValidateResult(boolean isValid, String idColumn, String idValue) {
        this.isValid = isValid;
        this.idColumn = idColumn;
        this.idValue = idValue;
    }

    public void addErrField(ErrField errField) {
        errFieldList.add(errField);
    }

    public List<ErrField> getErrFieldList() {
        return errFieldList;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
    }

    public Object getIdValue() {
        return idValue;
    }

    public void setIdValue(Object idValue) {
        this.idValue = idValue;
    }
}
