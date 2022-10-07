package by.dzmitry_lakisau.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.dzmitry_lakisau.testapp.data.local.dao.PostDao
import by.dzmitry_lakisau.testapp.data.local.dao.UserDao
import by.dzmitry_lakisau.testapp.data.local.dbModel.PostDb
import by.dzmitry_lakisau.testapp.data.local.dbModel.UserDb

@Database(
    version = 1,
    entities = [
        UserDb::class,
        PostDb::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun postDao(): PostDao
}