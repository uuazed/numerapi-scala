package models

import io.aexp.nodes.graphql.annotations.{GraphQLArgument, GraphQLProperty, GraphQLIgnore}

@GraphQLProperty(name = "dataset", arguments = Array(
  new GraphQLArgument(name = "tournament")))
class Dataset(rawUrl: String) {

  @GraphQLIgnore
  val url = rawUrl
}
