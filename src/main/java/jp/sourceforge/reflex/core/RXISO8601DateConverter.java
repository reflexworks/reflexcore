package jp.sourceforge.reflex.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractBasicConverter;

/**
 * A simple DateConverter conforming to the ISO8601 standard.
 * 
 * @author S.Takezaki
 */
public class RXISO8601DateConverter extends AbstractBasicConverter {

	public boolean canConvert(Class type) {
		return type.equals(Date.class);
	}

	protected Object fromString(String str) {

		try {

			// Date/Time ISO8601 TIME ZONE FORMAT 2006-02-10T10:00Z.
			// for use: String string = RXUtil.isoformat.format(date);
			// slow but,thread safe

			SimpleDateFormat isoformat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ssZZ");
			return isoformat.parse(str);

		} catch (ParseException e) {
			// try with next formatter
		}

		throw new ConversionException("Cannot parse date " + str);
	}

	protected String toString(Object obj) {
		SimpleDateFormat isoformat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ssZZ");
		return isoformat.format(obj);
	}

}
