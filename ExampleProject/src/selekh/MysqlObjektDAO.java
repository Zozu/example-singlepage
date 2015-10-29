package selekh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import selekh.DBQueries;
import selekh.User;
//import ua.nure.cache.java.mapper.Mapper;

public class MysqlObjektDAO {

	public int insertObjekt(User obj) {
		int result = -1;
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			result = insertObjekt(con, obj);
			if (result > 0) {
				con.commit();
			} else {
				MysqlDAOFactory.roolback(con);
			}
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return result;
	}

	private int insertObjekt(Connection con, User obj) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = con.prepareStatement(DBQueries.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, obj.getName());
			if (pstmt.executeUpdate() != 1) {
				return -1;
			} else {
				ResultSet generatedKeys = pstmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					result = generatedKeys.getInt(1);
				}
				return result;
			}
		} catch (SQLException e) {
		} finally {
			MysqlDAOFactory.closeStatement(pstmt);
		}
		return result;
	}

	/*public User getById(int objId) {
		Connection con = null;
		//User proj = new User();
		try {
			con = MysqlDAOFactory.getConnection();
			proj = findObjekt(con, objId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			MysqlDAOFactory.close(con);
		}
		return proj;

	}

	private User findObjekt(Connection con, int objId) throws SQLException {
		PreparedStatement stmt = null;
		//User proj = new User();
		stmt = con.prepareStatement(DBQueries.GET_BY_ID);
		stmt.setInt(1, objId);
		ResultSet rs = stmt.executeQuery();
		//proj = Mapper.unmapObj(rs);
		MysqlDAOFactory.closeStatement(stmt);
		return proj;
	}*/

	public boolean delete(int objId) {
		boolean result = false;
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			result = deleteObjekt(con, objId);
			if (result) {
				con.commit();
			} else {
				MysqlDAOFactory.roolback(con);
			}
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return result;
	}

	private boolean deleteObjekt(Connection con, int objId) throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(DBQueries.DELETE_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, objId);
			if (pstmt.executeUpdate() != 1) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
		} finally {
			MysqlDAOFactory.closeStatement(pstmt);
		}
		return result;
	}

	public boolean update(User obj) {
		boolean result = true;
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			result = updateObjekt(con, obj);
			if (result) {
				con.commit();
			} else {
				MysqlDAOFactory.roolback(con);
			}
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return result;
	}

	private boolean updateObjekt(Connection con, User obj) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DBQueries.UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, obj.getName());
			if (pstmt.executeUpdate() != 1) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
		} finally {
			MysqlDAOFactory.closeStatement(pstmt);
		}
		return false;
	}

}
