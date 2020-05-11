package com.ujiuye.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DateTimeConverter implements Converter<String, Date> {

    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = sdf.parse(source);
            return date;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public void sa(){
        System.out.println("nihao");
    }
    
}
