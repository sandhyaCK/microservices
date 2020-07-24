package com.bridgelabz.fundoonotes.repository;

/*
 *  author : Sandhya
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.NoteData;

@Repository
public class NoteRepository {
	@PersistenceContext
	EntityManager entityManger;

	/* Query for save the Data into NoteRepository */
	public NoteData save(NoteData noteInformation) {
		Session session = entityManger.unwrap(Session.class);
		session.save(noteInformation);
		return noteInformation;

	}

	/* Query to find the note by id */
	public NoteData findById(Long noteId) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery("FROM NoteData where noteId=:noteId");
		q.setParameter("noteId", noteId);
		return (NoteData) q.uniqueResult();

	}

	/* Query for delete the note by id */
	public boolean deleteNote(Long noteId, Long userid) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery(" delete FROM NoteData where noteId=:noteId ");
		q.setParameter("noteId", noteId);
		
		int result = q.executeUpdate();
		if (result >= 1) {
			return true;
		}
		return false;
	}

	/* Query for list out all the notes for a particular user */
	public List<NoteData> getNotes(Long userId) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery(
				"from NoteData where user_id=:userId AND is_trashed=0 AND is_archieved=0 AND is_pinned=0 ");
		List<NoteData> list = q.getResultList();

		return list;

	}

	/* Query for list out the trasehed notes */
	public List<NoteData> restoreNote(Long userId) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery("from NoteData where user_id=:userId AND isTrashed =1");
		List<NoteData> list = q.getResultList();
		return list;

	}

	/* Query for list out the archeived notes */
	public List<NoteData> getArchievedNotes(Long userId) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery("from NoteData where userId=:userId AND isArchieved= 1");
		List<NoteData> list = q.getResultList();
		return list;
	}

	/* Query for update the color */
	public boolean updateColor(Long noteId, Long userId, String color) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery("update NoteDate set color =:color");
		int res = q.executeUpdate();
		if (res >= 1) {
			return true;
		}
		return false;
	}

	/* Query for list out all the pinned notes */
	public List<NoteData> getPinnededNotes(Long userId) {
		Session session = entityManger.unwrap(Session.class);
		Query q = session.createQuery("from NoteData where user_id=:userId AND isPinned= 1");
		List<NoteData> list = q.getResultList();
		return list;
	}
}
