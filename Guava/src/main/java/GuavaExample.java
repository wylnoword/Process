import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 * @author wangyuliang
 * @date 13:23 2021/1/11
 */
public class GuavaExample {
	/**
	 * Multimap
	 * <p>
	 * 每个有经验的Java程序员都在某处实现过Map<K, List<V>>或Map<K, Set<V>>，并且要忍受这个结构的笨拙。
	 * 例如，Map<K, Set<V>>通常用来表示非标定有向图。Guava的 Multimap可以很容易地把一个键映射到多个值。
	 * 换句话说，Multimap是把键映射到任意多个值的一般方式。
	 */
	public static Multimap immutableListMultimap = ImmutableListMultimap.of();

	public static void main(String[] args) {
//        求集合元素的笛卡尔积
		List<List<Integer>> lists = Lists.cartesianProduct(Lists.newArrayList(2, 4), Lists.newArrayList(3, 1));
		System.out.println(lists);
	}
}
