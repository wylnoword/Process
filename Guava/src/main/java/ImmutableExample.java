import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * 不可变集合：
 * <p>
 * 当对象被不可信的库调用时，不可变形式是安全的；
 * 不可变对象被多个线程调用时，不存在竞态条件问题
 * 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 * 不可变对象因为有固定不变，可以作为常量来安全使用。
 * 重要提示：所有Guava不可变集合的实现都不接受null值。我们对Google内部的代码库做过详细研究，发现只有5%的情况需要在集合中允许null元素，
 * 剩下的95%场景都是遇到null值就快速失败。如果你需要在不可变集合中使用null，请使用JDK中的Collections.unmodifiableXXX方法
 *
 * @see <a href=http://ifeve.com/google-guava-immutablecollections/ >不可变集合</a>
 */
public class ImmutableExample {

	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
			"red",
			"orange",
			"yellow",
			null
	);

	public static void main(String[] args) {
		ImmutableList<String> strings = COLOR_NAMES.asList();
		System.out.println();
	}
}
