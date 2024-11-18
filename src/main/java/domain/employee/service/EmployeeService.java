package domain.employee.service;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import domain.employee.dto.EmployeeRequestDTO;
import domain.employee.dto.EmployeeResponseDTO;
import utils.CustomPage;

public class EmployeeService {

	private final String URL_BASE = "http://localhost:8091";

	private final RestTemplate restTemplate;

	public EmployeeService() {
		this.restTemplate = new RestTemplate();
	}

	/**
	 * Método para fazer a requisição POST e receber um EmployeeResponseDTO
	 * 
	 * @param request EmployeeRequestDTO
	 * @return EmployeeResponseDTO
	 */
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
		String url = URL_BASE + "/gestao-projeto/colaborador";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<EmployeeRequestDTO> entity = new HttpEntity<>(request, headers);
		return restTemplate.postForObject(url, entity, EmployeeResponseDTO.class);
	}

	/**
	 * Método para fazer a requisição GET para todos os projetos e receber uma lista
	 * de EmployeeResponseDTO
	 * 
	 * @param page int
	 * @param size int
	 * @param sort String
	 * @return CustomPage<EmployeeResponseDTO>
	 */
	public CustomPage<EmployeeResponseDTO> getAllEmployees(int page, int size, String sort) {
		String url = URL_BASE + "/gestao-projeto/colaborador/all?page=" + page + "&size=" + size + "&sort=" + sort;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<CustomPage<EmployeeResponseDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				entity, new ParameterizedTypeReference<CustomPage<EmployeeResponseDTO>>() {
				});

		return responseEntity.getBody();
	}

	/**
	 * Método para fazer a requisição GET por ID e receber um EmployeeResponseDTO
	 * 
	 * @param id String
	 * @return EmployeeResponseDTO
	 */
	public EmployeeResponseDTO getEmployeeById(String id) {
		String url = URL_BASE + "/gestao-projeto/colaborador/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(url, HttpMethod.GET, entity, EmployeeResponseDTO.class).getBody();
	}

	/**
	 * Método para fazer a requisição PUT para atualizar um projeto e receber um
	 * EmployeeResponseDTO
	 * 
	 * @param id      String
	 * @param request EmployeeRequestDTO
	 * @return EmployeeResponseDTO
	 */
	public EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO request) {
		String url = URL_BASE + "/gestao-projeto/colaborador/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<EmployeeRequestDTO> entity = new HttpEntity<>(request, headers);
		return restTemplate.exchange(url, HttpMethod.PUT, entity, EmployeeResponseDTO.class).getBody();
	}

	/**
	 * Método para fazer a requisição DELETE
	 * 
	 * @param id String
	 */
	public void deleteEmployee(String id) {
		String url = URL_BASE + "/gestao-projeto/colaborador/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	}

	/**
	 * Método para fazer a requisição GET por nome do projeto e receber uma lista de
	 * EmployeeResponseDTO
	 * 
	 * @param cpf String
	 * @return List<EmployeeResponseDTO>
	 */
	public List<EmployeeResponseDTO> getEmployeeByName(String cpf) {
		String url = URL_BASE + "/gestao-projeto/colaborador/cpf/" + cpf;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<EmployeeResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<EmployeeResponseDTO>>() {
				});
		return response.getBody();
	}
}
