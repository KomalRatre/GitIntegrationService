package com.spring.RestApiGitHub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/github")
public class GitHubController {

	private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public GitHubController(RestTemplate restTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    //About the organization
    @GetMapping("/{orgName}")
    public ResponseEntity<String> getOrganization(@PathVariable("orgName") String orgName) {
        String apiUrl = "https://api.github.com/orgs/" + orgName;
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        sendEventToKafka("organizations", response.getBody());
        return response;
    }
    //About the members of the organization
    @GetMapping("{orgName}/members")
    public ResponseEntity<String> getOrganizationMembers(@PathVariable("orgName") String orgName) {
        String apiUrl = "https://api.github.com/orgs/" + orgName + "/members";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        sendEventToKafka("members", response.getBody());
        return response;
    }  
    //About the repositories of the organization
    @GetMapping("{orgName}/repos")
    public ResponseEntity<String> getOrganizationRepos(@PathVariable("orgName") String orgName) {
        String apiUrl = "https://api.github.com/orgs/" + orgName + "/repos";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        sendEventToKafka("repos", response.getBody());
        return response;
    }
    //Events in an repository
    @GetMapping("{orgName}/{repo}/events")
    public ResponseEntity<String> getRepositoryEvents(@PathVariable("orgName") String orgName,
    		@PathVariable("repo") String repo){
    	String apiUrl = "https://api.github.com/repos/" + orgName + "/" + repo + "/events";
    	ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
    	sendEventToKafka("events", response.getBody());
    	return response;
    }
    //Contributors in an repository
    @GetMapping("{orgName}/{repo}/contributors")
    public ResponseEntity<String> getRepositoryContributors(@PathVariable("orgName") String orgName,
    		@PathVariable("repo") String repo){
    	String apiUrl = "https://api.github.com/repos/" + orgName + "/" + repo + "/contributors";
    	ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
    	sendEventToKafka("contributors", response.getBody());
    	return response;
    }
    //Pull requests in an repository
    @GetMapping("{orgName}/{repo}/pulls")
    public ResponseEntity<String> getRepositoryPulls(@PathVariable("orgName") String orgName,
    		@PathVariable("repo") String repo){
    	String apiUrl = "https://api.github.com/repos/" + orgName + "/" + repo + "/pulls";
    	ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
    	sendEventToKafka("pulls", response.getBody());
    	return response;
    }
    //Commits in an repository
    @GetMapping("{orgName}/{repo}/commits")
    public ResponseEntity<String> getRepositoryCommits(@PathVariable("orgName") String orgName,
    		@PathVariable("repo") String repo){
    	String apiUrl = "https://api.github.com/repos/" + orgName + "/" + repo + "/commits";
    	ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
    	sendEventToKafka("commits", response.getBody());
    	return response;
    } 
    
    private void sendEventToKafka(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}









