function filtrarTabela() {
        const filtroProduto = document.getElementById("filtro-produto").value.toLowerCase();
        const filtroCategoria = document.getElementById("filtro-categoria").value.toLowerCase();
        const filtroData = document.getElementById("filtro-data").value;

        const linhas = document.querySelectorAll("#tabela-estoque tr");

        linhas.forEach(linha => {
            const colunas = linha.querySelectorAll("td");

            const produto = colunas[0]?.textContent.toLowerCase();
            const categoria = colunas[1]?.textContent.toLowerCase();
            const data = colunas[2]?.textContent;

            const exibirProduto = !filtroProduto || produto.includes(filtroProduto);
            const exibirCategoria = !filtroCategoria || categoria.includes(filtroCategoria);
            const exibirData = !filtroData || data === formatarDataBR(filtroData);

            if (exibirProduto && exibirCategoria && exibirData) {
                linha.style.display = "";
            } else {
                linha.style.display = "none";
            }
        });
    }

    function limparFiltros() {
        document.getElementById("filtro-produto").value = "";
        document.getElementById("filtro-categoria").value = "";
        document.getElementById("filtro-data").value = "";
        filtrarTabela();
    }

    function formatarDataBR(dataISO) {
        const partes = dataISO.split("-");
        return `${partes[2]}/${partes[1]}/${partes[0]}`;
    }