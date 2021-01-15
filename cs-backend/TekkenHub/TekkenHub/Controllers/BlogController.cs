using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TekkenHub.Models;

namespace TekkenHub.Controllers
{
    
    [ApiController]
    [Route("blog")]
    public class BlogController: ControllerBase
    {
        private readonly TekkenHubContext _db;
        
        public BlogController(TekkenHubContext context)
        {
            _db = context;
        }
                
        [HttpGet("")]
        public async Task<IActionResult> Get()
        {
            var res = await _db.BlogPost.ToListAsync();
            return Ok(res);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetSingle(int id)
        {
            var res = await _db.BlogPost
                .SingleAsync(row => row.Id == id);
            
            return Ok(res);
        }
        
        [HttpPost("")]
        public async Task<IActionResult> Post(
            [Bind("Title,Author,Category,Text")] BlogPost post)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    _db.Add(post);
                    await _db.SaveChangesAsync();
                }
            }
            catch (DbUpdateException)
            {
                ModelState.AddModelError("", "Unable to save changes. " +
                         "Try again, if the problem persists contact an admin.");
            }

            return Ok(post);
        }
    }
}