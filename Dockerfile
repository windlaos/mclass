rm -rf deploy
mkdir -p deploy/static
mkdir -p deploy/templates
cp Dockerfile deploy/
cp target/demo-0.0.1-SNAPSHOT.jar deploy/app.jar
cp -R src/main/resources/static deploy/static/
cp -R src/main/resources/templates deploy/templates/
