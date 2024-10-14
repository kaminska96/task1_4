package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello_servlet")
public class Hello_servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("name");

        if (userName == null || userName.trim().isEmpty()) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write("Please, provide your name!"); 
            out.flush();
            out.close();
            return;
        }

        String greetingMessage = "Hello, " + userName + "!" + " You can find out about a country by selecting it in the Countries list or by typing its ISO code or full name in the search bar. <p style='color: red;'> Be careful: The country's ISO code is capitalised!</p>";

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(greetingMessage); 
        out.flush();
        out.close();
    }
}

