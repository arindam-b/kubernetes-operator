package fr.arindam.k8s.crd;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.client.CustomResourceList;
import io.quarkus.runtime.annotations.RegisterForReflection;

@SuppressWarnings("serial")
@JsonDeserialize
@RegisterForReflection
public class DemoResourceList extends CustomResourceList<DemoResource>{

}
