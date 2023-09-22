Prerequisite: structured-logging built and in Maven repository

Build: mvn install

Deploy on Jarkata10 compatible application server

Run: curl http://localhost:8080/structured-logging-demo/log


## Deploy to github Registry

docker build -t ghcr.io/gepardec/structured-logging-demo:latest -f Containerfile .

export CR_PAT=YOUR_TOKEN

echo $CR_PAT | docker login ghcr.io -u USERNAME --password-stdin

docker push ghcr.io/gepardec/structured-logging-demo:latest

Also see https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-container-registry#authenticating-with-a-personal-access-token-classic
