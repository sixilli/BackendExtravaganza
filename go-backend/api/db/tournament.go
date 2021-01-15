package db

import (
	"log"

	"github.com/sixilli/TekkenHub/api/models"
	storage "github.com/sixilli/TekkenHub/storage/postgres"
)

func CreateTournament(t models.Tournament) (models.Tournament, error) {
	tournament := models.Tournament{}
	log.Printf("%+v", t)
	query := `INSERT INTO tournaments
				(title, description, attendees, region, location, event_link, stream_link)
			  VALUES
			  	($1, $2, $3, $4, $5, $6, $7)
			  RETURNING *;`
	err := storage.DB.Get(&tournament, query, t.Title, t.Description, t.Attendees, t.Region, t.Location, t.EventLink, t.StreamLink)
	log.Printf("%+v", tournament)
	if err != nil {
		return t, err
	}

	return tournament, nil
}

func GetTournament(id int64) (models.Tournament, error) {
	tournament := models.Tournament{}
	query := `SELECT * FROM tournaments WHERE id = $1`
	err := storage.DB.Get(&tournament, query, id)
	if err != nil {
		return tournament, err
	}

	return tournament, nil
}

func GetAllTournaments() ([]models.Tournament, error) {
	tournaments := []models.Tournament{}
	query := `SELECT * FROM tournaments`
	err := storage.DB.Select(&tournaments, query)
	if err != nil {
		return tournaments, err
	}

	return tournaments, nil
}
