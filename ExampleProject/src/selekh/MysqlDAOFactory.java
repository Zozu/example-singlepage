package selekh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MysqlDAOFactory {

	private static DataSource getDataSource()  {
		DataSource dataSource = null;
		try {
			InitialContext initContext = new InitialContext();
			dataSource = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/java_lab2");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return dataSource;
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = getDataSource().getConnection();
		} catch (Exception e) {
			con = getExceptCon();
		}
		return con;
	}
	private static Connection getExceptCon() {
		Connection dbConnection = null;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	    try {
	        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_lab2"
	        		, "root","");
	        return dbConnection;
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return dbConnection;
	}
	public static void roolback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			try {
				System.out.println(e.getMessage());
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}

	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void closeStatement(Statement stmt) throws SQLException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}
	}

	public static void commitAndClose(Connection con) {
		commit(con);
		close(con);
	}



}
