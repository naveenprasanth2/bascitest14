package org.example.tesco.threading;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalExample {
    ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-dd-MM"));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
          Future<?> test = executorService.submit(() -> System.out.println("Summa"), "1");
            System.out.println(test.get());
        }

    }

    private String birthDate(int userId) {
        Date birthDate = Calendar.getInstance().getTime();
        return dateFormatter.get().format(birthDate);
    }
}
