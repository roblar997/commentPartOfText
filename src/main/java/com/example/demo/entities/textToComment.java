package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.mapping.List;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name="\"texttocomment\"", schema = "\"schematest\"")
public class textToComment implements Serializable {
//
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "\"id\"")
    private Integer id;

    public textToComment() {

    }

    @Override
    public String toString() {
        return "textToComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", tidslinjer=" + tidslinjer +
                '}';
    }

    public textToComment(String text, String title) {
        this.text = text;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof textToComment)) return false;
        textToComment that = (textToComment) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(title, that.title) && Objects.equals(tidslinjer, that.tidslinjer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, title, tidslinjer);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "\"text\"")
    private String text;
    @Column(name = "\"title\"")
    private String title;
    @Column(name = "\"isdeleted\"")
    private Boolean isdeleted;


    @OneToMany(mappedBy = "texttocommentfield")
     @JsonIgnore
     transient  Collection<Tidslinje> tidslinjer;
}
