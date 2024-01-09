### Intro

This repo demostrates use of events-driven architecture with `GCP Pub/Sub` - as the event broker, `MongoDb`, `Spring Boot`, `Graalvm`, and `Cloud Run`. 

This project subscript to one topic and expect to receive `OrderCreated`, `OrderCancelled` and `OrderItemQuantityUpdated` events. It then processes them to determine if any campaign is applicable and calculate the remaining amount for joining the campaign.  

One of the benefits of using this approach is that the campaign calculation is decoupled from user queries, which means it can be done when there is update on order - order created, cancelled or item quantity updated. The amounts for available campaigns are ready and always up-to-date when user query them. 

### Prerequisites


Create pubsub topic
```
gcloud pubsub topics create order
```

Create secret for MongoDb connection string
```
gcloud secrets create mongodb-dev --replication-policy=automatic --data="mongodb://USERNAME:PASSWORD@MONGO_HOST:27017/momo"
```


### Build and Deploy locally

GraalVM Native Image is great for startup time and memory usage. However the image building time can take over 10 mins, which is not ideal during development.

To avoid continously building / deploying container while developing, use `skaffold dev` instead. This will use `jib` for container image building, which is much quicker and convinient. 

```
skaffold -f skaffold-dev.yaml dev
```

This will sync files from local workstation to K8s automatically, so you do not need to rebuild / redeploy everytime you want to run it. 


To build and run the native image on Cloud Run, you can use below command for container image building and deployment:

```
skaffold -f skaffold-native.yaml run
```

### Build using Cloud Build

A `cloudbuild.yaml` file is also provided for using Cloud Build to build and deploy the container image to Artifact Registry.

Submit the build with this command:

```
gcloud builds submit
```