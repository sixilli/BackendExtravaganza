package controllers

import (
	"net/http"
	"strconv"

	"github.com/labstack/echo"
	"github.com/labstack/gommon/log"
	"github.com/sixilli/TekkenHub/api/db"
	errs "github.com/sixilli/TekkenHub/api/errors"
	"github.com/sixilli/TekkenHub/api/models"
)

func GetTournamentsHandle(c echo.Context) error {
	t, err := db.GetAllTournaments()
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, t)
}

func CreateTournamentHandle(c echo.Context) error {
	t := models.Tournament{}
	if err := c.Bind(&t); err != nil {
		log.Error(err)
		return errs.ErrBadRequest
	}

	newT, err := db.CreateTournament(t)
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, newT)
}

func DeleteTournamentPostHandle(c echo.Context) error {
	return c.JSON(http.StatusOK, "Nice")
}

func GetTournamentHandle(c echo.Context) error {
	id, err := strconv.ParseInt(c.Param("id"), 10, 64)
	if err != nil {
		return errs.ErrInvalidID
	}

	t, err := db.GetTournament(id)
	if err != nil {
		log.Error(err)
		return errs.ErrInternalServerError
	}

	return c.JSON(http.StatusOK, t)
}

func UpdateTournamentHandle(c echo.Context) error {
	//id, err := strconv.ParseInt(c.Param("id"), 10, 64)
	//if err != nil {
	//return errs.ErrInvalidID
	//}

	return c.JSON(http.StatusOK, "Nice")
}
