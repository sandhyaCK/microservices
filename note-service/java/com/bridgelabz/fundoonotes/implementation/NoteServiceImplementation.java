package com.bridgelabz.fundoonotes.implementation;

/*
 *  author : Sandhya
 */

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdate;
import com.bridgelabz.fundoonotes.dto.ReminderDto;
import com.bridgelabz.fundoonotes.exception.NoteException;
import com.bridgelabz.fundoonotes.model.NoteData;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.service.NoteService;

@Service
public class NoteServiceImplementation implements NoteService {
	
	private NoteData noteData = new NoteData();
	@Autowired
	private NoteRepository noterepo;

	/* method for creating the note for particular user */
	@Transactional
	@Override
	public NoteData createNote(NoteDto information, User user) {
		try {
			if (user != null) {
				noteData.setCreatedDateAndTime(LocalDateTime.now());
				noteData.setIsArchieved(0);
				noteData.setColour("white");
				noteData.setIsPinned(0);
				noteData.setIsTrashed(0);
				noteData.setReminder(null);
				noteData.setDescription(information.getDescription());
				noteData.setTitle(information.getTitle());
				noteData.setUpDateAndTime(null);
				System.out.println(user.getName());
				noteData.setUserId(user.getUserId());
			     noterepo.save(noteData);
			   
			}
		} catch (Exception e) {
			throw new NoteException("User doesnot exist with this id");
		}
		 return noteData;
	}

	/* method for updating the note for particular user */
	@Transactional	
	@Override
	public NoteData updateNote(NoteUpdate information, User user) {
		try {
			if(user!=null) {
			NoteData noteData = noterepo.findById(information.getId());
			if (noteData != null) {
				noteData.setNoteId(information.getId());
				noteData.setDescription(information.getDescription());
				noteData.setTitle(information.getTitle());
				noteData.setUpDateAndTime(LocalDateTime.now());
				noteData.setIsArchieved(information.getIsArchieved());
				noteData.setIsPinned(information.getIsPinned());
				noteData.setIsTrashed(information.getIsTrashed());
				 noterepo.save(noteData);
			}
		}} catch (Exception e) {
			throw new NoteException("user is not present");
		}
		return noteData;
	}

	/* method for deleting the note for particular user */
	@Transactional
	@Override
	public void deleteNote(Long id, User user) {
		try {
			if(user!=null) {
			NoteData noteData = noterepo.findById(id);
			if (noteData != null) {
				noteData.setIsTrashed(1);

				noterepo.save(noteData);
			}
		} }catch (Exception e) {
			throw new NoteException("user is not present");
		}
	}

	/* method for archieve the particular note for particular user */
	@Transactional
	@Override
	public void archieveNote(Long id, User user) {
		try {
			if (user != null) {
			NoteData noteData = noterepo.findById(id);
			if (noteData != null) {
				noteData.setIsPinned(0);
				noteData.setIsArchieved(1);
				//noteData.setUpDateAndTime(LocalDateTime.now());
				noterepo.save(noteData);
			}
		}}
			catch (Exception e) {
			throw new NoteException("user is not present");
		}
	}

	/* method for pin the particular note for particular user */
	@Transactional	
	@Override
	public void pinNote(Long id, User user) {
		try {
			if (user != null) {
			NoteData noteData = noterepo.findById(id);
			if (noteData != null) {
				noteData.setIsArchieved(0);
				noteData.setIsPinned(1);
				noteData.setUpDateAndTime(LocalDateTime.now());
				noterepo.save(noteData);
			}
		}} catch (Exception e) {
			throw new NoteException("user is not present");
		}
	}

	/* method for deleting the note permenatly */
//	@Transactional
//	@Override
//	public boolean deleteNotePermanently(Long id, User user) {
//		try {
//			if (user != null) {
//			NoteData noteData = noterepo.findById(id);
//			if (noteData != null) {
//				List<LabelData> labels = noteData.getList();
//				if (labels != null) {
//					labels.clear();
//				}
//				noterepo.deleteNote(id, user.getUserId());
//			}
//
//			else {
//				throw new UserException("note is not present");
//			}
//		} }catch (Exception e) {
//			throw new UserException("user is not present");
//		}
//		return false;
//	}

	/* method for list out all the notes for particular user */
//	@Transactional	
//	@Override
//	public List<NoteData> getAllNotes(User user) {
//		try {
//			if (user != null) {
//			if (noteData != null) {
//				List<NoteData> list = noterepo.getNotes(user.getUserId());
//				
//			} else {
//				throw new UserException("user Doesnt exist ");
//			}
//		} }catch (Exception e) {
//			throw new UserException("error occured ");
//		}
//		return list;
//	}

	/* method for get the trashednotes */
	@Transactional
	@Override
	public List<NoteData> gettrashednotes(User user) {
		try {
			
			if (user != null) {
				List<NoteData> list = noterepo.restoreNote(user.getUserId());
				return list;
			} else {
				throw new NoteException("user Doesnt exist ");
			}
		} catch (Exception e) {
			throw new NoteException("error occured ");
		}
	}

	/* method to get the archievedNotes */
	@Transactional
	@Override
	public List<NoteData> getarchieved(User user) {
		try {
		
			if (user != null) {
				List<NoteData> list = noterepo.getArchievedNotes(user.getUserId());
				return list;
			} else {
				throw new NoteException("user Doesnt exist ");
			}
		} catch (Exception e) {
			throw new NoteException("error occured ");
		}

	}

	/* Method to add the colour for a note */
	@Transactional
	@Override
	public void addColour(Long noteId, User user, String colour) {
		try {
			if (user != null) {
			NoteData noteData = noterepo.findById(noteId);
			if (noteData != null) {
				noteData.setColour(colour);
				noterepo.save(noteData);
			}
		} }catch (Exception e) {
			throw new NoteException("user is not present");
		}
	}

	/* method to get the pinnedNotes */
	@Transactional
	@Override
	public List<NoteData> getPinneded(User user) {
		try {
			
			if (user != null) {
				List<NoteData> list = noterepo.getPinnededNotes(user.getUserId());
				return list;
			} else {
				throw new NoteException("user Doesnt exist ");
			}
		} catch (Exception e) {
			throw new NoteException("error occured ");
		}
	}

	/* method to add the reminder for a Note */
	@Transactional
	@Override
	public void addReminder(Long id, User user, ReminderDto reminder) {

		try {
			
			if(user!=null) {
			NoteData noteData = noterepo.findById(id);
			if (noteData != null) {
				noteData.setReminder(reminder.getRemainder());
				
				noterepo.save(noteData);
			
			}
		}} catch (Exception e) {
			throw new NoteException("user is not present");
		}

	}

	/* method to remove the reminder for a Note */
	@Transactional
	@Override
	public void removeReminder(Long noteid, User user, ReminderDto remainder) {

		try {
			if (user != null) {
			NoteData noteData = noterepo.findById(noteid);
			if (noteData != null) {
				noteData.setReminder(null);
				noterepo.save(noteData);
			}
		} }catch (Exception e) {
			throw new NoteException("user is not present");
		}

	}

	@Override
	public NoteData getNoteById(Long noteId, User user) {
			 if(user!=null) {
				 NoteData noteData = noterepo.findById(noteId); 
			 }
		 
		return noteData;
	}
}
