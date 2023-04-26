package com.webflux.reactor.reactive;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
public class Part02Mono {


    public static void main(String[] args) throws InterruptedException {
        Mono.firstWithValue(
                        Mono.just(1).delayElement(Duration.ofMillis(90)).map(integer -> "foo" + integer),
                        Mono.delay(Duration.ofMillis(102)).thenReturn("bar")
                )
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }
//========================================================================================

    // TODO Return an empty Mono
    Mono<String> emptyMono() {
        return Mono.empty();
    }

//========================================================================================

    // TODO Return a Mono that never emits any signal
    Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

//========================================================================================

    // TODO Return a Mono that contains a "foo" value
    Mono<String> fooMono() {
        return Mono.<String>just("foo");
    }

//========================================================================================

    // TODO Create a Mono that emits an IllegalStateException
    Mono<String> errorMono() {
        return Mono.error(new IllegalStateException());
    }

}

