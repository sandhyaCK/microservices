package com.bridgelabz.fundoonotes.implementation;

/*
 *  author : Sandhya
 */

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.dto.LabelUpdate;
import com.bridgelabz.fundoonotes.exception.LabelException;
import com.bridgelabz.fundoonotes.model.LabelData;
import com.bridgelabz.fundoonotes.model.LabelNote;
import com.bridgelabz.fundoonotes.model.NoteData;
import com.bridgelabz.fundoonotes.model.User;
//import com.bridgelabz.fundoonotes.repository.LabelNoteRepository;
import com.bridgelabz.fundoonotes.repository.LabelRepository;
import com.bridgelabz.fundoonotes.service.LabelService;

@Service
public class LabelServiceImplementation implements LabelService {
	
	
	@Autowired
	private LabelRepository LabelRepo;
	@Autowired
	private ModelMapper modelMapper;
//@Autowired
//private LabelNoteRepository repository;


	/* Method for creating the label */
	@Override
	@Transactional
	public void createLabel(LabelDto labelDto, User user) {
		
		
		if (user != null) {
			LabelData labelData = LabelRepo.getLabel(user.getUserId(), labelDto.getName());
			if (labelData == null) {
				labelData = modelMapper.map(labelDto, LabelData.class);
				labelData.setUserId(user.getUserId());
				LabelRepo.save(labelData);
			} else {
				throw new LabelException("label with that name is already present ");
			}
		} else {
			throw new LabelException("note does not exist with userid");
		}
	}

	
//	/* Method for add the label */
//	@Transactional
//	@Override
//	public void addLabel(Long labelId, User user, NoteData note) {
//		if(user!=null) {
//			if(note!=null) {
//		LabelData label = LabelRepo.getLabel(labelId);
//		if(label!=null) {
//			LabelNote labelNote=new LabelNote();
//			labelNote.setLabelId(labelId);
//			labelNote.setNoteId(note.getNoteId());
//		repository.save(labelNote);
//	}}}
//	}
//
//	/* Method for remove the label */
//	@Transactional
//	@Override
//	public void removeLabel(Long labelId,User user,NoteData note) {
//		if(user!=null) {
//			if(note!=null) {
//				LabelData label = LabelRepo.getLabel(labelId);	
//		LabelNote labelNote=repository.findLabelbyNoteId(note.getNoteId(),labelId);
//		repository.delete(labelNote);
//			}
//		}
//	
//	}

	/* Method for deleting the label */
	@Transactional
	@Override
	public void deleteLabel(LabelUpdate label, User user) {
		
		if (user != null) {
			LabelData labelInfo = LabelRepo.getLabel(label.getLabelId());
			if (labelInfo != null) {

				LabelRepo.deleteLabel(label.getLabelId());
			} else {
				throw new LabelException("labelwith name does not exist");
			}
		} else {
			throw new LabelException("user does not exist");
		}
	}

	/* Method for edit the label */
	@Transactional
	@Override
	public void edit(LabelUpdate label, User user) {
		
		if (user != null) {
			LabelData labelInfo = LabelRepo.getLabel(label.getLabelId());
			if (labelInfo != null) {
				labelInfo.setName(label.getName());
				LabelRepo.save(labelInfo);
			} else {
				throw new LabelException("labelwith name does not exist");
			}
		} else {
			throw new LabelException("user does not exist");
		}
	}

	/* Method for list out all the label */
	@Transactional
	@Override
	public List<LabelData> getAllLabels(User user) {
		try {
			if(user!=null) {
			List<LabelData> list = LabelRepo.getAllLabels(user.getUserId());
			return list;

	      	}
			} catch (Exception e) {
			throw new LabelException("user doesn't exist with id");
		}
		return null;
	}

	/* Method for list out all the label which having notes */
	@Transactional
	@Override
	public List<NoteData> getNotes(User user, Long labelId) {
		
		if(user!=null) {
		LabelData label = LabelRepo.getLabel(labelId);
		if(label!=null) {
		List<NoteData> note = LabelRepo.getAllNotes(labelId);
		// List<NoteData> list = label.getList();
		return (List<NoteData>) note;
		}
		}
		return null;
	}

	/* Method for get the single label of single user */
	@Transactional
	@Override
	public LabelData getSingleLabel(User user, Long LabelId) {
		try {
			if (user != null) {
				LabelData data = LabelRepo.getLabel(LabelId);

				return data;
			}
		} catch (Exception e) {
			throw new LabelException("user does not exist");

		}
		return null;

	}


	
}
