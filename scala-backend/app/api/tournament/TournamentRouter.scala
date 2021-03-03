package api.tournament

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class TournamentId private (val underlying: Int) extends AnyVal {
  override def toString: String = underlying.toString
}

object TournamentId {
  def apply(raw: String): TournamentId = {
    require(raw != null)
    new TournamentId(Integer.parseInt(raw))
  }
}

class TournamentRouter @Inject()(controller: TournamentController) extends SimpleRouter {
  val prefix = "/api/tournaments"

  def link(id: TournamentId): String = {
    import io.lemonlabs.uri.dsl._
    val url = prefix / id.toString
    url.toString()
  }

  override def routes: Routes = {
    case GET(p"/") =>
      controller.index()

    case POST(p"/") =>
      controller.process()

    case GET(p"/$id") =>
      controller.show(id)
  }
}
