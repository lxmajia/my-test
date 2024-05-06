package cn.xwlin.cons;


public enum MyDdcExecuteStatus {
  /**
   * 执行中
   */
  PROCESSING(0),
  /**
   * 成功
   */
  SUCCESS(1),
  /**
   * 失败
   */
  FAILED(2),
  ;

  MyDdcExecuteStatus(int value) {
    this.value = value;
  }

  private int value;

  public int getValue() {
    return value;
  }
}
