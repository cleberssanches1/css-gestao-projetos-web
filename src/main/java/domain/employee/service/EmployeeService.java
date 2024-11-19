package domain.employee.service;

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

import domain.employee.dto.EmployeeRequestDTO;
import domain.employee.dto.EmployeeResponseDTO;
import utils.CustomPage;
import utils.exceptions.ServiceException;

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
	 * @throws ServiceException
	 */
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) throws ServiceException {

		EmployeeResponseDTO response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/colaborador";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<EmployeeRequestDTO> entity = new HttpEntity<>(request, headers);

			response = restTemplate.postForObject(url, entity, EmployeeResponseDTO.class);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return response;

	}

	/**
	 * Método para fazer a requisição GET para todos os projetos e receber uma lista
	 * de EmployeeResponseDTO
	 * 
	 * @param page int
	 * @param size int
	 * @param sort String
	 * @return CustomPage<EmployeeResponseDTO>
	 * @throws ServiceException
	 */
	public CustomPage<EmployeeResponseDTO> getAllEmployees(int page, int size, String sort) throws ServiceException {

		CustomPage<EmployeeResponseDTO> response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/colaborador/all?page=" + page + "&size=" + size + "&sort=" + sort;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<CustomPage<EmployeeResponseDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
					entity, new ParameterizedTypeReference<CustomPage<EmployeeResponseDTO>>() {
					});

			response = responseEntity.getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	/**
	 * Método para fazer a requisição GET por ID e receber um EmployeeResponseDTO
	 * 
	 * @param id String
	 * @return EmployeeResponseDTO
	 * @throws ServiceException
	 */
	public EmployeeResponseDTO getEmployeeById(String id) throws ServiceException {

		EmployeeResponseDTO response = null;
		try {
			String url = URL_BASE + "/gestao-projeto/colaborador/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			response = restTemplate.exchange(url, HttpMethod.GET, entity, EmployeeResponseDTO.class).getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	/**
	 * Método para fazer a requisição PUT para atualizar um projeto e receber um
	 * EmployeeResponseDTO
	 * 
	 * @param id      String
	 * @param request EmployeeRequestDTO
	 * @return EmployeeResponseDTO
	 * @throws ServiceException
	 */
	public EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO request) throws ServiceException {

		EmployeeResponseDTO response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/colaborador/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<EmployeeRequestDTO> entity = new HttpEntity<>(request, headers);

			response = restTemplate.exchange(url, HttpMethod.PUT, entity, EmployeeResponseDTO.class).getBody();
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
	public void deleteEmployee(String id) throws ServiceException {
		try {
			String url = URL_BASE + "/gestao-projeto/colaborador/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);
			restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Método para fazer a requisição GET por nome do projeto e receber uma lista de
	 * EmployeeResponseDTO
	 * 
	 * @param cpf String
	 * @return List<EmployeeResponseDTO>
	 * @throws ServiceException
	 */
	public List<EmployeeResponseDTO> getEmployeeByName(String cpf) throws ServiceException {

		List<EmployeeResponseDTO> responseList = new ArrayList<>();

		try {

			String url = URL_BASE + "/gestao-projeto/colaborador/cpf/" + cpf;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<List<EmployeeResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<EmployeeResponseDTO>>() {
					});

			responseList = response.getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return responseList;
	}
}
