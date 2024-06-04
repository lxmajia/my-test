//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xwlin.seqid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlIdGenerator {
  private static final Logger log = LoggerFactory.getLogger(MySqlIdGenerator.class);
  private final DataSource sequenceDataSource;
  private final String tableName;
  private final String INCREMENT_SEQUENCE_SQL;
  private static final String GET_SEQUENCE_SQL = "SELECT LAST_INSERT_ID()";

  public MySqlIdGenerator(DataSource sequenceDataSource, String tableName) {
    Assert.isTrue(sequenceDataSource != null, "sequence datasouce not is null.");
    Assert.isTrue(tableName != null && !tableName.isEmpty(), "table name not is null or empty.");
    this.sequenceDataSource = sequenceDataSource;
    this.tableName = tableName;
    this.INCREMENT_SEQUENCE_SQL = String.format("replace into %s (`id_unique`) values ('u')",this.tableName);
  }

  public long generateId() {
    long currentSequence = -1L;
    Connection conn = null;
    try {
      conn = this.sequenceDataSource.getConnection();
    } catch (SQLException e) {
      if (log.isErrorEnabled()) {
        log.error("the eddl MySQLIdGenerator sequence data source get connection error,reasons:",e);
      }
    }
    try {
      Assert.isTrue(conn != null && !conn.isClosed(), "The obtained connection cannot be null or has been closed.");
    } catch (SQLException var27) {
    }
    Statement statement = null;
    ResultSet rs = null;
    try {
      statement = conn.createStatement();
      statement.executeUpdate(this.INCREMENT_SEQUENCE_SQL);
      for(rs = statement.executeQuery("SELECT LAST_INSERT_ID()"); rs.next(); currentSequence = rs.getLong(1)) {
      }
    } catch (Throwable var25) {
      if (log.isErrorEnabled()) {
        log.error("the eddl MySQLIdGenerator get sequence error,reasons:", var25);
      }
    } finally {
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException var24) {
        }
      }
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException var23) {
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException var22) {
        }
      }
    }
    return currentSequence;
  }
}
