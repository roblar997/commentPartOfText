package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.mapping.List;

import javax.persistence.*;

import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name="\"texttocomment\"", schema = "\"schematest\"")
public class textToComment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "\"id\"")
    private Integer id;
    @Column(name = "\"text\"")
    private String text;

     @OneToMany(mappedBy = "id")
     @JsonIgnore
     Collection<Tidslinje> tidslinjer;
}
