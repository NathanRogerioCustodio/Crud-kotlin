class ProdutoDAO(private val database: Database) {

    fun criar(produto: Produto) {
        val sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)"
        database.connect().use { connection ->
            connection.prepareStatement(sql).use { statement ->
                statement.setString(1, produto.nome)
                statement.setDouble(2, produto.preco)
                statement.executeUpdate()
                println("Produto '${produto.nome}' criado com sucesso!")
            }
        }
    }

    fun listarTodos(): List<Produto> {
        val sql = "SELECT * FROM produtos"
        val produtos = mutableListOf<Produto>()

        database.connect().use { connection ->
            connection.createStatement().use { statement ->
                val resultado = statement.executeQuery(sql)
                while (resultado.next()) {
                    produtos.add(
                        Produto(
                            id = resultado.getInt("id"),
                            nome = resultado.getString("nome"),
                            preco = resultado.getDouble("preco")
                        )
                    )
                }
            }
        }
        return produtos
    }

    fun atualizar(produto: Produto) {
        val sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?"
        database.connect().use { connection ->
            connection.prepareStatement(sql).use { statement ->
                statement.setString(1, produto.nome)
                statement.setDouble(2, produto.preco)
                statement.setInt(3, produto.id)
                val itemAtualizado = statement.executeUpdate()
                if (itemAtualizado > 0) {
                    println("Produto '${produto.nome}' atualizado com sucesso!")
                } else {
                    println("Produto com ID ${produto.id} não encontrado.")
                }
            }
        }
    }

    fun deletar(id: Int) {
        val sql = "DELETE FROM produtos WHERE id = ?"
        database.connect().use { connection ->
            connection.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                val itemDeletado = statement.executeUpdate()
                if (itemDeletado > 0) {
                    println("Produto com ID $id deletado com sucesso!")
                } else {
                    println("Produto com ID $id não encontrado.")
                }
            }
        }
    }
}
