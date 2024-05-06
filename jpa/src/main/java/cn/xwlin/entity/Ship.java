package cn.xwlin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author xiang.liao
 * @create 2023/8/31
 */
@Getter
@Setter
@Entity(name = "ship")
public class Ship {
  @Id
  private Integer id;
  @Column(name = "cavans_id")
  private Integer cavansId;

  @Column(name = "dict_id")
  private Integer dictId;
}
