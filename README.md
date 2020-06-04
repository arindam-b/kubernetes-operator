### Generate Project

mvn io.quarkus:quarkus-maven-plugin:0.19.1:create -DprojectGroupId=fr.arindam.k8s -DprojectArtifactId=k8s-ops -DclassName="fr.arindam.k8s.MainOperatorResource" -Dpath="/check"


./mvnw quarkus:add-extension -Dextensions=quarkus-kubernetes-client


mvn compile quarkus:dev


http://localhost:8080/check


### To dockerize:

mvn package -DskipTests

docker build -f src/main/docker/Dockerfile.jvm -t  arindambanerjee/first-k8s-operator:latest .

docker push arindambanerjee/first-k8s-operator:latest

# Apply CRDS

kubectl apply -f .\customresourcedefinition.yml

kubectl apply -f .\customresource.yml



### To run:

1. Run deployment

kubectl create deployment abcd --image=arindambanerjee/first-k8s-operator

2. View pods

kubectl get pods

NAME                    READY   STATUS    RESTARTS   AGE
abcd-85c66b786d-lnfxx   1/1     Running   0          8s

3. View logs

kubectl logs abcd-85c66b786d-lnfxx

exec java -Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager -javaagent:/opt/agent-bond/agent-bond.jar=jmx_exporter{{9779:/opt/agent-bond/jmx_exporter_config.yml}} -XX:+UseParallelGC -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=40 -XX:+ExitOnOutOfMemoryError -cp . -jar /deployments/app.jar
2020-03-23 11:51:48,189 WARN  [io.fab.kub.cli.int.VersionUsageUtils] (main) The client is using resource type 'demos' with unstable version 'v1alpha1'
Found resource Resource Details: demo-play, version: 88247403 spec: Resource Spec: A,B,C
2020-03-23 11:51:48,321 INFO  [io.quarkus] (main) Quarkus 0.19.1 started in 4.597s. Listening on: http://[::]:8080
2020-03-23 11:51:48,323 INFO  [io.quarkus] (main) Installed features: [cdi, kubernetes-client, resteasy]
Received ADDED event Resource Details: demo-play, version: 88247403 spec: Resource Spec: A,B,C

The last line shows A, B, C spec for the pod

4. kubectl apply -f customresource_with_updated_value.yml

5. kubectl logs abcd-85c66b786d-lnfxx

![logs](https://github.com/arindam-b/kubernetes-operator/blob/master/demo/demo.JPG)

The last line shows appenede A,B,C,D,E all updated values
