package edu.project.webshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Lob
    @Column(name = "description", length = 10000)
    private String description;

    @Lob
    @Column(name = "avatar", length = 1000)
    private String avatar;

    private int price;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;


    public Course() {
    }

    public Course(int id, String name, String description, String avatar, int price, Tag tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.price = price;
        this.tag = tag;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
