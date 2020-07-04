package web;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpringRestClient_v2 {
    private static HttpHeaders headers = new HttpHeaders();
    private static HttpEntity<String> entity = new HttpEntity<>(headers);
    private static final String USERS_URL = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_USER_URL = "http://91.241.64.178:7081/api/users/{id}";
    private static RestTemplate restTemplate = new RestTemplate();
    private static String set_cookie;

    public static void main(String[] args) {
        get();
        post();
        put();
        //delete();
        delete_v2();
    }

    public static void get(){
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> res = restTemplate.exchange(USERS_URL, HttpMethod.GET, entity, String.class);
        System.out.println(res);

        headers = res.getHeaders();
        set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + res.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void post(){
        //RestTemplate template = new RestTemplate();
        User user = new User(new Long(3), "James", "Brown", (byte) 20);

        HttpEntity<User> request = new HttpEntity<>(user);
        HttpEntity<String> response = restTemplate.exchange(USERS_URL, HttpMethod.POST, request, String.class);
        headers = response.getHeaders();
        //set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void put(){
        User user = new User(new Long(3), "Thomas", "Shelby", (byte) 20);

        HttpEntity<User> request = new HttpEntity<>(user);
        HttpEntity<String> response = restTemplate.exchange(USERS_URL, HttpMethod.POST, request, String.class);
        headers = response.getHeaders();
        //set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void delete(){
        User user = new User(new Long(3), "Thomas", "Shelby", (byte) 20);

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "3");

        HttpEntity<User> request = new HttpEntity<>(user);

        HttpEntity<String> response = restTemplate.exchange(DELETE_USER_URL, HttpMethod.DELETE, request, String.class, params);
        headers = response.getHeaders();
        //set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void delete_v2(){
        String url1 = "http://91.241.64.178:7081/api/users/3";

        set_cookie = headers.getFirst(headers.SET_COOKIE);

        restTemplate.delete(url1, User.class);
    }
}
