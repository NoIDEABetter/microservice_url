This is a project consisting of a zuul gateway with a shortURL service sitting behind.

You can use the url-client angular app to connect to make calls to the service. Please find the README file in the URL client to learn more about how to run this app.

There is a dockerfile with each module, which allows you to create a docker image for each of the services. This setup is configured to run on localhost.

The zuul and Technical_Assessment modules were compiled using Gradle tool.

The Angular app would require you to have angular 9 installed locally so you can run the build and create your docker image.
