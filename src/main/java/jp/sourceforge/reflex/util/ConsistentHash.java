package jp.sourceforge.reflex.util;

import java.util.Collection;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConsistentHash.
 * 同じノードリスト・レプリカ数では、キーに対するノードは必ず同じになる関数。
 */
public class ConsistentHash<T> {

	/**
	 * ハッシュ関数.
	 * 実装はMD5を想定。このクラスの内部で使用するMessageDigestはスレッドセーフでないため、
	 * 各メソッドをsynchronizedにしている。
	 */
	private final HashFunction hashFunction;

	/**
	 * レプリカ数.
	 * 大きくすると分布がより均されるが、オブジェクト生成時に時間がかかる。
	 */
	private final int numberOfReplicas;

	/** circle */
	private final SortedMap<String, T> circle = new ConcurrentSkipListMap<String, T>();

	/**
	 * コンストラクタ.
	 * @param hashFunction ハッシュ関数.
	 * @param numberOfReplicas レプリカ数
	 * @param nodes ノードリスト
	 */
	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
			Collection<T> nodes) {

		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;

		if (nodes != null) {
			for (T node : nodes) {
				add(node);
			}
		}
	}

	/**
	 * ノード追加.
	 * @param node 追加ノード
	 */
	public synchronized void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.toString() + i), node);
		}
	}

	/**
	 * ノード削除.
	 * @param node 削除ノード
	 */
	public synchronized void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
	}

	/**
	 * ノード取得.
	 * @param key キー
	 * @return キーに割り当てられたノード
	 */
	public synchronized T get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		String hash = hashFunction.hash(key);
		if (!circle.containsKey(hash)) {
			SortedMap<String, T> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}

}
