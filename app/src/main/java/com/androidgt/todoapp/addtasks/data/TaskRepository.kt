package com.androidgt.todoapp.addtasks.data

import com.androidgt.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    //devuelve una lista de taskEntity lo que queremos es una lista de taskModel.
    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map {
        //mapeo de taskEntity a taskModel
        items ->
        items.map {
            // creamos un taskModel a partir de taskEntity
            TaskModel(it.id, it.task, it.selected)
        }
    }

    //necesitamos el dao para hacer las consoltas a la database.
    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }

}