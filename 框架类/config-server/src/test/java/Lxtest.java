import com.alibaba.fastjson2.JSON;

/**
 * @author xiang.liao
 * @create 2024/6/6
 */
public class Lxtest {
  public static void main(String[] args) {
    String s = "order$task$" + System.currentTimeMillis() + "$192.168.10.1";
    String[] split = s.split("\\$");
    System.out.println(JSON.toJSONString(split));
  }
}
