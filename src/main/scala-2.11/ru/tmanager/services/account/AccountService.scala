package ru.tmanager.services.account

import akka.actor.Actor
import akka.actor.Actor.Receive
import ru.tmanager.dao.account.AccountDao
import ru.tmanager.model.Account
import ru.tmanager.services.ActorDao

object AccountService {

  case class AccountByIdRequest(id: String)

  case class AccountByIdResponse(account: Option[Account])

  object AccountsRequest

  case class AccountsResponse(accounts: List[Account])

  case class CreateAccountRequest(account: Account)

  case class CreateAccountResponse(account: Account, success: Boolean)

}

class AccountService extends Actor with AccountDao with ActorDao{

  import AccountService._

  override def receive = {

    case AccountByIdRequest(id) => sender ! AccountByIdResponse(getAccountById(id))

    case CreateAccountRequest(account: Account) =>
      create(account) match{
        case None => sender ! CreateAccountResponse(account, success = true)
        case Some(a) => sender ! CreateAccountResponse(a, success = false)
      }

    case AccountsRequest => sender ! AccountsResponse(getAllAccounts)

  }
}
