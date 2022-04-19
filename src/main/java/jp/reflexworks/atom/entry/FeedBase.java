package jp.reflexworks.atom.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.annotation.Index;

/**
 * Feedの親クラス.
 * <p>
 * ATOM形式のFeedです。<br>
 * このクラスのentry項目に、データであるエントリーが複数格納されます。<br>
 * 各プロジェクトでこのクラスを継承し、カスタマイズしたFeedクラスを生成してください。<br>
 * </p>
 */
public abstract class FeedBase implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	/**
	 * デフォルトの名前空間
	 */
	@Index(0)
	public List<Author> author;
	@Index(1)
	public List<Category> category;
	@Index(2)
	public List<Contributor> contributor;
	@Index(3)
	public Generator generator;
	@Index(4)
	public String icon;
	@Index(5)
	public String id;

	/**
	 * 次ページカーソル.
	 * <p>
	 * 属性rel="next"の、href属性に設定された値が次ページ検索のためのカーソルです。
	 * </p>
	 */
	@Index(6)
	public List<Link> link;
	@Index(7)
	public String logo;
	@Index(8)
	public String rights;
	@Index(9)
	public String title;
	@Index(10)
	public String title_$type;
	@Index(11)
	public String subtitle;
	@Index(12)
	public String subtitle_$type;
	@Index(13)
	public String updated;
	/** エントリーリスト */
	@Index(14)
	public List<EntryBase> entry;

	private Boolean startArrayBracket;

	public boolean getStartArrayBracket() {
		if (startArrayBracket==null) return false;
		return startArrayBracket;
	}
	
	public void setStartArrayBracket(boolean startArrayBracket) {
		this.startArrayBracket=startArrayBracket;
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

	public List<Contributor> getContributor() {
		return contributor;
	}

	public void setContributor(List<Contributor> contributor) {
		this.contributor = contributor;
	}

	public Generator getGenerator() {
		return generator;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
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

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public List<EntryBase> getEntry() {
		return entry;
	}

	public void setEntry(List<EntryBase> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "Feed [entry=" + entry + "]";
	}
	
	/**
	 * エントリーを追加します.
	 * @param entry エントリー
	 */
	public void addEntry(EntryBase addingEntry) {
		if (entry == null) {
			entry = new ArrayList<EntryBase>();
		}
		entry.add(addingEntry);
	}
	
	/**
	 * エントリーリストを追加します.
	 * @param addingEntries エントリーリスト
	 */
	public void addEntries(List<EntryBase> addingEntries) {
		if (entry == null) {
			entry = new ArrayList<EntryBase>();
		}
		entry.addAll(addingEntries);
	}

	/**
	 * 項目チェック.
	 * バリデーションチェック、項目ACLチェックを行います。
	 * 注) 項目ACLの@指定ではuidとEntryBase.getCreatorUid()メソッドの値を比較します。
	 *     Entryのid設定後に実行してください。
	 * @param uid UID
	 * @param groups 参加グループリスト.<br>
	 *               nullの場合、項目ACLチェックを行いません。<br>
	 *               空リストの場合、項目ACLチェックを行います。
	 */
	public abstract boolean validate(String uid, List<String> groups)
			throws java.text.ParseException;

	public abstract void maskprop(String uid, List<String> groups);

	/**
	 * キーにサービス名を付加します.
	 * @param svcname サービス名
	 */
	public void addSvcname(String svcname) {
		if (svcname == null) {
			return;
		}
		if (entry != null) {
			for (EntryBase _e : entry) {
				_e.addSvcname(svcname);
			}
		}
	}

	/**
	 * キーからサービス名を除去します.
	 * @param svcname サービス名
	 */
	public void cutSvcname(String svcname) {
		if (svcname == null) {
			return;
		}
		if (entry != null) {
			for (EntryBase _e : entry) {
				_e.cutSvcname(svcname);
			}
		}
	}

}
