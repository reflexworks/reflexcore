package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.Calendar;

import jp.sourceforge.reflex.core.ResourceMapper;
import jp.sourceforge.reflex.dto.CalendarEvent;
import jp.sourceforge.reflex.dto.CamelBean;
import jp.sourceforge.reflex.dto.EventCategory;
import jp.sourceforge.reflex.dto.Event__Test;

public class ConverterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();

		CalendarEvent calendarEvent = new CalendarEvent();

		calendarEvent.event__Test = new ArrayList();

		// 1
		Event__Test event = new Event__Test();

		event.eventCategory = new EventCategory();
		// event.eventCategory.category ="1";
		event.eventCategory.categoryname = "holiday";
		event.eventCategory.color = "#666";

		cal.set(2006, 8, 21);
		event.eventDate = cal.getTime();
		event.text__area = "テスト1";
		event.category = 1;

		calendarEvent.event__Test.add(event);

		// 2
		event = new Event__Test();

		event.eventCategory = new EventCategory();
		// event.eventCategory.category ="2";
		event.eventCategory.categoryname = "saturday";
		event.eventCategory.color = "#CCC";

		cal.set(2006, 8, 23);
		event.eventDate = cal.getTime();

		event.text__area = "みんなの日[test:{ko::ko,dayo}]";

		// calendarEvent.event__Test.add(event);

		event = new Event__Test();

		event.eventCategory = new EventCategory();
		// event.eventCategory.category ="3";
		event.eventCategory.categoryname = "saturday";
		event.eventCategory.color = "#990";

		cal.set(2006, 8, 25);
		event.eventDate = cal.getTime();
		event.text__area = "自分の\n日";

		calendarEvent.event__Test.add(event);

		System.out.println("Start");
		// converter.marshal(calendarEvent,new PrintWriter(System.out,true));

		// RXStream rxstream = new RXStream("jp.sourceforge.reflex.dto");
/*		ResourceMapper rxstream = new ResourceMapper(
				"jp.sourceforge.reflex.dto", true, true);

		String toJSON = rxstream.toJSON(calendarEvent);
		System.out.println("json:" + toJSON);

		String toXML = rxstream.toXML(calendarEvent);

		System.out.println("xml:" + toXML);
*/
		// kokokara JSON
		// try {

		// 試しにデシリアリズしてみる XML
		// CalendarEvent calenderEvent2 = (CalendarEvent)
		// rxstream.fromXML(toXML);
		// toXML = rxstream.toXML(calenderEvent2);

		// System.out.println("\nDesirialized(XML):");
		// System.out.println(toXML);

		// System.out.println("\n");

		// 試しにデシリアリズしてみる JSON
		// CalendarEvent calenderEvent3 = (CalendarEvent)
		// rxstream.fromJSON(toJSON);
		// toXML = rxstream.toXML(calenderEvent3);

		// System.out.println("\nDesirialized(JSON):");
		// System.out.println(toXML);
		//
		// } catch (JSONException e) {
		// TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }

		// Camel Test
		CamelBean camelBean = new CamelBean();
		camelBean.Event__Test = new ArrayList();

		event = new Event__Test();

		event.eventCategory = new EventCategory();
		// event.eventCategory.category ="1";
		event.eventCategory.categoryname = "xxxx";
		event.eventCategory.color = "#999";

		camelBean.Event__Test.add(event);
/*
		ResourceMapper rxstream2 = new ResourceMapper(
				"jp.sourceforge.reflex.dto", true);

		// kokokara JSON

		// 試しにデシリアリズしてみる XML
		toXML = rxstream2.toXML(camelBean);

		System.out.println("\nDesirialized(Camel XML):");
		System.out.println(toXML);

		System.out.println("\n");
*/
	}

}
