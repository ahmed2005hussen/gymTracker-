package com.ahmed.Hadidy.config.web;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    /**
     * Configure API versioning for the application. In order for versioning to
     * be enabled, you must configure at least one way to resolve the API
     * version from a request (e.g. via request header).
     *
     * @param configurer
     * @since 7.0
     */
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        // from postman in headers add:
        // Accept: application/vnd.hadidy+json;v=1.0 (or any version do you want)
        configurer.
                useMediaTypeParameter(MediaType.parseMediaType("application/vnd.hadidy+json")
                        , "v")
                .addSupportedVersions("1.0", "2.0");
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api" ,_-> true );
    }

}
