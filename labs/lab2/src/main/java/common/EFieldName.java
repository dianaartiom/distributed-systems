package common;

import java.io.Serializable;

public enum EFieldName implements Serializable {
    NAME("name"), SURNAME("surame"), SALARY("salary");

    private String fieldName;

    EFieldName() {
    }

    EFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
