package jp.sourceforge.reflex.soap;

import java.util.HashMap;
import java.util.Map;

import jp.sourceforge.reflex.core.ResourceMapper;
import model.Login;
import model.RequestHdr;

public class ReflexSoapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map jo_package = new HashMap();
		jo_package.put("http://schemas.xmlsoap.org/soap/envelope/",
				"jp.sourceforge.reflex.soap");
		jo_package.put("ns1", "model"); // pref

		ResourceMapper rx = new ResourceMapper(jo_package);

		Envelope _envelope = new Envelope();
		_envelope.body = new Body();
		_envelope.body.login = new Login();
		((Login) _envelope.body.login).requestHdr = new RequestHdr();

		((Login) _envelope.body.login).requestHdr.clientID = "clientid";
		((Login) _envelope.body.login).requestHdr.ver = "version";
		((Login) _envelope.body.login).requestHdr.ver_$title = "xmlns1"; //�����
																			// ̎
																			// w
																			// �
																			// �
																			// i
																			// �
																			// K
																			// �
																			// �
																			// String
																			// �
																			// j

		String toXML = rx.toXML(_envelope);
		System.out.println("\nSerialized Soap message:");
		System.out.println(toXML);

		// desirialize���Ă݂�
		Envelope __envelope = (Envelope) rx.fromXML(toXML);

		toXML = rx.toXML(__envelope);
		System.out.println("\nDesirialized SOAP:");
		System.out.println(toXML);

	}

}
