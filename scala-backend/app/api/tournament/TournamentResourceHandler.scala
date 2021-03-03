package api.tournament

import javax.inject.{Inject, Provider}

import play.api.MarkerContext

import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._



case class TournamentFormInput(title: String, region: String, location: String, eventLink: String, streamLink: String,
                               attendees: Int, startTime: String)
/**
 * DTO for displaying tournament information.
 */

case class TournamentResource(id: Int, title: String, region: String, location: String, eventLink: String, streamLink: String,
                          attendees: Int, startTime: String, createdAt: String, updatedAt: String, link: String)

object TournamentResource {
  implicit val format: Format[TournamentResource] = Json.format
}
/**
 * Controls access to the backend data, returning [[TournamentResource]]
 */
class TournamentResourceHandler @Inject()(
   routerProvider: Provider[TournamentRouter],
   tournamentRepository: TournamentRepository)(implicit ec: ExecutionContext) {

  def create(form: TournamentFormInput)(
      implicit mc: MarkerContext): Future[TournamentResource] = {
    val tournament = TournamentResource(0, form.title, form.region, form.location, form.eventLink,
      form.streamLink, form.attendees, form.startTime, "", "", "")

    tournamentRepository.create(tournament).map { maybeData =>
      createTournamentResource(maybeData)
    }
  }

  def index()(implicit mc: MarkerContext): Future[List[TournamentResource]] = {
    println("made it to index handler owo")
    tournamentRepository.index().map { maybeRows =>
      maybeRows.map { tournament =>
        println(tournament)
        createTournamentResource(tournament)
      }
    }
  }

  def get(id: TournamentId)(implicit mc: MarkerContext): Future[TournamentResource] = {
    tournamentRepository.get(id).map { maybeData =>
      createTournamentResource(maybeData)
    }
  }

  def createTournamentResource(t: TournamentFormInput) = {
    TournamentResource(-1, t.title, t.region, t.location, t.eventLink, t.streamLink, t.attendees, t.startTime, "", "", "")
  }

  def createTournamentResource(t: TournamentDB) = {
    TournamentResource(t.id.underlying, t.title, t.region, t.location, t.eventLink, t.streamLink, t.attendees,
      t.startTime, t.createdAt, t.updatedAt,  routerProvider.get.link(t.id))
  }
}
