package com.example.studikasusroom

/*
    Kelas ini adalah abstraksi dari database Room.
    - Menggabungkan entitas dan DAO yang tersedia.
    - Menggunakan singleton pattern untuk memastikan hanya ada satu instance database dalam aplikasi.
*/

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    // Fungsi abstrak yang memberikan instance DAO.
    abstract fun wordDao(): WordDao

    companion object {
        // Variabel INSTANCE menyimpan instance database tunggal.
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        /*
            Fungsi ini membuat atau mendapatkan instance database.
            - Singleton pattern memastikan hanya ada satu instance yang aktif.
            - synchronized digunakan untuk menghindari konflik multithreading.
        */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        /*
            Callback digunakan untuk mempersiapkan database saat pertama kali dibuat.
            - Misalnya, untuk mengisi data awal.
            - CoroutineScope memungkinkan menjalankan operasi asinkron saat inisialisasi.
        */
        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Anda bisa menambahkan data awal di sini jika diperlukan.
            }
        }
    }
}
