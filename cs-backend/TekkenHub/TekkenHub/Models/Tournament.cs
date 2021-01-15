using System;

namespace TekkenHub.Models
{
    public class Tournaments
    {
        public Int64 id { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public int Attendees { get; set; }
        public string Region { get; set; }
        public string Location { get; set; }
        public string EventLink { get; set; }
        public string StreamLink { get; set; }
        public DateTime CreatedAt { get; set; }
        public DateTime UpdatedAt { get; set; }
    }
}