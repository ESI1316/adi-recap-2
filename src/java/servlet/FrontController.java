package servlet;

import be.esi.adi2.pizza.business.PizzaBusinessException;
import be.esi.adi2.pizza.business.PizzaFacade;
import be.esi.adi2.pizza.dto.ClientDto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    
                case "disconnect":
                    page = disconnect(request, response);
                    break;
                
                case "orderPizza":
                    page = orderPizza(request, response);
                    break;
                    
                case "endCommand":
                    page = endCommand(request, response);
                    break;
                
                default:
                    page = "WEB-INF/Error/error.jsp";
                    request.setAttribute("error", "ce n'est pas normal d'arriver ici !");
            }
        }
        
       
        request.getRequestDispatcher(page).forward(request, response);
    }

    private String newClient(HttpServletRequest request, HttpServletResponse response) {
        
        String page;
        
        try {
            
            String name = request.getParameter("nom");
            String email = request.getParameter("email");
            String address = request.getParameter("adresse");
           
            if (name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() &&
                address != null && !address.isEmpty())
            {
                // Si un client existe déjà avec l'email donné, c'est l'id de ce client
                // qui sera retourné par la méthode saveIfNotExist
                int id = PizzaFacade.saveIfNotExistClient(name, email, address);
                request.setAttribute("message", "Votre ID est : " + id);
            }
          
            page = "WEB-INF/message.jsp";
            
        } catch (Exception ex) {
            page = "WEB-INF/Error/error.jsp";
            request.setAttribute("error", ex.getMessage());
        }
        
        return page;
    }

    private String loginId(HttpServletRequest request, HttpServletResponse response) {
        
        String page;
        
        try 
        {
            int id = Integer.parseInt(request.getParameter("id"));
            ClientDto client = PizzaFacade.getClient(id);
            request.getSession().setAttribute("client", client);
            request.setAttribute("message", "Vous êtes connecté !");
            page = "WEB-INF/message.jsp";
        } 
        catch (PizzaBusinessException ex)
        {
            page = "WEB-INF/Error/error.jsp";
            request.setAttribute("error", ex.getMessage());
        } 
        catch (NumberFormatException nfe)
        {
            page = "WEB-INF/connexion.jsp";
            request.setAttribute("invalid-id", "ID invalide"); 
        }
        
        return page;
    }

    
    private String disconnect(HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().removeAttribute("client");
        return "WEB-INF/accueil.jsp";
    }
    
    private String loginEmail(HttpServletRequest request, HttpServletResponse response) {
        
        String page;
        
        try {
            
            String email = request.getParameter("email");
            ClientDto client = PizzaFacade.getClient(email);
            request.getSession().setAttribute("client", client);
            
            request.setAttribute("message", "Vous êtes connecté !");
            page = "WEB-INF/message.jsp";
            
        } catch (PizzaBusinessException ex) {
            page = "WEB-INF/Error/error.jsp";
            request.setAttribute("error", ex.getMessage());
        }
        
        return page;
    }

    private String showPizzas(HttpServletRequest request, HttpServletResponse response) {
        String page;
        
        try {
            // Assigne, si nécessaire (si null car n'a pas déjà été fait), 
            // la liste des pizzas persistées à l'attribut de session "pizzas", 
            if (request.getSession().getAttribute("pizzas") == null) 
                request.getSession().setAttribute("pizzas", PizzaFacade.getPizza());
            
            // Assigne, si nécessaire (si null car n'a pas déjà été fait),
            // la liste des garnitures persistées à l'attribut de session "toppings" 
            if (request.getSession().getAttribute("toppings") == null) 
                request.getSession().setAttribute("toppings", PizzaFacade.getToppings());
            
            
            page = "WEB-INF/Pizzeria/pizzas.jsp";
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            page = "WEB-INF/Error/error.jsp";
        }
        return page;
    }

    private String orderPizza(HttpServletRequest request, HttpServletResponse response) {
        String page  = "WEB-INF/Pizzeria/pizzas.jsp";
        return page;
    }

    private String showOrder(HttpServletRequest request, HttpServletResponse response) {
        
        String page = "WEB-INF/orders.jsp";
        ClientDto client = (ClientDto) request.getSession().getAttribute("client");
        
        if (client != null)
        {
            try 
            {
                request.setAttribute("orders", 
                        PizzaFacade.getPendingOrder(client.getClientId()));
            } 
            catch (PizzaBusinessException ex)
            {
                request.setAttribute("error", ex.getMessage());
                page = "WEB-INF/Error/error.jsp";
            }
        }
        return page;
    }

    private String showHistory(HttpServletRequest request, HttpServletResponse response) {
        
        String page = "WEB-INF/history.jsp";
        ClientDto client = (ClientDto) request.getSession().getAttribute("client");
        
        if (client != null)
        {
            try 
            {
                request.setAttribute("historique", 
                        PizzaFacade.getNotPendingOrders(client.getClientId()));
            } 
            catch (PizzaBusinessException ex)
            {
                request.setAttribute("error", ex.getMessage());
                page = "WEB-INF/Error/error.jsp";
            }
        }
        return page;
    }
    
     private String endCommand(HttpServletRequest request, HttpServletResponse response) {
        
        String page;
        ClientDto client = (ClientDto) request.getSession().getAttribute("client");

        try 
        {
            PizzaFacade.setNotPending(client.getClientId());
            page = showOrder(request, response);
        } 
        catch (PizzaBusinessException ex)
        {
            request.setAttribute("error", ex.getMessage());
            page = "WEB-INF/Error/error.jsp";
        }
        
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
