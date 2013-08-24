package jp.co.kuronekoyamato.b2web.model;


import java.io.Serializable;

import jp.reflexworks.atom.entry.EntryBase;

public class Entry extends EntryBase implements Serializable {

	// 独自名前空間があり、デフォルトの名前空間項目が親クラスに定義されている場合、デフォルト名前空間項目を子クラスにも定義する。

	public String _$xmlns = "http://www.w3.org/2005/Atom";
	private static final long serialVersionUID = 1L;

	// ---- B2名前空間定義 ----
	public String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";

	// ---- B2名前空間　ここから ----
	// トランザクション系
    public Shipment shipment;						// 出荷
    public Shipper shipper; 						// ご依頼主
    public Consignee consignee; 					// お届け先
    public Customer customer;						// 顧客
    public Tracking tracking;						// 配送状況
    public Content_details content_details;		    // 品名

    public Import_pattern import_pattern;			// 取込みパターン
    public Shipment_group shipment_group;			// 出荷先グループ
    public Event_log event_log;						// イベントログ
    public Status_count status_count;				// 状況表示
    public Email_notification email_notification;	// 出荷指示確認メール
    /** 日付 */
    public System_date system_date;


	// マスタ系
    public Address address;							// 郵政
    public Telephone_address telephone_address;		// 電話番号地域対応
    public Center center;							// 営業所
    public Sorting_code sorting_code;				// 仕分コード
    public Package_size package_size;				// サイズ品目
    /** 画像選択 */
    public Image_choice image_choice;

    // 共通系
    public Error_list error_list;					// エラー情報リスト

    /** 作成者（法人ID） */

    public String b2$creator;
    /** 作成者（ログインID） */
    
    public String b2$creator_loginid;
    /** 新規作成年月日 */
    
    public String b2$created;
    /** 更新者（法人ID） */
    
    public String b2$updater;
    /** 更新者（ログインID） */
    
    public String b2$updater_loginid;
    /** 更新年月日 */
    
    public String b2$updated;

    /** 論理削除フラグ */
    
    public String b2$deleted;

    /** 以下はRDB連携用 **/
    /** 更新年月日(all) */
    
    public String b2$updated_all;
    /** 更新年月日(settings) */
    
    public String b2$updated_settings;
    /** 更新年月日(email_message) */
    
    public String b2$updated_email_message;
    /** 更新年月日(email_notification) */
    
    public String b2$updated_email_notification;
    /** 更新年月日(consignee) */
    
    public String b2$updated_consignee;
    /** 更新年月日(shipper) */
    
    public String b2$updated_shipper;
    /** 更新年月日(item) */
    
    public String b2$updated_item;
    /** 更新年月日(shipment_group) */
    
    public String b2$updated_shipment_group;
    /** 更新年月日(importing_pattern) */
    
    public String b2$updated_importing_pattern;
    /** 更新年月日(event_log) */
    
    public String b2$updated_event_log;

    // ---- B2名前空間　ここまで ----

	/**
	 * _$xmlnsを取得します。
	 * @return _$xmlns
	 */
	public String get_$xmlns() {
		return _$xmlns;
	}

	/**
	 * _$xmlnsを設定します。
	 * @param _$xmlns _$xmlns
	 */
	// ---- B2名前空間　ここまで ----
	
	/**
	 * _$xmlnsを設定します。
	 * @param _$xmlns _$xmlns
	 */
	public void set_$xmlns(String _$xmlns) {
		this._$xmlns = _$xmlns;
	}

	/**
	 * _$xmlns$b2を取得します。
	 * @return _$xmlns$b2
	 */
	public String get_$xmlns$b2() {
	    return _$xmlns$b2;
	}

	/**
	 * $xmlns$b2を設定します。
	 * @param $xmlns$b2 $xmlns$b2
	 */
	public void set_$xmlns$b2(String $xmlns$b2) {
	    this._$xmlns$b2 = $xmlns$b2;
	}

	/**
	 * shipmentを取得します。
	 * @return shipment
	 */
	public Shipment getShipment() {
	    return shipment;
	}

	/**
	 * shipmentを設定します。
	 * @param shipment shipment
	 */
	public void setShipment(Shipment shipment) {
	    this.shipment = shipment;
	}

	/**
	 * shipperを取得します。
	 * @return shipper
	 */
	public Shipper getShipper() {
	    return shipper;
	}

