package com.bird.codegen.engine;

import com.bird.codegen.DataObject;
import com.bird.codegen.enums.Visibility;
import com.bird.codegen.exception.CodeGenException;
import com.bird.codegen.impl.DataObjectImpl;
import org.junit.Test;

/**
 * @author master
 * @date 2020-04-24 15:07
 */
public class CodeGenEngineTest {


    @Test
    public void test() throws CodeGenException {
        DataObject dataObject = new DataObjectImpl("com.bird.test","Demo","测试");
        dataObject.addProperty("name","姓名",String.class.getName(), Visibility.PRIVATE);
        dataObject.addProperty("age","年龄",Integer[].class.getSimpleName());
        Context context = new Context(dataObject);
        CodeGenEngine codeGenEngine = new CodeGenEngineImpl();
        String data = codeGenEngine.generator(context, CodeGenEngine.DEFAULT_TEMPLATE_POJO);
        System.out.println(data);
    }

}