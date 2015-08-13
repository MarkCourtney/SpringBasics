package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.FileSystemUtils;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;

@SpringBootApplication
public class Application {

    @Bean
        // Strictly speaking this bean is not necessary as boot creates a default
        // This creates a JmsListener that can then be used in annotations elsewhere for calling(Check Receiver.java)
    JmsListenerContainerFactory<?> webPageListener(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    public static void main(String[] args) {

        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        String message = "Variable in Main class";
        // Send a message
        // Create a message with the internal createMessage method
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ping! and " + message);
            }
        };
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        System.out.println("Sending a new message: " + message);
        jmsTemplate.send("getter-page", messageCreator);
    }
}