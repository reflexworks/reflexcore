package jp.sourceforge.reflex.util;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 * XXE対策済みのXMLInputFactoryを生成するユーティリティ.
 */
public final class XmlInputFactoryUtil {

	private XmlInputFactoryUtil() {
		// Do nothing.
	}

	/**
	 * DTDと外部実体を無効化したXMLInputFactoryを生成する.
	 * @return セキュア設定済みXMLInputFactory
	 */
	public static XMLInputFactory newSecureFactory() {
		XMLInputFactory factory = XMLInputFactory.newFactory();
		factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
		factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
		factory.setXMLResolver((publicId, systemId, baseURI, namespace) -> {
			throw new XMLStreamException("External entity resolution is disabled.");
		});
		return factory;
	}

}
