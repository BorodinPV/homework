package task01.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import task01.model.NumberRequest;
import task01.service.FactorialService;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Pavel Borodin on 2019-05-06
 */
@RestController
public class LaunchController {

    private final FactorialService factorialService;

    public LaunchController(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @PostMapping(value = "/launch/factorial")
    public List<BigInteger> launchFactorial(@RequestBody NumberRequest numberRequest) {
        return factorialService.launchFactorial(numberRequest.getNumbers());
    }
}