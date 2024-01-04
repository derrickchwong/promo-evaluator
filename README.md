To avoid continously building / deploying container while developing, use `skaffold dev` instead.

```
skaffold -f skaffold-dev.yaml dev
```

This will sync files from local workstation to K8s automatically, so you do not need to rebuild / redeploy everytime you want to run it. 

To build and deploy Spring Boot Native image, use the `native` profile defined on `pom.xml`.

```
./mvnw clean package -Dnative
```

To build container image and deploy to k8s, simply run: 

```
skaffold -f skaffold-native.yaml run
```
