package dev.zeronelab.mybatis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "fboard")
@ToString
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class fboard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "fno_seq")
	@SequenceGenerator(name = "fno_seq", sequenceName = "FNO_SEQ", allocationSize = 1)
	private long fno;
	
	@Column(length = 100)
	private String writer;
	
	@Column(length = 500)
	private String title;
	
	@Column(columnDefinition = "CLOB")
	private String content;
	
	@Column
	private int viewcnt;
	
	@Column
	private int replycnt;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date regidate;

}

