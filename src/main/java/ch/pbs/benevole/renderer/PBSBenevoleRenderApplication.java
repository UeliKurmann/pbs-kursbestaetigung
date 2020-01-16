package ch.pbs.benevole.renderer;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.TemplateEngine;
import ch.pbs.benevole.renderer.health.TemplateHealthCheck;
import ch.pbs.benevole.renderer.pdf.PdfDocumentImpl;
import ch.pbs.benevole.renderer.resources.BenevoleRendererResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PBSBenevoleRenderApplication extends Application<PBSBenevoleRenderConfiguration> {

	public static void main(final String[] args) throws Exception {
		new PBSBenevoleRenderApplication().run(args);
	}

	@Override
	public String getName() {
		return "PBS-Bénévole-Renderer";
	}

	@Override
	public void initialize(final Bootstrap<PBSBenevoleRenderConfiguration> bootstrap) {
		Factory.get().configPdfDocument(()-> PdfDocumentImpl.create());
		Factory.get().configTemplateEngine(()-> new TemplateEngine());
	}

	@Override
	public void run(final PBSBenevoleRenderConfiguration configuration, final Environment environment) {
		final BenevoleRendererResource resource = new BenevoleRendererResource();
		environment.jersey().register(resource);
		

		final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
		environment.healthChecks().register("renderer", healthCheck);
		environment.jersey().register(resource);	

	}

}
