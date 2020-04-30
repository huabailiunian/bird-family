
package com.bird.demo.app.config;


import com.bird.demo.app.filter.JsonpCallbackFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author master
 */
@Configuration
public class ServletConfig {

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        this.log.debug("Configuring localeChangeInterceptor");
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("language");
//        return localeChangeInterceptor;
//    }
//
//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        this.log.debug("Creating requestMappingHandlerMapping");
//        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
//        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
//        Object[] interceptors = new Object[]{this.localeChangeInterceptor()};
//        requestMappingHandlerMapping.setInterceptors(interceptors);
//        return requestMappingHandlerMapping;
//    }

    @Bean
    public JsonpCallbackFilter jsonpCallbackFilter() {
        return new JsonpCallbackFilter();
    }

//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        this.addDefaultHttpMessageConverters(converters);
//        Iterator var2 = converters.iterator();
//
//        while(var2.hasNext()) {
//            HttpMessageConverter<?> json = (HttpMessageConverter)var2.next();
//            if (json instanceof MappingJackson2HttpMessageConverter) {
//                MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter)json;
//                jackson2HttpMessageConverter.setObjectMapper(this.objectMapper);
//                break;
//            }
//        }
//
//    }

//    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }
}
