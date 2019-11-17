package models

import io.aexp.nodes.graphql.annotations.{GraphQLArgument, GraphQLProperty}

@GraphQLProperty(name = "createSubmission", arguments = Array(
  new GraphQLArgument(name = "filename"),
  new GraphQLArgument(name = "tournament")))
class Submission {

  var id: String = _

  def getId: String = id
}
