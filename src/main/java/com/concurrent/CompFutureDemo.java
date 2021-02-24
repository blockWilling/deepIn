package com.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * @author kangjin
 * @since 2021/1/18 13:59
 */
public class CompFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 22;
        }), (q, w) -> {
            return q + w;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 33;
        }), (q, w) -> {
            return q + w;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 44;
        }), (q, w) -> {
            return q + w;
        });
        integerCompletableFuture.join();
    }
}
