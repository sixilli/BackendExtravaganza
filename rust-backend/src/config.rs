use crate::api::blog_controllers::*;
use crate::api::tournament_controllers::*;
use diesel::pg::PgConnection;
use rocket::fairing::AdHoc;
use rocket::Rocket;

#[database("postgres_database")]
pub struct DbConn(PgConnection);

pub fn rocket() -> (Rocket, Option<DbConn>) {
    let rocket = rocket::ignite()
        .attach(DbConn::fairing())
        .mount("/blogs", routes![get_blog_posts, insert_blog_post])
        .mount("/tournaments", routes![]);
    
    let conn = if cfg!(test) {
        DbConn::get_one(&rocket)
    } else {
        None
    };

    (rocket, conn)
}
