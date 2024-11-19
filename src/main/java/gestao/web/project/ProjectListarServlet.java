package gestao.web.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.project.dto.ProjectResponseDTO;
import domain.project.service.ProjectService;
import utils.CustomPage;

@WebServlet("/projeto/listar")
public class ProjectListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DESTINY = "/projeto/listar.jsp";
	
	public ProjectListarServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			ProjectService projectService = new ProjectService();

			CustomPage<ProjectResponseDTO> reponse = projectService.getAllProjects(0, 100, "DESC");

			request.setAttribute("itens", reponse.getContent());
			request.getRequestDispatcher(DESTINY).forward(request, response);
			
		} catch (Exception e) {
			response.getWriter().append("Erro encontrado :" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
 
}