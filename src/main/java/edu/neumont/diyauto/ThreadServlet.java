package edu.neumont.diyauto;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.diyauto.Models.ModelAndView;
import edu.neumont.diyauto.diyautoControllers.PostGetController;
import edu.neumont.diyauto.diyautoControllers.PostPostController;
import edu.neumont.diyauto.diyautoControllers.ThreadGetController;
import edu.neumont.diyauto.diyautoControllers.ThreadPostController;

/**
 * Created by jjensen on 5/28/14.
 */
@WebServlet("/threads/*")
public class ThreadServlet extends HttpServlet {

    private static final Pattern P = Pattern.compile("(/threads)");
    private static final Pattern P2 = Pattern.compile("(/threads)(/create)");
    private static final Pattern P3 = Pattern.compile("(/threads/)([A-Za-z]+)");
    private static final Pattern P6 = Pattern.compile("(/threads/)(viewAll)");
    private static final Pattern P5 = Pattern.compile("(/threads/)(all)");
    private static final Pattern P4 = Pattern.compile("(/threads/)([0-9]+)");
    private static final Pattern P7 = Pattern.compile("(/threads/)([0-9]+)(/post/)([0-9]+)");
    private static final Pattern P8 = Pattern.compile("(/threads/)([0-9]+)(/post/)(create)");

    @Inject
    ThreadPostController tpc;

    @Inject
    PostPostController ppc;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView MAV = getURIParser(request, response);

        if(MAV.getModel()!= null)
        {
            request.setAttribute("Model", MAV.getModel());
        }
        RequestDispatcher view = request.getRequestDispatcher(MAV.getViewName());
        view.forward(request, response);

//        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/CreateThread.jsp");
//        view.forward(request, response);
    }
    public ModelAndView getURIParser(HttpServletRequest request, HttpServletResponse response)
    {
        ThreadGetController threadGet = new ThreadGetController();
        PostGetController postGet = new PostGetController();
        String URI = request.getRequestURI();
        ModelAndView MAV = null;
        Matcher match = this.P.matcher(URI);
        Matcher match2 = this.P2.matcher(URI);
        Matcher match3 = this.P3.matcher(URI);
        Matcher match4 = this.P4.matcher(URI);
        Matcher match5 = this.P5.matcher(URI);
        Matcher match6 = this.P6.matcher(URI);
        Matcher match7 = this.P7.matcher(URI);
        Matcher match8 = this.P8.matcher(URI);
        //This needs to be fixed you are handling and integer but
        //according to your pattern it should say all so that does not parse
        if(match7.find())
        {
            int threadID = Integer.parseInt(match7.group(2));
            int postID = Integer.parseInt(match7.group(4));
            MAV = postGet.viewPost(threadID, postID);
        }

        else if(match8.find())
        {
            MAV = postGet.createPost();
        } else if(match4.find())
        {
            int ID = Integer.parseInt(match4.group(2));
            MAV = threadGet.getThread(ID);
        }else if(match5.find())
        {
            MAV = threadGet.getAll();
        }
        else if(match6.find())
        {
            MAV = threadGet.getAll();
        }
        else if(match.find())
        {
            MAV = threadGet.createThread();
        }
        else if(match2.find())
        {
            MAV = threadGet.createThread();
        }
        else if(match4.find())
        {
            int ID = Integer.parseInt(match4.group(2));
            MAV = threadGet.getThread(ID);
        }
        return MAV;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ModelAndView MAV= PostURIParser(request, response);
        response.sendRedirect(request.getContextPath() + MAV.getViewName());
    }

    public ModelAndView PostURIParser(HttpServletRequest request, HttpServletResponse response)
    {
        String URI = request.getRequestURI();
        ModelAndView MAV = null;
        Matcher match = this.P.matcher(URI);//threads
        Matcher match2 = this.P2.matcher(URI);//create
        Matcher match3 = this.P3.matcher(URI);//thread by name
        Matcher match4 = this.P4.matcher(URI);//thread by ID
        Matcher match8 = this.P8.matcher(URI);
        Matcher match7 = this.P7.matcher(URI);
        if(match7.find())
        {
            int threadID = Integer.parseInt(match7.group(2));
            int postID = Integer.parseInt(match7.group(4));
            MAV = ppc.addComment(threadID, postID);
        }
        else if(match8.find())
        {
            int ID = Integer.parseInt(match8.group(2));
            MAV = ppc.createPost(ID);
        }
        else if(match2.find())
        {
            MAV = tpc.createThread();
        }
        else if(match4.find())
        {
            ThreadGetController threadGet = new ThreadGetController();
            int ID = Integer.parseInt(match4.group(2));
            MAV = threadGet.getThread(ID);
        }
        return MAV;
    }
}
