defmodule SrcWeb.BlogController do
    use SrcWeb, :controller
    alias Src.BlogPost
    alias Src.Repo
    alias Src.Blogs

    def index(conn, _params) do
        blogs = Repo.all(BlogPost)
        render conn, "index.json", blogs: blogs
    end

    def show(conn, %{"id" => id}) do
        post = Repo.get(BlogPost, id)
        render conn, "show.json", blog: post
    end

   def create(conn, blog_post) do
        with {:ok, %BlogPost{} = blog_post} <- Blogs.create_blog_post(blog_post) do
            blog_post = Repo.get!(BlogPost, blog_post.id)
            conn
            |> put_status(:created)
            |> render("show.json", blog: blog_post)
        end
   end
end