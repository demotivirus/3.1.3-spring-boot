package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;
//import web.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringRestClient {
    private static final String USERS_URL = "http://91.241.64.178:7081/api/users";
    private static final String MY_URL = "http://localhost:8080/api/user";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws JsonProcessingException {
//        getUsers();
//        createUser();
//        getUsers();

        final String url = "http://91.241.64.178:7081/api/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> res = restTemplate.exchange(USERS_URL, HttpMethod.GET, entity, String.class);
        System.out.println(res);

        headers = res.getHeaders();
        String set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + res.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");


//        RestTemplate template = new RestTemplate();
//        User user = new User(new Long(3), "James", "Brown", (byte) 20);
//
//        HttpEntity<User> request = new HttpEntity<>(user);
//        HttpEntity<String> response = template.exchange(url, HttpMethod.POST, request, String.class);
//        headers = response.getHeaders();
//        set_cookie = headers.getFirst(headers.SET_COOKIE);
//
//        System.out.println("Response: " + response.toString() + "\n");
//        System.out.println("Set-Cookie: " + set_cookie + "\n");
//        System.out.println("Headers: " + headers + "\n");
//        System.out.println("********* FINISH *******");

//        template = new RestTemplate();
//        user = new User(new Long(3), "Thomas", "Shelby", (byte) 20);
//
//        request = new HttpEntity<>(user);
//        response = template.exchange(url, HttpMethod.PUT, request, String.class);
//        headers = response.getHeaders();
//        //set_cookie = headers.getFirst(headers.SET_COOKIE);
//
//        System.out.println("Response: " + response.toString() + "\n");
//        System.out.println("Set-Cookie: " + set_cookie + "\n");
//        System.out.println("********* FINISH *******");

        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        entity = new HttpEntity<>("parameters", headers);

        res = restTemplate.exchange(USERS_URL, HttpMethod.GET, entity, String.class);
        System.out.println(res);

        headers = res.getHeaders();
        set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + res.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");

    }

    private static void getUsers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> res = restTemplate.exchange(USERS_URL, HttpMethod.GET, entity, String.class);
        System.out.println(res);

//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(GET_ALL_USERS_URL, String.class);
//
//        System.out.println(result);

//        RestTemplate template = new RestTemplate();
//        ResponseEntity<String> forEntity = template.getForEntity(GET_ALL_USERS_URL, String.class);
//        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
    }

    private static void createUser() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        User user = new User(new Long(3), "James", "Brown", (byte) 20);
        RestTemplate restTemplate = new RestTemplate();
        User res = restTemplate.postForObject(USERS_URL, user, User.class);

        System.out.println(res);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        User user = new User((long) 86, "IURYi", "IUWYEG", "HHHSUYUY");
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        User result = restTemplate.postForObject(MY_URL, user, User.class);
//        System.out.println(result);

        //===============WORK===================
//        String json="{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":20}";
//        ObjectMapper mapper = new ObjectMapper();
//        User user= mapper.readValue(json, User.class);
//        System.out.println(user);

    }

//    private void createEmployee() {
//
//        Employee newEmployee = new Employee("admin", "admin", "admin@gmail.com");
//
//        RestTemplate restTemplate = new RestTemplate();
//        Employee result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newEmployee, Employee.class);
//
//        System.out.println(result);
//    }
}
