package db

import (
	"github.com/sixilli/TekkenHub/api/models"
	storage "github.com/sixilli/TekkenHub/storage/postgres"
)

func CreateBlogPost(b models.BlogPost) (models.BlogPost, error) {
	bp := models.BlogPost{}
	query := `INSERT INTO blog_posts (title, author, category, text, link)
			  VALUES ($1, $2, $3, $4, $5)
			  RETURNING *;`
	err := storage.DB.Get(&bp, query, b.Title, b.Author, b.Category, b.Text, b.Link)
	if err != nil {
		return bp, err
	}

	return bp, nil
}

func GetBlogPost(id int64) (models.BlogPost, error) {
	bp := models.BlogPost{}
	query := `SELECT * FROM blog_posts WHERE id = $1`
	err := storage.DB.Get(&bp, query, id)
	if err != nil {
		return bp, err
	}

	return bp, nil
}

func GetAllBlogPosts() ([]models.BlogPost, error) {
	bp := []models.BlogPost{}
	query := `SELECT * FROM blog_posts`
	err := storage.DB.Select(&bp, query)
	if err != nil {
		return bp, err
	}

	return bp, nil
}

func UpdateBlogPost(id int64, b models.BlogPost) (models.BlogPost, error) {
	bp := models.BlogPost{}
	query := `
		UPDATE blog_posts
		SET title = $2,
			author = $3,
			category = $4,
			text = $5,
			link = $6
		WHERE id = $1
		RETURNING *`
	err := storage.DB.Get(&bp, query, id, b.Title, b.Author, b.Category, b.Text, b.Link)
	if err != nil {
		return bp, err
	}

	return bp, nil
}
