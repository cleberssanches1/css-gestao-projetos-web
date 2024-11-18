package domain.position.service;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import domain.position.dto.PositionRequestDTO;
import domain.position.dto.PositionResponseDTO;
import utils.CustomPage;

public class PositionService {

	private final String URL_BASE = "http://localhost:8091";

	private final RestTemplate restTemplate;

	public PositionService() {
		this.restTemplate = new RestTemplate();
	}

	/**
	 * Método para fazer a requisição POST e receber um PositionResponseDTO
	 * 
	 * @param request PositionRequestDTO
	 * @return PositionResponseDTO
	 */
	public PositionResponseDTO createPosition(PositionRequestDTO request) {
		String url = URL_BASE + "/gestao-projeto/cargo";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<PositionRequestDTO> entity = new HttpEntity<>(request, headers);
		return restTemplate.postForObject(url, entity, PositionResponseDTO.class);
	}

	/**
	 * Método para fazer a requisição GET para todos os projetos e receber uma lista
	 * de PositionResponseDTO
	 * 
	 * @param page int
	 * @param size int
	 * @param sort String
	 * @return CustomPage<PositionResponseDTO>
	 */
	public CustomPage<PositionResponseDTO> getAllPositions(int page, int size, String sort) {
		String url = URL_BASE + "/gestao-projeto/cargo/all?page=" + page + "&size=" + size + "&sort=" + sort;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<CustomPage<PositionResponseDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				entity, new ParameterizedTypeReference<CustomPage<PositionResponseDTO>>() {
				});

		return responseEntity.getBody();
	}

	/**
	 * Método para fazer a requisição GET por ID e receber um PositionResponseDTO
	 * 
	 * @param id String
	 * @return PositionResponseDTO
	 */
	public PositionResponseDTO getPositionById(String id) {
		String url = URL_BASE + "/gestao-projeto/cargo/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(url, HttpMethod.GET, entity, PositionResponseDTO.class).getBody();
	}

	/**
	 * Método para fazer a requisição PUT para atualizar um projeto e receber um
	 * PositionResponseDTO
	 * 
	 * @param id      String
	 * @param request PositionRequestDTO
	 * @return PositionResponseDTO
	 */
	public PositionResponseDTO updatePosition(String id, PositionRequestDTO request) {
		String url = URL_BASE + "/gestao-projeto/cargo/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<PositionRequestDTO> entity = new HttpEntity<>(request, headers);
		return restTemplate.exchange(url, HttpMethod.PUT, entity, PositionResponseDTO.class).getBody();
	}

	/**
	 * Método para fazer a requisição DELETE
	 * 
	 * @param id String
	 */
	public void deletePosition(String id) {
		String url = URL_BASE + "/gestao-projeto/cargo/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	}

	/**
	 * Método para fazer a requisição GET por nome do projeto e receber uma lista de
	 * PositionResponseDTO
	 * 
	 * @param cpf String
	 * @return List<PositionResponseDTO>
	 */
	public List<PositionResponseDTO> getPositionByName(String name) {
		String url = URL_BASE + "/gestao-projeto/cargo/nome/" + name;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<PositionResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<PositionResponseDTO>>() {
				});
		return response.getBody();
	}
}
