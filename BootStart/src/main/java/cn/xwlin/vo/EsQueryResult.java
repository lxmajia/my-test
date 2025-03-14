package cn.xwlin.vo;

import java.util.List;

public class EsQueryResult<T> {
  /**
   * 查询时间
   */
  private long took;
  /**
   * 满足条件的个数
   */
  private long count;
  /**
   * 结果list
   */
  private List<T> list;

  public long getTook() {
    return took;
  }

  public void setTook(long took) {
    this.took = took;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
