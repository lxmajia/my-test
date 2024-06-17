package cn.xwlin.datasource.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDatasource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DatasourceSwtich.getDsName();
  }
}
