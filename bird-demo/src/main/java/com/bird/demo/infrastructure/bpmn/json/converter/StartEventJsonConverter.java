/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bird.demo.infrastructure.bpmn.json.converter;

import com.bird.demo.infrastructure.bpmn.constants.StencilConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.bpmn.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Tijs Rademakers
 */
public class StartEventJsonConverter extends BaseBpmnJsonConverter {

  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(StencilConstants.STENCIL_EVENT_START_NONE, StartEventJsonConverter.class);
    convertersToBpmnMap.put(StencilConstants.STENCIL_EVENT_START_TIMER, StartEventJsonConverter.class);
    convertersToBpmnMap.put(StencilConstants.STENCIL_EVENT_START_ERROR, StartEventJsonConverter.class);
    convertersToBpmnMap.put(StencilConstants.STENCIL_EVENT_START_MESSAGE, StartEventJsonConverter.class);
    convertersToBpmnMap.put(StencilConstants.STENCIL_EVENT_START_SIGNAL, StartEventJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(StartEvent.class, StartEventJsonConverter.class);
  }
  
  protected String getStencilId(BaseElement baseElement) {
    Event event = (Event) baseElement;
    if (event.getEventDefinitions().size() > 0) {
      EventDefinition eventDefinition = event.getEventDefinitions().get(0);
      if (eventDefinition instanceof TimerEventDefinition) {
        return StencilConstants.STENCIL_EVENT_START_TIMER;
      } else if (eventDefinition instanceof ErrorEventDefinition) {
        return StencilConstants.STENCIL_EVENT_START_ERROR;
      } else if (eventDefinition instanceof MessageEventDefinition) {
        return StencilConstants.STENCIL_EVENT_START_MESSAGE;
      } else if (eventDefinition instanceof SignalEventDefinition) {
        return StencilConstants.STENCIL_EVENT_START_SIGNAL;
      } 
    }
    return StencilConstants.STENCIL_EVENT_START_NONE;
  }
  
  protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
    StartEvent startEvent = (StartEvent) baseElement;
    if (StringUtils.isNotEmpty(startEvent.getInitiator())) {
    	propertiesNode.put(StencilConstants.PROPERTY_NONE_STARTEVENT_INITIATOR, startEvent.getInitiator());
    }
    if (StringUtils.isNotEmpty(startEvent.getFormKey())) {
    	propertiesNode.put(StencilConstants.PROPERTY_FORMKEY, startEvent.getFormKey());
    }
    addFormProperties(startEvent.getFormProperties(), propertiesNode);
    addEventProperties(startEvent, propertiesNode);
  }
  
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    StartEvent startEvent = new StartEvent();
    startEvent.setInitiator(getPropertyValueAsString(StencilConstants.PROPERTY_NONE_STARTEVENT_INITIATOR, elementNode));
    String stencilId = BpmnJsonConverterUtil.getStencilId(elementNode);
    if (StencilConstants.STENCIL_EVENT_START_NONE.equals(stencilId)) {
      String formKey = getPropertyValueAsString(StencilConstants.PROPERTY_FORMKEY, elementNode);
      if (StringUtils.isNotEmpty(formKey)) {
        startEvent.setFormKey(formKey);
      }
    	convertJsonToFormProperties(elementNode, startEvent);
    	
    } else if (StencilConstants.STENCIL_EVENT_START_TIMER.equals(stencilId)) {
    	convertJsonToTimerDefinition(elementNode, startEvent);
    } else if (StencilConstants.STENCIL_EVENT_START_ERROR.equals(stencilId)) {
    	convertJsonToErrorDefinition(elementNode, startEvent);
    } else if (StencilConstants.STENCIL_EVENT_START_MESSAGE.equals(stencilId)) {
    	convertJsonToMessageDefinition(elementNode, startEvent);
    } else if (StencilConstants.STENCIL_EVENT_START_SIGNAL.equals(stencilId)) {
    	convertJsonToSignalDefinition(elementNode, startEvent);
    }
    return startEvent;
  }
}
