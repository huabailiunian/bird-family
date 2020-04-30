package com.bird.demo.app.config;

import com.bird.demo.infrastructure.bpmn.json.converter.BpmnJsonConverter;
import com.bird.demo.infrastructure.bpmn.xml.converter.BpmnXMLConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author master
 * @date 2020-04-30 09:34
 */
@Configuration
public class RuleFlowConfig {

    @Bean
    public BpmnXMLConverter bpmnXMLConverter(){
        return new BpmnXMLConverter();
    }

    @Bean
    public BpmnJsonConverter bpmnJsonConverter(){
        return new BpmnJsonConverter();
    }

}
