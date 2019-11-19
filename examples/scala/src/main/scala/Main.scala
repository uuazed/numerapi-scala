import numerapi.NumerApi


object Main extends App {

  val napi = new NumerApi("my_public_id", "my_secret_key")

  // download current dataset
  napi.downloadDataset("data.zip")

  // upload predictions
  val submissionId = napi.uploadSubmission("my_predictions.csv")

  // get some information from a user's public profile
  val user = napi.getPublicUserProfile("uuazed")
  println(user)
}
