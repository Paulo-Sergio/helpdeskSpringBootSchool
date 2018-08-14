package br.com.paulofranca.Helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.Interaction;
import br.com.paulofranca.Helpdesk.service.InteractionService;

@Controller
@RequestMapping("/tickets/{ticketId}/interactions")
public class InteractionController {

	@Autowired
	private InteractionService interactionService;

	@PostMapping
	public String save(@PathVariable("ticketId") Long ticketId, @Valid @ModelAttribute("interaction") Interaction interaction,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "ticket/show";
		}
		
		this.interactionService.create(interaction, ticketId);

		return "redirect:/tickets/" + ticketId;
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("ticketId") Long ticketId, @PathVariable("id") Long id, Model model) {
		this.interactionService.delete(id, ticketId);
		
		return "redirect:/tickets/" + ticketId;
	}
}
