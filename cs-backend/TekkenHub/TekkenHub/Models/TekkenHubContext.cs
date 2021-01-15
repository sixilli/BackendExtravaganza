using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

namespace TekkenHub.Models
{
    public class TekkenHubContext : DbContext
    {
        public TekkenHubContext(DbContextOptions<TekkenHubContext> options) : base(options) { }
        
        public DbSet<BlogPost> BlogPost { get; set; }
        public DbSet<Tournaments> Tournaments { get; set; }
    }
}