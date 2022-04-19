package jp.reflexworks.atom.mapper;

import jp.reflexworks.atom.entry.EntryBase;
import jp.sourceforge.reflex.IResourceMapper;

public class BQJSONSerializer {

	public static final int MAXROWSIZE = 1024*1024;	// Row size limit: 1 MB

	public static String toJSON(IResourceMapper mapper,EntryBase entry) throws SizeLimitExceededException {

		String json = mapper.toJSON(entry,true);	// trueでBigQuery用JSON出力(___num付)
		if(entry.id!=null) {
			int n = entry.id.indexOf(",");
			StringBuilder sb = new StringBuilder();
			if (n>0) {
				sb.append("{ \"___key\" : \""+entry.id.substring(0,n)+"\"");
				sb.append(",\"___revision\" : \""+entry.id.substring(n+1)+"\"");
				sb.append(",");
			}
			String result = sb.toString() + json.substring(1);
			if (result.length()>MAXROWSIZE) throw new SizeLimitExceededException();
			return result;
		}else {
			return json;
		}
	}


}
