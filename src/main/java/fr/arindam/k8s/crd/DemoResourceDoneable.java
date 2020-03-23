package fr.arindam.k8s.crd;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

public class DemoResourceDoneable extends CustomResourceDoneable<DemoResource> {

	public DemoResourceDoneable(DemoResource resource, Function<DemoResource, DemoResource> function) {
		super(resource, function);
	}

}
