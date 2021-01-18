defmodule SrcWeb.TournamentView do
  use SrcWeb, :view
  alias SrcWeb.TournamentView

  def render("index.json", %{tournaments: tournaments}) do
    render_many(tournaments, TournamentView, "tournaments.json")
  end

  def render("show.json", %{tournament: tournament}) do
    render_one(tournament, TournamentView, "tournament.json")
  end

  def render("tournament.json", %{tournament: tournament}) do
    %{
        id: tournament.id,
        attendees: tournament.attendees,
        location: tournament.location,
        event_link: tournament.event_link,
        stream_link: tournament.stream_link,
        region: tournament.region,
        title: tournament.title,
        start_time: tournament.start_time,
        created_at: tournament.created_at,
        updated_at: tournament.updated_at
    }
    end
end