package ch.pbs.benevole.renderer.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Objects;

import static ch.pbs.benevole.renderer.resources.Renderer.renderPdf;

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
		return Resources.toString(Objects.requireNonNull(BenevoleRendererResource.class.getClassLoader().getResource("assets/demo.html")), Charsets.UTF_8);
	}

	@GET
	@Path("/pdf/{kurs}/{lang}")
	@Produces("application/pdf")
	@Timed
	public Response getPdfDemo(@PathParam("kurs") String kurs, @PathParam("lang") String lang) throws Exception {
		KursParameterJson parameter;
		if ("de".equals(lang)) {
			parameter = TestData.createDemoParameterDe();
		} else if ("fr".equals(lang)) {
			parameter = TestData.createDemoParameterFr();
		} else if("it".equals(lang)) {
			parameter = TestData.createDemoParameterIt();
		} else {
			throw new IllegalStateException("language not defined.");
		}

		return Response.ok()//
				.entity(renderPdf(kurs, lang, parameter)).build();
	}

}
