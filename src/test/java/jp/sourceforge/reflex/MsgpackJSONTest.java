package jp.sourceforge.reflex;


import java.io.IOException;
import java.util.Map.Entry;
import java.util.Stack;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.type.ValueFactory;
import org.msgpack.util.json.JSONBufferPacker;
import org.msgpack.util.json.JSONBufferUnpacker;

public class MsgpackJSONTest {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

        MessagePack msgpack = new MessagePack();

        Value v = ValueFactory.createMapValue(new Value[] {
                        ValueFactory.createRawValue("k1"),
                        ValueFactory.createIntegerValue(1),
                        ValueFactory.createRawValue("k2"),
                        ValueFactory.createArrayValue(new Value[] {
                            ValueFactory.createNilValue(),
                            ValueFactory.createBooleanValue(true),
                            ValueFactory.createBooleanValue(false)
                        }),
                        ValueFactory.createRawValue("k3"),
                        ValueFactory.createFloatValue(0.1)
                    });
        
        

        JSONBufferPacker pk = new JSONBufferPacker(msgpack);
        try {
			pk.write(v);

        byte[] raw = pk.toByteArray();

        String str = new String(raw);
//        assertEquals("{\"k1\":1,\"k2\":[null,true,false],\"k3\":0.1}", str);

//        JSONBufferUnpacker u = new JSONBufferUnpacker(msgpack).wrap("{\"k1\":1,\"k2\":[null,true,{\"k3\":\"a'¥bc\"}],\"k3\":0.1}".getBytes());
        JSONBufferUnpacker u = new JSONBufferUnpacker(msgpack).wrap("{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : \"ポップス\"}}}}".getBytes());
        Value v2 = u.readValue();

    	System.out.println(v2.asMapValue().entrySet());
    	
    	MsgpackJSONTest mt = new MsgpackJSONTest();
    	mt.setValue("Feed",v2);
    	
    	/*
        if (v2.isMapValue()) {
        	for(Entry e:v2.asMapValue().entrySet()) {
            	System.out.println("key="+e.getKey()+"value="+e.getValue());
            	if (((Value)e.getValue()).isMapValue()) {
                	for(Entry e2:((Value)e.getValue()).asMapValue().entrySet()) {
                		System.out.println("key="+e2.getKey()+"value="+e2.getValue());
                	}
            	}
        	}
        }*/

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

//        assertEquals(v, v2);

	}
	
	
	public void setValue(String classname,Value v) {
        if (v.isMapValue()) {
        	for(Entry<Value,Value> e:v.asMapValue().entrySet()) {
        		if ((e.getValue()).isMapValue()) {
        			String next = e.getKey().toString().replace("\"", "");
        			setValue(next,e.getValue());
        			System.out.println("parent="+classname+" child="+next);
        		}else {
        			if (e.getValue().isArrayValue()) break;
        			else {
                		System.out.println("key="+classname+"."+e.getKey().toString().replace("\"", "")+" value="+e.getValue());
        			}
        		}
        	}
        }
		
	}

}
