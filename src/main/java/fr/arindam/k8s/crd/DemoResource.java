package fr.arindam.k8s.crd;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.client.CustomResource;
import io.quarkus.runtime.annotations.RegisterForReflection;

@SuppressWarnings("serial")
@JsonDeserialize
@RegisterForReflection
public class DemoResource extends CustomResource{

	private DemoResourceSpec spec;

	public DemoResourceSpec getSpec() {
		return spec;
	}

	public void setSpec(DemoResourceSpec spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "Resource Details: " + 
					this.getMetadata().getName() + 
					", version: " +
					this.getMetadata().getResourceVersion() + 
					" spec: " +
					this.spec;
	}
	
}
