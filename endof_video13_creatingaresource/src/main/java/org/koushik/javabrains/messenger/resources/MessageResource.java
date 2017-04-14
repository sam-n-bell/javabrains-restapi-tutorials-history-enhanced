package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * when you make a HTTP GET request to localhost:8080/messenger/webapi/messages (port # may differ), 
 * this method will get detected and return the String "Hello World".
 * The web.xml file (src/main/webapp/WEB-INF/web.xml) should not need to be modified for this to work
 * because this class is a sub-package under the root package and is called after /webapi/ in the URL.
 */

@Path("messages")
public class MessageResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN) //return format of the GET request
	public String getMessage()
	{
		return "Hello World";
	}
}
