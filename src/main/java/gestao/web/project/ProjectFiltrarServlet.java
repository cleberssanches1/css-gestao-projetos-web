package gestao.web.project;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.project.dto.ProjectResponseDTO;
import domain.project.service.ProjectService;

@WebServlet("/projeto/filtrar")
public class ProjectFiltrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DESTINY = "/projeto/listar.jsp";

	public ProjectFiltrarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			ProjectService projectService = new ProjectService();
			List<ProjectResponseDTO> reponse = null;

			String projectName = request.getParameter("busca");
			if (Objects.isNull(projectName) || projectName.isBlank()) {
				reponse = projectService.getAllProjects(0, 100, "DESC").getContent();
			} else {
				reponse = projectService.getProjectByName(request.getParameter("busca"));
			}

			request.setAttribute("itens", reponse);
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