package models

import io.aexp.nodes.graphql.annotations.{GraphQLArgument, GraphQLProperty}

@GraphQLProperty(name = "User")
class User {

  var username: String = _
  var email: String = _

  def getUsername: String = username
  def getEmail: String = email
}
