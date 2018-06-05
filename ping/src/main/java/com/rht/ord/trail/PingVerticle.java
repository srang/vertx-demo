package com.rht.ord.trail;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class PingVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/ping").handler(routingContext -> {

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            // Write to the response and end it
            response.end(new JsonObject().put("msg", "ping").encodePrettily());
        });

        router.route("/pong").handler(routingContext -> {
            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            // Write to the response and end it
            response.end(new JsonObject().put("msg", "pong").encodePrettily());
        });

        router.route("/greet/:name").handler(routingContext -> {
            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");

            // Write to the response and end it
            response.end(new JsonObject().put("msg", routingContext.request().getParam("name")).encodePrettily());

        });

        server.requestHandler(router::accept).listen(8080);
    }
}
