package cn.xwlin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xiang.liao
 * @create 2023/8/31
 */
@Getter
@Setter
@Entity(name = "dict")
public class Dict {
  @Id
  private Integer id;
  @Column(name = "dict_name")
  private String dictName;
}
