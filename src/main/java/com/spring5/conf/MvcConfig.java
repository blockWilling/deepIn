package com.spring5.conf;

import com.spring5.entity.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Created by 康金 on 2019/2/12.
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        /**
         * 添加自定义的formatter
         */
        registry.addFormatter(new Formatter<Person>() {
            @Override
            public Person parse(String text, Locale locale) throws ParseException {
                Person person = new Person();
                person.setName(text);
                return person;
            }

            @Override
            public String print(Person object, Locale locale) {
                return null;
            }
        });
    }
}
