package org.campusmolndal;

import java.sql.Connection;

public interface Database {

 String disConnect();
 Connection connection();
 boolean createTable(String Query);


}
