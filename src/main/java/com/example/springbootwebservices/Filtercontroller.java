package com.example.springbootwebservices;

import javax.swing.Box.Filler;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Filtercontroller {
	
	@GetMapping(value = "/filter")
	public MappingJacksonValue retrieveSomefilter() {
		//Dynamic filtering instaed of using jsonignore hardcoded
		
		SomeBean b=new SomeBean("A", "B", "C");
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("data1","data2"); 
		FilterProvider fil=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(b);
		mapping.setFilters(fil);
		return mapping;
		
	}

}
