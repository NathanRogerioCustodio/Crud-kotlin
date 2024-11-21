import java.sql.Connection
import java.sql.DriverManager

class Database {
    private val url = "jdbc:sqlite:produtos.db"

    fun connect(): Connection {
        return DriverManager.getConnection(url)
    }

    fun init() {
        val sql = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                preco REAL NOT NULL
            );
        """.trimIndent()

        connect().use { connection ->
            connection.createStatement().use { statement ->
                statement.execute(sql)
            }
        }
    }
}
