using System;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TekkenHub.Models;

namespace TekkenHub.Controllers
{
    [ApiController]
    [Route("tournament")]
    public class TournamentController : ControllerBase
    {
        private readonly TekkenHubContext _db;

        public TournamentController(TekkenHubContext context)
        {
            _db = context;
        }
        
        [HttpGet("")]
        public async Task<IActionResult> Get()
        {
            var res = await _db.Tournaments.ToListAsync();
            return Ok(res);
        }
        
        [HttpGet("{id}")]
        public async Task<IActionResult> GetSingle(int id)
        {
            if (id <= 0)
            {
                return NotFound();
            }
            var res = await _db.Tournaments
                .SingleAsync(row => row.id == id);

            if (res == null)
            {
                return NotFound();
            }
            
            return Ok(res);
        }
        
        [HttpPost("")]
        public async Task<IActionResult> Post()
        {
            var res = _db.Tournaments;
                return Ok("Tournament");
        }

        [HttpPatch("{id}")]
        public async Task<IActionResult> Update(int id)
        {
            var res = _db.Tournaments
                .Single(row => row.id == id);
            return Ok("Tournament");
        }
    }
}