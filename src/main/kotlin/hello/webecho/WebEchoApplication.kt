package hello.webecho

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.kotlin.core.publisher.toMono

@SpringBootApplication
class WebEchoApplication

@RestController
@RequestMapping("/echo")
class EchoController {

    private val logger = LoggerFactory.getLogger(EchoController::class.java);

    @GetMapping
    fun answer(@RequestParam say: String) =
        "Hello $say".toMono()
            .doOnNext {
                logger.info(it)
                logger.debug(it)
            }

    @GetMapping("/warning")
    fun answerWarning(@RequestParam say: String) =
        "Warning $say".toMono()
            .doOnNext {
                logger.warn(it)
            }

    @GetMapping("/error")
    fun answerError(@RequestParam say: String) =
        "Error $say".toMono()
            .doOnNext {
                logger.error(it)
            }

}

fun main(args: Array<String>) {
    runApplication<WebEchoApplication>(*args)
}