	/**
	 * shipperを設定します。
	 * @param shipper shipper
	 */
	public void setShipper(Shipper shipper) {
	    this.shipper = shipper;
	}

	/**
	 * consigneeを取得します。
	 * @return consignee
	 */
	public Consignee getConsignee() {
	    return consignee;
	}

	/**
	 * consigneeを設定します。
	 * @param consignee consignee
	 */
	public void setConsignee(Consignee consignee) {
	    this.consignee = consignee;
	}

	/**
	 * customerを取得します。
	 * @return customer
	 */
	public Customer getCustomer() {
	    return customer;
	}

	/**
	 * customerを設定します。
	 * @param customer customer
	 */
	public void setCustomer(Customer customer) {
	    this.customer = customer;
	}

	/**
	 * trackingを取得します。
	 * @return tracking
	 */
	public Tracking getTracking() {
	    return tracking;
	}

	/**
	 * trackingを設定します。
	 * @param tracking tracking
	 */
	public void setTracking(Tracking tracking) {
	    this.tracking = tracking;
	}

	/**
	 * content_detailsを取得します。
	 * @return content_details
	 */
	public Content_details getContent_details() {
	    return content_details;
	}

	/**
	 * content_detailsを設定します。
	 * @param content_details content_details
	 */
	public void setContent_details(Content_details content_details) {
	    this.content_details = content_details;
	}

	/**
	 * import_patternを取得します。
	 * @return import_pattern
	 */
	public Import_pattern getImport_pattern() {
	    return import_pattern;
	}

	/**
	 * import_patternを設定します。
	 * @param import_pattern import_pattern
	 */
	public void setImport_pattern(Import_pattern import_pattern) {
	    this.import_pattern = import_pattern;
	}

	/**
	 * shipment_groupを取得します。
	 * @return shipment_group
	 */
	public Shipment_group getShipment_group() {
	    return shipment_group;
	}

	/**
	 * shipment_groupを設定します。
	 * @param shipment_group shipment_group
	 */
	public void setShipment_group(Shipment_group shipment_group) {
	    this.shipment_group = shipment_group;
	}

	/**
	 * event_logを取得します。
	 * @return event_log
	 */
	public Event_log getEvent_log() {
	    return event_log;
	}

	/**
	 * event_logを設定します。
	 * @param event_log event_log
	 */
	public void setEvent_log(Event_log event_log) {
	    this.event_log = event_log;
	}

	/**
	 * status_countを取得します。
	 * @return status_count
	 */
	public Status_count getStatus_count() {
	    return status_count;
	}

	/**
	 * status_countを設定します。
	 * @param status_count status_count
	 */
	public void setStatus_count(Status_count status_count) {
	    this.status_count = status_count;
	}

	/**
	 * email_notificationを取得します。
	 * @return email_notification
	 */
	public Email_notification getEmail_notification() {
	    return email_notification;
	}

	/**
	 * email_notificationを設定します。
	 * @param email_notification email_notification
	 */
	public void setEmail_notification(Email_notification email_notification) {
	    this.email_notification = email_notification;
	}

	/**
	 * 日付を取得します。
	 * @return 日付
	 */
	public System_date getSystem_date() {
	    return system_date;
	}

	/**
	 * 日付を設定します。
	 * @param system_date 日付
	 */
	public void setSystem_date(System_date system_date) {
	    this.system_date = system_date;
	}

	/**
	 * addressを取得します。
	 * @return address
	 */
	public Address getAddress() {
	    return address;
	}

	/**
	 * addressを設定します。
	 * @param address address
	 */
	public void setAddress(Address address) {
	    this.address = address;
	}

	/**
	 * telephone_addressを取得します。
	 * @return telephone_address
	 */
	public Telephone_address getTelephone_address() {
	    return telephone_address;
	}

	/**
	 * telephone_addressを設定します。
	 * @param telephone_address telephone_address
	 */
	public void setTelephone_address(Telephone_address telephone_address) {
	    this.telephone_address = telephone_address;
	}

	/**
	 * centerを取得します。
	 * @return center
	 */
	public Center getCenter() {
	    return center;
	}

	/**
	 * centerを設定します。
	 * @param center center
	 */
	public void setCenter(Center center) {
	    this.center = center;
	}

	/**
	 * sorting_codeを取得します。
	 * @return sorting_code
	 */
	public Sorting_code getSorting_code() {
	    return sorting_code;
	}

