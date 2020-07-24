package com.bridgelabz.fundoonotes.service;

/*
 *  author : Sandhya
 */

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.dto.LabelUpdate;
import com.bridgelabz.fundoonotes.model.LabelData;
import com.bridgelabz.fundoonotes.model.NoteData;
import com.bridgelabz.fundoonotes.model.User;

public interface LabelService {
	
 public void createLabel(LabelDto labelDto, User user);
// public void createLabelAndMAp(LabelDto labelDto,User user);
// public  void addLabel(Long labelId,User user,NoteData note);
// public void removeLabel(Long labelId,User user,NoteData note);
 public void deleteLabel(LabelUpdate label,User user);
 public void edit(LabelUpdate label,User user);
 public List<LabelData> getAllLabels(User user);
 public List<NoteData>  getNotes(User user,Long labelId);
public LabelData getSingleLabel(User user,Long LabelId);


}
