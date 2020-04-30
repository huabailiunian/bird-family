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
package com.bird.demo.infrastructure.bpmn.xml.converter;

import com.bird.demo.infrastructure.bpmn.constants.BpmnXMLConstants;
import com.bird.demo.infrastructure.bpmn.xml.converter.util.BpmnXMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Association;
import org.flowable.bpmn.model.AssociationDirection;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.BpmnModel;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Tijs Rademakers
 */
public class AssociationXMLConverter extends BaseBpmnXMLConverter {

    @Override
    public Class<? extends BaseElement> getBpmnElementType() {
        return Association.class;
    }

    @Override
    protected String getXMLElementName() {
        return BpmnXMLConstants.ELEMENT_ASSOCIATION;
    }

    @Override
    protected BaseElement convertXMLToElement(XMLStreamReader xtr, BpmnModel model) throws Exception {
        Association association = new Association();
        BpmnXMLUtil.addXMLLocation(association, xtr);
        association.setSourceRef(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FLOW_SOURCE_REF));
        association.setTargetRef(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_FLOW_TARGET_REF));
        association.setId(xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_ID));

        String associationDirectionString = xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_ASSOCIATION_DIRECTION);
        if (StringUtils.isNotEmpty(associationDirectionString)) {
            AssociationDirection associationDirection = AssociationDirection.valueOf(associationDirectionString.toUpperCase());

            association.setAssociationDirection(associationDirection);
        }

        parseChildElements(getXMLElementName(), association, model, xtr);

        return association;
    }

    @Override
    protected void writeAdditionalAttributes(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
        Association association = (Association) element;
        writeDefaultAttribute(BpmnXMLConstants.ATTRIBUTE_FLOW_SOURCE_REF, association.getSourceRef(), xtw);
        writeDefaultAttribute(BpmnXMLConstants.ATTRIBUTE_FLOW_TARGET_REF, association.getTargetRef(), xtw);
        AssociationDirection associationDirection = association.getAssociationDirection();
        if (associationDirection != null) {
            writeDefaultAttribute(BpmnXMLConstants.ATTRIBUTE_ASSOCIATION_DIRECTION, associationDirection.getValue(), xtw);
        }
    }

    @Override
    protected void writeAdditionalChildElements(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
    }
}
