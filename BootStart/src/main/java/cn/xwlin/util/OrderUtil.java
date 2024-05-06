package cn.xwlin.util;

import cn.hutool.core.util.RandomUtil;

import java.math.BigDecimal;
import java.util.Random;

public class OrderUtil {

    private static final String[] PHONE_PRE = {"134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "182", "183", "184", "187", "188", "178", "147", "172", "198", "130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "133", "149", "153", "173", "177", "180", "181", "189", "199"};

    /**
     * 生成手机号方法
     */
    public static String getAPhoneNum() {
        // 定义随机数对象
        Random random = new Random();
        // 定义StringBuilder对象用于存储生成的手机号
        StringBuilder builder = new StringBuilder();
        // 手机号前三位
        String mobilePrefix = null;
        // 随机生成指定运营商中的手机前三位
        mobilePrefix = PHONE_PRE[random.nextInt(PHONE_PRE.length)];
        // 拼接手机号前三位
        builder.append(mobilePrefix);
        // 定义辅助变量用于手机号后八位的生成
        int temp;
        // 生成手机号后8位
        for (int i = 0; i < 8; i++) {
            // 随机生成一个 [0, 9] 以内的整数
            temp = random.nextInt(10);
            // 拼接当前随机数
            builder.append(temp);
        }
        // 将生成的电话号码返回
        return builder.toString();
    }

    public static Integer getOrderType() {
        return RandomUtil.randomInt(0, 5);
    }

    public static Integer getOrderFrom() {
        return RandomUtil.randomInt(0, 10);
    }

    public static Long getShopId() {
        return RandomUtil.randomLong(1000L, 99999L);
    }

    public static Integer getOrderStatus() {
        return RandomUtil.randomInt(0, 6);
    }

    public static Long getGoodsId() {
        return RandomUtil.randomLong(100000L, 999999999L);
    }

    public static String getGoodsName() {
        String s = RandomUtil.randomString(10);
        return "商品:" + s;
    }

    public static BigDecimal getSaleAmount() {
        return RandomUtil.randomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(100000));
    }

    public static BigDecimal getCostAmount(BigDecimal saleAmount) {
        BigDecimal min = saleAmount.subtract(BigDecimal.valueOf(100));
        BigDecimal max = saleAmount.subtract(BigDecimal.valueOf(5));
        return RandomUtil.randomBigDecimal(min, max);
    }
}