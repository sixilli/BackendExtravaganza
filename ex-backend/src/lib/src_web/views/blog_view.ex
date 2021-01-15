defmodule SrcWeb.BlogView do
  use SrcWeb, :view

  def render("index.json", %{blogs: blogs}) do
    Enum.map(blogs, &blog_json/1)
  end

  def blog_json(blog) do
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