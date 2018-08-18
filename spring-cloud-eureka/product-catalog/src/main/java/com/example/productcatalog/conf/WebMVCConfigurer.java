package com.example.productcatalog.conf;

import com.example.productcatalog.conf.jackson.XmlResponseDtoMixin;
import com.example.productcatalog.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;

@Configuration
@CommonsLog
public class WebMVCConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(it -> {
            if (it instanceof MappingJackson2HttpMessageConverter) {
                log.info("*********** Overriding Configuration of MappingJackson2HttpMessageConverter");
                configureMappingJackson2HttpMessageConverter((MappingJackson2HttpMessageConverter) it);
            } else if (it instanceof MappingJackson2XmlHttpMessageConverter) {
                log.info("*********** Overriding Configuration of MappingJackson2XmlHttpMessageConverter");
                configureMappingJackson2XmlHttpMessageConverter((MappingJackson2XmlHttpMessageConverter) it);
            } else {
                log.info(String.format("*********** %s is configured with default configuration", it));
            }
        });

    }

    private void configureMappingJackson2HttpMessageConverter(MappingJackson2HttpMessageConverter jsonHttpMessageConverter) {
        ObjectMapper objectMapper = jsonHttpMessageConverter.getObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(READ_ENUMS_USING_TO_STRING);
        objectMapper.enable(WRITE_ENUMS_USING_TO_STRING);
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.enable(ACCEPT_SINGLE_VALUE_AS_ARRAY);

        // TODO leverage NamingStrategy to make response attributes more Java-like
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    private void configureMappingJackson2XmlHttpMessageConverter(MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter) {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        // and then configure, for example:
        xmlModule.setDefaultUseWrapper(Boolean.TRUE);

        final XmlMapper mapper = new XmlMapper(xmlModule);
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(READ_ENUMS_USING_TO_STRING);
        mapper.enable(WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.addMixIn(ResponseDTO.class, XmlResponseDtoMixin.class);
        xmlHttpMessageConverter.setObjectMapper(mapper);
        /*
        AnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        // make serializer use JAXB annotations (only)
        SerializationConfig sc = mapper.getSerializationConfig();
        sc.with(jaxbAnnotationIntrospector)
                .with(INDENT_OUTPUT);

*/
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }
}
