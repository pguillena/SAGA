package reclutamiento.app.api_proceso.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;


    public static final String EXCHANGE = "saga.exchange";


    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public TopicExchange sagaExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue procesoCreatedQueue() {
        return new Queue("proceso.created.queue");
    }

    @Bean
    public Queue procesoDeletedQueue() {
        return new Queue("proceso.deleted.queue");
    }

    @Bean
    public Queue procesoCreationFailedQueue() {
        return new Queue("proceso.creation.failed.queue");
    }

    @Bean
    public Queue procesoDeletionFailedQueue() {
        return new Queue("proceso.deletion.failed.queue");
    }

    // TODO

    @Bean
    public Queue comiteCreatedQueue() {
        return new Queue("proceso.comite.created.queue");
    }

    @Bean
    public Queue comiteNotificatedQueue() {
        return new Queue("proceso.comite.notificated.queue");
    }

    @Bean
    public Queue comiteCreationFailedQueue() {
        return new Queue("proceso.comite.creation.failed.queue");
    }


    @Bean
    public Queue comiteNotificationFailedQueue() {
        return new Queue("proceso.comite.notification.failed.queue");
    }

    @Bean
    public Binding bindProcesoCreated() {
        return BindingBuilder
                .bind(procesoCreatedQueue())
                .to(sagaExchange())
                .with("proceso.created");
    }

    @Bean
    public Binding bindComiteCreated() {
        return BindingBuilder
                .bind(comiteCreatedQueue())
                .to(sagaExchange())
                .with("proceso.comite.created");
    }

    @Bean
    public Binding bindComiteNotificated() {
        return BindingBuilder
                .bind(comiteNotificatedQueue())
                .to(sagaExchange())
                .with("proceso.comite.notificated");
    }


    @Bean
    public Binding bindComiteCreationFailed() {
        return BindingBuilder
                .bind(comiteCreationFailedQueue())
                .to(sagaExchange())
                .with("proceso.comite.creation.failed");
    }

    @Bean
    public Binding bindComiteNotificationFailed() {
        return BindingBuilder
                .bind(comiteNotificationFailedQueue())
                .to(sagaExchange())
                .with("proceso.comite.notification.failed");
    }

    @Bean
    public Binding bindProcesoDeleted() {
        return BindingBuilder
                .bind(procesoDeletedQueue())
                .to(sagaExchange())
                .with("proceso.deleted");
    }

    @Bean
    public Binding bindProcesoCreationFailed() {
        return BindingBuilder
                .bind(procesoCreationFailedQueue())
                .to(sagaExchange())
                .with("proceso.creation.failed");
    }

    @Bean
    public Binding bindProcesoDeletionFailed() {
        return BindingBuilder
                .bind(procesoDeletionFailedQueue())
                .to(sagaExchange())
                .with("proceso.deletion.failed");
    }

    @Bean
    public Queue createProcesoComiteQueue() {
        return new Queue("proceso.comite.create.queue");
    }

    @Bean
    public Queue notificationProcesoComiteQueue() {
        return new Queue("proceso.comite.notification.queue");
    }



    @Bean
    public Binding bindCreateProcesoComiteQueue() {
        return BindingBuilder
                .bind(createProcesoComiteQueue())
                .to(sagaExchange())
                .with("proceso.comite.create");
    }


    @Bean
    public Binding bindNotificationProcesoComiteQueue() {
        return BindingBuilder
                .bind(notificationProcesoComiteQueue())
                .to(sagaExchange())
                .with("proceso.comite.notification");
    }
}