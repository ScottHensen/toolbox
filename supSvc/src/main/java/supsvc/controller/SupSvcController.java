package supsvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import supsvc.messaging.Message;
import supsvc.messaging.MessageSender;
import supsvc.model.persistence.Person;
import supsvc.model.persistence.PersonRepository;

@RestController
@RequestMapping(value="/")
public class SupSvcController {

	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private MessageSender messageSender;

	//NOTE: Spring REST/JPA-REPO handles posts without any need for intervention or a controller
	//      I am adding one here anyway for PUT requests, so that after the local DB gets updated, 
	//      we can send a msg to update the global DB too.
	@RequestMapping(value="/people/{id}", method = RequestMethod.PUT)
	public void update( @PathVariable( "id" ) Long id,
			            @RequestBody Person person )
	{
		person.setId(id);
		personRepo.save(person);
		
		Message statusMessage = new Message(person);
		messageSender.sendMessage(statusMessage);
	}
	

}
