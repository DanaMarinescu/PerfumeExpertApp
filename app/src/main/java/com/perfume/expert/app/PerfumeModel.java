package com.perfume.expert.app;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Objects;

@Entity
@Table(name = "perfumescopy")
public class PerfumeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "perfume_name")
    private String perfume_name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "price")
    private String price;

    @Column(name = "olfactory_group")
    private String olfactory_group;

    @Column(name = "season")
    private String season;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "dominant_note")
    private String dominant_note;

    @Column(name = "image")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerfume_name() {
        return perfume_name;
    }

    public void setPerfume_name(String perfume_name) {
        this.perfume_name = perfume_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOlfactory_group() {
        return olfactory_group;
    }

    public void setOlfactory_group(String olfactory_group) {
        this.olfactory_group = olfactory_group;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getDominant_note() {
        return dominant_note;
    }

    public void setDominant_note(String dominant_note) {
        this.dominant_note = dominant_note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfumeModel that = (PerfumeModel) o;
        return Objects.equals(perfume_name, that.perfume_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfume_name);
    }
}