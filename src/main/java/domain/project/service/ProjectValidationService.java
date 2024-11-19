package domain.project.service;

import java.util.ArrayList;
import java.util.List;

import domain.project.dto.ProjectDTO;
import utils.exceptions.ValidacaoException;

public class ProjectValidationService {

	public static void validate(ProjectDTO project) throws ValidacaoException {
		List<String> erros = new ArrayList<>();

		if (project.getProjectDescription() == null) {
			erros.add("Favor preencher o campo descrição do projeto");
		}
		if (project.getProjectName() == null) {
			erros.add("Favor preencher o campo nome do projeto");
		}
		if (project.getProjectStatus() == null) {
			erros.add("Favor preencher um valor válido para o Status");
		}
		if (project.getResponsibleManager() == null) {
			erros.add("Favor preencher um valor válido para o responsável");
		}

		if (!erros.isEmpty()) {
			throw new ValidacaoException("Erro de validação", erros);
		}
	}
	
}
