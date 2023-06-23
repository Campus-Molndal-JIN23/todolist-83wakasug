package org.campusmolndal;

import java.sql.Connection;

public interface Database {

 boolean disConnect(Connection conn);
 Connection connection();


}
