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
import com.bird.demo.infrastructure.bpmn.xml.converter.export.ProcessExport;
import com.bird.demo.infrastructure.bpmn.xml.converter.util.BpmnXMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;

import javax.xml.stream.XMLStreamReader;
import java.util.List;

/**
 * @author Tijs Rademakers
 */
public class ProcessParser implements BpmnXMLConstants {

    @SuppressWarnings("unchecked")
    public Process parse(XMLStreamReader xtr, BpmnModel model) throws Exception {
        Process process = null;
        if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, ATTRIBUTE_ID))) {
            String processId = xtr.getAttributeValue(null, ATTRIBUTE_ID);
            process = new Process();
            process.setId(processId);
            BpmnXMLUtil.addXMLLocation(process, xtr);
            process.setName(xtr.getAttributeValue(null, ATTRIBUTE_NAME));
            if (StringUtils.isNotEmpty(xtr.getAttributeValue(null, ATTRIBUTE_PROCESS_EXECUTABLE))) {
                process.setExecutable(Boolean.parseBoolean(xtr.getAttributeValue(null, ATTRIBUTE_PROCESS_EXECUTABLE)));
            }

            String candidateUsersString = BpmnXMLUtil.getAttributeValue(ATTRIBUTE_PROCESS_CANDIDATE_USERS, xtr);
            if (StringUtils.isNotEmpty(candidateUsersString)) {
                List<String> candidateUsers = BpmnXMLUtil.parseDelimitedList(candidateUsersString);
                process.setCandidateStarterUsers(candidateUsers);
            }

            String candidateGroupsString = BpmnXMLUtil.getAttributeValue(ATTRIBUTE_PROCESS_CANDIDATE_GROUPS, xtr);
            if (StringUtils.isNotEmpty(candidateGroupsString)) {
                List<String> candidateGroups = BpmnXMLUtil.parseDelimitedList(candidateGroupsString);
                process.setCandidateStarterGroups(candidateGroups);
            }
            
            if (StringUtils.isNotEmpty(BpmnXMLUtil.getAttributeValue(ATTRIBUTE_PROCESS_EAGER_EXECUTION_FETCHING, xtr))) {
                process.setEnableEagerExecutionTreeFetching(
                        Boolean.parseBoolean(BpmnXMLUtil.getAttributeValue(ATTRIBUTE_PROCESS_EAGER_EXECUTION_FETCHING, xtr)));
            }
            
            BpmnXMLUtil.addCustomAttributes(xtr, process, ProcessExport.defaultProcessAttributes);

            model.getProcesses().add(process);

        }
        return process;
    }
}
