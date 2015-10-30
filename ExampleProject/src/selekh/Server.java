package selekh;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import selekh.DBQueries;
import selekh.MysqlDAOFactory;

public class Server implements IServer {

	@Override
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username").toString();
		String password = req.getParameter("password").toString();
		Connection con = null;
		boolean status;
		try {
			con = MysqlDAOFactory.getConnection();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DBQueries.LOGIN);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			status = false;
			while (rs.next()) {
				String name = rs.getString("name");
				String pass = rs.getString("pass");
				if (new String(name).equals(username) && new String(pass).equals(password)) {
					status = true;
				}
			}
			if (status) {
				resp.getWriter().write("OK");
			} else {
				resp.getWriter().write("Wrong credentials");
			}

			MysqlDAOFactory.closeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp.getWriter().write("Error: SQLException, try later");
		} finally {
			MysqlDAOFactory.close(con);
		}

	}

	public void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DBQueries.GET_ALL_USERS);
			ResultSet rs = stmt.executeQuery();
			StringBuilder result = new StringBuilder();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				result.append(id + ":" + name + ";");
			}
			resp.getWriter().write(result.toString());

			MysqlDAOFactory.closeStatement(stmt);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp.getWriter().write("ERROR");
		} finally {
			MysqlDAOFactory.close(con);
		}

	}

	public void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = new User("", 0);
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DBQueries.GET_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int id2 = rs.getInt("id");
				if (id == id2) {
					user = new User(name, id);
				}
			}
			MysqlDAOFactory.closeStatement(stmt);
			resp.getWriter().write(user.getID() + ":" + user.getName());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	public void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username").toString();
		int id = Integer.parseInt(req.getParameter("id"));
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DBQueries.INSERT_USER);
			stmt.setInt(1, id);
			stmt.setString(2, username);
			boolean status = false;
			if (stmt.executeUpdate() != 1) {
				MysqlDAOFactory.roolback(con);
				return;
			} else {
				con.commit();
			}
			stmt = con.prepareStatement(DBQueries.GET_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				if (new String(name).equals(username)) {
					status = true;
				}
			}
			MysqlDAOFactory.closeStatement(stmt);
			if (status)
				resp.getWriter().write("OK");
			else
				resp.getWriter().write("Can't create user, try later");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	public void registrate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			int status = registrateMember(con, username, password);
			if (status == 1)
				resp.getWriter().write("OK");
			else if (status == 0)
				resp.getWriter().write("User is alredy exist");
			else
				resp.getWriter().write("Can't register, try later");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp.getWriter().write("ERROR");
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	private int registrateMember(Connection con, String username, String password) throws SQLException {
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(DBQueries.GET_ALL_MEMBERS);
		ResultSet rs = stmt.executeQuery();
		int id = 0;
		while (rs.next()) {
			String name = rs.getString("name");
			if (new String(name).equals(username))
				return 0;
			id = rs.getInt("id");
		}
		id++;
		stmt = con.prepareStatement(DBQueries.INSERT_MEMBER);
		stmt.setInt(1, id);
		stmt.setString(2, username);
		stmt.setString(3, password);
		if (stmt.executeUpdate() != 1) {
			MysqlDAOFactory.roolback(con);
			return -1;
		} else {
			con.commit();
		}
		MysqlDAOFactory.closeStatement(stmt);
		return 1;
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DBQueries.DELETE_USER);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() != 1) {
				MysqlDAOFactory.roolback(con);
				resp.getWriter().write("Can't delete, try later");
				return;
			} else {
				con.commit();
			}
			MysqlDAOFactory.closeStatement(stmt);
			resp.getWriter().write("OK");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username").toString();
		int id = Integer.parseInt(req.getParameter("id"));
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DBQueries.UPDATE_USER);
			stmt.setString(1, username);
			stmt.setInt(2, id);
			boolean status = false;
			if (stmt.executeUpdate() != 1) {
				MysqlDAOFactory.roolback(con);
				return;
			} else {
				con.commit();
			}
			stmt = con.prepareStatement(DBQueries.GET_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				if (new String(name).equals(username)) {
					status = true;
				}
			}
			MysqlDAOFactory.closeStatement(stmt);
			if (status)
				resp.getWriter().write("OK");
			else
				resp.getWriter().write("Can't save user, try later");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			MysqlDAOFactory.close(con);
		}
	}
}
