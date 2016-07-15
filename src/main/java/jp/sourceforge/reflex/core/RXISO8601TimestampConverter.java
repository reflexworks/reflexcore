package jp.sourceforge.reflex.core;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.sourceforge.reflex.util.DateUtil;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractBasicConverter;

/**
 * A simple TimeConverter conforming to the ISO8601 standard.
 * 
 * @author S.Takezaki
 */
public class RXISO8601TimestampConverter extends AbstractBasicConverter {

	public boolean canConvert(Class type) {
		return type.equals(Timestamp.class);
	}

	protected Object fromString(String str) {

		try {
			// Date/Time ISO8601 TIME ZONE FORMAT 2006-02-10T10:00Z.
			// for use: String string = RXUtil.isoformat.format(date);
			// slow but,thread safe

			//SimpleDateFormat isoformat = new SimpleDateFormat(
			//		"yyyy-MM-dd'T'HH:mm:ssZZ");
			//return isoformat.parse(str);
			
			return DateUtil.getDate(str);


		} catch (ParseException e) {
			// try with next formatter
		}

		throw new ConversionException("Cannot parse date " + str);
	}

	protected String toString(Object obj) {
		// TODO 戻り値のフォーマットを指定したい
		//SimpleDateFormat isoformat = new SimpleDateFormat(
		//		"yyyy-MM-dd'T'HH:mm:ssZZ");
		SimpleDateFormat isoformat = new SimpleDateFormat(
				DateUtil.FORMAT_PATTERN);
		return isoformat.format(obj);
	}

}
