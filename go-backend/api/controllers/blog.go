package controllers

import (
	"net/http"
	"strconv"

	"github.com/sixilli/TekkenHub/api/db"
	"github.com/sixilli/TekkenHub/api/models"

	"github.com/labstack/echo"
	"github.com/labstack/gommon/log"
	errs "github.com/sixilli/TekkenHub/api/errors"
)

func GetBlogsHandle(c echo.Context) error {
	bp, err := db.GetAllBlogPosts()
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, bp)
}

func GetBlogPostHandle(c echo.Context) error {
	id, err := strconv.ParseInt(c.Param("id"), 10, 64)
	if err != nil {
		return errs.ErrInvalidID
	}

	bp, err := db.GetBlogPost(id)
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, bp)
}

func CreateBlogPostHandle(c echo.Context) error {
	bp := models.BlogPost{}
	if err := c.Bind(&bp); err != nil {
		log.Error(err)
		return errs.ErrBadRequest
	}

	newBp, err := db.CreateBlogPost(bp)
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, newBp)
}

func DeleteBlogPostHandle(c echo.Context) error {
	return c.JSON(http.StatusOK, "Nice")
}

func UpdateBlogPostHandle(c echo.Context) error {
	id, err := strconv.ParseInt(c.Param("id"), 10, 64)
	if err != nil {
		log.Error(err)
		return errs.ErrInvalidID
	}

	newPost := models.BlogPost{}
	if err := c.Bind(&newPost); err != nil {
		log.Error(err)
		return errs.ErrBadRequest
	}

	oldPost, err := db.GetBlogPost(id)
	if err != nil {
		log.Error(err)
		return errs.ErrInvalidID
	}

	newPost.Merge(oldPost)

	result, err := db.UpdateBlogPost(id, newPost)
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, result)
}
