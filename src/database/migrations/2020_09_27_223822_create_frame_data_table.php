<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateFrameDataTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('frame_data', function (Blueprint $table) {
            $table->id();
            $table->string('command');
            $table->string('hit_level');
            $table->string('damage');
            $table->string('start_up_frame');
            $table->integer('block_frame');
            $table->integer('hit_frame');
            $table->integer('counter_hit_frame');
            $table->string('notes');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('frame_data');
    }
}
