package com.example.bootstrap;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class DevelopmentOnlyCustomerService extends BaseCustomerService {
    DevelopmentOnlyCustomerService(){
        super(buildDataSource());
    }

    public static DataSource buildDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).build();
        return MyDataSourceUtils.initializeDdl(dataSource);
    }
}
