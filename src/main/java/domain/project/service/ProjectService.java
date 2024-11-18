package domain.project.service;

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

public class ProjectService {

	private final String URL_BASE = "http://localhost:8091";
	
    private final RestTemplate restTemplate;
 
    public ProjectService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     *  Método para fazer a requisição POST e receber um ProjectResponseDTO
     * @param request ProjectRequestDTO
     * @return ProjectResponseDTO
     */
    public ProjectResponseDTO createProject(ProjectRequestDTO request) {
        String url = URL_BASE +"/gestao-projeto/projeto";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<ProjectRequestDTO> entity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(url, entity, ProjectResponseDTO.class);
    }

    /**
     *  Método para fazer a requisição GET para todos os projetos e receber uma lista de ProjectResponseDTO
     * @param page int
     * @param size int
     * @param sort String
     * @return CustomPage<ProjectResponseDTO>
     */
    public CustomPage<ProjectResponseDTO> getAllProjects(int page, int size, String sort) { 
        String url = URL_BASE + "/gestao-projeto/projeto/all?page=" + page + "&size=" + size + "&sort=" + sort;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CustomPage<ProjectResponseDTO>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<CustomPage<ProjectResponseDTO>>() {
                });
      
        return responseEntity.getBody();
    }

    /**
     *  Método para fazer a requisição GET por ID e receber um ProjectResponseDTO
     * @param id String
     * @return ProjectResponseDTO
     */
    public ProjectResponseDTO getProjectById(String id) {
        String url = URL_BASE + "/gestao-projeto/projeto/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, ProjectResponseDTO.class).getBody();
    }

    /**
     *  Método para fazer a requisição PUT para atualizar um projeto e receber um ProjectResponseDTO
     * @param id String
     * @param request ProjectRequestDTO
     * @return ProjectResponseDTO
     */
    public ProjectResponseDTO updateProject(String id, ProjectRequestDTO request) {
        String url =  URL_BASE + "/gestao-projeto/projeto/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<ProjectRequestDTO> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, entity, ProjectResponseDTO.class).getBody();
    }

    /**
     *  Método para fazer a requisição DELETE
     * @param id String
     */
    public void deleteProject(String id) {
        String url = URL_BASE + "/gestao-projeto/projeto/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
    }

    /**
     *  Método para fazer a requisição GET por nome do projeto e receber uma lista de ProjectResponseDTO
     * @param name String
     * @return List<ProjectResponseDTO>
     */
    public List<ProjectResponseDTO> getProjectByName(String name) {
        String url = URL_BASE + "/gestao-projeto/projeto/nome/" + name;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<ProjectResponseDTO>> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProjectResponseDTO>>() {});
        return response.getBody();
    }
}

