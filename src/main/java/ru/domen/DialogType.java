package ru.domen;

import javax.persistence.*;
import java.awt.Dialog;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="DialogType")
public class DialogType {
    @Id
    @Column(name="typeId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long typeId;
    @Column(name="typeName")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "type")
    private List<Dialog> dialogs = new ArrayList<>();

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }
}
