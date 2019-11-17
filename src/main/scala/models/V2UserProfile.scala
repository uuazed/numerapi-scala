package models

import io.aexp.nodes.graphql.annotations.{GraphQLArgument, GraphQLProperty}

@GraphQLProperty(name = "V2UserProfile", arguments = Array(
  new GraphQLArgument(name = "username")))
class V2UserProfile {

  var bio: String = _
  var historicalNetNmrEarnings: Float = _
  var totalStake: Float = _

  def getBio: String = bio
  def getHistoricalNetNmrEarnings: Float = historicalNetNmrEarnings
  def getTotalStake: Float = totalStake
}
