package po;

import com.google.common.collect.Multiset;

/**
 * 统计一个词在文档中出现了多少次，传统的做法是这样的：
 *
 * 	Map<String, Integer> counts = new HashMap<String, Integer>();
 * 	for (String word : words) {
 * 	    Integer count = counts.get(word);
 * 	    if (count == null) {
 * 	        counts.put(word, 1);
 *                } else {
 * 	        counts.put(word, count + 1);
 *       }
 *  	}
 *
 * 这种写法很笨拙，也容易出错，并且不支持同时收集多种统计信息，如总词数。我们可以做的更好。
 *
 * Guava提供了一个新集合类型 Multiset，它可以多次添加相等的元素。
 * 维基百科从数学角度这样定义Multiset：”集合[set]概念的延伸，它的元素可以重复出现…与集合[set]相同而与元组[tuple]相反的是，
 * Multiset元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的”。
 * ——译者注：这里所说的集合[set]是数学上的概念，Multiset继承自JDK中的Collection接口，而不是Set接口，
 * 所以包含重复元素并没有违反原有的接口契约。
 *
 * 可以用两种方式看待Multiset：
 *
 *     没有元素顺序限制的ArrayList<E>
 *     Map<E, Integer>，键为元素，值为计数
 *
 * @see <a href=http://ifeve.com/google-guava-newcollectiontypes>
 * 新集合类型</a>
 * @see <a href=https://www.cnblogs.com/peida/p/Guava_Multiset.html>
 * 典型用例</a>
 */
public class MultisetExample {

}
