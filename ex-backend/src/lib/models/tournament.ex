defmodule SrcWeb.Tournament do
    use Ecto.Schema

    @primary_key {:id, :id, autogenerate: true}

    schema "tournaments" do
        field :attendees, :integer
        field :event_link, :string
        field :location, :string
        field :region, :string
        field :stream_link, :string
        field :title, :string
        field :start_time, :utc_datetime
        field :created_at, :utc_datetime
        field :updated_at, :utc_datetime
    end
end