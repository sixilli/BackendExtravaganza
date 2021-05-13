use crate::config::DbConn;
//use models::person::PersonDTO;
use crate::models::response::Response;
use rocket::http::RawStr;
use rocket::http::Status;
use rocket::response::status;
use rocket_contrib::json::Json;

//#[get("/")]
//pub fn get_tournaments(conn: DbConn) -> status::Custom<Json<Response>> {
    ////let response = tournament_repository::find_all(conn);
    //let response = Response{
        //message: "suh",
        //data: 32
    //}
//
    //status::Custom(
        //Status::from_code(response.status_code).unwrap(),
        //Json(response.response),
    //)
//}