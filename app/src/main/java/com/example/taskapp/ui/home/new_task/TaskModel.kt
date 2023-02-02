package com.example.taskapp.ui.home.new_task

import androidx.room.*

data class TaskModel(
    val title: String,
    val description: String? = null,
    val pictureUri: String? = null
)

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "desc")
    val desc: String? = null,
    @ColumnInfo(name = "picture_uri")
    val pictureUri: String? = null,

)

@Dao
interface TaskDao {

    @Insert
    fun insert(task: TaskEntity)

    @Query("SElECT * FROM tasks")
    fun getAll() : List<TaskEntity>
}