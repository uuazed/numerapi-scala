package models

import io.aexp.nodes.graphql.annotations.{GraphQLArgument, GraphQLProperty}

@GraphQLProperty(name = "submission_upload_auth", arguments = Array(
  new GraphQLArgument(name = "filename"),
  new GraphQLArgument(name = "tournament")))
class SubmissionAuthorization {

  var filename: String = _
  var url: String = _

  def getFilename: String = filename
  def getUrl: String = url
}
