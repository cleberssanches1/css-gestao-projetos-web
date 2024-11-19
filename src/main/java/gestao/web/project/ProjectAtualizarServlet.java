package gestao.web.project;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.project.dto.ProjectAdapter;
import domain.project.dto.ProjectDTO;
import domain.project.dto.ProjectResponseDTO;
import domain.project.service.ProjectService;
import domain.project.service.ProjectValidationService;
import utils.exceptions.ServiceException;
import utils.exceptions.ValidacaoException;

@WebServlet("/projeto/atualizar")
public class ProjectAtualizarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String FORM = "/projeto/formEditar.jsp";
	private static final String ERRO = "/publico/erro.jsp";
	private static final String DESTINO = "/projeto/listar.jsp";

	public ProjectAtualizarServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProjectService projectService = new ProjectService();

		ProjectDTO project = ProjectAtualizarServlet.getProjectDTO(request);

		try {

			String s = request.getParameter("codeProject");
			if (s != null && !s.isEmpty()) {
				project.setProjectCode(Integer.valueOf(s));
			}

			ProjectValidationService.validate(project);
			projectService.updateProject(project.getProjectCode().toString(), ProjectAdapter.dotoToRequestDto(project));

			List<ProjectResponseDTO> itens = projectService.getAllProjects(0, 100, "ASC").getContent();

			request.setAttribute("itens", itens);
			request.getRequestDispatcher(DESTINO).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(ERRO).forward(request, response);
		} catch (ValidacaoException e) {
			request.setAttribute("erros", e.getErros());
			request.setAttribute("item", project);
			request.getRequestDispatcher(FORM).forward(request, response);
		}
	}

	public static ProjectDTO getProjectDTO(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ProjectDTO dto = new ProjectDTO();
		String s;

		s = request.getParameter("nomeProjeto");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setProjectName(s);
			} catch (NumberFormatException e) {
				System.out.println("Instanciacao: nomeProjeto invalido");
			}
		}

		s = request.getParameter("projectStartDate");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setProjectStartDate(sdf.parse(s));
			} catch (ParseException e) {
				System.out.println("Instanciacao: projectStartDate invalido");
			}
		}

		s = request.getParameter("responsibleManager");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setResponsibleManager(Integer.parseInt(s));
			} catch (NumberFormatException e) {
				System.out.println("Instanciacao: responsibleManager invalido");
			}
		}

		s = request.getParameter("forecastEndProject");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setForecastEndProject(sdf.parse(s));
			} catch (ParseException e) {
				System.out.println("Instanciacao: forecastEndProject invalido");
			}
		}

		s = request.getParameter("realEndProject");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setRealEndProject(sdf.parse(s));
			} catch (ParseException e) {
				System.out.println("Instanciacao: realEndProject invalido");
			}
		}

		s = request.getParameter("cache");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setTotalProjectBudgetValue(new BigDecimal(s));
			} catch (NumberFormatException e) {
				System.out.println("Instanciacao: totalProjectBudgetValue invalido");
			}
		}

		s = request.getParameter("projectDescription");
		if (s != null && !s.isEmpty()) {
			dto.setProjectDescription(s);
		}

		s = request.getParameter("risk");
		if (s != null && !s.isEmpty()) {
			dto.setRisk(s);
		}

		s = request.getParameter("projectStatus");
		if (s != null && !s.isEmpty()) {
			try {
				dto.setProjectStatus(Integer.parseInt(s));
			} catch (NumberFormatException e) {
				System.out.println("Instanciacao: projectStatus invalido");
			}
		}

		return dto;
	}

}