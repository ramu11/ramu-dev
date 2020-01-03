# getting-started project

Quarkus mvn archtype:
mvn io.quarkus:quarkus-maven-plugin:1.1.0.Final:create \
    -DprojectGroupId=org.redhat \
    -DprojectArtifactId=getting-started \
    -DclassName="org.redhat.quickstart.GreetingResource" \
    -Dpath="/hello"
 

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
mvn compile quarkus:dev

To list quarkus extensions:
mvn quarkus:list-extensions
To add:
mvn quarkus:add-extensions -Dextensions="reactive-streams,vertx"


```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `getting-started-1.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/getting-started-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`. or cd

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/getting-started-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .

```

oc new-project quarkus-s2i-demo
oc new-app --name=quarkus-1 quay.io/quarkus/ubi-quarkus-native-s2i:19.2.1~https://github.com/ramu11/ramu-dev.git --context-dir=quarkus/getting-started 

oc expose service quarkus-1

export URL="http://$(oc get route | grep quarkus-1 | awk '{print $2}')"
echo $URL
curl $URL/hello

please refer 'https://dzone.com/articles/learning-to-use-the-graalvm' for more info regarding graalvm
install graalvm on RHEL8:
Download Graalvm from 
1)sudo mv graalvm-ce-java11-linux-amd64-19.3.0.2 /usr/lib/jvm
2)cd /usr/lib/jvm
3)sudo ln -s graalvm-ce-java11-linux-amd64-19.3.0.2 graalvm
4)alternatives --config java

5)sudo alternatives --install /usr/bin/java java /usr/lib/jvm/graalvm/bin/java 3
  5.1)sudo update-alternatives --config java

  select 3
  
6)java -version


7)The native-image executable is not bundled in the GraalVM distribution anymore. Install it manually using $GRAALVM_HOME/bin/gu install native-image.
 ./gu install native-image
 
8)export GRAALVM_HOME=/usr/lib/jvm/graalvm/
   export PATH=${GRAALVM_HOME}/bin:$PATH
   $GRAALVM_HOME/bin/gu install native-image
   
   
Note:sudo alternatives --remove java /usr/lib/jvm/graalvm/bin/java

