package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping("home")
	public  String home()

	{
		return "home.jsp";
	}
	@RequestMapping("machineinfo")
	public ModelAndView   machineinfo()

	{
		InetAddress ip=null;
		String hostname=null;
		String os=null;
		String[] arrOfStr=null;
		try {
			ip = InetAddress.getLocalHost();
			String p=ip.toString();
			arrOfStr = p.split("/", 2);
			hostname = ip.getHostName();
			os = System.getProperty("os.name");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ModelAndView mv=new ModelAndView("machineinfo.jsp");
        mv.addObject("ip",arrOfStr[1]);
        mv.addObject("hostname",hostname);
        mv.addObject("os",os);
		return mv;
        
	}
	public String getMessage(String name) {

        StringBuilder result = new StringBuilder();

        if (name == null || name.trim().length() == 0) {

            result.append("Please provide a name!");

        } else {

            result.append("Hello " + name);

        }
        return result.toString();
    }
	public long D2B(int n)
	{
		 long binaryNumber = 0;
	    int remainder, i = 1, step = 1;
	    if(n==0)
	    {
	    	return 0;
	    }
	    if(n<0)
	    {
	    	return 0;
	    }
	    while (n!=0)
	    {
	        remainder = n%2;
	   //     printf("Step %d: %d/2, Remainder = %d, Quotient = %d\n", step++, n, remainder, n/2);
	        n /= 2;
	        binaryNumber += remainder*i;
	        i *= 10;
	    }
	    return binaryNumber;
	}

}
