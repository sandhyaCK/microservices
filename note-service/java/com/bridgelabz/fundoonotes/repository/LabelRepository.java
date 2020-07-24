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

import com.bridgelabz.fundoonotes.model.LabelData;
import com.bridgelabz.fundoonotes.model.NoteData;

@Repository
public class LabelRepository {
	@PersistenceContext
	EntityManager entityManager;

	/* Query for save the Data into LabelRepository */
	
	public LabelData save(LabelData labelData) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(labelData);
		return labelData;
	}

	/* Query to get the single label of particular LabelId */
	
	public LabelData getLabel(Long labelId) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from LabelData where labelId=:labelId");
		q.setParameter("labelId", labelId);
		return (LabelData) q.uniqueResult();

	}

	/* Query for list out all the labels */

	public List<LabelData> getAllLabels(Long id) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from LabelData");
		List<LabelData> labelList = q.getResultList();
		return labelList;
	}
	/* Query for list out all the notes for a particularlabelId */
	
	public List<NoteData> getAllNotes(Long Labelid) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from Label_note where labelId=:Labelid");
		List<NoteData> labelList = q.getResultList();
		return labelList;
	}
	/* Query for get the label of particular name */
	
	public LabelData getLabel(Long userId, String name) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("from LabelData where userId=:userId and name=:name");
		q.setParameter("userId", userId);
		q.setParameter("name", name);
		return (LabelData) q.uniqueResult();
	}

	/* Query for delete the label */

	public int deleteLabel(Long i) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("delete from LabelData where labelId=:i");
		q.setParameter("labelId", i);
		int res = q.executeUpdate();
		return res;

	}

}
