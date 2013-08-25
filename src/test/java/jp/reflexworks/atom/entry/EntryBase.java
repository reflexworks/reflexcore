package jp.reflexworks.atom.entry;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import org.msgpack.annotation.Message;

import jp.reflexworks.atom.source.Source;

/**
 * Entryの親クラス.
 * <p>
 * ATOM形式のEntryです。<br>
 * このEntryが1件のデータになります。<br>
 * 各プロジェクトでこのクラスを継承し、カスタマイズしたEntryクラスを生成してください。<br>
 * </p>
 */
public abstract class EntryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String KIND = "Entry";
	public static final String TOP = ":";

	// 親のURI＋子のURI＋revision TODO 削除予定
//	protected KeyBase key;

	// 更新のつどカウントUp TODO Integerに変更予定
	protected int revision;
	//protected Integer revision;

	// 日時が登録されているとtrue(削除済)、nullだとfalse(有効レコード)
	protected String deleted;

	// aliasレコードかどうか　true:alias、false:aliasでない TODO Booleanに変更予定
	protected boolean isAlias;
	//protected Boolean isAlias;

	// 自分までのURI
	protected String parent;

	// 自分のURI
	protected String selfid;

	/**
	 * デフォルトの名前空間
	 */
	public String _$xmlns;

	public String _$xml$lang;

	public String _$xml$base;

	/**
	 * 更新者.
	 * <p>
	 * urn:virtual-tech.net:{created|updated|deleted}:{username} の形式です。
	 * </p>
	 */
	public List<Author> author;

	/**
	 * カテゴリ.
	 * <p>
	 * termにプロパティ名、labelに値をセットすることで、検索項目に使用できます。<br>
	 * termに型を指定することもできます。型とプロパティ名をコロンでつないでください。指定できる型は以下の通りです。
	 * <ul>
	 * <li>String</li>
	 * <li>Integer</li>
	 * <li>long</li>
	 * <li>Long</li>
	 * <li>float</li>
	 * <li>Float</li>
	 * <li>double</li>
	 * <li>Double</li>
	 * </ul>
	 * </p>
	 */
	public List<Category> category;

	/**
	 * コンテンツ.
	 * <p>
	 * HTMLなどのテキストコンテンツや、画像のリンク先を設定します。
	 * </p>
	 */
	public Content content;

	/**
	 * 認証・認可情報定義.
	 * <p>
	 * <b>WSSE指定</b><br>
	 * /_user/{username} をキーとするエントリーのｃontributorタグのuriタグに、以下の書式で認証情報を設定します。
	 * <ul>
	 * <li>urn:virtual-tech.net:wsse:{username},{password}</li>
	 * </ul>
	 * <br>
	 * <b>ACL指定</b><br>
	 * uriタグに、以下の書式でACLを設定します。<br><br>
	 * <ul>
	 * <li>urn:virtual-tech.net:acl:{username},{C|R|U|D|A|E}</li>
	 * </ul>
	 * <br>
	 * このACLは、配下のエントリーに対し有効です。<br>
	 * 配下のエントリーにACLの設定がある場合、上位階層で設定されたACLは全て無効となり、配下のACLのみ有効となります。<br>
	 * <br>
	 * <ul>
	 * <li><b>username</b><br>
	 * ログインユーザを指定します。先頭と末尾にワイルドカード(*)が指定できます。<br>
	 * *のみを指定した場合、ログインしていないユーザを含むすべてのユーザに対しACLが適用されます。<br>
	 * ?を指定した場合、ログインしているすべてのユーザに対しACLが適用されます。<br>
	 * <br></li>
	 * <li><b>ACLの種類</b><br>
	 * 以下の種類があります。複数指定可能です。<br>
	 * <ul>
	 * <li>C : 登録処理を許可</li>
	 * <li>R : 検索処理を許可</li>
	 * <li>U : 更新処理を許可</li>
	 * <li>D : 削除処理を許可</li>
	 * <li>A : 管理者 (CRUD権限に加え、権限の付与および参照が可能)</li>
	 * <li>E : 外部サービス呼び出しからのみデータ操作可で、Reflexサービスから直接データ操作が不可。</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * </p>
	 */
	public List<Contributor> contributor;

	/**
	 * ID.
	 * <p>
	 * idの構成は、{キー},{リビジョン}です。<br>
	 * PUTの場合、idで楽観的排他チェックを行います。<br>
	 * DELETEの場合、idのリビジョンで楽観的排他チェックを行います。<br>
	 * </p>
	 */
	public String id;

	public String id_$xml$lang;

	public String id_$xml$base;

	/**
	 * Link.
	 * <p>
	 * linkタグは、rel属性により様々な役割を持ちます。<br>
	 * <br>
	 * <b>キー</b><br>
	 * このエントリーのキーを、rel="self"の、href属性に設定します。<br>
	 * <br>
	 * <b>エイリアス</b><br>
	 * rel="alternate"の、href属性に設定できます。複数指定可能です。<br>
	 * エイリアスで検索したり、ACLを適用させることができます。<br>
	 * エイリアスで検索した場合、キーであるrel="self"のhref属性にエイリアスの値が設定されます。ただしidタグは本体のキーが使用されます。<br>
	 * <br>
	 * <b>コンテンツ</b><br>
	 * rel="related"の場合、href属性に外部コンテンツのURLを指定します。<br>
	 * GETでURLにリダイレクトすることができます。<br>
	 * <br>
	 * <b>Blobstore　(GAE用)</b><br>
	 * rel="related"で、type="blobstore"の場合、Blobstoreのデータを表します。<br>
	 * href属性にBlobKeyの文字列、title属性に名前を指定できます。<br>
	 * <br>
	 * <b>WebHook</b><br>
	 * rel="via"、type="webhook"の場合、href属性に指定されたURLにリクエストします。<br>
	 * リクエストのタイミングは、エントリーの登録・更新後です。<br>
	 * title属性にGET、POST、PUT、DELETEが指定できます。この場合、配下のエントリーが検索・登録・更新・削除された場合リクエストが実行されます。自身の登録・更新時には実行されません。<br>
	 * hrefのURLに?async={数字}パラメータが設定されている場合、{数字}秒後にリクエストを実行します。<br>
	 * <br>
	 * <b>JavaScript</b><br>
	 * rel="via"、type="text/javascript"の場合、href="{キー}#{関数名}"でJavascript実行を指定できます。<br>
	 * Javascriptはキーで指定されたエントリーのcontentに格納されている必要があります。<br>
	 * title属性にGET、POST、PUTが指定できます。この場合、配下のエントリーに対して実行されます。自身には実行されません。<br>
	 * Javascriptはエントリー登録・更新後に実行され、実行結果をデータストアに格納されます。<br>
	 * JavascriptがGET指定されている場合、検索後に実行され、実行結果をレスポンスデータに設定します。<br>
	 * Javascript実行時、rel="related"、type="text/javascript"で指定されたエントリーのcontentをJavascriptのコードに加えることができます。<br>
	 * </p>
	 */
	public List<Link> link;

	public String published;

	public String published_$xml$lang;

	public String published_$xml$base;

	public String rights;

	public String rights_$type;

	public String rights_$xml$lang;

	public String rights_$xml$base;

	public Source source;

	/**
	 * サマリー.
	 * <p>
	 * Reflexでは、登録・更新時やエラー時のメッセージを設定します。
	 * </p>
	 */
	public String summary;

	public String summary_$type;

	public String summary_$xml$lang;

	public String summary_$xml$base;

	/**
	 * タイトル.
	 * <p>
	 * Reflexでは、"Error"や"POST""PUT""DELETE"等を設定します。<br>
	 * Index アノテーションが設定されています。
	 * </p>
	 */
