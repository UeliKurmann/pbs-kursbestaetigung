package ch.pbs.benevole.renderer.resources;

import static ch.pbs.benevole.renderer.resources.Renderer.renderPdf;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

@Path("/benevole/renderer")
@Produces(MediaType.APPLICATION_JSON)
public class BenevoleRendererResource {

	public BenevoleRendererResource() {
		// default constructor.
	}

	@POST
	@Path("/pdf/{kurs}/{lang}")
	@Produces("application/pdf")
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	public Response getPdfFromJson(@PathParam("kurs") String kurs, @PathParam("lang") String lang,
			KursParameterJson kpj) throws Exception {
		return Response.ok()//
				.header(HttpHeaders.CONTENT_DISPOSITION, createContentDisposition(kurs)) //
				.entity(renderPdf(kurs, lang, kpj)).build();
	}

	@POST
	@Path("/pdf/{kurs}/{lang}")
	@Produces("application/pdf")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Timed
	public Response getPdfFromUrlencoded(@PathParam("kurs") String kurs, @PathParam("lang") String lang,
			@BeanParam KursParameterJson kpj) throws Exception {

		return Response.ok()//
				.header(HttpHeaders.CONTENT_DISPOSITION, createContentDisposition(kurs)) //
				.entity(renderPdf(kurs, lang, kpj)).build();
	}

	private String createContentDisposition(String kurs) {
		return String.format("attachment; filename=\"%s.pdf\"", kurs);
	}
}