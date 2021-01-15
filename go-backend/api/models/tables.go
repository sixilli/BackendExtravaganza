package models

import "time"

type BlogPost struct {
	ID        uint64    `db:"id" json:"id"`
	Title     string    `db:"title" json:"title"`
	Author    string    `db:"author" json:"author"`
	Category  int       `db:"category" json:"category"`
	Link      string    `db:"link" json:"link"`
	Text      string    `db:"text" json:"text"`
	CreatedAt time.Time `db:"created_at" json:"created_at"`
	UpdatedAt time.Time `db:"updated_at" json:"updated_at"`
}

type Tournament struct {
	ID          uint64    `db:"id" json:"id"`
	Title       string    `db:"title" json:"title"`
	Description string    `db:"description" json:"description"`
	Attendees   int       `db:"attendees" json:"attendees"`
	Region      string    `db:"region" json:"region"`
	Location    string    `db:"location" json:"location"`
	EventLink   string    `db:"event_link" json:"event_link"`
	StreamLink  string    `db:"stream_link" json:"stream_link"`
	StartTime   time.Time `db:"start_time" json:"start_time"`
	CreatedAt   time.Time `db:"created_at" json:"created_at"`
	UpdatedAt   time.Time `db:"updated_at" json:"updated_at"`
}

// Fills in old data if users doesn't add it in their request.
func(bp *BlogPost) Merge(oldBp BlogPost) {
	if bp.Title == "" {
		bp.Title = oldBp.Title
	}

	if bp.Author == "" {
		bp.Author = oldBp.Author
	}

	if bp.Category == 0 {
		bp.Category = oldBp.Category
	}

	if bp.Link == "" {
		bp.Link = oldBp.Link
	}

	if bp.Text == "" {
		bp.Text = oldBp.Text
	}
}

func(t *Tournament) Merge(oldT Tournament) {
	if t.Title == "" {
		t.Title = oldT.Title
	}

	if t.Description == "" {
		t.Description = oldT.Description
	}

	if t.Attendees == 0 {
		t.Attendees = oldT.Attendees
	}

	if t.Region == "" {
		t.Region = oldT.Region
	}

	if t.EventLink == "" {
		t.EventLink = oldT.EventLink
	}

	if t.StreamLink == "" {
		t.StreamLink = oldT.StreamLink
	}
}
