package com.example.demo.entities;

import javax.persistence.*;

import java.util.List;

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

   // @OneToMany(mappedBy = "Tidslinje", fetch = FetchType.LAZY,
        //    cascade = CascadeType.ALL)
   // List<Tidslinje> tidslinjer;
}
