package com.example.audit.comment;

import javax.persistence.Entity;

import com.example.audit.Auditable;

import lombok.Data;

@Entity
@Data
public class Comment extends Auditable{
  
  private String comment;
  
}
