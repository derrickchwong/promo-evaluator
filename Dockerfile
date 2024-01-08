FROM ghcr.io/graalvm/graalvm-community:17 as build 
WORKDIR /build
COPY . . 
RUN ./mvnw clean package -Pnative 

FROM ubuntu:jammy
COPY --from=build /build/target/promo-evaluator /app/promo-evaluator
CMD ["/app/promo-evaluator"]