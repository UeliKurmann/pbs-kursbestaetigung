package ch.pbs.benevole.renderer;

import com.expediagroup.dropwizard.prometheus.PrometheusBundleConfig;

import io.dropwizard.Configuration;

public class PBSBenevoleRenderConfiguration extends Configuration {
	
	PrometheusBundleConfig prometheusBundleConfig;
	
	public PrometheusBundleConfig getPrometheusBundleConfig() {
        return this.prometheusBundleConfig;
     }
	
	

	


}
