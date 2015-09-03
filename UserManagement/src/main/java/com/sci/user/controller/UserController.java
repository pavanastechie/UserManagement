package com.sci.user.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sci.user.dao.UserDAO;
import com.sci.user.model.User;
import com.sci.user.util.Constants;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	public @Value ("${app.name}") String appName;
	@RequestMapping("login")
	public ModelAndView login() {
		LOGGER.info("In UserController: login");
		ModelAndView model = new ModelAndView(Constants.LOGIN_VIEW);
		model.addObject("user", new User());
		model.addObject("appName", appName);
		return model;
	}
	
	@RequestMapping(value = "users",method = RequestMethod.POST)
	public ModelAndView users(@ModelAttribute User user) {
		LOGGER.info("In UserController: login");	
		Boolean userExists = userDao.authenticate(user);
		ModelAndView model = new ModelAndView();
		if(userExists) {
		  List<User> listUsers = userDao.list();
		  model = new ModelAndView(Constants.USER_LIST_VIEW);
		  model.addObject("appName", appName);
		  model.addObject("userList", listUsers);
		  return model;
		} else {
			model.setViewName(Constants.LOGIN_VIEW);
			model.addObject("user", new User());
			model.addObject("errorMsg", "Username or Password entered does not exists");
		}
		
		return model;
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView newUser() {
		LOGGER.info("In UserController: newUser");
		ModelAndView model = new ModelAndView(Constants.USER_FORM_VIEW);
		model.addObject("user", new User());
		model.addObject("appName", appName);
		return model;		
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		LOGGER.info("In UserController: editUser");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.get(userId);
		ModelAndView model = new ModelAndView(Constants.USER_EDIT_FORM_VIEW);
		model.addObject("appName", appName);
		model.addObject("user", user);
		return model;		
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		LOGGER.info("In UserController: deleteUser");
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		ModelAndView model = new ModelAndView();
		 List<User> listUsers = userDao.list();
		model.setViewName(Constants.USER_LIST_VIEW);
		model.addObject("userList", listUsers);
		model.addObject("appName", appName);
		model.addObject("successMsg", "User deleted  successfully");
		return model;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		LOGGER.info("In UserController: saveUser");
		if(!userDao.isUserExists(user.getUsername())){
		userDao.saveOrUpdate(user);
		ModelAndView model = new ModelAndView();
		List<User> listUsers = userDao.list();
		model.setViewName(Constants.USER_LIST_VIEW);
		model.addObject("userList", listUsers);
		model.addObject("appName", appName);
		model.addObject("successMsg", "User created successfully");
		return model;
		}
		else{
			ModelAndView model = new ModelAndView(Constants.USER_FORM_VIEW);
			model.setViewName(Constants.USER_FORM_VIEW);
			model.addObject("user", new User());
			model.addObject("errorMsg", "Username is already exists");
			model.addObject("appName", appName);
			return model;
		}
	}
	
	@RequestMapping(value = "back", method = RequestMethod.GET)
	public ModelAndView showHome() {
		LOGGER.info("In UserController: showHome");
		ModelAndView model = new ModelAndView();
		List<User> listUsers = userDao.list();
		  model = new ModelAndView(Constants.USER_LIST_VIEW);
		  model.addObject("userList", listUsers);
		  model.addObject("appName", appName);
		  return model;
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user) {
		LOGGER.info("In UserController: updateUser");
		userDao.saveOrUpdate(user);
		ModelAndView model = new ModelAndView();
		List<User> listUsers = userDao.list();
		model.setViewName(Constants.USER_LIST_VIEW);
		model.addObject("userList", listUsers);
		model.addObject("appName", appName);
		model.addObject("successMsg", "User updated successfully");
		return model;
	}
	
	@RequestMapping("list")
	public ModelAndView listUsers() {
		LOGGER.info("In UserController: listUsers");
		List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView(Constants.USER_LIST_VIEW);
		model.addObject("userList", listUsers);
		return model;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout() {
		LOGGER.info("In UserController: logout");
		ModelAndView model = new ModelAndView(Constants.LOGIN_VIEW);
		model.addObject("user", new User());
		model.addObject("appName", appName);
		return model;
	}
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<User> getAllUsers() {
		LOGGER.info("In UserController: getAllUsers");
        List<User> emps = userDao.list();
        Map<Integer, String> empData = new HashMap<Integer, String>();
        Iterator<User> iterator = emps.listIterator();
        while (iterator.hasNext()) {
			User user = (User) iterator.next();
			empData.put(user.getId(), user.getUsername());
		}
        return emps;
    }
	
}
