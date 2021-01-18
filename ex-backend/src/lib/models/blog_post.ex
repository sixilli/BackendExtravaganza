defmodule Src.BlogPost do
    use Ecto.Schema
    import Ecto.Changeset
    alias Src.BlogPost

    @primary_key {:id, :id, autogenerate: true}

    schema "blog_posts" do
        field :author, :string
        field :category, :integer
        field :link, :string
        field :text, :string
        field :title, :string
        field :created_at, :utc_datetime
        field :updated_at, :utc_datetime
    end

    @doc false
    def changeset(post, attrs) do
        post
        |> cast(attrs, [:author, :category, :text, :title, :link])
        |> validate_required([:author, :category, :text, :title, :link])
    end
end