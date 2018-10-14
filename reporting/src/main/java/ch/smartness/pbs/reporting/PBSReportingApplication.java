package ch.smartness.pbs.reporting;

import ch.smartness.pbs.reporting.core.Factory;
import ch.smartness.pbs.reporting.core.TemplateEngine;
import ch.smartness.pbs.reporting.health.TemplateHealthCheck;
import ch.smartness.pbs.reporting.pdf.PdfDocumentImpl;
import ch.smartness.pbs.reporting.resources.KursRendererResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PBSReportingApplication extends Application<PBSReportingConfiguration> {

	public static void main(final String[] args) throws Exception {
		new PBSReportingApplication().run(args);
	}

	@Override
	public String getName() {
		return "PBSReporting";
	}

	@Override
	public void initialize(final Bootstrap<PBSReportingConfiguration> bootstrap) {
		Factory.get().configPdfDocument(()-> PdfDocumentImpl.create());
		Factory.get().configTemplateEngine(()-> new TemplateEngine());
	}

	@Override
	public void run(final PBSReportingConfiguration configuration, final Environment environment) {
		final KursRendererResource resource = new KursRendererResource();
		environment.jersey().register(resource);
		

		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);	

	}

}
