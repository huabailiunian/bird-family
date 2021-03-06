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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.bpmn.model.*;

import java.util.List;
import java.util.Map;

/**
 * @author Tijs Rademakers
 */
public class SubProcessJsonConverter extends BaseBpmnJsonConverter {
  
    public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
            Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
      fillJsonTypes(convertersToBpmnMap);
      fillBpmnTypes(convertersToJsonMap);
    }
  
    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
      convertersToBpmnMap.put(STENCIL_SUB_PROCESS, SubProcessJsonConverter.class);
    }
  
    public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
      convertersToJsonMap.put(SubProcess.class, SubProcessJsonConverter.class);
      convertersToJsonMap.put(Transaction.class, SubProcessJsonConverter.class);
    }
  
    protected String getStencilId(BaseElement baseElement) {
      return STENCIL_SUB_PROCESS;
    }

    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
      SubProcess subProcess = (SubProcess) baseElement;
    
      propertiesNode.put("activitytype", "Sub-Process");
      propertiesNode.put("subprocesstype", "Embedded");
      ArrayNode subProcessShapesArrayNode = objectMapper.createArrayNode();
      GraphicInfo graphicInfo = model.getGraphicInfo(subProcess.getId());
      processor.processFlowElements(subProcess, model, subProcessShapesArrayNode,
              graphicInfo.getX(), graphicInfo.getY());
      flowElementNode.set("childShapes", subProcessShapesArrayNode);
      
      if (subProcess instanceof Transaction) {
        propertiesNode.put("istransaction", true);
      }
      
      BpmnJsonConverterUtil.convertDataPropertiesToJson(subProcess.getDataObjects(), propertiesNode);
    }
  
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
      SubProcess subProcess = null;
      if (getPropertyValueAsBoolean("istransaction", elementNode)) {
        subProcess = new Transaction();
        
      } else {
        subProcess = new SubProcess();
      }
      
      JsonNode childShapesArray = elementNode.get(EDITOR_CHILD_SHAPES);
      processor.processJsonElements(childShapesArray, modelNode, subProcess, shapeMap, model);
      
      JsonNode processDataPropertiesNode = elementNode.get(EDITOR_SHAPE_PROPERTIES).get(PROPERTY_DATA_PROPERTIES);
      if (processDataPropertiesNode != null) {
        List<ValuedDataObject> dataObjects = BpmnJsonConverterUtil.convertJsonToDataProperties(processDataPropertiesNode, subProcess);
        subProcess.setDataObjects(dataObjects);
        subProcess.getFlowElements().addAll(dataObjects);
      }
      
      return subProcess;
    }

}
