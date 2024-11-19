package domain.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import domain.project.dto.ProjectRequestDTO;
import domain.project.dto.ProjectResponseDTO;
import utils.CustomPage;
import utils.exceptions.ServiceException;

public class ProjectService {

	private final String URL_BASE = "http://localhost:8091";

	private final RestTemplate restTemplate;

	public ProjectService() {
		this.restTemplate = new RestTemplate();
	}

	/**
	 * Método para fazer a requisição POST e receber um ProjectResponseDTO
	 * 
	 * @param request ProjectRequestDTO
	 * @return ProjectResponseDTO
	 * @throws ServiceException
	 */
	public ProjectResponseDTO createProject(ProjectRequestDTO request) throws ServiceException {
		ProjectResponseDTO response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/projeto";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<ProjectRequestDTO> entity = new HttpEntity<>(request, headers);

			response = restTemplate.postForObject(url, entity, ProjectResponseDTO.class);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	/**
	 * Método para fazer a requisição GET para todos os projetos e receber uma lista
	 * de ProjectResponseDTO
	 * 
	 * @param page int
	 * @param size int
	 * @param sort String
	 * @return CustomPage<ProjectResponseDTO>
	 * @throws ServiceException
	 */
	public CustomPage<ProjectResponseDTO> getAllProjects(int page, int size, String sort) throws ServiceException {

		CustomPage<ProjectResponseDTO> response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/projeto/all?page=" + page + "&size=" + size + "&sort=" + sort;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<CustomPage<ProjectResponseDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
					entity, new ParameterizedTypeReference<CustomPage<ProjectResponseDTO>>() {
					});

			response = responseEntity.getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	/**
	 * Método para fazer a requisição GET por ID e receber um ProjectResponseDTO
	 * 
	 * @param id String
	 * @return ProjectResponseDTO
	 * @throws ServiceException
	 */
	public ProjectResponseDTO getProjectById(String id) throws ServiceException {

		ProjectResponseDTO response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/projeto/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			response = restTemplate.exchange(url, HttpMethod.GET, entity, ProjectResponseDTO.class).getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	/**
	 * Método para fazer a requisição PUT para atualizar um projeto e receber um
	 * ProjectResponseDTO
	 * 
	 * @param id      String
	 * @param request ProjectRequestDTO
	 * @return ProjectResponseDTO
	 * @throws ServiceException
	 */
	public ProjectResponseDTO updateProject(String id, ProjectRequestDTO request) throws ServiceException {

		ProjectResponseDTO response = null;

		try {

			String url = URL_BASE + "/gestao-projeto/projeto/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<ProjectRequestDTO> entity = new HttpEntity<>(request, headers);

			response = restTemplate.exchange(url, HttpMethod.PUT, entity, ProjectResponseDTO.class).getBody();

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return response;
	}

	/**
	 * Método para fazer a requisição DELETE
	 * 
	 * @param id String
	 * @throws ServiceException
	 */
	public void deleteProject(String id) throws ServiceException {
		String url = URL_BASE + "/gestao-projeto/projeto/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		try {
			restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Método para fazer a requisição GET por nome do projeto e receber uma lista de
	 * ProjectResponseDTO
	 * 
	 * @param name String
	 * @return List<ProjectResponseDTO>
	 * @throws ServiceException
	 */
	public List<ProjectResponseDTO> getProjectByName(String name) throws ServiceException {

		List<ProjectResponseDTO> responseList = new ArrayList<>();

		try {
			String url = URL_BASE + "/gestao-projeto/projeto/nome/" + name;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<List<ProjectResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<ProjectResponseDTO>>() {
					});

			responseList = response.getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return responseList;
	}
}
