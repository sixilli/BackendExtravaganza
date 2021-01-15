module github.com/sixilli/TekkenHub

go 1.14

require (
	github.com/jmoiron/sqlx v1.2.0
	github.com/labstack/echo v3.3.10+incompatible
	github.com/labstack/gommon v0.3.0
	github.com/lib/pq v1.9.0
	github.com/sirupsen/logrus v1.7.0
	github.com/spf13/viper v1.7.1
	github.com/tylerb/graceful v1.2.15
	github.com/valyala/fasttemplate v1.2.1 // indirect
)

replace github.com/sixilli/TekkenHub/storage => ./storage
