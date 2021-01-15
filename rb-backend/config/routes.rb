Rails.application.routes.draw do
  namespace :api do
    resources :blogs do
      resources :tournaments
    end
  end
end