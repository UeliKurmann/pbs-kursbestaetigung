package ch.pbs.benevole.renderer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

@Path("/benevole/check")
public class BenevoleHealthCheckResource {
	
	@GET
	@Path("/ping")
	@Produces(MediaType.TEXT_PLAIN)
	@Timed
	public String ping() {
		return "pong";
	}

}
