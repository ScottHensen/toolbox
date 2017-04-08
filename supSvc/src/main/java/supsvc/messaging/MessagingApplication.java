package supsvc.messaging;

/*
 * NOTE:  The boilerplate from https://github.com/mechero/spring-boot-amqp-messaging/blob/master/src/main/java/es/macero/MessagingApplication.java
 *        Thank you for the fine tutorial Moises!
 */

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class MessagingApplication implements RabbitListenerConfigurer
{
	public static final String DIRECT_EXCHANGE    = "person.update.exchange";
	public static final String DIRECT_QUEUE       = "person.update.queue";
	public static final String DIRECT_ROUTING_KEY = "person.update.key";
	
	public static final String TOPIC_EXCHANGE     = "person.exchange";
	public static final String TOPIC_QUEUE_BASE   = "person.gen-";
	public static final String TOPIC_ROUTING_KEY  = "person.key";
	public static final String TOPIC_BINDING_KEY  = "person.key";
	
	@Bean
	public DirectExchange directExchange()
	{
		return new DirectExchange(DIRECT_EXCHANGE);
	}
	
	@Bean
	public Queue directQueue()
	{
		return new Queue(DIRECT_QUEUE);
	}
	
	@Bean
	public Binding directBinding()
	{
		return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTING_KEY);
	}

	@Bean
	public TopicExchange topicExchange()
	{
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	@Bean
	public Queue topicQueue()
	{
		return new AnonymousQueue(new AnonymousQueue.Base64UrlNamingStrategy(TOPIC_QUEUE_BASE));
	}
	
	@Bean
	public Binding topicBinding()
	{
		return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPIC_BINDING_KEY);
	}


	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() 
	{
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() 
	{
		return new MappingJackson2MessageConverter();
	}
	
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory()
	{
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
	
	@Bean
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory)
	{
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(1);
		factory.setMaxConcurrentConsumers(1);
		return factory;
	}
	
	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
	
}
