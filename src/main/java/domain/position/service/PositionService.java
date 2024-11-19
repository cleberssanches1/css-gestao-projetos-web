package domain.position.service;

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

import domain.position.dto.PositionRequestDTO;
import domain.position.dto.PositionResponseDTO;
import utils.CustomPage;
import utils.exceptions.ServiceException;

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
	 * @throws ServiceException
	 */
	public PositionResponseDTO createPosition(PositionRequestDTO request) throws ServiceException {
		PositionResponseDTO response = null;
		try {
			String url = URL_BASE + "/gestao-projeto/cargo";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<PositionRequestDTO> entity = new HttpEntity<>(request, headers);
			response = restTemplate.postForObject(url, entity, PositionResponseDTO.class);

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return response;
	}

	/**
	 * Método para fazer a requisição GET para todos os projetos e receber uma lista
	 * de PositionResponseDTO
	 * 
	 * @param page int
	 * @param size int
	 * @param sort String
	 * @return CustomPage<PositionResponseDTO>
	 * @throws ServiceException
	 */
	public CustomPage<PositionResponseDTO> getAllPositions(int page, int size, String sort) throws ServiceException {

		CustomPage<PositionResponseDTO> response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/cargo/all?page=" + page + "&size=" + size + "&sort=" + sort;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<CustomPage<PositionResponseDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
					entity, new ParameterizedTypeReference<CustomPage<PositionResponseDTO>>() {
					});

			response = responseEntity.getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return response;
	}

	/**
	 * Método para fazer a requisição GET por ID e receber um PositionResponseDTO
	 * 
	 * @param id String
	 * @return PositionResponseDTO
	 * @throws ServiceException
	 */
	public PositionResponseDTO getPositionById(String id) throws ServiceException {

		PositionResponseDTO response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/cargo/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			response = restTemplate.exchange(url, HttpMethod.GET, entity, PositionResponseDTO.class).getBody();

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return response;
	}

	/**
	 * Método para fazer a requisição PUT para atualizar um projeto e receber um
	 * PositionResponseDTO
	 * 
	 * @param id      String
	 * @param request PositionRequestDTO
	 * @return PositionResponseDTO
	 * @throws ServiceException
	 */
	public PositionResponseDTO updatePosition(String id, PositionRequestDTO request) throws ServiceException {

		PositionResponseDTO response = null;

		try {
			String url = URL_BASE + "/gestao-projeto/cargo/" + id;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<PositionRequestDTO> entity = new HttpEntity<>(request, headers);

			response = restTemplate.exchange(url, HttpMethod.PUT, entity, PositionResponseDTO.class).getBody();

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
	public void deletePosition(String id) throws ServiceException {
		try {
			String url = URL_BASE + "/gestao-projeto/cargo/" + id;
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
	 * PositionResponseDTO
	 * 
	 * @param name String
	 * @return List<PositionResponseDTO>
	 * @throws ServiceException
	 */
	public List<PositionResponseDTO> getPositionByName(String name) throws ServiceException {

		List<PositionResponseDTO> responseList = new ArrayList<>();
		try {
			String url = URL_BASE + "/gestao-projeto/cargo/nome/" + name;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<List<PositionResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<PositionResponseDTO>>() {
					});

			responseList = response.getBody();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return responseList;
	}
}
