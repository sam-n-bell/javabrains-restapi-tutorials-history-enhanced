package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON) //lets every method do this without having to repeat this line
@Consumes(MediaType.APPLICATION_JSON) //lets every method do this without having to repeat this line
public class MessageResource {
	
	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessage()
	{
		return messageService.getAllMessages();
	}
	
	/**
	 * Returns a specific message based off a param.
	 * If MessageService finds no message with the passed Id, null gets passed back. 204 status would appear.
	 * Could cause a 404 by passing any non-numerical value in as the messageId.
	 * @param messageId
	 * @return
	 */
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId)
	{
		return messageService.getMessage(messageId);	
	}
	
	/**
	 * Adds a new message to the messages Map. 
	 * When making the request, be sure to set content-type header with value of application/json
	 * Would ideally want to check to make sure at least a author and message is being passed with the Message to the service
	 * I added an extra 'if' to the MessageService to set the date automatically since I strongly don't agree with the way he did it in the video.
	 * Remember, Id will be created by the MessageService class so you don't need to pass it in the JSON POST
	 * @param message
	 * @return
	 */
	@POST
	public Message addMessage(Message message)
	{
		return messageService.addMessage(message);
	}
	
	/**
	 * You will see a bug with this if you try to update messages 1 or 2:
	 * From YouTube:
	 * "There is a little bug in the "mock database" implementation that will prevent message IDs 1 and 2 from ever being effectively updated or deleted: Apparently, Jersey creates a new instance of the MessageResource for every request. So the MessageService reference is instantiated time and time again, overwriting changes to messages 1 and 2 in its constructor.
	 * This can be solved by moving the messages.put(...) code inside a 'static' block inside the DatabaseClass"
	 * I updated the updateMessage method in MessageService to grab the message's original creation date so the user does not have to include it in the JSON payload
	 * @param messageId
	 * @param message
	 * @return
	 */
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message)
	{
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	/**
	 * This removes a message from the Map based on the Id provided in the URL.
	 * I changed the return type to demonstrate the message being removed without making the user do a GET.
	 * @param messageId
	 * @return 
	 */
	@DELETE
	@Path("/{messageId}")
	public List<Message> deleteMessage(@PathParam("messageId") long messageId)
	{
		return messageService.removeMessage(messageId);
	}
}


