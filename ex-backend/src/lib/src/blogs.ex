defmodule Src.Blogs do
    import Ecto.Query, warn: false
    alias Src.Repo
    alias Src.BlogPost

    def create_blog_post(attrs) do
        %BlogPost{}
        |> BlogPost.changeset(attrs)
        |> Repo.insert()
    end
end