	/**
	 * sorting_codeを設定します。
	 * @param sorting_code sorting_code
	 */
	public void setSorting_code(Sorting_code sorting_code) {
	    this.sorting_code = sorting_code;
	}

	/**
	 * package_sizeを取得します。
	 * @return package_size
	 */
	public Package_size getPackage_size() {
	    return package_size;
	}

	/**
	 * package_sizeを設定します。
	 * @param package_size package_size
	 */
	public void setPackage_size(Package_size package_size) {
	    this.package_size = package_size;
	}

	/**
	 * 画像選択を取得します。
	 * @return 画像選択
	 */
	public Image_choice getImage_choice() {
	    return image_choice;
	}

	/**
	 * 画像選択を設定します。
	 * @param image_choice 画像選択
	 */
	public void setImage_choice(Image_choice image_choice) {
	    this.image_choice = image_choice;
	}

	/**
	 * error_listを取得します。
	 * @return error_list
	 */
	public Error_list getError_list() {
	    return error_list;
	}

	/**
	 * error_listを設定します。
	 * @param error_list error_list
	 */
	public void setError_list(Error_list error_list) {
	    this.error_list = error_list;
	}

	/**
	 * 作成者（法人ID）を取得します。
	 * @return 作成者（法人ID）
	 */
	public String getB2$creator() {
	    return b2$creator;
	}

	/**
	 * 作成者（法人ID）を設定します。
	 * @param b2$creator 作成者（法人ID）
	 */
	public void setB2$creator(String b2$creator) {
	    this.b2$creator = b2$creator;
	}

	/**
	 * 作成者（ログインID）を取得します。
	 * @return 作成者（ログインID）
	 */
	public String getB2$creator_loginid() {
	    return b2$creator_loginid;
	}

	/**
	 * 作成者（ログインID）を設定します。
	 * @param b2$creator_loginid 作成者（ログインID）
	 */
	public void setB2$creator_loginid(String b2$creator_loginid) {
	    this.b2$creator_loginid = b2$creator_loginid;
	}

	/**
	 * 新規作成年月日を取得します。
	 * @return 新規作成年月日
	 */
	public String getB2$created() {
	    return b2$created;
	}

	/**
	 * 新規作成年月日を設定します。
	 * @param b2$created 新規作成年月日
	 */
	public void setB2$created(String b2$created) {
	    this.b2$created = b2$created;
	}

	/**
	 * 更新者（法人ID）を取得します。
	 * @return 更新者（法人ID）
	 */
	public String getB2$updater() {
	    return b2$updater;
	}

	/**
	 * 更新者（法人ID）を設定します。
	 * @param b2$updater 更新者（法人ID）
	 */
	public void setB2$updater(String b2$updater) {
	    this.b2$updater = b2$updater;
	}

	/**
	 * 更新者（ログインID）を取得します。
	 * @return 更新者（ログインID）
	 */
	public String getB2$updater_loginid() {
	    return b2$updater_loginid;
	}

	/**
	 * 更新者（ログインID）を設定します。
	 * @param b2$updater_loginid 更新者（ログインID）
	 */
	public void setB2$updater_loginid(String b2$updater_loginid) {
	    this.b2$updater_loginid = b2$updater_loginid;
	}

	/**
	 * 更新年月日を取得します。
	 * @return 更新年月日
	 */
	public String getB2$updated() {
	    return b2$updated;
	}

	/**
	 * 更新年月日を設定します。
	 * @param b2$updated 更新年月日
	 */
	public void setB2$updated(String b2$updated) {
	    this.b2$updated = b2$updated;
	}

	/**
	 * 論理削除フラグを取得します。
	 * @return 論理削除フラグ
	 */
	public String getB2$deleted() {
	    return b2$deleted;
	}

	/**
	 * 論理削除フラグを設定します。
	 * @param b2$deleted 論理削除フラグ
	 */
	public void setB2$deleted(String b2$deleted) {
	    this.b2$deleted = b2$deleted;
	}

	/**
	 * 以下はRDB連携用 *を取得します。
	 * @return 以下はRDB連携用 *
	 */
	public String getB2$updated_all() {
	    return b2$updated_all;
	}

	/**
	 * 以下はRDB連携用 *を設定します。
	 * @param b2$updated_all 以下はRDB連携用 *
	 */
	public void setB2$updated_all(String b2$updated_all) {
	    this.b2$updated_all = b2$updated_all;
	}

