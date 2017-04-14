package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService()
	{
		messages.put(1L, new Message(1, "The first message", "Roosevelt"));
		messages.put(2L, new Message(2, "The second message", "Truman"));
	}
	/**
	 * Returns all messages from the DB class
	 * @return
	 */
	public List<Message> getAllMessages() 
	{
		return new ArrayList<Message>(messages.values());
	}
	
	/**
	 * Returns a specific message, if it exists, based on id
	 * @param id
	 * @return
	 */
	public Message getMessage(long id)
	{
		if (messages.containsKey(id))
		{
			return messages.get(id);
		} 
		else
		{
			return null;
		}
	}
	
	/**
	 * Adds a new message to the DB HashMap
	 * @param message
	 * @return
	 */
	public Message addMessage(Message message)
	{
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	/**
	 * Updated an existing Message if found
	 * @param message
	 * @return
	 */
	public Message updateMessage(Message message)
	{
		if (messages.containsKey(message.getId()))
		{
			messages.put(message.getId(), message);
			return messages.get(message.getId());
		}
		else
		{
			return null;
		}	
	}
	
	/**
	 * Removes a message based on id.
	 * @param id
	 * @return
	 */
	public Message removeMessage(long id)
	{
		if (messages.containsKey(id))
		{
			return messages.remove(id);
		}
		else
		{
			return null;
		}	
	}

}
