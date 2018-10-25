# carty-temp
Repo for question https://stackoverflow.com/questions/52982123/can-not-retrieve-principal-from-spring-okta-cra-spa


# Purpose:
A trialing of the spring okta aplpication, this repo is meant to serve as a backend /resource serve for a seperate clients, in our case A React SPA.



# Issue
Currently the okta intergration works fine with the SPA however I am unsure as how to obtain the authorized user making a resource request, hence the stack overflow question.
The contrller can be found at *com.verric.carty.product.ProductController*

# Pre-reqs

- Java 8
- gradle

# Run
gradle bootRun || ./gradelw bootRun
