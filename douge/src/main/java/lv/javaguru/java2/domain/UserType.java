package lv.javaguru.java2.domain;

/**
 * Created by Radchuk on 10/10/2014.
 */
public class UserType {
    private Long typeId;
    private String typeName;

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
