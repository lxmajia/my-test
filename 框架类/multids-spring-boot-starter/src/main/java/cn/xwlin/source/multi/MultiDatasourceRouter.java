package cn.xwlin.source.multi;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiDatasourceRouter extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return MultiDatasourceHolder.getDsName();
  }
}
