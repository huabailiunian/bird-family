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
import org.flowable.bpmn.model.Activity;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.BpmnModel;

import javax.xml.stream.XMLStreamReader;

public class FlowableFailedjobRetryParser extends BaseChildElementParser {

    @Override
    public String getElementName() {
        return BpmnXMLConstants.FAILED_JOB_RETRY_TIME_CYCLE;
    }

    @Override
    public void parseChildElement(XMLStreamReader xtr, BaseElement parentElement, BpmnModel model) throws Exception {
        if (!(parentElement instanceof Activity))
            return;
        String cycle = xtr.getElementText();
        if (cycle == null || cycle.isEmpty()) {
            return;
        }
        ((Activity) parentElement).setFailedJobRetryTimeCycleValue(cycle);
    }

}
