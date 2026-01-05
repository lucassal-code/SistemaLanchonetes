package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.DadosCliente;
import model.ServicosLanchonete;

//essa parte tá bagunçada mas é o que eu consegui fazer

@WebServlet("/login") //servlet indicando a pagina index (login)
//se tudo tiver certo, ao escrever no url "/login", ele vai redirecionar pro index
public class RequestLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //abre a página de login, redirecionando pra pagina index
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        ServicosLanchonete service = new ServicosLanchonete();

        //método que você VAI criar (veja abaixo)
        DadosCliente cliente = service.buscarClientePorEmailESenha(email, senha);

        if (cliente != null) {
            // login OK → salva na sessão
            req.getSession().setAttribute("clienteLogado", cliente);

            resp.sendRedirect("home.xhtml");
        } else {
            // login inválido
            resp.sendRedirect("index.html?erro=true");
        }
    }
}
