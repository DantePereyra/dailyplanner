package com.dantepereyra.dailyplanner.bd

import android.provider.BaseColumns

object TaskDBScheme: BaseColumns {
    const val TABLE_NAME = "tasks"
    const val COLUMN_ID = "id"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_IS_COMPLETED = "is_completed"
    const val COLUMN_DATE = "date"

}

const val SQL_CREATE_ENTRIES = """
CREATE TABLE ${TaskDBScheme.TABLE_NAME}(
${TaskDBScheme.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
${TaskDBScheme.COLUMN_DESCRIPTION} TEXT,
${TaskDBScheme.COLUMN_IS_COMPLETED} INTEGER DEFAULT 0,
${TaskDBScheme.COLUMN_DATE} INTEGER
)
"""

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TaskDBScheme.TABLE_NAME}"

