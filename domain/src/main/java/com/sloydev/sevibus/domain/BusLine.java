package com.sloydev.sevibus.domain;

import java.util.List;

public class BusLine {

    private Integer id;
    private String name;
    private String description;
    private List<BusLineSection> sections;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<BusLineSection> getSections() {
        return sections;
    }

    public void setSections(List<BusLineSection> sections) {
        this.sections = sections;
    }
}
