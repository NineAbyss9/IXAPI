
package org.NineAbyss9.test;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {
    /*static void a() {
        a();
    }*/

    public static void main(String[] args) {
        //十个数
        int[] numbers = {12, 16, 10, 21, 9, 25, 10, 6, 15, 13};
        System.out.println(Arrays.toString(numbers));
        //数量
        int size = numbers.length;
        System.out.println(size);
        //排序
        var list = Arrays.stream(numbers).sorted().toArray();
        System.out.println(Arrays.toString(list));
        //求和
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
        BigDecimal bigSum = BigDecimal.valueOf(sum);
        //平均值
        BigDecimal average = bigSum.divide(BigDecimal.valueOf(size));
        System.out.println(average.floatValue());
        //离差平方和
        BigDecimal ssd = BigDecimal.ZERO;
        for (int i = 0;i < size;i++) {
            BigDecimal cache = (BigDecimal.valueOf(list[i]).add(average.negate()));
            ssd = ssd.add(cache.multiply(cache));
        }
        System.out.println(ssd.doubleValue());
        //方差
        BigDecimal d = ssd.divide(BigDecimal.valueOf(size));
        System.out.println(d.doubleValue());
    }
}
