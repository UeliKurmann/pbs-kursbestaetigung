package ch.pbs.benevole.renderer;

import ch.pbs.benevole.renderer.core.Factory;
import ch.pbs.benevole.renderer.core.TemplateEngine;
import ch.pbs.benevole.renderer.health.TemplateHealthCheck;
import ch.pbs.benevole.renderer.pdf.PdfDocumentImpl;
import ch.pbs.benevole.renderer.resources.BackwardCompatibilityResource;
import ch.pbs.benevole.renderer.resources.BenevoleDemoResource;
import ch.pbs.benevole.renderer.resources.BenevoleHealthCheckResource;
import ch.pbs.benevole.renderer.resources.BenevoleRendererResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.dhatim.dropwizard.prometheus.PrometheusBundle;


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
		Factory.get().configPdfDocument(PdfDocumentImpl::create);
		Factory.get().configTemplateEngine(TemplateEngine::new);
		bootstrap.addBundle(new PrometheusBundle());
	}

	@Override
	public void run(final PBSBenevoleRenderConfiguration configuration, final Environment environment) {
		final BenevoleRendererResource resource = new BenevoleRendererResource();
		environment.jersey().register(resource);
		environment.jersey().register(BenevoleDemoResource.class);
		environment.jersey().register(BenevoleHealthCheckResource.class);
		environment.jersey().register(BackwardCompatibilityResource.class);
		
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck();
		environment.healthChecks().register("renderer", healthCheck);
		environment.jersey().register(resource);	
	}

}
