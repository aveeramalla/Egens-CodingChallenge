package com.abhinav.codingchallenge.egens.codingchallenge;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;

public class UserResource {

    private static final String API_CONTEXT = "/api/v1";

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
        this.setUpEndpoints();
    }

    private void setUpEndpoints() {

        post(API_CONTEXT + "/users", "application/json", (request, response) -> {
            request.body();
            User user = new Gson().fromJson(request.body(), User.class);
            userService.createNewUser(request.body());
            response.status(201);
            return user;
        }, new JsonTransformer());

        get(API_CONTEXT + "/users/:id", "application/json", (request, response) -> {
            User user = userService.find(request.params(":id"));
            if (user == null) {
                response.status(404);
                return "User not found";
            }
            return user;
        }, new JsonTransformer());

        get(API_CONTEXT + "/users", "application/json", (request, response) -> {
            return userService.findAll();
        }, new JsonTransformer());

        put(API_CONTEXT + "/users", "application/json", (request, response) -> {
            User user = new Gson().fromJson(request.body(), User.class);
            if (userService.find(user.getId()) == null) {
                response.status(404);
                return "User not found";
            }
            return userService.update(request.body());
        }, new JsonTransformer());
    }
}
