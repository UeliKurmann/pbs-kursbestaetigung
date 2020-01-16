package ch.pbs.benevole.renderer.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@Path("/benevole/demo")
public class BenevoleDemoResource {
	
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	public KursParameterJson getDemoParameter() {
		return TestData.createDemoParameter();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	@Timed
	public String demo() throws IOException {
		return Resources.toString(BenevoleRendererResource.class.getClassLoader().getResource("assets/demo.html"),
				Charsets.UTF_8);
	}

}
