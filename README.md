### Intro

This repo demostrates use of events-driven architecture with `GCP Pub/Sub` - as the event broker, `MongoDb`, `Spring Boot`, `Graalvm`, and `GKE`. 

This project subscript to one topic and expect to receive `OrderCreated`, `OrderCancelled` and `OrderItemQuantityUpdated` events. It then processes them to determine if any campaign is applicable and calculate the remaining amount for joining the campaign.  

One of the benefits of using this approach is that the campaign calculation is decoupled from user queries, which means it can be done when there is update on order - order created, cancelled or item quantity updated. The amounts for available campaigns are ready and always up-to-date when user query them. 

### Build and Deploy locally

GraalVM Native Image is great for startup time and memory usage. However the image building time can take over 10 mins, which is not ideal during development.

To avoid continously building / deploying container while developing, use `skaffold dev` instead. This will use `jib` for container image building, which is much quicker and convinient. 

```
skaffold -f skaffold-dev.yaml dev
```

This will sync files from local workstation to K8s automatically, so you do not need to rebuild / redeploy everytime you want to run it. 

To build and deploy Spring Boot Graalvm Native image, use the `native` profile defined on `pom.xml`. 

```
./mvnw clean package -Pnative
```

You will then see an executable binary file `promo-evaluator` on the `target` folder. 

```
user@workstation-basic:~/promo-evaluator/target$ ll
total 237240
drwxr-xr-x 12 user user      4096 Jan  5 02:51 .
drwxr-xr-x  8 user user      4096 Jan  5 02:44 ..
drwxr-xr-x  5 user user      4096 Jan  5 02:44 classes
drwxr-xr-x  3 user user      4096 Jan  5 02:44 generated-sources
drwxr-xr-x  3 user user      4096 Jan  5 02:44 generated-test-sources
drwxr-xr-x  3 user user      4096 Jan  5 02:44 graalvm-reachability-metadata
drwxr-xr-x  2 user user      4096 Jan  5 02:44 maven-archiver
drwxr-xr-x  3 user user      4096 Jan  5 02:44 maven-status
-rwxr-xr-x  1 user user 167985616 Jan  5 02:51 promo-evaluator
-rw-r--r--  1 user user  74557172 Jan  5 02:44 promo-evaluator-0.0.1-SNAPSHOT.jar
-rw-r--r--  1 user user    329198 Jan  5 02:44 promo-evaluator-0.0.1-SNAPSHOT.jar.original
drwxr-xr-x  3 user user      4096 Jan  5 02:44 spring-aot
drwxr-xr-x  2 user user      4096 Jan  5 02:44 surefire-reports
drwxr-xr-x  3 user user      4096 Jan  5 02:44 test-classes
drwxr-xr-x  2 user user      4096 Jan  5 02:44 test-ids
```

You can run it locally with `./promo-evaluator`. 


To deploy the native image to GKE, you can use below command for container image building and deployment:

```
skaffold -f skaffold-native.yaml run
```

### Build using Cloud Build

A `cloudbuild.yaml` file is also provided for using Cloud Build to build and deploy the container image to Artifact Registry.

Submit the build with this command:

```
gcloud builds submit
```