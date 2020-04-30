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
package com.bird.demo.infrastructure.bpmn.xml.converter.child;

import com.bird.demo.infrastructure.bpmn.constants.BpmnXMLConstants;
import com.bird.demo.infrastructure.bpmn.xml.converter.util.BpmnXMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.CompensateEventDefinition;
import org.flowable.bpmn.model.Event;

import javax.xml.stream.XMLStreamReader;

/**
 * @author Tijs Rademakers
 */
public class CompensateEventDefinitionParser extends BaseChildElementParser {

    @Override
    public String getElementName() {
        return BpmnXMLConstants.ELEMENT_EVENT_COMPENSATEDEFINITION;
    }

    @Override
    public void parseChildElement(XMLStreamReader xtr, BaseElement parentElement, BpmnModel model) throws Exception {
        if (!(parentElement instanceof Event))
            return;

        CompensateEventDefinition eventDefinition = new CompensateEventDefinition();
        BpmnXMLUtil.addXMLLocation(eventDefinition, xtr);
        eventDefinition.setActivityRef(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_COMPENSATE_ACTIVITYREF));
        if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_COMPENSATE_WAITFORCOMPLETION))) {
            eventDefinition.setWaitForCompletion(Boolean.parseBoolean(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_COMPENSATE_WAITFORCOMPLETION)));
        }

        BpmnXMLUtil.parseChildElements(BpmnXMLConstants.ELEMENT_EVENT_COMPENSATEDEFINITION, eventDefinition, xtr, model);

        ((Event) parentElement).getEventDefinitions().add(eventDefinition);
    }
}
