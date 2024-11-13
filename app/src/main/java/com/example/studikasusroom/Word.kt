package com.example.studikasusroom

/*
    Kelas ini merepresentasikan tabel dalam database Room.
    - @Entity digunakan untuk memberi tahu Room bahwa kelas ini adalah entitas database.
    - `tableName` mendefinisikan nama tabel yang akan dibuat di SQLite.
    - Field dalam kelas ini akan menjadi kolom dalam tabel.
    - Room membutuhkan primary key untuk setiap tabel, yang ditentukan dengan anotasi @PrimaryKey.
*/

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class 5 Word(
    /*
        Kolom 'word' adalah Primary Key untuk tabel.
        - Field ini tidak akan null karena Room menggunakan nilai ini untuk identifikasi unik.
        - autoGenerate = false, sehingga nilai key harus diberikan oleh pengembang.
    */
    @PrimaryKey val word: String
)
