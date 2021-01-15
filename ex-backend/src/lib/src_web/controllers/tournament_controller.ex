defmodule SrcWeb.TournamentController do
    use SrcWeb, :controller

    def index(conn, _params) do
        tournaments = Repo.all(Tournament)
        render conn, "index.json", tournaments: tournaments
    end
end