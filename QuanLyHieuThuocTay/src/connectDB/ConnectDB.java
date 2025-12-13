package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QLThuoc;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";
//    private static final String PASSWORD = "sapassword";
    private static final String PASSWORD = "310105";

    /**
     * Phương thức này sẽ mở và trả về MỘT KẾT NỐI MỚI mỗi khi được gọi.
     * @return một đối tượng Connection mới hoặc null nếu có lỗi.
     */
    public static Connection getConnection() {
        try {
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Không cần thiết ở các bản JDBC hiện đại
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối CSDL!");
            e.printStackTrace();
            return null;
        }
    }
}