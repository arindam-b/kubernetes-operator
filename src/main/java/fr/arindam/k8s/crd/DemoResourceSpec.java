package fr.arindam.k8s.crd;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonDeserialize
@RegisterForReflection
public class DemoResourceSpec {

	@JsonProperty("demo.properties")
	private Set<String> demoProperties;

	public Set<String> getDemoProperties() {
		return demoProperties;
	}

	public void setDemoProperties(Set<String> demoProperties) {
		this.demoProperties = demoProperties;
}

	@Override
	public String toString() {
		return "Resource Spec: " + String.join(",", demoProperties.toArray(new String[] {}));
	}
	
}
