package ch.pbs.benevole.renderer.resources;

import static ch.pbs.benevole.renderer.resources.Renderer.renderPdf;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		return TestData.createDemoParameterDe();
	}

	@GET
	@Path("/index")
	@Produces(MediaType.TEXT_HTML)
	@Timed
	public String demo() throws IOException {
		return Resources.toString(BenevoleRendererResource.class.getClassLoader().getResource("assets/demo.html"), Charsets.UTF_8);
	}

	@GET
	@Path("/pdf/{kurs}/{lang}")
	@Produces("application/pdf")
	@Timed
	public Response getPdfDemo(@PathParam("kurs") String kurs, @PathParam("lang") String lang) throws Exception {
		KursParameterJson parameter = null;
		if ("de".equals(lang)) {
			parameter = TestData.createDemoParameterDe();
		} else if ("fr".equals(lang)) {
			parameter = TestData.createDemoParameterFr();
		} else if("it".equals(lang)) {
			parameter = TestData.createDemoParameterIt();
		}
		return Response.ok()//
				.entity(renderPdf(kurs, lang, parameter)).build();
	}

}
