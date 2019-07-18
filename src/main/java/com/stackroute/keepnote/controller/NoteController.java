package com.stackroute.keepnote.controller;


import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note 
	 *    should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */
	ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
	        Note noteObj=  context.getBean("note",Note.class);
	        NoteRepository noteRepositoryObj=context.getBean("noteRepository",NoteRepository.class);
	
	/*Define a handler method to read the existing notes by calling the getAllNotes() method 
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
	@RequestMapping("/")
	public ModelAndView getNote()
	{
		ModelMap modelMap=new ModelMap();
		modelMap.addAllAttributes(noteRepositoryObj.getAllNotes());
	   	ModelAndView modelAndView=new ModelAndView();
	   	modelAndView.setViewName("index");
	   	modelAndView.addAllObjects(modelMap);
	   	return modelAndView;
	}
	
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/
	@RequestMapping(value="/saveNote",method= RequestMethod.POST)

	public ModelAndView addNote(int noteId,String noteTitle,String noteContent,String noteStatus)
	{
      noteObj.setNoteId(noteId);
      noteObj.setNoteTitle(noteTitle);
      noteObj.setNoteContent(noteContent);
      noteObj.setNoteStatus(noteStatus);
      noteObj.setCreatedAt(LocalDateTime.now());
      noteRepositoryObj.addNote(noteObj);

      ModelMap modelMap=new ModelMap();
      modelMap.addAllAttributes(noteRepositoryObj.getList());
      ModelAndView modelAndView=new ModelAndView();
      modelAndView.setViewName("index");
      modelAndView.addAllObjects(modelMap);
      return modelAndView;
	}
	
	/* Define a handler method to delete an existing note by calling the deleteNote() method 
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote" 
	*/
@RequestMapping(value="/deleteNote")
	public String deleteNote(Integer id)
{
	ModelAndView modelAndView=new ModelAndView("/");
	if(id!=null)
	{
		modelAndView.addObject(noteRepositoryObj.deleteNote(id));
	}
	return "redirect:/";
}
}