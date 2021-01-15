package api

import (
	"fmt"
	"net/http"
)

type Error struct {
	Code     int         `json:"-"`
	Message  interface{} `json:"message"`
	Internal error       `json:"-"` // Stores the error returned by an external dependency
}

func (he *Error) Error() string {
	if he.Internal == nil {
		return fmt.Sprintf("code=%d, message=%v", he.Code, he.Message)
	}
	return fmt.Sprintf("code=%d, message=%v, internal=%v", he.Code, he.Message, he.Internal)
}

// SetInternal sets error to Error.Internal
func (he *Error) SetInternal(err error) *Error {
	he.Internal = err
	return he
}

func NewError(code int, message ...interface{}) *Error {
	he := &Error{Code: code, Message: http.StatusText(code)}
	if len(message) > 0 {
		he.Message = message[0]
	}
	return he
}
func NewHttpError(err error, code int, message ...interface{}) *Error {
	he := &Error{Code: code, Message: http.StatusText(code), Internal: err}
	if len(message) > 0 {
		he.Message = message[0]
	}
	return he
}

var (
	ErrUnsupportedMediaType        = NewError(http.StatusUnsupportedMediaType)
	ErrNotFound                    = NewError(http.StatusNotFound)
	ErrUnauthorized                = NewError(http.StatusUnauthorized)
	ErrNoPermission                = NewError(http.StatusUnauthorized, "You don't have permission to perform this action.")
	ErrForbidden                   = NewError(http.StatusForbidden)
	ErrMethodNotAllowed            = NewError(http.StatusMethodNotAllowed)
	ErrStatusRequestEntityTooLarge = NewError(http.StatusRequestEntityTooLarge)
	ErrTooManyRequests             = NewError(http.StatusTooManyRequests)
	ErrBadRequest                  = NewError(http.StatusBadRequest)
	ErrBadGateway                  = NewError(http.StatusBadGateway)
	ErrRequestTimeout              = NewError(http.StatusRequestTimeout)
	ErrServiceUnavailable          = NewError(http.StatusServiceUnavailable)
	ErrMissingData                 = NewError(http.StatusBadRequest, "Invalid or missing parameters. Please verify and resubmit.")
	ErrSomethingWrong              = NewError(http.StatusBadRequest, "Sorry, something went wrong. Please try again.")
	ErrInternalServerError         = NewError(http.StatusInternalServerError, "Something went wrong on our end. Please try again later or open a support ticket.")
	ErrPointsDistributed           = NewError(http.StatusBadRequest, "Points have already been distributed.")
	ErrDailyPointsRewardFailed     = NewError(http.StatusBadRequest, "Daily points rewards failed.")
	ErrMissingEventDate            = NewError(http.StatusBadRequest, "Missing or invalid event date.")
	ErrMissingEvent                = NewError(http.StatusBadRequest, "Missing or invalid event.")
	ErrMissingUser                 = NewError(http.StatusBadRequest, "Missing or invalid user.")
	ErrBadgeServiceFailed          = NewError(http.StatusBadRequest, "Badge service failed.")
	ErrRollbackError               = NewError(http.StatusBadRequest, "DB transactional rollback failed.")
	ErrInvalidID                   = NewError(http.StatusBadRequest, "Invalid ID")
)
