package com.example.rest.webservices.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {

    @GetMapping("/someBean")
    public MappingJacksonValue getSomeBean(){
        SomeBean bean =  new SomeBean("filter1","filter2","filterthree");
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("filter1","filter3");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filterProvider);
        return  mapping;
    }

    @GetMapping("/someBeanList")
    public MappingJacksonValue getSomeBeanList(){
        List<SomeBean> beans = List.of(new SomeBean("filter1","filter2","filter3"),
                new SomeBean("filter1","filtertwo","filter3") );
        MappingJacksonValue mapping = new MappingJacksonValue(beans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("filter1","filter2");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}
