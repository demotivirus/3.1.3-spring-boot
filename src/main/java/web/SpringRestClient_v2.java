package web;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpringRestClient_v2 {
    private static HttpHeaders headers = new HttpHeaders();
    private static HttpEntity<String> entity = new HttpEntity<>(headers);
    private static final String USERS_URL = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_USER_URL = "http://91.241.64.178:7081/api/users/3";
    private static RestTemplate restTemplate = new RestTemplate();
    private static String set_cookie;

    public static void main(String[] args) {
        get();
        post();
        put();
        delete();
        //delete_v2();
    }

    public static void get(){
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> res = restTemplate.exchange(USERS_URL, HttpMethod.GET, entity, String.class);
        System.out.println(res);

        headers = res.getHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + res.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println(headers);
        System.out.println("********* FINISH *******");
    }

    public static void post(){
        //RestTemplate template = new RestTemplate();
        User user = new User(new Long(3), "James", "Brown", (byte) 20);

//        HttpEntity<User> request = new HttpEntity<>(user);
//        HttpEntity<String> response = restTemplate.exchange(USERS_URL, HttpMethod.POST, request, String.class);
//        headers = response.getHeaders();
//        //set_cookie = headers.getFirst(headers.SET_COOKIE);
//
//        System.out.println("Response: " + response.toString() + "\n");
//        System.out.println("Set-Cookie: " + set_cookie + "\n");
//        System.out.println(headers);
//        System.out.println("********* FINISH *******");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", set_cookie);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://91.241.64.178:7081/api/users",
                HttpMethod.POST,
                new HttpEntity<>(user, httpHeaders),
                String.class
        );

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void put(){
        User user = new User(new Long(3), "Thomas", "Shelby", (byte) 20);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", set_cookie);

        HttpEntity<String> response = restTemplate.exchange(USERS_URL, HttpMethod.PUT,
                new HttpEntity<>(user, httpHeaders), String.class);

       // headers = response.getHeaders();
        //set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void delete(){
        User user = new User(Long.parseLong("3"), "Thomas", "Shelby", (byte) 21);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", set_cookie);

        HttpEntity<String> response = restTemplate.exchange(DELETE_USER_URL, HttpMethod.DELETE,
                new HttpEntity<>(user, httpHeaders), String.class);

        // headers = response.getHeaders();
        //set_cookie = headers.getFirst(headers.SET_COOKIE);

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");
    }

    public static void delete_v2(){
        String url1 = "http://91.241.64.178:7081/api/users/3";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie:\"JSESSIONID=96D78DB28C7F3B2ACEFCCABFA156BA05; " +
                "Path=/; HttpOnly\", X-Content-Type-Options:\"nosniff\", " +
                "X-XSS-Protection:\"1; mode=block\", " +
                "Cache-Control:\"no-cache, no-store, max-age=0, must-revalidate\", " +
                "Pragma:\"no-cache\", Expires:\"0\", X-Frame-Options:\"DENY\", " +
                "Content-Type:\"text/plain;charset=UTF-8\", Content-Length:\"6\", " +
                "Date:\"Sun, 05 Jul 2020 02:49:09 GMT\", " +
                "Keep-Alive:\"timeout=60\", Connection:\"keep-alive\"", "true");

        //set_cookie = headers.getFirst(headers.SET_COOKIE);

        restTemplate.delete(url1, User.class);
    }
}
