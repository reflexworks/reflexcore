package jp.sourceforge.reflex.core;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.Converter;

public class TextConverter extends RXUtil implements Converter  {

    public boolean canConvert(Class type) {
        return isText(type);
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {

    	
		writer.setValue(getTextValue(source));
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

    	return newTextInstance(reader.getValue());
    }

}
