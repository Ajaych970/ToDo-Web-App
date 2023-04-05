package com.ctrl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.TodoDao;
import com.entities.Todo;

@Controller
public class HomeCtrl {
	
	
	
	@Autowired
	TodoDao todoDao;
	
	@RequestMapping("/home")
	public String home(Model m) {
		
		String str="home";
		m.addAttribute("page",str);
		
		List<Todo> list=this.todoDao.getAll();
		
		m.addAttribute("todos", list);
		
		return "home";		
	}
	
	@RequestMapping("/add")
	public String addTODO(Model m) {
		Todo t = new Todo();
		
		m.addAttribute("page", "add");
		m.addAttribute("todo", t);
		return "home";
	}
	
	@RequestMapping("/view")
	public String viewToDo(Model m) {
		
		m.addAttribute("page", "view");
		
		return "home";
		
	}
	
	@RequestMapping(value ="/saveTodo",method =RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t,Model m) {
		m.addAttribute("page","home");
		t.setTodoDate(new Date());
		this.todoDao.save(t);
		
		m.addAttribute("msg", "successfully added..");
		
		return "home";
	}
	

}
