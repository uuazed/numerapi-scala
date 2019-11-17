import io.aexp.nodes.graphql.{Argument, Arguments, GraphQLRequestEntity, GraphQLTemplate}
import java.net.URL
import java.nio.file.{Files, Paths}
import java.io.File
import java.util
import scalaj.http._
import sys.process._
import scala.language.postfixOps
import models._

class NumerApi(publidId: String, secretKey: String) {

  private val headers = new util.HashMap[String, String]()
  headers.put("Authorization", s"Token ${publidId}$$${secretKey}")

  def getUserProfile(username: String): V2UserProfile = {
    val template = new GraphQLTemplate()
    val requestEntity: GraphQLRequestEntity = GraphQLRequestEntity.Builder()
        .url("https://api-tournament.numer.ai")
        .request(classOf[V2UserProfile])
        .arguments(new Arguments("V2UserProfile",
                   new Argument("username", "uuazed")))
        .build()

    template.query(requestEntity, classOf[V2UserProfile]).getResponse
  }

  def getUser: User = {
    val template = new GraphQLTemplate()
    val requestEntity: GraphQLRequestEntity = GraphQLRequestEntity.Builder()
        .url("https://api-tournament.numer.ai")
        .headers(headers)
        .request(classOf[User])
        .build()

    template.query(requestEntity, classOf[User]).getResponse
  }

  def getDatasetUrl: String = {
    val template = new GraphQLTemplate()
    val requestEntity: GraphQLRequestEntity = GraphQLRequestEntity.Builder()
        .url("https://api-tournament.numer.ai")
        .request(classOf[Dataset])
        .arguments(new Arguments("dataset",
                   new Argument("tournament", 8)))
        .build()

    template.query(requestEntity, classOf[Dataset]).getResponse.url
  }

  def downloadDataset(filename: String) = {
    new URL(getDatasetUrl) #> new File(filename) !!
  }

  def uploadSubmission(filePath: String) = {
    val filename: String = filePath.split("/").last
    val template = new GraphQLTemplate()
    val requestEntity: GraphQLRequestEntity = GraphQLRequestEntity.Builder()
      .url("https://api-tournament.numer.ai")
      .headers(headers)
      .request(classOf[SubmissionAuthorization])
      .arguments(new Arguments("submission_upload_auth",
                 new Argument("tournament", 8),
                 new Argument("filename", filename)))
      .build()
    val url = template
      .query(requestEntity, classOf[SubmissionAuthorization])
      .getResponse
      .getUrl
    val bytes: Array[Byte] = Files.readAllBytes(Paths.get(filePath))

    // upload the file
    Http(url).put(bytes).asString

    val requestEntity2: GraphQLRequestEntity = GraphQLRequestEntity.Builder()
      .url("https://api-tournament.numer.ai")
      .headers(headers)
      .arguments(new Arguments("createSubmission",
                 new Argument("tournament", 8),
                 new Argument("filename", filename)))
      .request(classOf[Submission])
      .build()

    template.mutate(requestEntity2, classOf[Submission]).getResponse
    }

}
