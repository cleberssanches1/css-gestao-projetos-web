package gestao.web.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.project.dto.ProjectResponseDTO;
import domain.project.service.ProjectService;
import utils.exceptions.ServiceException;

@WebServlet("/projeto/excluir")
public class ProjectExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DESTINY = "/projeto/listar.jsp";
	private static final String ERRO = "/publico/erro.jsp";

	public ProjectExcluirServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProjectService projectService = new ProjectService();

		String cod = request.getParameter("cod");
		try {
			projectService.deleteProject(cod);

			List<ProjectResponseDTO> itens = projectService.getAllProjects(0, 100, "ASC").getContent();

			request.setAttribute("itens", itens);
			request.getRequestDispatcher(DESTINY).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(ERRO).forward(request, response);
		}
	}

}