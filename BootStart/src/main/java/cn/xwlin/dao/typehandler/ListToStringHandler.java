package cn.xwlin.dao.typehandler;

import com.alibaba.druid.util.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListToStringHandler extends BaseTypeHandler<List<Integer>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Integer> integers, JdbcType jdbcType) throws SQLException {
        if (integers == null || integers.size() < 1) {
            preparedStatement.setString(i, "");
        } else {
            String join = integers.stream().map(String::valueOf).collect(Collectors.joining(";"));
            preparedStatement.setString(i, join);
        }
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (StringUtils.isEmpty(string)) {
            return new ArrayList<>();
        }
        String[] split = string.split(";");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (StringUtils.isEmpty(string)) {
            return new ArrayList<>();
        }
        String[] split = string.split(";");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (StringUtils.isEmpty(string)) {
            return new ArrayList<>();
        }
        String[] split = string.split(";");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }
}
