FROM golang:1.14

RUN apt-get update && apt-get install -y ca-certificates git-core ssh
RUN export GO111MODULE=on && go get github.com/beego/bee

# COPY go.build.mod ./go.mod
# COPY go.sum ./
# Expose the application on port 8001
# EXPOSE 12080

# Set the entry point of the container to the bee command that runs the
# application and watches for changes
CMD ["bee", "run"]