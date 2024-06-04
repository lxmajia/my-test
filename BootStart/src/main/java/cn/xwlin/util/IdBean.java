package cn.xwlin.util;

/**
 * @author xiang.liao
 * @create 2024/6/4
 */
public class IdBean {
  private Long id;
  private String tableName;

  public IdBean() {
  }

  public IdBean(String tableName) {
    this.tableName = tableName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
}
