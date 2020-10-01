package com.shanemaglangit.jobjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "action_history_table")
data class ActionHistory (
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var detail: String = "",
    var dateCreated: Date = Date(System.currentTimeMillis())
)