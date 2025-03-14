package cn.xwlin.util;

import cn.hutool.core.util.RandomUtil;
import cn.xwlin.object.EsOrderInfoIndexOBJGoodsInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderUtil {

  private static final String[] PHONE_PRE = {"134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "182", "183", "184", "187", "188", "178", "147", "172", "198", "130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "133", "149", "153", "173", "177", "180", "181", "189", "199"};

  private static final String[] Name_PRE = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪"};


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

  public static Integer getOrderStatus() {
    return RandomUtil.randomInt(0, 6);
  }

  public static Integer getGoodsType() {
    return RandomUtil.randomInt(0, 10);
  }

  public static Long getGoodsId() {
    return RandomUtil.randomLong(999989999L, 999999999L);
  }

  public static Long getGoodsUniqueId() {
    return RandomUtil.randomLong(69999999799999L, 69999999999999L);
  }

  public static Long getUserId() {
    return RandomUtil.randomLong(438944208L, 438954209L);
  }


  public static String getName() {
    Random random = new Random();
    String mobilePrefix = null;
    // 随机生成指定运营商中的手机前三位
    mobilePrefix = Name_PRE[random.nextInt(Name_PRE.length)];
    StringBuilder chineseName = new StringBuilder();
    // Unicode范围：4e00-9fa5 是中文字符的范围
    int unicodeStart = 0x4e00;
    int unicodeEnd = 0x9fa5;
    //参数控制生成名字的长度（2个字/3个字）
    for (int i = 0; i < random.nextInt(2) + 1; i++) {
      // 生成随机中文字符
      char randomChar = (char) (unicodeStart + random.nextInt(unicodeEnd - unicodeStart + 1));
      chineseName.append(randomChar);
    }
    return mobilePrefix + chineseName.toString();
  }


  public static String getGoodsName() {
    String s = RandomUtil.randomString(10);
    return "商品:" + s;
  }

  public static BigDecimal getSaleAmount() {
    return RandomUtil.randomBigDecimal(BigDecimal.valueOf(1000), BigDecimal.valueOf(100000)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
  }

  public static BigDecimal getCostAmount(BigDecimal saleAmount) {
    int i = RandomUtil.randomInt(80, 95);
    BigDecimal bigDecimal = BigDecimal.valueOf(i).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    return saleAmount.multiply(bigDecimal);
  }


  public static List<EsOrderInfoIndexOBJGoodsInfo> getGoodsInfo() {
    // 随机生成1到5个商品
    List<EsOrderInfoIndexOBJGoodsInfo> result = new ArrayList<>();
    int i = RandomUtil.randomInt(1, 5);
    for (int j = 0; j < i; j++) {
      EsOrderInfoIndexOBJGoodsInfo esOrderInfoIndexOBJGoodsInfo = new EsOrderInfoIndexOBJGoodsInfo();
      esOrderInfoIndexOBJGoodsInfo.setGoodsId(getGoodsId());
      esOrderInfoIndexOBJGoodsInfo.setGoodsName(getGoodsName());
      esOrderInfoIndexOBJGoodsInfo.setGoodsUniqueId(getGoodsUniqueId().toString());
      esOrderInfoIndexOBJGoodsInfo.setGoodsType(getGoodsType());
      esOrderInfoIndexOBJGoodsInfo.setSaleAmount(getSaleAmount().setScale(2, RoundingMode.HALF_UP));
      esOrderInfoIndexOBJGoodsInfo.setCostAmount(getCostAmount(esOrderInfoIndexOBJGoodsInfo.getSaleAmount()).setScale(2, RoundingMode.HALF_UP));
      result.add(esOrderInfoIndexOBJGoodsInfo);
    }
    return result;
  }


}