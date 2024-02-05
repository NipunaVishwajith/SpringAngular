# Makefile for Dockerizing Spring Boot Application

IMAGE_NAME = image-upload
IMAGE_VERSION = v2.0
CONTAINER_NAME = my-spring-app

build:
    docker build -t $(IMAGE_NAME):$(IMAGE_VERSION) .

docker-run:
	docker run -p 8083:8083 -e DB_HOST=localhost -e DB_PORT=3306 -e DB_NAME=Task01 -e DB_USERNAME=root -e DB_PASSWORD=156e26377#E $(DOCKER_IMAGE_NAME):$(DOCKER_IMAGE_TAG)

stop:
    docker stop $(CONTAINER_NAME) || true
    docker rm $(CONTAINER_NAME) || true

clean:
    docker rmi $(IMAGE_NAME):$(IMAGE_VERSION) || true

all: build run

help:
	@echo "Available targets:"
	@echo "  make build        - Build the Spring Boot application"
	@echo "  make test         - Run tests"
	@echo "  make run          - Run the Spring Boot application"
	@echo "  make docker-build - Build Docker image"
	@echo "  make docker-run   - Run Docker container"
	@echo "  make clean        - Clean build artifacts"