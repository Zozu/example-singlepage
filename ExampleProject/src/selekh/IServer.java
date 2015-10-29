package selekh;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IServer {

	void getAll(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

	void getById(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

	void create(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

	void registrate(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

	void delete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

	void update(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

	void login(HttpServletRequest req, HttpServletResponse resp)
			throws IOException;

}
