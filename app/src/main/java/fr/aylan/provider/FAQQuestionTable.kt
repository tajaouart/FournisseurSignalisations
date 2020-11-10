package fr.aylan.provider

import android.database.sqlite.SQLiteDatabase

object FAQQuestionTable {
    // Database table
    const val TABLE_NAME = "FAQQuestion"
    const val ID = "id"
    const val QUESTION = "question"
    const val ANSWER = "answer"
    const val CREATE_TABLE = (" CREATE TABLE "
            + TABLE_NAME
            + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + QUESTION + " TEXT NOT NULL, "
            + ANSWER + " TEXT NOT NULL "
            + " ); ")

    @JvmStatic
    fun onCreate(database: SQLiteDatabase) {
        database.execSQL(CREATE_TABLE)
    }

    @JvmStatic
    fun onUpgrade(database: SQLiteDatabase, oldVersion: Int,
                  newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(database)
    }
}