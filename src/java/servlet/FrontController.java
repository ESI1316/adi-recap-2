package servlet;

import be.esi.adi2.pizza.business.PizzaFacade;
import be.esi.adi2.pizza.dto.ClientDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author aro
 */
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String cible = request.getParameter("cible");
        String page;
        
        if (cible != null)
        {
            switch (cible)
            {
                case "nouveau":
                    page = "WEB-INF/nouveau.jsp";
                    break;
                
                case "login":
                    page = "WEB-INF/connexion.jsp";
                    break;
                
                case "accueil":
                    page = "WEB-INF/accueil.jsp";
                    break;
                
                case "commandes":
                    page = showOrder(request, response);
                    break;
                
                case "pizzas": 
                    page = showPizzas(request, response);
                    break;
                    
                case "historique":
                    page = showHistory(request, response);
                    break;
                
                default:
                    page = "WEB-INF/Error/error.jsp";
                    request.setAttribute("error", "ce n'est pas normal d'arriver ici !");
            }
        }
        else if (action == null)
        {
            page = "WEB-INF/accueil.jsp";
        }
        else
        {
            switch (action)
            {
                case "newClient":
                    page = newClient(request, response);
                    break;
                    
                case "loginId":
                    page = loginId(request, response);
                    break;
                    
                case "loginEmail":
                    page = loginEmail(request, response);
                    break;
                
                case "showPizzas":
                    page = showPizzas(request, response);
                    break;
                
                case "orderPizza":
                    page = orderPizza(request, response);
                    break;
                
                case "showOrder":
                    page = showOrder(request, response);
                    break;
                
                case "showHistory":
                    page = showHistory(request, response);
                    break;
                    
                default:
                    page = "WEB-INF/Error/error.jsp";
                    request.setAttribute("error", "ce n'est pas normal d'arriver ici !");
            }
        }
        
       
        request.getRequestDispatcher(page).forward(request, response);
    }

    private String newClient(HttpServletRequest request, HttpServletResponse response) {
        
        
        String page="";
        
        try {
            String name = "nom du client"; // A modifier
            String email = "email du client"; // A modifier
            String address = "adresse du client"; // A modifier
            // Si un client existe déjà avec l'email donné, c'est l'id de ce client
            // qui sera retourné par la méthode saveIfNotExist
            int id = PizzaFacade.saveIfNotExistClient(name, email, address);
            request.getSession().setAttribute("clientid", id);
            
            page = "WEB-INF/nouveau.jsp";
        } catch (Exception ex) {
            page = "WEB-INF/Error/error.jsp";
            request.setAttribute("error", ex.getMessage());
        }
        return page;
    }

    private String loginId(HttpServletRequest request, HttpServletResponse response) {
        String page="";
        try {
            int id = 0; // A modifier
            ClientDto client = PizzaFacade.getClient(id);
            // A compléter
        } catch (Exception ex) {
            page = "WEB-INF/Error/error.jsp";
            request.setAttribute("error", ex.getMessage());
        }
        return page;
    }

    private String loginEmail(HttpServletRequest request, HttpServletResponse response) {
        String page = "";
        try {
            String email = "email du client"; // A modifier
            ClientDto client = PizzaFacade.getClient(email);
            // A compléter
        } catch (Exception ex) {
            page = "WEB-INF/Error/error.jsp";
            request.setAttribute("error", ex.getMessage());
        }
        return page;
    }

    private String showPizzas(HttpServletRequest request, HttpServletResponse response) {
        String page="";
        try {
            // Assigne, si nécessaire (si null car n'a pas déjà été fait), 
            // la liste des pizzas persistées à l'attribut de session "pizzas", 
            if (request.getSession().getAttribute("pizzas") == null) {
                request.getSession().setAttribute("pizzas", PizzaFacade.getPizza());
            }
            // Assigne, si nécessaire (si null car n'a pas déjà été fait),
            // la liste des garnitures persistées à l'attribut de session "toppings" 
            if (request.getSession().getAttribute("toppings") == null) {
                request.getSession().setAttribute("toppings", PizzaFacade.getToppings());
            }
            // A compléter
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            page = "WEB-INF/Error/error.jsp";
        }
        return page;
    }

    private String orderPizza(HttpServletRequest request, HttpServletResponse response) {
        String page = "WEB-INF/Pizzeria/todo.jsp";
        request.setAttribute("todo", "orderPizza");
        return page;
    }

    private String showOrder(HttpServletRequest request, HttpServletResponse response) {
        String page = "WEB-INF/Pizzeria/todo.jsp";
        request.setAttribute("todo", "showOrder");
        return page;
    }

    private String showHistory(HttpServletRequest request, HttpServletResponse response) {
        String page = "WEB-INF/Pizzeria/todo.jsp";
        request.setAttribute("todo", "showHistory");
        return page;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