//	@Index
	public String title;

	public String title_$type;

	public String title_$xml$lang;

	public String title_$xml$base;

	/**
	 * サブタイトル.
	 * <p>
	 * Reflexでは、ステータスコードを設定します。
	 * </p>
	 */
	public String subtitle;

	public String subtitle_$type;

	public String subtitle_$xml$lang;

	public String subtitle_$xml$base;

	/**
	 * 更新日時.
	 * <p>
	 * yyyy-MM-dd'T'hh:mm:ss.SSS+99:99 形式です。<br>
	 * Index アノテーションが設定されています。
	 * </p>
	 */
//	@Index
	public String updated;

	public String updated_$xml$lang;

	public String updated_$xml$base;

	// TODO 削除予定
	/**
	 * @deprecated 削除予定です。
	public KeyBase getKey() {
		return key;
	}
	 */

	/**
	 * @deprecated 削除予定です。
	public void setKey(KeyBase key) {
		this.key = key;
	}
	 */

	// TODO Integerに変更予定
	public int getRevision() {
	//public Integer getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
	//public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	public boolean isDeleted() {
		if (!"".equals(deleted)) {
			return true;
		}
		return false;
	}

	// TODO isAliasをBooleanに変更予定(このメソッドの戻り値はbooleanのまま)
	public boolean isAlias() {
		//if (isAlias == null) {
		//	return false;
		//}
		return isAlias;
	}
	
	//public Boolean getIsAlias() {
	//	return isAlias;
	//}

	public void setAlias(boolean isAlias) {
	//public void setAlias(Boolean isAlias) {
		this.isAlias = isAlias;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		if (parent != null) {
			if (TOP.equals(parent)) {
				this.parent = parent;
			} else if (!"/".equals(parent.substring(parent.length() - 1))) {
				this.parent = parent + "/";
			} else {
				this.parent = parent;
			}
		}
	}

	public String getSelfid() {
		return selfid;
	}

	public void setSelfid(String selfid) {
		if (selfid != null) {
			if ("/".equals(selfid)) {
				this.selfid = selfid;	// root layer
			} else if (selfid.length() > 0 && "/".equals(selfid.substring(selfid.length() - 1))) {
				this.selfid = selfid.substring(0, selfid.length() - 1);
			} else {
				this.selfid = selfid;
			}
		}
	}

	public String get_$xmlns() {
		return _$xmlns;
	}

	public void set_$xmlns(String _$xmlns) {
		this._$xmlns = _$xmlns;
	}

	public String get_$xml$lang() {
		return _$xml$lang;
	}

	public void set_$xml$lang(String _$xml$lang) {
		this._$xml$lang = _$xml$lang;
	}

	public String get_$xml$base() {
		return _$xml$base;
	}

	public void set_$xml$base(String _$xml$base) {
		this._$xml$base = _$xml$base;
	}

	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Contributor> getContributor() {
		return contributor;
	}

	public void setContributor(List<Contributor> contributor) {
		this.contributor = contributor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_$xml$lang() {
		return id_$xml$lang;
	}

	public void setId_$xml$lang(String id_$xml$lang) {
		this.id_$xml$lang = id_$xml$lang;
	}

	public String getId_$xml$base() {
		return id_$xml$base;
	}

	public void setId_$xml$base(String id_$xml$base) {
		this.id_$xml$base = id_$xml$base;
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getPublished_$xml$lang() {
		return published_$xml$lang;
	}

	public void setPublished_$xml$lang(String published_$xml$lang) {
		this.published_$xml$lang = published_$xml$lang;
	}

	public String getPublished_$xml$base() {
		return published_$xml$base;
	}

	public void setPublished_$xml$base(String published_$xml$base) {
		this.published_$xml$base = published_$xml$base;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getRights_$type() {
		return rights_$type;
	}

	public void setRights_$type(String rights_$type) {
		this.rights_$type = rights_$type;
	}

	public String getRights_$xml$lang() {
		return rights_$xml$lang;
	}

	public void setRights_$xml$lang(String rights_$xml$lang) {
		this.rights_$xml$lang = rights_$xml$lang;
	}

	public String getRights_$xml$base() {
		return rights_$xml$base;
	}

	public void setRights_$xml$base(String rights_$xml$base) {
		this.rights_$xml$base = rights_$xml$base;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary_$type() {
		return summary_$type;
	}

	public void setSummary_$type(String summary_$type) {
		this.summary_$type = summary_$type;
	}

	public String getSummary_$xml$lang() {
		return summary_$xml$lang;
	}

	public void setSummary_$xml$lang(String summary_$xml$lang) {
		this.summary_$xml$lang = summary_$xml$lang;
	}

	public String getSummary_$xml$base() {
		return summary_$xml$base;
	}

	public void setSummary_$xml$base(String summary_$xml$base) {
		this.summary_$xml$base = summary_$xml$base;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_$type() {
		return title_$type;
	}

	public void setTitle_$type(String title_$type) {
		this.title_$type = title_$type;
	}

	public String getTitle_$xml$lang() {
		return title_$xml$lang;
	}

	public void setTitle_$xml$lang(String title_$xml$lang) {
		this.title_$xml$lang = title_$xml$lang;
	}

	public String getTitle_$xml$base() {
		return title_$xml$base;
	}

	public void setTitle_$xml$base(String title_$xml$base) {
		this.title_$xml$base = title_$xml$base;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSubtitle_$type() {
		return subtitle_$type;
	}

	public void setSubtitle_$type(String subtitle_$type) {
		this.subtitle_$type = subtitle_$type;
	}

	public String getSubtitle_$xml$lang() {
		return subtitle_$xml$lang;
	}

	public void setSubtitle_$xml$lang(String subtitle_$xml$lang) {
		this.subtitle_$xml$lang = subtitle_$xml$lang;
	}

	public String getSubtitle_$xml$base() {
		return subtitle_$xml$base;
	}

	public void setSubtitle_$xml$base(String subtitle_$xml$base) {
		this.subtitle_$xml$base = subtitle_$xml$base;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUpdated_$xml$lang() {
		return updated_$xml$lang;
	}

	public void setUpdated_$xml$lang(String updated_$xml$lang) {
		this.updated_$xml$lang = updated_$xml$lang;
	}

	public String getUpdated_$xml$base() {
		return updated_$xml$base;
	}

	public void setUpdated_$xml$base(String updated_$xml$base) {
		this.updated_$xml$base = updated_$xml$base;
	}

	/**
	 * キーとなるURIを取得します.
	 * <p>
	 * <link rel="self">タグのhrefの値を返却します.
	 * </p>
	 * @return キー
	 */
	public String getMyUri() {
		String myUri = null;
		if (link != null) {
			for (Link childLink : link) {
				if (Link.REL_SELF.equals(childLink._$rel)) {
					myUri = childLink._$href;
				}
			}
		}
		return myUri;
	}

	/*
	public String getMyUriSlash() {
		String myUri = getMyUri();
		return editSlash(myUri);
	}

	public String getParentUri() {
		String myUri = getMyUriSlash();
		return getParentUri(myUri);
	}

	public String getSelfidUri() {
		String myUri = getMyUri();
		return getSelfidUri(myUri);
	}
	*/
	
	/*
	public void setKeyItem() {
		this.setParent(this.getParentUri());
		this.setSelfid(this.getSelfidUri());
		if (this.id != null) {
			this.setRevision(getRevisionFromId());
		}
		this.setDeleted("");
	}
	*/

	/**
	 * このエントリーにキーを設定します.
	 * <p>
	 * 引数の値を、<link rel="self">タグのhref属性に設定します.
	 * </p>
	 * @param uri キー
	 */
	public void setMyUri(String uri) {
		String tmpUri = uri;
		// uriをparentとselfidに分割
		if (uri != null && uri.length() > 0) {
			if ("/".equals(uri)) {	// root layer
				this.setParent(TOP);
				this.setSelfid("/");
				
			} else {
				if ("/".equals(tmpUri.substring(tmpUri.length() - 1))) {
					tmpUri = tmpUri.substring(0, tmpUri.length() - 1);
				}
	
				int slash = tmpUri.lastIndexOf("/");
	
				if (slash == -1) {
					this.setParent("/");
					this.setSelfid(tmpUri);
				} else {
					this.setParent(tmpUri.substring(0, slash + 1));
					this.setSelfid(tmpUri.substring(slash + 1));
				}
			}

		} else {
			// root layer
			this.setParent(TOP);
			this.setSelfid("/");
			tmpUri = "/";
		}
		
		setLinkSelf(tmpUri);
	}
	
	public static String getMyUri(String uri) {
		String tmpUri = uri;
		if (uri != null && uri.length() > 0) {
			if ("/".equals(uri)) {	
				// root layer
				
			} else if (TOP.equals(uri)) {
				// rootの親階層
				
			} else {
				if ("/".equals(tmpUri.substring(tmpUri.length() - 1))) {
					tmpUri = tmpUri.substring(0, tmpUri.length() - 1);
				}
			}

		} else {
			// root layer
			tmpUri = "/";
		}
		
		return tmpUri;
	}
	
	public void setLinkSelf(String uri) {
		if (uri == null || uri.trim().length() == 0) {
			return;
		}
		if (link == null) {
			link = new ArrayList<Link>();
		}
		for (Link childLink : link) {
			if (Link.REL_SELF.equals(childLink._$rel)) {
				childLink._$href = uri;
				return;
			}
		}
		Link childLink = new Link();
		childLink._$rel = Link.REL_SELF;
		childLink._$href = uri;
		link.add(childLink);
	}

	/*
	public String getUriFromId() {
		return getUriFromId(this.id);
	}
	
	public static String getUriFromId(String id) {
		String url = null;
		if (id != null) {
			String[] temp = id.split(",");
			if (temp != null && temp.length >= 1) {
				url = temp[0];
			}
		}

		return url;
	}
	*/

	/*
	public int getRevisionFromId() {
		return getRevisionFromId(this.id);
	}

	public static int getRevisionFromId(String id) {
		int rev = 0;
		if (id != null) {
			String[] temp = id.split(",");
			try {
				if (temp != null && temp.length >= 2) {
					rev = Integer.parseInt(temp[1]);
				}
			} catch (Exception e) {}
		}

		return rev;
	}

	public void setRevisionFromId() {
		this.setRevision(this.getRevisionFromId());
	}
	*/
	
	/**
	 * URI(parent + selfid)を返却.
	 * parentとselfidを設定後に実行してください。
	 */
	public String getUri() {
		if (parent == null && selfid == null) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		if (!isTop(parent)) {
			buf.append(parent);
		}
		if (selfid != null) {
			buf.append(selfid);
		}
		return buf.toString();
	}

	/*
	public static String editSlash(String myUri) {
		if (!isTop(myUri)) {
			if (myUri.lastIndexOf("/") < myUri.length() - 1) {
				myUri = myUri + "/";
			}
		} else {
			myUri = "/";
		}
		return myUri;
	}
	
	public static String removeLastSlash(String myUri) {
		String uri = editSlash(myUri);
		if (uri.length() > 1) {
			uri = uri.substring(0, uri.length() - 1);
		}
		return uri;
	}
	*/

	/**
	 * 親階層を返却します.
	 * <p>
	 * <ul>
	 * <li>uriが"/"の場合、親はnull、子は"/"です。親がnullの場合は":"を返却します。</li>
	 * <li>uriが"/aaa"の場合、親は"/"、子は"aaa"のため、"/"を返却します。</li>
	 * <li>uriが"/aaa/bbb"の場合、親は"/aaa/"、子は"bbb"のため、"/aaa/"を返却します。</li>
	 * </ul>
	 * </p>
	 * @param pMyUri キー
	 * @return キーの親階層
	 */
	/*
	public static String getParentUri(String pMyUri) {
		String parentUri = null;
		String myUri = editSlash(pMyUri);
		if ("/".equals(myUri)) {
			return TOP;	// root layer
		}
		if (myUri != null && myUri.length() > 1) {
			int index = myUri.lastIndexOf("/", myUri.length() - 2);
			if (index > -1) {
				parentUri = myUri.substring(0, index + 1);
			}
		}
		return parentUri;
	}
	*/

	/**
	 * 自分の階層を返却します.
	 * <p>
	 * <ul>
	 * <li>uriが"/"の場合、親はnull、子は"/"のため、"/"を返却します。</li>
	 * <li>uriが"/aaa"の場合、親は"/"、子は"aaa"のため、"aaa"を返却します。</li>
	 * <li>uriが"/aaa/bbb"の場合、親は"/aaa/"、子は"bbb"のため、"bbb"を返却します。</li>
	 * </ul>
	 * </p>
	 * @param pMyUri キー
	 * @return キーの自階層
	 */
	/*
	public static String getSelfidUri(String pMyUri) {
		String selfidUri = null;
		String myUri = editSlash(pMyUri);
		if ("/".equals(myUri)) {
			return "/";	// root layer
		}
		myUri = myUri.substring(0, myUri.length() - 1);
		if (myUri != null) {
			int index = myUri.lastIndexOf("/");
			if (index > -1) {
				selfidUri = myUri.substring(index + 1);
			} else {
				selfidUri = myUri;
			}
		}
		return selfidUri;
	}
	*/
	
	/**
	 * categoryから値を取得します。
	 * @param term キー
	 */
	public String getProperty(String term) {
		if (term == null) {
			return null;
		}
		if (category == null) {
			return null;
		}
		for (Category prop : category) {
			if (term.equals(prop.get_$term())) {
				return prop.get_$label();
			}
		}
		return null;
	}
	
	/**
	 * categoryに値を設定します。
	 * @param term キー
	 * @param label 値
	 */
	public void setProperty(String term, String label) {
		if (term == null) {
			return;
		}
		if (category == null) {
			category = new ArrayList<Category>();
		}
		boolean hasTerm = false;
		for (Category prop : category) {
			if (term.equals(prop.get_$term())) {
				prop.set_$label(label);
				hasTerm = true;
			}
		}
		if (!hasTerm) {
			Category prop = new Category();
			prop.set_$term(term);
			prop.set_$label(label);
			category.add(prop);
		}
	}
	
	/**
	 * エイリアスに指定されたURLを追加します。
	 * @param uri エイリアス
	 */
	public void addAlternate(String uri) {
		if (isTop(uri)) {
			return;
		}
		if (link == null) {
			link = new ArrayList<Link>();
		}
		
		boolean isExist = false;
		for (Link li : link) {
			if (Link.REL_ALTERNATE.equals(li.get_$rel()) && 
					uri.equals(li.get_$href())) {
				isExist = true;
			}
		}
		if (!isExist) {
			Link li = new Link();
			li.set_$rel(Link.REL_ALTERNATE);
			li.set_$href(uri);
			link.add(li);
		}
	}
	
	/**
	 * エイリアスから指定されたURLを削除します。
	 * @param uri エイリアス
	 */
	public void removeAlternate(String uri) {
		if (isTop(uri)) {
			return;
		}
		if (link == null) {
			return;
		}
		
		for (int i = 0; i < link.size(); i++) {
			Link li = link.get(i);
			if (Link.REL_ALTERNATE.equals(li.get_$rel()) && 
					uri.equals(li.get_$href())) {
				link.remove(i);
				break;
			}
		}
	}
	
	/**
	 * エイリアス一覧を取得します。
	 */
	public List<String> getAlternate() {
		if (link == null) {
			return null;
		}
		List<String> aliases = new ArrayList<String>();
		for (Link li : link) {
			if (Link.REL_ALTERNATE.equals(li.get_$rel()) &&
					li._$href != null && li._$href.length() > 0) {
				aliases.add(li.get_$href());
			}
		}
		if (aliases.size() == 0) {
			return null;
		}
		return aliases;
	}
	
	/**
	 * Linkを追加します.
	 * @param ln Link
	 */
	public void addLink(Link ln) {
		if (ln == null) {
			return;
		}
		if (link == null) {
			link = new ArrayList<Link>();
		}
		link.add(ln);
	}
	
	/**
	 * Contributorを追加します.
	 * @param cont Contributor
	 */
	public void addContributor(Contributor cont) {
		if (cont == null) {
			return;
		}
		if (contributor == null) {
			contributor = new ArrayList<Contributor>();
		}
		contributor.add(cont);
	}
	
	/*
	 * 親階層が最上位かどうかを判定します.
	 */
	private static boolean isTop(String parent) {
		if (parent == null || parent.length() == 0 || EntryBase.TOP.equals(parent)) {
			return true;
		}
		return false;
	}

	/*
	 * Validation用メソッド
	 */
	public boolean validate() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Entry [myUri=" + getMyUri() + ", title=" + title + "]";
	}

}
