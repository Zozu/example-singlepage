package selekh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller {

	private static Map<String, Method> methods = new HashMap<String, Method>();

	static {
		try {
			Class<?> cardManager = Server.class;
			Class<?> request = HttpServletRequest.class;
			Class<?> response = HttpServletResponse.class;

			methods.put("/users/all", cardManager.getDeclaredMethod(
					"getAll", request, response));
			methods.put("/user", cardManager.getDeclaredMethod(
					"getById", request, response));
			methods.put("/users/add", cardManager.getDeclaredMethod(
					"create", request, response));
			methods.put("/users/update", cardManager.getDeclaredMethod(
					"update", request, response));
			methods.put("/users/delete", cardManager.getDeclaredMethod(
					"delete", request, response));
			methods.put("/members/add", cardManager.getDeclaredMethod(
					"registrate", request, response));
			methods.put("/member", cardManager.getDeclaredMethod(
					"login", request, response));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static Method get(String commandName) {
		if (commandName == null || !methods.containsKey(commandName)) {
			return null;
		}
		return methods.get(commandName);
	}

	public static void routeIt(String url, HttpServletRequest request,
			HttpServletResponse response) {
		Method m = get(url);
		System.out.println(url);
		if (m == null) {
			return;
		}
		try {
			m.invoke(m.getDeclaringClass().newInstance(), request, response);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}



}
