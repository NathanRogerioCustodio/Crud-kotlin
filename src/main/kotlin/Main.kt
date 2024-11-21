fun main() {
    val database = Database()
    database.init() // Inicializa o banco de dados

    val produtoDAO = ProdutoDAO(database)

    // Menu simples
    while (true) {
        println("\n--- Menu ---")
        println("1. Criar Produto")
        println("2. Listar Produtos")
        println("3. Atualizar Produto")
        println("4. Deletar Produto")
        println("5. Sair")
        print("Escolha uma opção: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> {
                print("Digite o nome do produto: ")
                val nome = readlnOrNull() ?: ""
                print("Digite o preço: ")
                val preco = readlnOrNull()?.toDoubleOrNull() ?: 0.0
                produtoDAO.criar(Produto(nome = nome, preco = preco))
            }
            2 -> {
                val produtos = produtoDAO.listarTodos()
                if (produtos.isEmpty()) {
                    println("Nenhum produto encontrado.")
                } else {
                    produtos.forEach { println(it) }
                }
            }
            3 -> {
                print("Digite o ID do produto para atualizar: ")
                val id = readlnOrNull()?.toIntOrNull()
                if (id != null) {
                    print("Digite o novo nome: ")
                    val nome = readlnOrNull() ?: ""
                    print("Digite o novo preço: ")
                    val preco = readlnOrNull()?.toDoubleOrNull() ?: 0.0
                    produtoDAO.atualizar(Produto(id, nome, preco))
                } else {
                    println("ID inválido.")
                }
            }
            4 -> {
                print("Digite o ID do produto para deletar: ")
                val id = readlnOrNull()?.toIntOrNull()
                if (id != null) {
                    produtoDAO.deletar(id)
                } else {
                    println("ID inválido.")
                }
            }
            5 -> {
                println("Saindo...")
                break
            }
            else -> println("Opção inválida.")
        }
    }
}
