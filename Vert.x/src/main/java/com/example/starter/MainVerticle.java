package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;
/**
 * @author wangyuliang
 * @description //vert.x demo
 * @date 15:28 2020/12/5
 * @param
 * @return
 */
public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    long current = System.currentTimeMillis();
    Launcher.main(new String[] { "run", MainVerticle.class.getName()});
    long end = System.currentTimeMillis();
    System.out.println(end - current);
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/json")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

}
