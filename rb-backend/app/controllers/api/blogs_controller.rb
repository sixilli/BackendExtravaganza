class Api::BlogsController < ApplicationController
    def index
        @blogs = Blog.all
        render json: @blogs
    end

    def show
        @blog = Blog.find(params[:id])
        render json: @user
    end

    def create
        @blog = Blog.new(user_params)
        if @blog.save
            render  json: @blog
        else
           render error: { error: 'Unable to create blog post.'}, status: 400
        end
    end

    def user_params
        params.require{:blog}.permit(:author, :category, :link, :text, :title)
    end
end
