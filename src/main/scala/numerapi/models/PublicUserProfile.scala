package numerapi.models

import play.api.libs.json.{JsPath, Reads}
import play.api.libs.functional.syntax._


case class PublicUserProfile(
  username: String,
  netEarnings: String,
  historicalNetNmrEarnings: String,
  historicalNetUsdEarnings: String,
  id: String,
  startDate: String,
  badges: Seq[String])


object PublicUserProfile {
    implicit val reads: Reads[PublicUserProfile] = (
        (JsPath \ "v2UserProfile" \ "username").read[String] and
        (JsPath \ "v2UserProfile" \ "netEarnings").read[String] and
        (JsPath \ "v2UserProfile" \ "historicalNetNmrEarnings").read[String] and
        (JsPath \ "v2UserProfile" \ "historicalNetUsdEarnings").read[String] and
        (JsPath \ "v2UserProfile" \ "id").read[String] and
        (JsPath \ "v2UserProfile" \ "startDate").read[String] and
        (JsPath \ "v2UserProfile" \ "badges").read[Seq[String]])(PublicUserProfile.apply _)
}
