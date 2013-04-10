package jp.sourceforge.reflex.soap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jp.sourceforge.reflex.core.ResourceMapper;

public class SOAPRequester {

	private ResourceMapper rx;

	public SOAPRequester(String packagename) {

		Map jo_package = new HashMap();
		jo_package.put("http://schemas.xmlsoap.org/soap/envelope/",
				"jp.sourceforge.reflex.soap"); // ""�̓f�t�H���g�̖��O���
		jo_package.put("ns1", packagename);

		rx = new ResourceMapper(jo_package);

	}

	public ResourceMapper getRXStream() {
		return rx;
	}

	public Envelope doRequest(Envelope envelope, String endpointurl) {

		try {

			URL url = new URL(endpointurl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			uc.setDoOutput(true);// POST�\�ɂ���
			uc.setRequestMethod("POST");
			setHttpHeader(uc);
			int code;

			uc.connect();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(uc.getOutputStream(), "UTF-8")));
			rx.toXML(envelope, out); // serialize

			out.flush();
			out.close();

			code = uc.getResponseCode(); // execute command actually
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc
					.getInputStream(), "UTF-8"));
			envelope = (Envelope) rx.fromXML(reader); // deserialize

			uc.disconnect();

		} catch (IOException e) {
		}

		return envelope;

	}

	private void setHttpHeader(HttpURLConnection uc) {

		uc
				.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.1.4322)");
		uc.setRequestProperty("Accept", "application/soap");
		uc.setRequestProperty("Content-Type", "application/soap");
		// uc.setRequestProperty("Accept-Encoding","gzip");
	}

}
