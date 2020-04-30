package com.bird.demo.app.rest;

import com.bird.commons.constant.Const;
import com.bird.commons.tools.IdWorker;
import com.bird.demo.infrastructure.bpmn.constants.BpmnXMLConstants;
import com.bird.demo.infrastructure.bpmn.constants.EditorJsonConstants;
import com.bird.demo.infrastructure.bpmn.constants.ModelDataJsonConstants;
import com.bird.demo.infrastructure.bpmn.constants.StencilConstants;
import com.bird.demo.infrastructure.bpmn.json.converter.BpmnJsonConverter;
import com.bird.demo.infrastructure.bpmn.xml.converter.BpmnXMLConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author youly
 * 2019/7/16 10:12
 */
@Controller
@RequestMapping("/modeler")
public class ModelerController implements ModelDataJsonConstants, StencilConstants, EditorJsonConstants {

    private Logger logger = LoggerFactory.getLogger(ModelerController.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BpmnJsonConverter bpmnJsonConverter;
    @Autowired
    private BpmnXMLConverter bpmnXMLConverter;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(value = {"/editor/stencilset"}, method = {RequestMethod.GET}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getStencilset() {
        InputStream stream = ClassLoader.getSystemResourceAsStream("static/stencilset_CN.json");
        try {
            return IOUtils.toString(stream, Const.CHARSET_UTF8);
        } catch (Exception var3) {
            throw new RuntimeException("Error while loading stencil set", var3);
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/model/{modelId}/json"}, method = {RequestMethod.GET}, produces = {"application/json;charset=utf-8"})
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = objectMapper.createObjectNode();
        String name = "test";
        modelNode.put(MODEL_NAME, name);
        modelNode.put(MODEL_ID, modelId);
        modelNode.put(MODEL_DESCRIPTION, "测试");
        modelNode.set("model", initProcess(modelId, name));
        return modelNode;
    }

    @RequestMapping(value = {"/model/{modelId}/save"}, method = {RequestMethod.POST})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {
        try {
            System.out.println(modelId);
            String jsonXml = values.getFirst("json_xml");
            System.out.println(jsonXml);
            BpmnModel bpmnModel = bpmnJsonConverter.convertToBpmnModel(objectMapper.readTree(jsonXml));
            byte[] bytes = bpmnXMLConverter.convertToXML(bpmnModel, Const.CHARSET_UTF8.name());
            System.out.println(new String(bytes,Const.CHARSET_UTF8));
//            Model model = this.repositoryService.getModel(modelId);
//            ObjectNode modelJson = (ObjectNode) this.objectMapper.readTree(model.getMetaInfo());
//            modelJson.put("name", (String) values.getFirst("name"));
//            modelJson.put("description", (String) values.getFirst("description"));
//            model.setMetaInfo(modelJson.toString());
//            model.setName((String) values.getFirst("name"));
//            this.repositoryService.saveModel(model);
//            this.repositoryService.addModelEditorSource(model.getId(), ((String) values.getFirst("json_xml")).getBytes("utf-8"));
//            InputStream svgStream = new ByteArrayInputStream(((String) values.getFirst("svg_xml")).getBytes("utf-8"));
//            TranscoderInput input = new TranscoderInput(svgStream);
//            PNGTranscoder transcoder = new PNGTranscoder();
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            TranscoderOutput output = new TranscoderOutput(outStream);
//            transcoder.transcode(input, output);
//            byte[] result = outStream.toByteArray();
//            this.repositoryService.addModelEditorSourceExtra(model.getId(), result);
//            outStream.close();
        } catch (Exception var11) {
            logger.error("Error saving model", var11);
            throw new RuntimeException("Error saving model", var11);
        }
    }

    private ObjectNode initProcess(String id, String name) {
        ObjectNode process = objectMapper.createObjectNode();
        process.put(EDITOR_SHAPE_ID, id);

        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        process.set("stencilset", stencilSetNode);

        ObjectNode propertiesNode = objectMapper.createObjectNode();
        propertiesNode.put(PROPERTY_NAME, name);
        propertiesNode.put(PROPERTY_PROCESS_ID, id);
        propertiesNode.put(PROPERTY_PROCESS_NAMESPACE, BpmnXMLConstants.PROCESS_NAMESPACE);
        process.set(EDITOR_SHAPE_PROPERTIES, propertiesNode);
        return process;
    }

    @RequestMapping("/create")
    public void create(HttpServletResponse response) {
        try {
            long id = idWorker.nextId();
            response.sendRedirect("/static/modeler.html?modelId=A" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping
    public String index(){
        return "index";
    }
}
