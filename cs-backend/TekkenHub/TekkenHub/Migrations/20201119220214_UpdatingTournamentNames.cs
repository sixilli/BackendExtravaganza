using Microsoft.EntityFrameworkCore.Migrations;

namespace TekkenHub.Migrations
{
    public partial class UpdatingTournamentNames : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "title",
                table: "Tournaments",
                newName: "Title");

            migrationBuilder.RenameColumn(
                name: "region",
                table: "Tournaments",
                newName: "Region");

            migrationBuilder.RenameColumn(
                name: "location",
                table: "Tournaments",
                newName: "Location");

            migrationBuilder.RenameColumn(
                name: "description",
                table: "Tournaments",
                newName: "Description");

            migrationBuilder.RenameColumn(
                name: "attendees",
                table: "Tournaments",
                newName: "Attendees");

            migrationBuilder.RenameColumn(
                name: "updated_at",
                table: "Tournaments",
                newName: "UpdatedAt");

            migrationBuilder.RenameColumn(
                name: "stream_link",
                table: "Tournaments",
                newName: "StreamLink");

            migrationBuilder.RenameColumn(
                name: "event_link",
                table: "Tournaments",
                newName: "EventLink");

            migrationBuilder.RenameColumn(
                name: "created_at",
                table: "Tournaments",
                newName: "CreatedAt");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "Title",
                table: "Tournaments",
                newName: "title");

            migrationBuilder.RenameColumn(
                name: "Region",
                table: "Tournaments",
                newName: "region");

            migrationBuilder.RenameColumn(
                name: "Location",
                table: "Tournaments",
                newName: "location");

            migrationBuilder.RenameColumn(
                name: "Description",
                table: "Tournaments",
                newName: "description");

            migrationBuilder.RenameColumn(
                name: "Attendees",
                table: "Tournaments",
                newName: "attendees");

            migrationBuilder.RenameColumn(
                name: "UpdatedAt",
                table: "Tournaments",
                newName: "updated_at");

            migrationBuilder.RenameColumn(
                name: "StreamLink",
                table: "Tournaments",
                newName: "stream_link");

            migrationBuilder.RenameColumn(
                name: "EventLink",
                table: "Tournaments",
                newName: "event_link");

            migrationBuilder.RenameColumn(
                name: "CreatedAt",
                table: "Tournaments",
                newName: "created_at");
        }
    }
}
