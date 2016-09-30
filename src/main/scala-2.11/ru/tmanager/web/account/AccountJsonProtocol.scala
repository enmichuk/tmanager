package ru.tmanager.web.account

import ru.tmanager.model.Account
import ru.tmanager.services.account.AccountService._
import spray.json.DefaultJsonProtocol

object AccountJsonProtocol extends DefaultJsonProtocol{

  implicit val accountFormat = jsonFormat3(Account)
  implicit val accountByIdRequestFormat = jsonFormat1(AccountByIdRequest)
  implicit val accountByIdResponseFormat = jsonFormat1(AccountByIdResponse)
  implicit val accountsRequestFormat = jsonFormat0(() => AccountsRequest)
  implicit val accountsResponseFormat = jsonFormat1(AccountsResponse)
  implicit val createAccountRequestFormat = jsonFormat1(CreateAccountRequest)
  implicit val createAccountResponseFormat = jsonFormat2(CreateAccountResponse)

}