	/**
	 * 更新年月日(settings)を取得します。
	 * @return 更新年月日(settings)
	 */
	public String getB2$updated_settings() {
	    return b2$updated_settings;
	}

	/**
	 * 更新年月日(settings)を設定します。
	 * @param b2$updated_settings 更新年月日(settings)
	 */
	public void setB2$updated_settings(String b2$updated_settings) {
	    this.b2$updated_settings = b2$updated_settings;
	}

	/**
	 * 更新年月日(email_message)を取得します。
	 * @return 更新年月日(email_message)
	 */
	public String getB2$updated_email_message() {
	    return b2$updated_email_message;
	}

	/**
	 * 更新年月日(email_message)を設定します。
	 * @param b2$updated_email_message 更新年月日(email_message)
	 */
	public void setB2$updated_email_message(String b2$updated_email_message) {
	    this.b2$updated_email_message = b2$updated_email_message;
	}

	/**
	 * 更新年月日(email_notification)を取得します。
	 * @return 更新年月日(email_notification)
	 */
	public String getB2$updated_email_notification() {
	    return b2$updated_email_notification;
	}

	/**
	 * 更新年月日(email_notification)を設定します。
	 * @param b2$updated_email_notification 更新年月日(email_notification)
	 */
	public void setB2$updated_email_notification(String b2$updated_email_notification) {
	    this.b2$updated_email_notification = b2$updated_email_notification;
	}

	/**
	 * 更新年月日(consignee)を取得します。
	 * @return 更新年月日(consignee)
	 */
	public String getB2$updated_consignee() {
	    return b2$updated_consignee;
	}

	/**
	 * 更新年月日(consignee)を設定します。
	 * @param b2$updated_consignee 更新年月日(consignee)
	 */
	public void setB2$updated_consignee(String b2$updated_consignee) {
	    this.b2$updated_consignee = b2$updated_consignee;
	}

	/**
	 * 更新年月日(shipper)を取得します。
	 * @return 更新年月日(shipper)
	 */
	public String getB2$updated_shipper() {
	    return b2$updated_shipper;
	}

	/**
	 * 更新年月日(shipper)を設定します。
	 * @param b2$updated_shipper 更新年月日(shipper)
	 */
	public void setB2$updated_shipper(String b2$updated_shipper) {
	    this.b2$updated_shipper = b2$updated_shipper;
	}

	/**
	 * 更新年月日(item)を取得します。
	 * @return 更新年月日(item)
	 */
	public String getB2$updated_item() {
	    return b2$updated_item;
	}

	/**
	 * 更新年月日(item)を設定します。
	 * @param b2$updated_item 更新年月日(item)
	 */
	public void setB2$updated_item(String b2$updated_item) {
	    this.b2$updated_item = b2$updated_item;
	}

	/**
	 * 更新年月日(shipment_group)を取得します。
	 * @return 更新年月日(shipment_group)
	 */
	public String getB2$updated_shipment_group() {
	    return b2$updated_shipment_group;
	}

	/**
	 * 更新年月日(shipment_group)を設定します。
	 * @param b2$updated_shipment_group 更新年月日(shipment_group)
	 */
	public void setB2$updated_shipment_group(String b2$updated_shipment_group) {
	    this.b2$updated_shipment_group = b2$updated_shipment_group;
	}

	/**
	 * 更新年月日(importing_pattern)を取得します。
	 * @return 更新年月日(importing_pattern)
	 */
	public String getB2$updated_importing_pattern() {
	    return b2$updated_importing_pattern;
	}

	/**
	 * 更新年月日(importing_pattern)を設定します。
	 * @param b2$updated_importing_pattern 更新年月日(importing_pattern)
	 */
	public void setB2$updated_importing_pattern(String b2$updated_importing_pattern) {
	    this.b2$updated_importing_pattern = b2$updated_importing_pattern;
	}

	/**
	 * 更新年月日(event_log)を取得します。
	 * @return 更新年月日(event_log)
	 */
	public String getB2$updated_event_log() {
	    return b2$updated_event_log;
	}

	/**
	 * 更新年月日(event_log)を設定します。
	 * @param b2$updated_event_log 更新年月日(event_log)
	 */
	public void setB2$updated_event_log(String b2$updated_event_log) {
	    this.b2$updated_event_log = b2$updated_event_log;
	}
}
