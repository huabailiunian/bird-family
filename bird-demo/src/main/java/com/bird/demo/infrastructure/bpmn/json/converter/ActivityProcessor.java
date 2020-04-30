package com.bird.demo.infrastructure.bpmn.json.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElementsContainer;

import java.util.Map;

/**
 * @author Tijs Rademakers
 */
public interface ActivityProcessor {

    void processFlowElements(FlowElementsContainer container, BpmnModel model, ArrayNode shapesArrayNode,
                             double subProcessX, double subProcessY);

    void processJsonElements(JsonNode shapesArrayNode, JsonNode modelNode, BaseElement parentElement,
                             Map<String, JsonNode> shapeMap, BpmnModel bpmnModel);
}
