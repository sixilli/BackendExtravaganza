<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Models\Tournament;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

// Tournament Endpoints
Route::get('/tournaments', function () {
    $tournament = Tournament::create([
        'title' => 'Tekken World Tour!',
        'description' => 'King of the iron fist tournament',
        'attendees' => 64,
        'region' => 'NA lul',
        'location' => 'Los Angeles, California',
        'link' => 'TekkenHub.com',
        'start_time' => Carbon\Carbon::now()
    ]);

    return $tournament;
});

Route::get('/tournament/results/$id', function () {
    return ['message' => 'hello'];
});

// Players
Route::get('/players', function () {
    return ['message' => 'hello'];
});

// Frame data

// Character Guides / Resources / Discords
// Discords
Route::get('/discords', function () {
    return ['message' => 'hello'];
});

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
