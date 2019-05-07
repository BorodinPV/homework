package task01.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by Pavel Borodin on 2019-05-06
 */
@Service
public class FactorialService {

    private static final Logger log = Logger.getLogger(FactorialService.class);

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public List<BigInteger> launchFactorial(List<Double> numbers) {
        List<Callable<BigInteger>> call = new ArrayList<>();
        for (Double number : numbers) {
            call.add(() -> getFactorial(number));
        }

        List<Future<BigInteger>> futures = new ArrayList<>();
        try {
            futures = threadPool.invokeAll(call);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return futures.stream().map(longFuture -> {
            try {
                return longFuture.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.toList());
    }

    private BigInteger getFactorial(Double number) {
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 1; i <= number.longValue(); i++) {
           result = result.multiply((BigInteger.valueOf(i)));
        }
        log.info("Factorial = " + result);
        return result;
    }
}