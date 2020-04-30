package com.bird.demo.infrastructure.bpmn.xml.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Tijs Rademakers
 */
public class XMLStreamReaderUtil {

    protected static final Logger LOGGER = LoggerFactory.getLogger(XMLStreamReaderUtil.class);

    public static String moveDown(XMLStreamReader xtr) {
        try {
            while (xtr.hasNext()) {
                int event = xtr.next();
                switch (event) {
                case XMLStreamConstants.END_DOCUMENT:
                    return null;
                case XMLStreamConstants.START_ELEMENT:
                    return xtr.getLocalName();
                case XMLStreamConstants.END_ELEMENT:
                    return null;
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Error while moving down in XML document", e);
        }
        return null;
    }

    public static boolean moveToEndOfElement(XMLStreamReader xtr, String elementName) {
        try {
            while (xtr.hasNext()) {
                int event = xtr.next();
                switch (event) {
                case XMLStreamConstants.END_DOCUMENT:
                    return false;
                case XMLStreamConstants.END_ELEMENT:
                    if (xtr.getLocalName().equals(elementName))
                        return true;
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Error while moving to end of element {}", elementName, e);
        }
        return false;
    }
}
