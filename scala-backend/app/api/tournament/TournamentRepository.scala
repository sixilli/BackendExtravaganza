package api.tournament

import javax.inject.{Inject, Singleton}
import scalikejdbc._
import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext
import play.api.{Logger, MarkerContext}

import scala.concurrent.Future

class TournamentExecutionContext @Inject()(actorSystem: ActorSystem)
  extends CustomExecutionContext(actorSystem, "repository.dispatcher")

/**
 * A pure non-blocking interface for the PostRepository.
 */
trait TournamentRepository {
  def create(data: TournamentResource)(implicit mc: MarkerContext): Future[TournamentDB]

  def index()(implicit mc: MarkerContext): Future[List[TournamentDB]]

  def get(id: TournamentId)(implicit mc: MarkerContext): Future[TournamentDB]
}

@Singleton
class TournamentRepositoryImpl @Inject()()(implicit ec: TournamentExecutionContext)
  extends TournamentRepository {

  private val logger = Logger(this.getClass)
  private val db = TournamentDB

  def create(data: TournamentResource)(implicit mc: MarkerContext): Future[TournamentDB] = {
    logger.trace(s"create: data = $data")
    Future {
      db.create(data).getOrElse(emptyTournamentDB())
    }
  }

  def index()(implicit mc: MarkerContext): Future[List[TournamentDB]] = {
    logger.trace(s"index: ")
    Future {
      db.index()
    }
  }

  def get(id: TournamentId)(implicit mc: MarkerContext): Future[TournamentDB] = {
    logger.trace(s"get: id = $id")
    Future {
      db.get(id).getOrElse(emptyTournamentDB())
    }
  }

  def emptyTournamentDB(): TournamentDB = {
    TournamentDB(TournamentId("-1"), "", "", "", "", "", 0, "", "", "")
  }
}

case class TournamentDB(id: TournamentId, title: String, region: String, location: String, eventLink: String, streamLink: String,
                                attendees: Int, startTime: String, createdAt: String, updatedAt: String)

object TournamentDB extends SQLSyntaxSupport[TournamentDB] {
  override val schemaName = Some("PUBLIC")
  override val tableName = "tournaments"
  override val columns = Seq("id", "title", "region", "location", "event_link", "stream_link", "attendees", "start_time", "created_at", "updated_at")

  def apply(t: SyntaxProvider[TournamentDB])(rs: WrappedResultSet): TournamentDB = apply(t.resultName)(rs)
  def apply(t: ResultName[TournamentDB])(rs: WrappedResultSet): TournamentDB = {
    new TournamentDB(
      id = TournamentId(rs.long("id").toString),
      title = rs.get("title"),
      region = rs.get("region"),
      location = rs.get("location"),
      eventLink = rs.get("event_link"),
      streamLink = rs.get("stream_link"),
      attendees = rs.get("attendees"),
      startTime = rs.get("start_time"),
      createdAt = rs.get("created_at"),
      updatedAt = rs.get("updated_at")
    )
  }

  val t = TournamentDB.syntax("t")

  override val autoSession = AutoSession

  def index()(implicit session: DBSession = autoSession, mc: MarkerContext): List[TournamentDB] = {
    // Query builder way
    //withSQL(select.from(Tournament as t)).map(Tournaments(t.resultName)).list.apply()
    sql"SELECT * FROM tournaments".map(TournamentDB(t.resultName)).list().apply()
  }

  def get(id: TournamentId)(implicit session: DBSession = autoSession): Option[TournamentDB] = {
    // Using variables
    sql"SELECT * FROM tournaments WHERE id = ${id}".map(TournamentDB(t.resultName)).single().apply()
  }

  def create(td: TournamentResource)(implicit session: DBSession = autoSession): Option[TournamentDB] = {
    DB.localTx { implicit session =>
      sql"""INSERT INTO tournaments (title, region, location, event_link, stream_link, attendees, start_time)
      VALUES (${td.title}, ${td.region}, ${td.location}, ${td.eventLink},
        ${td.streamLink}, ${td.attendees}, TO_TIMESTAMP(${td.startTime}, 'YYYY-MM-DDXHH24:MI:SS:US'))
      RETURNING *;
    """
      .map(TournamentDB(t.resultName))
      .single()
      .apply()
    }
  }
}
