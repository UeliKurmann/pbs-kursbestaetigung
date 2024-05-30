package ch.pbs.benevole.renderer.resources;

import com.codahale.metrics.annotation.Timed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


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
