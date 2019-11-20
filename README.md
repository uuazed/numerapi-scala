
# Numerai Scala API
Automatically download data and upload predictions for the Numerai machine learning
competition.

This library is a Scala client to the Numerai API. It allows downloading the
training data, uploading predictions, and accessing user, submission and
competition information.

If you encounter any problems or have suggestions, feel free to open an issue.

# Installation

To get started, simply add the following lines to your `build.sbt` file

    resolvers += Resolver.bintrayRepo("uuazed", "maven")

    libraryDependencies += "uuazed" %% "numerapi" % "0.1.1"


# Usage

Make sure you checkout the full usage examples in the `examples` directory.
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


# API Reference

TODO
