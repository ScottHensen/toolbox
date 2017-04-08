package supsvc.messaging;

import java.io.Serializable;
import java.time.OffsetDateTime;

import supsvc.model.persistence.Person;


public final class Message implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Person person;
//	private OffsetDateTime offsetDateTime;

	
	
	public Message() 
	{
		
	}
	
	public Message(Person person)
	{
		this.person = person;
//		this.offsetDateTime = OffsetDateTime.now();
	}
	
	public Message(Person person, OffsetDateTime offsetDateTime)
	{
		this.person = person;
//		this.offsetDateTime = offsetDateTime;
	}
	

	
	public Person getPerson() 
	{
		return person;
	}

//	public OffsetDateTime getOffsetDateTime() 
//	{
//		return offsetDateTime;
//	}

	@Override
	public String toString() 
	{
		return "StatusMessage {"
		     + " person="          + person.toString()
//		     + " ,offsetDateTime=" + offsetDateTime
		     + " }";
	}
	
}
