package cn.xwlin.dao.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.Date;

public class DateTimeTypeHandler extends BaseTypeHandler<Date> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
    preparedStatement.setTimestamp(i, new Timestamp(date.getTime()));
  }

  @Override
  public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return resultSet.getDate(s);
  }

  @Override
  public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return resultSet.getDate(i);
  }

  @Override
  public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return callableStatement.getDate(i);
  }
}
