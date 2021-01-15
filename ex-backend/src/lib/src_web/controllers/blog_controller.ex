defmodule SrcWeb.BlogController do
    use SrcWeb, :controller
    alias Src.BlogPost
    alias Src.Repo

    def index(conn, _params) do
        blogs = Repo.all(BlogPost)
        render conn, "index.json", blogs: blogs
    end
end