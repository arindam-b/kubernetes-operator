package fr.arindam.k8s.config;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

import fr.arindam.k8s.crd.DemoResource;
import fr.arindam.k8s.crd.DemoResourceDoneable;
import fr.arindam.k8s.crd.DemoResourceList;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;

public class ClientProducer {
	
	@Produces
	@Singleton
	public MixedOperation<DemoResource,DemoResourceList,DemoResourceDoneable,Resource<DemoResource,DemoResourceDoneable>> makeCustomResourceClient(KubernetesClient defaultClient) {
		
		KubernetesDeserializer.registerCustomKind("arindam.com/v1alpha1", "Demo", DemoResource.class);
		
		CustomResourceDefinition crd = defaultClient.customResourceDefinitions()
													.list()
													.getItems()
													.stream()
													.filter( c -> "demos.arindam.com".equals(c.getMetadata().getName()))
													.findAny()
													.orElseThrow(RuntimeException::new);
		
		
		return defaultClient.customResources(crd, DemoResource.class, DemoResourceList.class, DemoResourceDoneable.class);
		
	}
	
	
	@SuppressWarnings("resource")
	@Produces
	@Singleton
	public KubernetesClient makeDefaultClient(@Named("namespace") String namespace) {
		return new DefaultKubernetesClient().inNamespace(namespace);
	}
	
	@Produces
	@Singleton
	@Named("namespace")
	public String findMyCurrentNamespace() throws IOException {
		return new String(Files.readAllBytes(Paths.get("/var/run/secrets/kubernetes.io/serviceaccount/namespace")));
	}

}
