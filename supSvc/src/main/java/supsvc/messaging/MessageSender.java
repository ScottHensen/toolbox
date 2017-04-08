package supsvc.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender 
{
	private final RabbitTemplate rabbitTemplate;

	final static Logger logger = LoggerFactory.getLogger(MessageSender.class);

	@Autowired
	public MessageSender(final RabbitTemplate rabbitTemplate)
	{
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage( Message message )
	{
		logger.info( "Msg Sent: " + message.toString() );
		rabbitTemplate.convertAndSend(MessagingApplication.DIRECT_EXCHANGE, 
									  MessagingApplication.DIRECT_ROUTING_KEY, 
									  message);		
	}
}
