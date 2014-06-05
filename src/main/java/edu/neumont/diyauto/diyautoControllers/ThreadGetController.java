package edu.neumont.diyauto.diyautoControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.diyauto.Models.ThreadsModel;
import edu.neumont.diyauto.ServiceLoader;
import edu.neumont.diyauto.Models.Threads;

import java.util.Set;

public class ThreadGetController 
{
	HttpServletRequest request;
	HttpServletResponse response;
	//Threads threads = ServiceLoader.threads;
	public ThreadGetController(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}
    public ModelAndView getAll()
    {
        ThreadsModel thread =
        Set<ThreadModel> allThreads =
        ModelAndView MAV = new ModelAndView(allThreads,"/WEB-INF/views/threads/AllThreadsView.jsp");
        return MAV;
    }
	public ModelAndView createThread()
	{

		ModelAndView MAV = new ModelAndView(null, "/WEB-INF/views/threads/CreateThread.jsp");
		return MAV;
		
	}
	public ModelAndView getThread(int ID)
	{
		ThreadsModel thread = threads.getThread(ID);
		ModelAndView MAV = new ModelAndView(thread, "/WEB-INF/views/threads/ThreadView.jsp");
		return MAV;
		
	}
	
	
}
