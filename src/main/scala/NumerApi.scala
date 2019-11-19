import io.aexp.nodes.graphql.{Argument, Arguments, GraphQLRequestEntity, GraphQLTemplate}
import java.net.URL
import java.nio.file.{Files, Paths}
import java.io.File
import java.util
import scalaj.http._
import sys.process._
import scala.language.postfixOps
import play.api.libs.json.{Json, JsValue, JsSuccess, JsError, JsObject, JsString}
import models._

class NumerApi(publidId: String, secretKey: String) {

  private val headers = new util.HashMap[String, String]()
  headers.put("Authorization", s"Token ${publidId}$$${secretKey}")


  def rawQuery(query: String, parameter: Map[String, String] = Map.empty[String, String], authorization: Boolean = false): JsValue = {

    val headers2 = Seq("Content-type" -> "application/json",
                       "Accept" -> "application/json")
    val auth =
      if (authorization)
        Seq("Authorization" -> s"Token ${publidId}$$${secretKey}")
      else
        Nil

    val content = Json.obj(
        "query" -> query,
        "variables" -> Json.toJson(parameter)
      )
    println("PAYLOAD: ", content.toString)

    val raw = Http("https://api-tournament.numer.ai")
      .postData(content.toString)
      .headers(headers2)
      .headers(auth)
      .asString
    val json = Json.parse(raw.body)

    (json \ "errors").asOpt[JsValue] match {
      case Some(messages) => println(messages)
      case None =>
    }
    println("DATA: ", (json \ "data").get)
    (json \ "data").get
  }

  def getUserProfile(username: String): V2UserProfile = {
    val template = new GraphQLTemplate()
    val requestEntity: GraphQLRequestEntity = GraphQLRequestEntity.Builder()
        .url("https://api-tournament.numer.ai")
        .request(classOf[V2UserProfile])
        .arguments(new Arguments("V2UserProfile",
                   new Argument("username", "uuazed")))
        .build()
    println(requestEntity.getRequest)
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
    val query = "query {dataset(tournament: 8)}"
    (rawQuery(query) \ "dataset").as[String]
  }

  def downloadDataset(filename: String) = {
    new URL(getDatasetUrl) #> new File(filename) !!
  }

  def uploadSubmission(filePath: String): String = {
    val filename: String = filePath.split("/").last
    val authQuery = """
      query($filename: String!) {
          submission_upload_auth(filename: $filename
                                 tournament: 8) {
              url
              filename
          }
      }"""
    val auth = rawQuery(authQuery, Map("filename" -> filename), authorization=true)
    val url = (auth \ "submission_upload_auth" \ "url").as[String]
    val targetFilename = (auth \ "submission_upload_auth" \ "filename").as[String]
    val bytes: Array[Byte] = Files.readAllBytes(Paths.get(filePath))
    // upload the file
    Http(url).put(bytes).asString
    val submissionMutation = """
      mutation($filename: String!) {
          create_submission(filename: $filename
                            tournament: 8) {
              id
          }
      }"""
    val create = rawQuery(submissionMutation,
      Map("filename" -> targetFilename), authorization=true)
    (create \ "create_submission" \ "id").as[String]
  }
}
