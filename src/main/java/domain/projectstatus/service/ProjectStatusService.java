package domain.projectstatus.service;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import domain.projectstatus.dto.ProjectStatusRequestDTO;
import domain.projectstatus.dto.ProjectStatusResponseDTO;
import utils.CustomPage;

public class ProjectStatusService {
	
	private final String URL_BASE = "http://localhost:8091";

	private final RestTemplate restTemplate;

	public ProjectStatusService() {
		this.restTemplate = new RestTemplate();
	}

	/**
	 * Método para fazer a requisição POST e receber um ProjectStatusResponseDTO
	 * 
	 * @param request ProjectStatusRequestDTO
	 * @return ProjectStatusResponseDTO
	 */
	public ProjectStatusResponseDTO createProjectStatus(ProjectStatusRequestDTO request) {
		String url = URL_BASE + "/gestao-projeto/projeto/status";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<ProjectStatusRequestDTO> entity = new HttpEntity<>(request, headers);
		return restTemplate.postForObject(url, entity, ProjectStatusResponseDTO.class);
	}

	/**
	 * Método para fazer a requisição GET para todos os projetos e receber uma lista
	 * de ProjectStatusResponseDTO
	 * 
	 * @param page int
	 * @param size int
	 * @param sort String
	 * @return CustomPage<ProjectStatusResponseDTO>
	 */
	public CustomPage<ProjectStatusResponseDTO> getAllProjectStatuss(int page, int size, String sort) {
		String url = URL_BASE + "/gestao-projeto/projeto/status/all?page=" + page + "&size=" + size + "&sort=" + sort;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<CustomPage<ProjectStatusResponseDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				entity, new ParameterizedTypeReference<CustomPage<ProjectStatusResponseDTO>>() {
				});

		return responseEntity.getBody();
	}

	/**
	 * Método para fazer a requisição GET por ID e receber um ProjectStatusResponseDTO
	 * 
	 * @param id String
	 * @return ProjectStatusResponseDTO
	 */
	public ProjectStatusResponseDTO getProjectStatusById(String id) {
		String url = URL_BASE + "/gestao-projeto/projeto/status/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(url, HttpMethod.GET, entity, ProjectStatusResponseDTO.class).getBody();
	}

	/**
	 * Método para fazer a requisição PUT para atualizar um projeto e receber um
	 * ProjectStatusResponseDTO
	 * 
	 * @param id      String
	 * @param request ProjectStatusRequestDTO
	 * @return ProjectStatusResponseDTO
	 */
	public ProjectStatusResponseDTO updateProjectStatus(String id, ProjectStatusRequestDTO request) {
		String url = URL_BASE + "/gestao-projeto/projeto/status/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<ProjectStatusRequestDTO> entity = new HttpEntity<>(request, headers);
		return restTemplate.exchange(url, HttpMethod.PUT, entity, ProjectStatusResponseDTO.class).getBody();
	}

	/**
	 * Método para fazer a requisição DELETE
	 * 
	 * @param id String
	 */
	public void deleteProjectStatus(String id) {
		String url = URL_BASE + "/gestao-projeto/projeto/status/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	}

	/**
	 * Método para fazer a requisição GET por nome do projeto e receber uma lista de
	 * ProjectStatusResponseDTO
	 * 
	 * @param cpf String
	 * @return List<ProjectStatusResponseDTO>
	 */
	public List<ProjectStatusResponseDTO> getProjectStatusByName(String name) {
		String url = URL_BASE + "/gestao-projeto/projeto/status/name/" + name;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<ProjectStatusResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ProjectStatusResponseDTO>>() {
				});
		return response.getBody();
	}
}
