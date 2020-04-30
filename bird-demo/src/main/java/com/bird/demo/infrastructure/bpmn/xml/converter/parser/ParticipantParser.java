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
package com.bird.demo.infrastructure.bpmn.xml.converter.parser;

import com.bird.demo.infrastructure.bpmn.constants.BpmnXMLConstants;
import com.bird.demo.infrastructure.bpmn.xml.converter.util.BpmnXMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamReader;

/**
 * @author Tijs Rademakers
 */
public class ParticipantParser implements BpmnXMLConstants {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ParticipantParser.class.getName());

    public void parse(XMLStreamReader xtr, BpmnModel model) throws Exception {

        if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, ATTRIBUTE_ID))) {
            Pool pool = new Pool();
            pool.setId(xtr.getAttributeValue(null, ATTRIBUTE_ID));
            pool.setName(xtr.getAttributeValue(null, ATTRIBUTE_NAME));
            pool.setProcessRef(xtr.getAttributeValue(null, ATTRIBUTE_PROCESS_REF));
            BpmnXMLUtil.parseChildElements(ELEMENT_PARTICIPANT, pool, xtr, model);
            model.getPools().add(pool);
        }
    }
}
