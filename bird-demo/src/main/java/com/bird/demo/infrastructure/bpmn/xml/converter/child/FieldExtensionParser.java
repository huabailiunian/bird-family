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

import javax.xml.stream.XMLStreamReader;

/**
 * @author Tijs Rademakers
 */
public class FieldExtensionParser extends BaseChildElementParser {

    @Override
    public String getElementName() {
        return BpmnXMLConstants.ELEMENT_FIELD;
    }

    @Override
    public boolean accepts(BaseElement element) {
        return ((element instanceof FlowableListener) || (element instanceof ServiceTask) || (element instanceof SendTask) || (element instanceof AbstractFlowableHttpHandler));
    }

    @Override
    public void parseChildElement(XMLStreamReader xtr, BaseElement parentElement, BpmnModel model) throws Exception {

        if (!accepts(parentElement))
            return;

        FieldExtension extension = new FieldExtension();
        BpmnXMLUtil.addXMLLocation(extension, xtr);
        extension.setFieldName(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FIELD_NAME));

        if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FIELD_STRING))) {
            extension.setStringValue(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FIELD_STRING));

        } else if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FIELD_EXPRESSION))) {
            extension.setExpression(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FIELD_EXPRESSION));

        } else {
            boolean readyWithFieldExtension = false;
            try {
                while (!readyWithFieldExtension && xtr.hasNext()) {
                    xtr.next();
                    if (xtr.isStartElement() && BpmnXMLConstants.ELEMENT_FIELD_STRING.equalsIgnoreCase(xtr.getLocalName())) {
                        extension.setStringValue(xtr.getElementText().trim());

                    } else if (xtr.isStartElement() && BpmnXMLConstants.ATTRIBUTE_FIELD_EXPRESSION.equalsIgnoreCase(xtr.getLocalName())) {
                        extension.setExpression(xtr.getElementText().trim());

                    } else if (xtr.isEndElement() && getElementName().equalsIgnoreCase(xtr.getLocalName())) {
                        readyWithFieldExtension = true;
                    }
                }
            } catch (Exception e) {
                LOGGER.warn("Error parsing field extension child elements", e);
            }
        }

        if (parentElement instanceof FlowableListener) {
            ((FlowableListener) parentElement).getFieldExtensions().add(extension);
        } else if (parentElement instanceof ServiceTask) {
            ((ServiceTask) parentElement).getFieldExtensions().add(extension);
        } else if (parentElement instanceof SendTask) {
            ((SendTask) parentElement).getFieldExtensions().add(extension);
        } else {
            ((AbstractFlowableHttpHandler) parentElement).getFieldExtensions().add(extension);
        }
    }
}
