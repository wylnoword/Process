package po;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Customer {
    private String name;
    private String age;
    private String hireTime;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int compareTo(Customer that) {
        return ComparisonChain.start()
                .compare(this.name, that.name)
                .compare(this.age, that.age)
                .compare(this.hireTime, that.hireTime, Ordering.natural().nullsLast())
                .result();
    }
}
