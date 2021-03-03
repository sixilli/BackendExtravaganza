package api.tournament

import javax.inject.Inject
import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc.{Action, _}

import com.github.tototoshi.play.json.JsonNaming

import scala.concurrent.{ExecutionContext, Future}

class TournamentController @Inject()(cc: TournamentControllerComponents)(
    implicit ec: ExecutionContext)
    extends TournamentBaseController(cc) {

  private val logger = Logger(getClass)

  implicit val tournamentFormat = JsonNaming.snakecase(Json.format[TournamentResource])

  def index(): Action[AnyContent] = TournamentAction.async { implicit request =>
    logger.trace("index: ")
    tournamentResourceHandler.index().map { tournaments =>
      Ok(Json.toJson(tournaments))
    }
  }

  def show(id: String) = TournamentAction { implicit request =>
    Ok("")
  }

  def process() = TournamentAction.async { implicit request =>
    val form: Form[TournamentFormInput] = {
      import play.api.data.Forms._
      Form(
        mapping(
          "title" -> nonEmptyText,
          "region" -> text,
          "location" -> text,
          "event_link" -> text,
          "stream_link" -> text,
          "attendees" -> number,
          "start_time" -> text,
        )(TournamentFormInput.apply)(TournamentFormInput.unapply)
      )
    }
    logger.trace("process: ")
    processJsonPost(form)
  }

  private def processJsonPost[A](form: Form[TournamentFormInput])(
        implicit request: TournamentRequest[A]): Future[Result] = {
    def failure(badForm: Form[TournamentFormInput]) = {
      println("We failure :(")
      Future.successful(BadRequest(badForm.errorsAsJson))
    }

    def success(input: TournamentFormInput) = {
      tournamentResourceHandler.create(input).map { tournament =>
        Created(Json.toJson(tournament)).withHeaders(LOCATION -> "OwO")
      }
    }

    form.bindFromRequest().fold(failure, success)
  }
}
