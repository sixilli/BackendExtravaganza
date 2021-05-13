use crate::config::DbConn;
use crate::models::response::Response;
use crate::models::blog_repository::{BlogPost, BlogPostJsonReq};
use rocket::http::RawStr;
use rocket::http::Status;
use rocket::response::status;
use rocket_contrib::json::Json;

#[get("/")]
pub fn get_blog_posts(conn: DbConn) -> status::Custom<Json<Vec<BlogPost>>> {
    let response = BlogPost::find_all(&conn);
    status::Custom(
        Status::from_code(200).unwrap(),
        Json(response),
    )
}

#[post("/", format = "json", data = "<blog_post>")]
pub fn insert_blog_post(
    conn: DbConn,
    blog_post: Json<BlogPostJsonReq>,
) -> status::Custom<Json<BlogPost>> {
    let bp: BlogPostJsonReq = blog_post.into_inner();
    
    let response = bp.insert(&conn);
    status::Custom(
        Status::from_code(200).unwrap(),
        Json(response),
    )
}
