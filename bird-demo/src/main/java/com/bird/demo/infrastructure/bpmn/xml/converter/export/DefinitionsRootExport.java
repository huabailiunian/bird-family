package com.bird.demo.infrastructure.bpmn.xml.converter.export;

import com.bird.demo.infrastructure.bpmn.constants.BpmnXMLConstants;
import com.bird.demo.infrastructure.bpmn.xml.converter.util.BpmnXMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ExtensionAttribute;

import javax.xml.stream.XMLStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefinitionsRootExport implements BpmnXMLConstants {

    /** default namespaces for definitions */
    protected static final Set<String> defaultNamespaces = new HashSet<>(Arrays.asList(XSI_PREFIX, XSD_PREFIX, FLOWABLE_EXTENSIONS_PREFIX, BPMNDI_PREFIX, OMGDC_PREFIX, OMGDI_PREFIX));

    protected static final List<ExtensionAttribute> defaultAttributes = Arrays.asList(new ExtensionAttribute(TYPE_LANGUAGE_ATTRIBUTE), new ExtensionAttribute(EXPRESSION_LANGUAGE_ATTRIBUTE),
            new ExtensionAttribute(TARGET_NAMESPACE_ATTRIBUTE));

    @SuppressWarnings("unchecked")
    public static void writeRootElement(BpmnModel model, XMLStreamWriter xtw, String encoding) throws Exception {
        xtw.writeStartDocument(encoding, "1.0");

        // start definitions root element
        xtw.writeStartElement(ELEMENT_DEFINITIONS);
        xtw.setDefaultNamespace(BPMN2_NAMESPACE);
        xtw.writeDefaultNamespace(BPMN2_NAMESPACE);
        xtw.writeNamespace(XSI_PREFIX, XSI_NAMESPACE);
        xtw.writeNamespace(XSD_PREFIX, SCHEMA_NAMESPACE);
        xtw.writeNamespace(FLOWABLE_EXTENSIONS_PREFIX, FLOWABLE_EXTENSIONS_NAMESPACE);
        xtw.writeNamespace(BPMNDI_PREFIX, BPMNDI_NAMESPACE);
        xtw.writeNamespace(OMGDC_PREFIX, OMGDC_NAMESPACE);
        xtw.writeNamespace(OMGDI_PREFIX, OMGDI_NAMESPACE);
        for (String prefix : model.getNamespaces().keySet()) {
            if (!defaultNamespaces.contains(prefix) && StringUtils.isNotEmpty(prefix))
                xtw.writeNamespace(prefix, model.getNamespaces().get(prefix));
        }
        xtw.writeAttribute(TYPE_LANGUAGE_ATTRIBUTE, TYPE_LANGUAGE_ATTRIBUTE_VALUE);
        xtw.writeAttribute(EXPRESSION_LANGUAGE_ATTRIBUTE, EXPRESSION_LANGUAGE_ATTRIBUTE_VALUE);
//        xtw.writeAttribute(TYPE_LANGUAGE_ATTRIBUTE, SCHEMA_NAMESPACE);
//        xtw.writeAttribute(EXPRESSION_LANGUAGE_ATTRIBUTE, XPATH_NAMESPACE);
        if (StringUtils.isNotEmpty(model.getTargetNamespace())) {
            xtw.writeAttribute(TARGET_NAMESPACE_ATTRIBUTE, model.getTargetNamespace());
        } else {
            xtw.writeAttribute(TARGET_NAMESPACE_ATTRIBUTE, PROCESS_NAMESPACE);
        }

        BpmnXMLUtil.writeCustomAttributes(model.getDefinitionsAttributes().values(), xtw, model.getNamespaces(), defaultAttributes);
    }
}
