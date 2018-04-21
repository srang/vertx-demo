package com.rht.ord.trail;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class PongVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.setPeriodic(1000, id -> {
            WebClient client = WebClient.create(vertx);
            client
                    .get(8080, "ping-service", "/ping")
                    .send(ar -> {
                        if (ar.succeeded()) {
                            // Obtain response
                            HttpResponse<Buffer> response = ar.result();

                            System.out.println(response.bodyAsString());
                        } else {
                            System.out.println("Something went wrong " + ar.cause().getMessage());
                        }
                    });
        });
    }
}
