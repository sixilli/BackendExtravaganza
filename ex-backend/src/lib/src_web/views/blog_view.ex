defmodule SrcWeb.BlogView do
  use SrcWeb, :view
  alias SrcWeb.BlogView

  def render("index.json", %{blogs: blogs}) do
    render_many(blogs, BlogView, "blog.json")
  end

  def render("show.json", %{blog: blog}) do
    render_one(blog, BlogView, "blog.json")
  end

  def render("blog.json", %{blog: blog}) do
    %{
        id: blog.id,
        author: blog.author,
        category: blog.category,
        link: blog.link,
        text: blog.text,
        title: blog.title,
        created_at: blog.created_at,
        updated_at: blog.updated_at
    }
    end
end