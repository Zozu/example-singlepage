package selekh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import selekh.DBQueries;
import selekh.User;




public class Server implements IServer {

	@Override
	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String username = req.getParameter("username").toString();
		String password = req.getParameter("password").toString();
		Connection con = null;
		try {
			con = MysqlDAOFactory.getConnection();
			Member proj = findObjekt(con, username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			MysqlDAOFactory.close(con);
		}

		resp.setStatus(100);
	}
	private Member findObjekt(Connection con, String username, String password) throws SQLException {
		PreparedStatement stmt = null;
		//
		stmt = con.prepareStatement(DBQueries.LOGIN);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		Member proj = new Member(rs.getInt(1), rs.getString(2), rs.getString(3));
		MysqlDAOFactory.closeStatement(stmt);
		return proj;
	}

	@Override
	public void getAll(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Integer id = Integer.parseInt(req.getParameter("projectId"));
		//resp.getWriter().print(new Gson().toJson(dao.findProcectObj(id)));
	}




	@Override
	public void getById(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/*ObjektDAO dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
				.getObjektDAO();
		Integer projectId = Integer.parseInt(req.getParameter("projectId"));
		Integer objectId = Integer.parseInt(req.getParameter("objectId"));
		resp.getWriter().print(
				new Gson().toJson(dao.findObjekt(objectId, projectId)));*/
	}

	@Override
	public void create(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/*String line = req.getParameter("object");
		System.out.println(line);
		Objekt jsonJavaRootObject = new Gson().fromJson(line, Objekt.class);
		System.out.println(jsonJavaRootObject.getName());
		ObjektDAO dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
				.getObjektDAO();
		int result = dao.insertObjekt(jsonJavaRootObject);
		Resp res = new Resp();
		if (result != -1) {
			res.setId(result);
			res.setSuccess(true);
		} else {
			res.setId(result);
			res.setSuccess(false);
		}
		resp.getWriter().print(new Gson().toJson(res));*/
	}

	@Override
	public void registrate(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/*String line = req.getParameter("attribute");
		Attribute attr = new Gson().fromJson(line, Attribute.class);
		AttributeDAO dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
				.getAttributeDAO();
		int result = dao.insertAttribute(attr);
		Resp res = new Resp();
		if (result != -1) {
			res.setId(result);
			res.setSuccess(true);
		} else {
			res.setId(result);
			res.setSuccess(false);
		}
		resp.getWriter().print(new Gson().toJson(res));*/
	}



	@Override
	public void delete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/*Integer projectId = Integer.valueOf(req.getParameter("projectId"));
		Integer objectId = Integer.valueOf(req.getParameter("objectId"));
		ObjektDAO dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
				.getObjektDAO();
		boolean result = dao.deleteObjekt(objectId, projectId);
		Resp res = new Resp();
		if (result != false) {
			res.setSuccess(true);
		} else {
			res.setSuccess(false);
		}
		resp.getWriter().print(new Gson().toJson(res));*/

	}

	@Override
	public void update(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/*String line = req.getParameter("obje�t");
		System.out.println(line);
		Objekt jsonJavaRootObject = new Gson().fromJson(line, Objekt.class);
		System.out.println(jsonJavaRootObject.getName());
		ObjektDAO dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
				.getObjektDAO();
		boolean result = dao.updateObjekt(jsonJavaRootObject);
		Resp res = new Resp();
		if (result) {
			res.setSuccess(true);
		} else {
			res.setSuccess(false);
		}
		resp.getWriter().print(new Gson().toJson(res));*/
	}

}
