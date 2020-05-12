package web_project.requests;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;

import web_project.requests.controller.SelectController;

/**
 * Servlet implementation class test
 */
@WebServlet("/RequestSelectAllUser")
public class RequestSelectAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestSelectAllUser() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SelectController.selectAllUser(request, response);
		} catch (Exception e) {
			response.sendError(500, ExceptionUtils.getRootCauseMessage(e));
		}
	}
}
