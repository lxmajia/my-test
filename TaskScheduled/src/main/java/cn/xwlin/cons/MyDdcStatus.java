package cn.xwlin.cons;


public enum MyDdcStatus {
  /**
   * 关闭
   */
  CLOSE(0),
  /**
   * 打开
   */
  OPEN(1),
  /**
   * 删除
   */
  DELETE(2),
  ;

  MyDdcStatus(int value) {
    this.value = value;
  }

  private int value;

  public int getValue() {
    return value;
  }
}
