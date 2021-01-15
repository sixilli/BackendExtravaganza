defmodule Src.BlogPost do
    use Ecto.Schema
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
end