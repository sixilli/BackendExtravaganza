package v1.Tournament

import play.api.{Logger, MarkerContext}
import scalikejdbc._


case class Tournament(id: BigInt, title: String, region: String, location: String, eventLink: String, streamLink: String,
                                attendees: BigInt, startTime: String, createdAt: String, updatedAt: String)


object Tournament extends SQLSyntaxSupport[Tournament] {

  override val schemaName = Some("PUBLIC")
  override val tableName = "tournaments"
  override val columns = Seq("id", "title", "region", "location", "event_link", "stream_link", "attendees", "start_time", "created_at", "updated_at")

  def apply(u: SyntaxProvider[Tournament])(rs: WrappedResultSet): Tournament = apply(u.resultName)(rs)
  def apply(u: ResultName[Tournament])(rs: WrappedResultSet): Tournament = new Tournament(
    id = rs.get(u.id),
    title = rs.get(u.title),
    region = rs.get(u.region),
    location = rs.get(u.location),
    eventLink = rs.get(u.event_link),
    streamLink = rs.get(u.stream_link),
    attendees = rs.get(u.attendees),
    startTime = rs.get(u.start_time),
    createdAt = rs.get(u.created_at),
    updatedAt = rs.get(u.updated_at)
  )

  val t = Tournament.syntax("t")

  override val autoSession = AutoSession

  def index()(implicit session: DBSession = autoSession): List[Tournament] = {
    // Query builder way
    //withSQL(select.from(Tournament as t)).map(Tournaments(t.resultName)).list.apply()
    sql"SELECT * FROM $tableName".map(Tournament(t.resultName)).list.apply()
  }

  def get(id: BigInt)(implicit session: DBSession = autoSession): Option[Tournament] = {
    // Using variables
    sql"SELECT * FROM $tableName WHERE id = $id".map(Tournament(t.resultName)).single.apply()
  }

  def create(title: String, region: String, location: String, eventLink: String, streamLink: String,
             attendees: Int, startTime: String)(implicit session: DBSession = autoSession): Option[Tournament] = {
    sql"""INSERT INTO $tableName (title, region, location, event_link, stream_link, attendees, start_time)
      VALUES ($title, $region, $location, $eventLink, $streamLink, $attendees, $startTime)
      RETURNING *;
    """
      .map(Tournament(t.resultName))
      .single
      .apply()
  }
}
