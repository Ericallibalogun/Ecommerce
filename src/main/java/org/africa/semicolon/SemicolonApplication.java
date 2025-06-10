package org.africa.semicolon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SemicolonApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SemicolonApplication.class, args);

        var orderService = context.getBean("orderService", OrderService.class);
        orderService.setPaymentService(new PaypalPaymentService());
        orderService.placeOrder();

    }

}
