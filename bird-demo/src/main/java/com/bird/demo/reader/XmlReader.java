package com.bird.demo.reader;

import com.bird.commons.tools.StringTools;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author youly
 * 2019/11/21 17:47
 */
@SuppressWarnings("unchecked")
public class XmlReader {
    public static void read(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element element = document.getRootElement();

        List<Element> nodes = element.selectNodes("dictionary/word");
        for (Element node : nodes) {
            String name = node.elementText("physical_name");
            System.out.println(StringTools.underlineToLowerCamel(name));
        }

        List<Element> tables = element.selectNodes("contents/table");
        for (Element table : tables) {
            String name = table.elementText("physical_name");
            System.out.println(StringTools.underlineToUpperCamel(name));
        }
    }

    public static void main(String[] args) throws DocumentException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("rcs.erm");
        XmlReader.read(inputStream);
    }
}
