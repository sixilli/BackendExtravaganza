use crate::config::DbConn;
use crate::schema::blog_posts;
use diesel::PgConnection;
use diesel::{prelude::*, sql_query};
use diesel::sql_types::{Varchar, Integer};

const TABLE_NAME: &str = "blog_posts";

#[derive(Queryable, AsChangeset, Serialize, Deserialize, QueryableByName, Insertable)]
#[table_name="blog_posts"]
pub struct BlogPost {
    pub id: i64,
    pub title: String,
    pub author: String,
    pub category: i32,
    pub link: String,
    pub text: String,
    pub created_at: chrono::NaiveDateTime,
    pub updated_at: chrono::NaiveDateTime,
}

impl BlogPost {
    pub fn find_all(conn: &PgConnection) -> Vec<BlogPost> {
        let query = format!("SELECT * FROM {}", TABLE_NAME);
        let resp: Vec<BlogPost> = sql_query(query).get_results(conn).unwrap();
        println!("{:?} {:?}", resp[0].id, resp[0].title);
        resp
    }

    pub fn find_one(conn: &PgConnection, id: &i64) -> BlogPost {
        let query = format!("SELECT * FROM {} WHERE id = {}", TABLE_NAME, id);
        sql_query(query).get_result(conn).expect(format!("Error couldn't find blogpost with ID {}", id).as_str())
    }

     
}
#[derive(Queryable, AsChangeset, Serialize, Deserialize, QueryableByName, Insertable)]
#[table_name="blog_posts"]
pub struct BlogPostJsonReq {
    pub title: String,
    pub author: String,
    pub category: i32,
    pub link: String,
    pub text: String,
}

impl BlogPostJsonReq {
    pub fn insert(&self, conn: &PgConnection) -> BlogPost {
        let query = "INSERT INTO blog_posts (title, author, category, text, link)
                          VALUES ($1, $2, $3, $4, $5)
                          RETURNING *;"; 
        sql_query(query)
            .bind::<Varchar, _>(&self.title)
            .bind::<Varchar, _>(&self.author)
            .bind::<Integer, _>(&self.category)
            .bind::<Varchar, _>(&self.link)
            .bind::<Varchar, _>(&self.text)
            .get_result(conn)
            .expect("error with blog post insert")
    }
}