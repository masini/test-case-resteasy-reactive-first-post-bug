# Reproducer
Launch

    ./mvnw quarkus:dev

attach a debugger and wait that it starts then execute this:

    curl -X POST --location "https://localhost:9081/resources/test" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d "{
          \"property\": \"prova\"
        }" \
    --basic --user admin:admin -k -v

This should hang on the socket after the content is uploaded.

After the first request all works as expected.