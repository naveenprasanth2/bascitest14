package org.example.tesco.threading;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {
    ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-dd-MM"));

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

    }

    private String birthDate(int userId) {
        Date birthDate = Calendar.getInstance().getTime();
        return dateFormatter.get().format(birthDate);
    }
}
