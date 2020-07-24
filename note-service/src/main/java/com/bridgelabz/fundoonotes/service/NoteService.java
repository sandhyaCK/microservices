package com.bridgelabz.fundoonotes.service;

/*
 *  author : Sandhya
 */

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.dto.ReminderDto;
import com.bridgelabz.fundoonotes.model.NoteData;
import com.bridgelabz.fundoonotes.model.User;

public interface NoteService {

	NoteData createNote(NoteDto information, User user);

	NoteData updateNote(NoteUpdate information,User user);

	void deleteNote(Long id, User user);

	void archieveNote(Long id,User user);

	void pinNote(Long id, User user);

	//boolean deleteNotePermanently(Long id, User user);

	// List<NoteData> getAllNotes(User user);

	List<NoteData> gettrashednotes(User user);

	List<NoteData> getarchieved(User user);

	void addColour(Long noteId, User user, String colour);

	List<NoteData> getPinneded(User user);

	void addReminder(Long noteid, User user, ReminderDto reminder);

	void removeReminder(Long noteid, User user, ReminderDto reminder);
NoteData  getNoteById(Long noteId,User user);
}
