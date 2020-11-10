
package fr.aylan.provider
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import java.util.*

class FAQQuestionProvider : ContentProvider() {
    // database declarations
    private var dbHelper: DatabaseManager? = null
    private var database: SQLiteDatabase? = null

    companion object {
        // fields for my content provider
        const val PROVIDER_NAME = "fr.aylan.provider.FAQQuestion"
        const val URL = "content://" + PROVIDER_NAME + "/questions"
        val CONTENT_URI = Uri.parse(URL)

        // fields for the database table birthday
        const val TABLE_NAME = "FAQQuestion"
        const val ID = "id"
        const val QUESTION = "question"
        const val QUESTION_URL = "answer"

        // integer values used in content Uri
        const val QUESTIONS = 1
        const val QUESTIONS_ID = 2

        // projection map for a query
        private val QuestionMap: HashMap<String, String>? = null

        // maps content URI "patterns" to the integer values that were set above
        var uriMatcher: UriMatcher? = null

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher?.addURI(PROVIDER_NAME, "questions", QUESTIONS)
            uriMatcher?.addURI(PROVIDER_NAME, "questions/#", QUESTIONS_ID)
        }
    }

    override fun onCreate(): Boolean {
        val context = context
        dbHelper = DatabaseManager(context)
        // permissions to be uritable
        database = dbHelper!!.writableDatabase
        return database != null
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        var sortOrder = sortOrder
        val queryBuilder = SQLiteQueryBuilder()
        // the table_name to query on
        queryBuilder.tables = TABLE_NAME
        when (uriMatcher!!.match(uri)) {
            QUESTIONS -> queryBuilder.projectionMap = QuestionMap
            QUESTIONS_ID -> queryBuilder.appendWhere(ID + "=" + uri.lastPathSegment)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        if (sortOrder == null || sortOrder === "") {
            // No sorting-> sort on names by default
            sortOrder = QUESTION
        }
        val cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder)
        /**
         * register to match a content URI for changes
         */
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val row = database!!.insert(TABLE_NAME, "", values)

        // if record is added successfully
        if (row > 0) {
            val newUri = ContentUris.withAppendedId(CONTENT_URI, row)
            context!!.contentResolver.notifyChange(newUri, null)
            return newUri
        }
        throw SQLException("Fail to add new record into$uri")
    }

    override fun update(uri: Uri, values: ContentValues?,
                        selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?,
                        selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher!!.match(uri)) {
            QUESTIONS -> "vnd.android.cursor.dir/vnd.questionmobapp.questions"
            QUESTIONS_ID -> "vnd.android.cursor.item/vnd.questionmobapp.questions"
            else -> throw IllegalArgumentException("Unsuported URI: $uri")
        }
    }
}