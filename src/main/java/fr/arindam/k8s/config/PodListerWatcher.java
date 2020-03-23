package fr.arindam.k8s.config;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import fr.arindam.k8s.crd.DemoResource;
import fr.arindam.k8s.crd.DemoResourceDoneable;
import fr.arindam.k8s.crd.DemoResourceList;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.quarkus.runtime.StartupEvent;

public class PodListerWatcher {

	@Inject
	private MixedOperation<DemoResource,DemoResourceList,DemoResourceDoneable,Resource<DemoResource,DemoResourceDoneable>> crClient;	

	public void onStartUpListPods(@Observes StartupEvent ev) {

		List<DemoResource> list = crClient.list().getItems();

		for(DemoResource demoResource: list) 
			System.out.println("Found resource "+demoResource);


		crClient.watch(new Watcher<DemoResource>() {

			@Override
			public void eventReceived(Action action, DemoResource demoResource) {
				System.out.println("Received "+ action + " event "+demoResource);
			}

			@Override
			public void onClose(KubernetesClientException exception) {
				System.out.println("DemoResource closing, reason is : ");
				if(exception != null) {
					exception.printStackTrace();
					System.exit(-1);
				}
			}
		});
	}

}
