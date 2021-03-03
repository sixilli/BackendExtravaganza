// @GENERATOR:play-routes-compiler
// @SOURCE:G:/projects/php/TekkenHub/test-back/conf/routes
// @DATE:Wed Mar 03 01:09:28 CST 2021

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  api_tournament_TournamentRouter_0: api.tournament.TournamentRouter,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    api_tournament_TournamentRouter_0: api.tournament.TournamentRouter
  ) = this(errorHandler, api_tournament_TournamentRouter_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, api_tournament_TournamentRouter_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    prefixed_api_tournament_TournamentRouter_0_0.router.documentation,
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] val prefixed_api_tournament_TournamentRouter_0_0 = Include(api_tournament_TournamentRouter_0.withPrefix(this.prefix + (if (this.prefix.endsWith("/")) "" else "/") + "api/tournaments"))


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case prefixed_api_tournament_TournamentRouter_0_0(handler) => handler
  }
}
