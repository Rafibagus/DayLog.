package org.d3if0075.daylog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if0075.daylog.model.Catatan
import org.d3if0075.daylog.model.User

@Database(entities = [User::class, Catatan::class], version = 1, exportSchema = false)
abstract class DaylogDb:RoomDatabase() {
    abstract val dao: UserDao
    abstract val catatanDao: CatatanDao

    companion object {
        @Volatile
        private var INSTANCE: DaylogDb? = null

        fun getInstance(context: Context): DaylogDb {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DaylogDb::class.java,
                        "daylog.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}