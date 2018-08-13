package com.example.globomartapi.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.converter.SpringManyMultipartFilesReader;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FeignClientFactory {

    private final Request.Options options = new Request.Options(30 * 1000, 15 * 60 * 1000);
    private Encoder encoder;
    private Decoder decoder;

    public FeignClientFactory() {
        this.encoder = new GsonEncoder();
        this.decoder = new GsonDecoder();
    }

    public FeignClientFactory(Encoder defaultEncoder, Decoder defaultDecoder) {
        this.encoder = defaultEncoder;
        this.decoder = defaultDecoder;
    }

    public static Decoder springDecoder(final ObjectFactory<HttpMessageConverters> messageConverters) {
        final List<HttpMessageConverter<?>> springConverters = messageConverters.getObject().getConverters();
        //Create a separate list of MessageConverter for feign client,
        // instead of modifying the HttpMessageConverter used by Spring framework
        final List<HttpMessageConverter<?>> feignConverters
                = new ArrayList<HttpMessageConverter<?>>(springConverters.size() + 1);

        feignConverters.addAll(springConverters);
        feignConverters.add(new SpringManyMultipartFilesReader(4096));
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(feignConverters);

        return new SpringDecoder(() -> httpMessageConverters);
    }

    public static Encoder springEncoder(final ObjectFactory<HttpMessageConverters> messageConverters) {
        final List<HttpMessageConverter<?>> springConverters = messageConverters.getObject().getConverters();
        final List<HttpMessageConverter<?>> decoderConverters
                = new ArrayList<HttpMessageConverter<?>>(springConverters.size() + 1);

        decoderConverters.addAll(springConverters);
//        decoderConverters.add(new SpringManyMultipartFilesWriter());
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(decoderConverters);

        return new SpringEncoder(() -> httpMessageConverters);

    }


    public <T> T getInstance(Class<T> apiType, String baseUrl) {
        return Feign.builder()
                .client(new OkHttpClient())
                .options(options)
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Logger.JavaLogger())
                .logLevel(Logger.Level.BASIC)
                .retryer(Retryer.NEVER_RETRY)
                .target(apiType, baseUrl);
    }

    private void howToSendMultiPartForm() {
        /*Ref:- https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/converter/FormHttpMessageConverter.html*/
        /*For example, the following snippet shows how to submit an HTML form:*/
        RestTemplate template = new RestTemplate();
        // AllEncompassingFormHttpMessageConverter is configured by default

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("field 1", "value 1");
        form.add("field 2", "value 2");
        form.add("field 2", "value 3");
        template.postForLocation("http://example.com/myForm", form);

        /*The following snippet shows how to do a file upload:*/
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("field 1", "value 1");
        parts.add("file", new ClassPathResource("myFile.jpg"));
        template.postForLocation("http://example.com/myFileUpload", parts);

    }
}
