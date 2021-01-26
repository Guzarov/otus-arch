FROM golang:1.15-alpine AS BUILD
COPY . /src
RUN cd /src && go build -o app

FROM alpine
WORKDIR /app
COPY --from=BUILD /src/app /app/
ENTRYPOINT ["/app/app"]
