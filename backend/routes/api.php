<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Models\Tournament;
use App\Http\Controllers\TournamentController;

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
Route::apiResource('/tournaments', TournamentController::class);

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
