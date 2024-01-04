FROM ubuntu:jammy
EXPOSE 8080
COPY target/promo-evaluator /app/promo-evaluator
CMD ["/app/promo-evaluator"]