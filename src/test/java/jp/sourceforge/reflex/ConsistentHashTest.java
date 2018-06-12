package jp.sourceforge.reflex;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import jp.sourceforge.reflex.util.ConsistentHash;
import jp.sourceforge.reflex.util.HashFunction;
import jp.sourceforge.reflex.util.MD5;

/**
 * Unit test for simple App.
 */
public class ConsistentHashTest {

	/**
	 * Rigourous Test :-)
	 */
	public void testconsistenthash() {

		HashFunction hashFunction = new MD5();
		int numberOfReplicas = 500;	// レプリカを100個作成
		List<String> nodes = new ArrayList<String>();
		nodes.add("A");
		nodes.add("B");
		nodes.add("C");
		nodes.add("D");		// このノードを削除してみる
		nodes.add("E");
		nodes.add("F");

		// nodeの順番を入れ替えてみても、結果は同じ
		
		ConsistentHash<String> consistentHash = 
			new ConsistentHash<String>(hashFunction, numberOfReplicas, nodes);

		String node = null;
		for (Object nodename : nodes) {
			System.out.print(nodename + ":");
			for (int i = 0; i < 100; i++) {
				node = consistentHash.get("" + i);
				if (node.equals("" + nodename)) {
					System.out.print(i + ",");
				}
			}
			System.out.println("");
		}

		assertTrue(true);
		
		System.out.println("---- node D を削除 ----");
		consistentHash.remove("D");

		for (Object nodename : nodes) {
			System.out.print(nodename + ":");
			for (int i = 0; i < 100; i++) {
				node = consistentHash.get("" + i);
				if (node.equals("" + nodename)) {
					System.out.print(i + ",");
				}
			}
			System.out.println("");
		}
		System.out.println("---- node G を追加 ----");
		consistentHash.add("G");
		nodes.add("G");

		for (Object nodename : nodes) {
			System.out.print(nodename + ":");
			for (int i = 0; i < 100; i++) {
				node = consistentHash.get("" + i);
				if (node.equals("" + nodename)) {
					System.out.print(i + ",");
				}
			}
			System.out.println("");
		}

		String group = "sample";
		node = consistentHash.get(group);
		System.out.println(group + " node = " + node);
		group = "test";
		node = consistentHash.get(group);
		System.out.println(group + " node = " + node);
		group = "sample";
		node = consistentHash.get(group);
		System.out.println(group + " node = " + node);
/*		*/

	}
}
