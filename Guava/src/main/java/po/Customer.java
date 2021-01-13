package po;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Customer {
    private String id;
    private String age;
    private String hireTime;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHireTime() {
        return hireTime;
    }

    public void setHireTime(String hireTime) {
        this.hireTime = hireTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

/**
 * 排序器
 * ComparisonChain执行一种懒比较：可以构造返回结构，排序顺序等...
 *
 *  创建排序器：常见的排序器可以由下面的静态方法创建
 *  natural() 	对可排序类型做自然排序，如数字按大小，日期按先后排序
 *  usingToString() 	按对象的字符串形式做字典排序[lexicographical ordering]
 *  from(Comparator) 	把给定的Comparator转化为排序器
 *  arbitrary() 按照identityHashCode "随机"排序
 *  explicit(List<T> valuesInOrder)   只能比较参数列表中存在的对象
 * @see <a href=https://blog.wangqi.love/articles/Java/guava%20Ordering%E6%80%BB%E7%BB%93.html>
 *     guava Ordering总结
 *     </a>
 *
 */
    public int compareTo(Customer that) {
        return ComparisonChain.start()
                .compare(this.id, that.id)
                .compare(this.age, that.age)
                .compare(this.hireTime, that.hireTime, Ordering.natural().nullsLast())
                .result();
    }
}
