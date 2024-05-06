package cn.xwlin.service;

import cn.xwlin.cons.MyDdcStatus;
import cn.xwlin.dao.MyDdcMapper;
import cn.xwlin.entity.MyDdc;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class MyDdcService {

  @Autowired
  private MyDdcMapper myDdcMapper;

  public MyDdc findById(Integer id){
    return myDdcMapper.selectByPrimaryKey(id);
  }


  /**
   * 把当前时候向后延迟5分钟
   */
  private List<MyDdc> listFuture20MinsDdcWaitTask() {
    // +5分钟扫描
    Date date = DateTime.now().plusMinutes(5).toDate();
    List<MyDdc> myDdcs = myDdcMapper.queryBeforeDateToExecute(date);
    return null;
  }

  public void closeDdc(Integer id) {
    MyDdc myDdc = new MyDdc();
    myDdc.setId(id);
    myDdc.setStatus(MyDdcStatus.CLOSE.getValue());
    myDdcMapper.updateByPrimaryKeySelective(myDdc);
  }

  public void updateToNextScanTime(Integer id, Date nextScanTime, MyDdcStatus newStatus) {
    MyDdc myDdc = new MyDdc();
    myDdc.setId(id);
    myDdc.setTimeWheelEnd(nextScanTime);
    if (newStatus != null) {
      myDdc.setStatus(newStatus.getValue());
    }
    myDdcMapper.updateByPrimaryKeySelective(myDdc);
  }

}
