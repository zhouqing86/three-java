package annotation.util;

import annotation.annotations.*;
import annotation.result.ErrField;
import annotation.result.ValidateResult;
import com.google.common.base.Strings;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUtil {

    public static String getTableName(Class<?> aClass) {
        Table annotation = aClass.getAnnotation(Table.class);
        String tableName = "";
        if (annotation != null) {
            tableName = annotation.name();
            if ("".equals(tableName)) {
                tableName = aClass.getSimpleName();
            }
        } else {
            tableName = aClass.getSimpleName();
        }
        return tableName;
    }

    public static ValidateResult validateObject(Object obj) throws Exception {
        ValidateResult validateResult = new ValidateResult();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = StringUtil.camelCaseToUnderline(field.getName());
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            Class<?> fieldType = field.getType();

            Annotation[] annotations = field.getAnnotations();

            if (! String.class.equals(fieldType)) {
                continue;
            }
            if ( fieldValue == null || "".equals(fieldValue) ) {
                continue;
            }

            String strFieldValue = (String) fieldValue;
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.equals(TableId.class)) {
                    String idColumn = ((TableId) annotation).name();
                    if ("".equals(idColumn)) {
                        idColumn = fieldName;
                    }
                    validateResult.setIdColumn(idColumn);
                    validateResult.setIdValue(fieldValue);
                } else if (annotationType.equals(CheckPhoneNumber.class)) {
                    String annotationRegex = ((CheckPhoneNumber) annotation).regex();
                    String annotationValue = ((CheckPhoneNumber) annotation).value();
                    if ("".equals(annotationRegex) && "".equals(annotationValue)) {
                        annotationValue = fieldName;
                    }
                    String errStr = "invalid phone number";

                    if (!"".equals(annotationValue)) {
                        if (!annotationValue.equalsIgnoreCase(strFieldValue)) {
                            validateResult.setValid(false);
                            ErrField errField = new ErrField(fieldName, fieldValue, errStr);
                            validateResult.addErrField(errField);
                        }
                    } else if (!"".equals(annotationRegex)) {
                        if (fieldValue != null && ((String) fieldValue).matches(annotationRegex)) {
                            validateResult.setValid(false);
                            ErrField errField = new ErrField(fieldName, fieldValue, errStr);
                            validateResult.addErrField(errField);
                        }
                    }

                } else if (annotationType.equals(CheckName.class)) {
                    String annotationRegex = ((CheckName) annotation).regex();
                    String annotationValue = ((CheckName) annotation).value();
                    if ("".equals(annotationRegex) && "".equals(annotationValue)) {
                        annotationValue = fieldName;
                    }
                    String errStr = "invalid name";

                    if (!"".equals(annotationValue)) {
                        if (!annotationValue.equalsIgnoreCase(strFieldValue)) {
                            validateResult.setValid(false);
                            ErrField errField = new ErrField(fieldName, fieldValue, errStr);
                            validateResult.addErrField(errField);
                        }
                    } else if (!"".equals(annotationRegex)) {
                        if (fieldValue != null && ((String) fieldValue).matches(annotationRegex)) {
                            validateResult.setValid(false);
                            ErrField errField = new ErrField(fieldName, fieldValue, errStr);
                            validateResult.addErrField(errField);
                        }
                    }

                } else if (annotationType.equals(CheckAddress.class)) {
                    String annotationRegex = ((CheckAddress) annotation).regex();
                    String annotationValue = ((CheckAddress) annotation).value();
                    if ("".equals(annotationRegex) && "".equals(annotationValue)) {
                        annotationValue = fieldName;
                    }
                    String errStr = "invalid address";

                    if (!"".equals(annotationValue)) {
                        if (!annotationValue.equalsIgnoreCase(strFieldValue)) {
                            validateResult.setValid(false);
                            ErrField errField = new ErrField(fieldName, fieldValue, errStr);
                            validateResult.addErrField(errField);
                        }
                    } else if (!"".equals(annotationRegex)) {
                        if (fieldValue != null && ((String) fieldValue).matches(annotationRegex)) {
                            validateResult.setValid(false);
                            ErrField errField = new ErrField(fieldName, fieldValue, errStr);
                            validateResult.addErrField(errField);
                        }
                    }
                }
            }
        }
        return validateResult;
    }

    public static List<ValidateResult> validateObjectList(List<?> objectList) throws Exception {
        List<ValidateResult> resultList = new ArrayList<>();
        for (Object obj : objectList) {
            resultList.add(validateObject(obj));
        }
        return resultList;
    }
}
