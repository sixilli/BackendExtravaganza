package main

import (
	"net/http"
	"os"
	"time"

	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
	log "github.com/sirupsen/logrus"
	"github.com/sixilli/TekkenHub/api/controllers"
	errs "github.com/sixilli/TekkenHub/api/errors"
	storage "github.com/sixilli/TekkenHub/storage/postgres"
	"github.com/spf13/viper"
	"github.com/tylerb/graceful"
)

type (
	mapI map[string]interface{}
)

func init() {
	viper.SetConfigName("config")
	viper.SetConfigType("json")
	viper.AddConfigPath(".")

	err := viper.ReadInConfig()

	if err != nil {
		log.Panic("No configuration file loaded - using defaults", err)
	}

}

func ErrorHandler(err error, c echo.Context) {
	he, ok := err.(*errs.Error)
	if ok {
		if he.Internal != nil {
			if herr, ok := he.Internal.(*errs.Error); ok {
				log.Error(herr)
				// 	he = herr
			}
		}
	} else {
		he = &errs.Error{
			Code:    http.StatusInternalServerError,
			Message: http.StatusText(http.StatusInternalServerError),
		}
	}

	code := he.Code
	message := he.Message
	if m, ok := message.(string); ok {
		message = mapI{"message": m}
	}

	// Send response
	if !c.Response().Committed {
		if c.Request().Method == http.MethodHead { // Issue #608
			err = c.NoContent(he.Code)
		} else {
			err = c.JSON(code, message)
		}
		if err != nil {
			log.Error(err)
		}
	}
}

func main() {
	API_VERSION := "/v1/"
	storage.NewConnection(storage.Options{
		DataSourceName:  viper.GetString("storage.pgsql_connection"),
		MaxIdleConns:    50,
		MaxOpenConns:    500,
		ConnMaxLifetime: 2 * time.Minute,
	})

	// Echo instance
	e := echo.New()
	e.HTTPErrorHandler = ErrorHandler

	// Middleware
	e.Use(middleware.Logger())
	e.Use(middleware.Recover())
	e.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins: []string{"*"},
	}))

	// Note: Protected Routes

	// Blog endpoints
	eb := e.Group(API_VERSION + "blog")
	eb.GET("", controllers.GetBlogsHandle)
	eb.POST("", controllers.CreateBlogPostHandle)
	eb.DELETE("/:id", controllers.DeleteBlogPostHandle)
	eb.GET("/:id", controllers.GetBlogPostHandle)
	eb.PATCH("/:id", controllers.UpdateBlogPostHandle)

	// Tournament endpoints
	et := e.Group(API_VERSION + "tournaments")
	et.GET("", controllers.GetTournamentsHandle)
	et.POST("", controllers.CreateTournamentHandle)
	et.DELETE("/:id", controllers.DeleteTournamentPostHandle)
	et.GET("/:id", controllers.GetTournamentHandle)
	et.PATCH("/:id", controllers.UpdateTournamentHandle)

	// Start server
	e.Server.Addr = ":" + os.Getenv("PORT")
	graceful.ListenAndServe(e.Server, 5*time.Second)
}
