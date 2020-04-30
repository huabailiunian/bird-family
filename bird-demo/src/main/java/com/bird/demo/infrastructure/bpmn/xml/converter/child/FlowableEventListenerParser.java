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
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;

import javax.xml.stream.XMLStreamReader;

/**
 * @author Frederik Heremans
 */
public class FlowableEventListenerParser extends BaseChildElementParser {

    @Override
    public void parseChildElement(XMLStreamReader xtr, BaseElement parentElement, BpmnModel model) throws Exception {
        EventListener listener = new EventListener();
        BpmnXMLUtil.addXMLLocation(listener, xtr);

        if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_CLASS))) {
            listener.setImplementation(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_CLASS));
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        } else if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_DELEGATEEXPRESSION))) {
            listener.setImplementation(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_DELEGATEEXPRESSION));
            listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
        } else if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_EVENT_TYPE))) {
            String eventType = xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_EVENT_TYPE);
            if (BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_SIGNAL.equals(eventType)) {
                listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_THROW_SIGNAL_EVENT);
                listener.setImplementation(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_SIGNAL_EVENT_NAME));
            } else if (BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_GLOBAL_SIGNAL.equals(eventType)) {
                listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_THROW_GLOBAL_SIGNAL_EVENT);
                listener.setImplementation(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_SIGNAL_EVENT_NAME));
            } else if (BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_MESSAGE.equals(eventType)) {
                listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_THROW_MESSAGE_EVENT);
                listener.setImplementation(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_MESSAGE_EVENT_NAME));
            } else if (BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_ERROR.equals(eventType)) {
                listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_THROW_ERROR_EVENT);
                listener.setImplementation(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_THROW_ERROR_EVENT_CODE));
            } else {
                listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_INVALID_THROW_EVENT);
            }
        }
        
        listener.setEvents(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_EVENTS));
        listener.setEntityType(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_ENTITY_TYPE));
        
        if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_ON_TRANSACTION))){
            listener.setOnTransaction(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_LISTENER_ON_TRANSACTION));
        }

        Process parentProcess = (Process) parentElement;
        parentProcess.getEventListeners().add(listener);
    }

    @Override
    public String getElementName() {
        return BpmnXMLConstants.ELEMENT_EVENT_LISTENER;
    }
}
