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
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;

import javax.xml.stream.XMLStreamReader;

public class OutParameterParser extends BaseChildElementParser {

    @Override
    public String getElementName() {
        return BpmnXMLConstants.ELEMENT_OUT_PARAMETERS;
    }

    @Override
    public void parseChildElement(XMLStreamReader xtr, BaseElement parentElement, BpmnModel model) throws Exception {
        String source = xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_IOPARAMETER_SOURCE);
        String sourceExpression = xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_IOPARAMETER_SOURCE_EXPRESSION);
        String target = xtr.getAttributeValue(null, BpmnXMLConstants.ATTRIBUTE_IOPARAMETER_TARGET);
        if ((StringUtils.isNotEmpty(source) || StringUtils.isNotEmpty(sourceExpression)) && StringUtils.isNotEmpty(target)) {

            IOParameter parameter = new IOParameter();
            if (StringUtils.isNotEmpty(sourceExpression)) {
                parameter.setSourceExpression(sourceExpression);
            } else {
                parameter.setSource(source);
            }

            parameter.setTarget(target);

            if (parentElement instanceof CallActivity) {
                ((CallActivity) parentElement).getOutParameters().add(parameter);
                
            } else if (parentElement instanceof CaseServiceTask) {
                ((CaseServiceTask) parentElement).getOutParameters().add(parameter);
            }
        }
    }
}