package com.example.studikasusroom

/*
    Interface ini berfungsi untuk mendefinisikan operasi database.
    - DAO mengabstraksi operasi SQL untuk mempermudah interaksi dengan database Room.
    - Setiap metode DAO akan diimplementasikan oleh Room secara otomatis.
*/

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    /*
        Fungsi ini digunakan untuk mengambil seluruh data dari tabel 'word_table'.
        - @Query adalah anotasi untuk menulis perintah SQL langsung.
        - Flow digunakan untuk mendapatkan data secara reaktif, sehingga UI akan diperbarui secara otomatis saat data berubah.
    */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    /*
        Fungsi ini digunakan untuk menambahkan data baru ke tabel.
        - @Insert adalah anotasi yang menangani proses SQL INSERT.
        - Operasi ini dilakukan secara asinkron menggunakan coroutine.
    */
    @Insert
    suspend fun insert(word: Word)

    /*
        Fungsi ini digunakan untuk menghapus seluruh data dari tabel.
        - @Query dengan perintah DELETE digunakan untuk mengosongkan tabel.
        - Operasi ini juga dilakukan secara asinkron untuk mencegah blocking UI.
    */
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}
