using System;

namespace TekkenHub.Models
{
    public class BlogPost
    {
        public Int64 Id { get; set; } 
        public string Title { get; set; }
        public string Author { get; set; }
        public BlogCategory Category { get; set; }
        public string Text  { get; set; }
        public string Link { get; set; }
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
        public DateTime UpdatedAt { get; set; } = DateTime.UtcNow;
    }
}