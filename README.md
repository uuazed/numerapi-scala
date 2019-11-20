![Bintray](https://img.shields.io/bintray/v/uuazed/maven/numerapi)

# Numerai API for JVM languages
Automatically download data and upload predictions for the Numerai machine learning
competition.

This library is a Scala client to the Numerai API. It allows downloading the
training data, uploading predictions, and accessing user, submission and
competition information.

If you encounter any problems or have suggestions, feel free to open an issue.

# Installation

## sbt

To get started, simply add the following lines to your `build.sbt` file

    resolvers += Resolver.bintrayRepo("uuazed", "maven")

    libraryDependencies += "uuazed" %% "numerapi" % "0.1.2"

## gradle

Add this to your `build.gradle` file:

    repositories {
         maven {
             url  "https://dl.bintray.com/uuazed/maven"
         }
         mavenCentral()
     }

    dependencies {
        compile "uuazed:numerapi_2.13:0.1.2"
    }

# Usage

Make sure you checkout the full usage examples in the `examples` directory.

## Scala

In short, using `numerapi` works like this:

    import numerapi.NumerApi

    object Main extends App {

      val napi = new NumerApi("my_public_id", "my_secret_key")

      // download current dataset
      napi.downloadDataset("data.zip")

      //.. your code to generate predictions

      // upload predictions
      val submissionId = napi.uploadSubmission("my_predictions.csv")

      // get some information from a user's public profile
      val user = napi.getPublicUserProfile("uuazed")
      println(user)
}

## Java

You can also use `numerapi` from Java!

    public class Main {

        public static void main(String[] args) {
            NumerApi napi = new NumerApi("<my_public_id>", "<my_secret_key>");

            // download current dataset
            napi.downloadDataset("data.zip");

            //.. your code to generate predictions

            // upload predictions
            String submissionId = napi.uploadSubmission("my_predictions.csv")
            System.out.println("submission_id: " + submissionId)

            // get some information from a user's public profile
            System.out.println(napi.getPublicUserProfile("uuazed"));
            }
    }


# API Reference

TODO
