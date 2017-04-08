package supsvc.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supsvc.model.persistence.PersonRepository;

@Service
public class MessageListener {
	
	final static Logger logger = LoggerFactory.getLogger(MessageListener.class);

	@Autowired
	private PersonRepository personRepo;

	@RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = "#{@topicQueue}")
	public void receiveMessage(final Message message)
	{
		logger.info( "Msg Rcvd: " + message.toString() );
		personRepo.save(message.getPerson());
	}
}
