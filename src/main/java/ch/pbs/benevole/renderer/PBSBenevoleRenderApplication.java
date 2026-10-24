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
import io.prometheus.metrics.exporter.servlet.jakarta.PrometheusMetricsServlet;
import io.prometheus.metrics.instrumentation.dropwizard.DropwizardExports;
import io.prometheus.metrics.model.registry.PrometheusRegistry;


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

		final PrometheusRegistry prometheusRegistry = new PrometheusRegistry();
		DropwizardExports.builder().dropwizardRegistry(environment.metrics()).register(prometheusRegistry);
		environment.admin().addServlet("prometheus-metrics", new PrometheusMetricsServlet(prometheusRegistry))
				.addMapping("/prometheus-metrics");
		environment.jersey().register(resource);	
	}

}
