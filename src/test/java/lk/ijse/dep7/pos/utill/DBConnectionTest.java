package lk.ijse.dep7.pos.utill;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void getConnection()throws SQLException {
       assertNotNull(DBConnection.getConnection());
    }
}