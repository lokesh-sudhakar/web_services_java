package com.example.rest.webservices.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String filter1;

    private String filter2;

    private String filter3;


    public SomeBean(String filter1, String filter2, String filter3) {
        this.filter1 = filter1;
        this.filter2 = filter2;
        this.filter3 = filter3;
    }

    public String getFilter2() {
        return filter2;
    }

    public void setFilter2(String filter2) {
        this.filter2 = filter2;
    }

    public String getFilter1() {
        return filter1;
    }

    public void setFilter1(String filter1) {
        this.filter1 = filter1;
    }

    public String getFilter3() {
        return filter3;
    }

    public void setFilter3(String filter3) {
        this.filter3 = filter3;
    }
}
