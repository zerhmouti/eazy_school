package com.eazybytes.eazyschool.model;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Courses extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int courseId;

    @NotBlank(message = "name must not be blank")
    @Size(min = 5,message = "Name must be at least 5 characters long")
    private String name;
    private String fees;

    @ManyToMany(mappedBy="courses", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Person> persons= new HashSet<>();

}
