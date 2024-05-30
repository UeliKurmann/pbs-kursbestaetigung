package ch.pbs.benevole.renderer.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

	public TemplateHealthCheck() {
	}

	@Override
	protected Result check() {
		return Result.healthy();
	}
}