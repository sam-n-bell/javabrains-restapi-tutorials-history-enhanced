package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.koushik.javabrains.messenger.model.Message;

public class MessageService {
	
	
	/**
	 * Mocked messages instead of connecting to a actual database
	 * @return
	 */
	public List<Message> getAllMessages() 
	{
		Message messageOne = new Message(1L, "Hello World!", "Koushik");
		Message messageTwo = new Message(2L, "Hello Jersey!", "Koushik");
		List<Message> listOfMessages = new ArrayList<>();
		listOfMessages.add(messageOne);
		listOfMessages.add(messageTwo);
		return listOfMessages;
	}

}
