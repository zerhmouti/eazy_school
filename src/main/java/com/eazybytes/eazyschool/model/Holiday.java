package com.eazybytes.eazyschool.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity(name="holidays")
public class Holiday extends BaseEntity{

    @Id
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type ;

    public enum Type{
        FESTIVAL, FEDERAL
    }

}
