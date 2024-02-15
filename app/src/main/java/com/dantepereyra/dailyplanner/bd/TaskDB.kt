package com.dantepereyra.dailyplanner.bd

import android.provider.BaseColumns

object TaskDBScheme: BaseColumns {
    const val TABLE_NAME = "tasks"
    const val COLUMN_NAME = "name"
    const val COLUMN_DESCRIPTION = "description"

}

const val SQL_CREATE_ENTRIES = """
CREATE TABLE ${TaskDBScheme.TABLE_NAME}(
${TaskDBScheme.COLUMN_NAME} TEXT PRIMARY KEY,
${TaskDBScheme.COLUMN_DESCRIPTION} TEXT,

)
"""

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST ${TaskDBScheme.TABLE_NAME}"

