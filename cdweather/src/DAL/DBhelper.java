package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBhelper {

    public static Connection getConnection() {
        Connection conn = null;
        try {

            String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//数据库引擎
            String url="jdbc:sqlserver://localhost:1433;DatabaseName=cdweather";
            String Name="sa";
            String passWord="159753456ffsail";
            Class.forName(driverName);
            if (null == conn) {
                conn = DriverManager.getConnection(url, Name, passWord);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can't find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }


    public static int executeNonQuery(String sql) {

        int result = 0;
        Connection conn = null;
        Statement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException err) {

            err.printStackTrace();
            free(null, stmt, conn);

        } finally {
            free(null, stmt, conn);
        }

        return result;

    }




    public static int executeNonQuery(String sql, Object... obj) {

        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();

        } catch (SQLException err) {
            err.printStackTrace();
            free(null, pstmt, conn);

        } finally {

            free(null, pstmt, conn);

        }

        return result;

    }


    public static ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, stmt, conn);
        }
        return rs;

    }




    public static ResultSet executeQuery(String sql, Object... obj) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);

            }
            rs = pstmt.executeQuery();

        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, pstmt, conn);
        }
        return rs;

    }


    public static Boolean isExist(String sql) {

        ResultSet rs = null;
        try {

            rs = executeQuery(sql);
            rs.last();
            int count = rs.getRow();
            if (count > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException err) {
            err.printStackTrace();
            free(rs);
            return false;
        } finally {
            free(rs);
        }

    }


    public static void free(ResultSet rs) {

        try {

            if (rs != null) {

                rs.close();

            }

        } catch (SQLException err) {

            err.printStackTrace();

        }

    }


    public static void free(Statement st) {

        try {

            if (st != null) {

                st.close();

            }

        } catch (SQLException err) {

            err.printStackTrace();

        }

    }


    public static void free(Connection conn) {

        try {

            if (conn != null) {

                conn.close();

            }

        } catch (SQLException err) {

            err.printStackTrace();

        }

    }


    public static void free(ResultSet rs, Statement st, Connection conn) {

        free(rs);
        free(st);
        free(conn);

    }
}

